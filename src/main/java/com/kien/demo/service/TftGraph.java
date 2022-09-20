package com.kien.demo.service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.kien.demo.dao.dao.TftDao;
import com.kien.demo.model.Trait;
import com.kien.demo.model.Unit;



public class TftGraph implements TftService {

    //data representation
    //Trait m...n Unit
    TftDao tftDao;
    private MutableGraph<Unit> unitGraph;
    private Table<Unit, Unit, Path> path;

   
    TftGraph(TftDao tftDao){
        this.tftDao = tftDao;
        unitGraph = GraphBuilder.undirected().expectedNodeCount(58).build();

        for(Unit unit: tftDao.allUnits()){
            for(Unit adjaUnit: tftDao.adjaUnit(unit)){
                unitGraph.putEdge(unit, adjaUnit);
            }
        }


        path = ArrayTable.create(tftDao.allUnits(), tftDao.allUnits());
        updateAllPath();
    }

    /** Update the shortest paths between points
	 * @see https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm
     * @apiNote
     * <p> Currently implemented with Floyd-Warshall algorithm, which isn't most efficient to changes.
     * A D* implementation is pending.
	 */
    private void updateAllPath(){
        List<Unit> unitsCache = tftDao.allUnits();
        for(Unit ui: unitsCache)
            for(Unit uj: unitsCache)
                path.put(ui, uj, new Path().setPathLength(100).setNext(null));
            
        for(Unit ui: unitsCache){
            path.get(ui, ui).setPathLength(0).setNext(ui);
            for(Unit uj: unitGraph.adjacentNodes(ui))
                path.get(ui, uj).setPathLength(1).setNext(uj);   
        }

        for(Unit uk: unitsCache)
            for(Unit ui: unitsCache)
                for(Unit uj: unitsCache)
                    if(path.get(ui, uj).getPathLength() > 
                                path.get(ui, uk).getPathLength() + path.get(uk, uj).getPathLength())
                        path.get(ui, uj)
                        .setPathLength(path.get(ui, uk).getPathLength() + path.get(uk, uj).getPathLength())
                        .setNext(path.get(ui, uk).getNext());
    }

    public void removeATrait(Trait trait){
        tftDao.removeATrait(trait);
        List<Unit> l = tftDao.unitOf(trait);
        for(int i = 0; i < l.size(); i++)
            for(int j = i + 1; j < l.size(); j++)
                unitGraph.removeEdge(l.get(i), l.get(j));
            
        
        updateAllPath();
    }

    public void removeATrait(Unit unit, Trait trait){
        tftDao.removeATrait(unit, trait);
    }

    public void addATrait(Unit unit, Trait trait){
        tftDao.unitOf(trait).stream().forEach(u -> unitGraph.putEdge(u, unit));
        tftDao.getTraitToUnitMapping().put(trait, unit);
        tftDao.getUnitToTraitMapping().put(unit, trait);
        
        updateAllPath();
    }

    public LinkedList<Unit> getPathAsList(Unit src, Unit des){
        LinkedList<Unit> result = new LinkedList<>();
        result.add(src);
        while(!src.equals(des)){
            src = path.get(src, des).getNext();
            result.add(src);
        }
        return result;
    }

    public int getPathLength(Unit src, Unit des){
        return path.get(src, des).getPathLength();
    }

    /**
     * Get all active Traits of an unit.
     * @param unit {@code Unit} to find
     * @return an unmodifiable List
     */
    public List<Trait> traitOf(Unit unit){
        return tftDao.traitOf(unit);
    }

    /**
     * Get all active Units of a Trait.
     * @param trait {@code Trait} to find
     * @return an unmodifiable List
     */
    public List<Unit> unitOf(Trait trait){
        return tftDao.unitOf(trait);
    }

    @Override
    public List<Trait> allTraits() {
        return tftDao.allTraits();
    }

    @Override
    public List<Unit> allUnits() {
        return tftDao.allUnits();
    }

    @Override
    public List<Unit> adjaUnit(Unit unit) {
        return tftDao.adjaUnit(unit);
    }

    @Override
    public LinkedHashMap<String, Double> avgPathLength(Collection<Unit> comp) {
        LinkedHashMap<String, Double> result = new LinkedHashMap<>();
        for(Unit unit: comp){
            result.put(unit.getName(), unitGraph.adjacentNodes(unit).stream().mapToInt(unit2 -> getPathLength(unit, unit2)).average().orElse(-1));
        }
        Double average = result.values().stream().mapToDouble(value -> value).average().orElse(-1);
        result.put("Average", average);
        return result;
    }

    
}
