package com.kien.demo.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Table;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.kien.demo.dao.preload.Preload;
import com.kien.demo.model.Trait;
import com.kien.demo.model.Unit;



public class TftGraph {

    //data representation
    //Trait m...n Unit
    private Multimap<Trait, Unit> mapTraitToUnit;
    private Multimap<Unit, Trait> mapUnitToTrait;

    private MutableGraph<Unit> unitGraph;


    private Table<Unit, Unit, Path> path;

    
    
    TftGraph(Preload preload){
        mapTraitToUnit = MultimapBuilder.hashKeys(28).hashSetValues(58).build();
        mapUnitToTrait = MultimapBuilder.hashKeys(58).hashSetValues(28).build();
        unitGraph = GraphBuilder.undirected().expectedNodeCount(58).build();

        List<Unit> unitsCache = preload.getUnits();

        for(String query: preload.getQueries()){
            String[] arr = query.split(":");
            Unit unit = preload.getUnit(arr[0]);
            Stream.of(arr).skip(1).map(name -> preload.getTrait(name)).forEach(trait -> {
                mapTraitToUnit.get(trait).stream().forEach(unit2 -> unitGraph.putEdge(unit, unit2));
                mapTraitToUnit.put(trait, unit);
                mapUnitToTrait.put(unit, trait);
            });
        }

        path = ArrayTable.create(unitsCache, unitsCache);
        updateAllPath();
    }

    /** Update the shortest paths between points
	 * @see https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm
     * @apiNote
     * <p> Currently implemented with Floyd-Warshall algorithm, which isn't most efficient to changes.
     * A D* implementation is pending.
	 */
    private void updateAllPath(){
        Set<Unit> unitsCache = mapUnitToTrait.keySet();
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
        mapTraitToUnit.get(trait).stream().forEach(unit -> mapUnitToTrait.get(unit).remove(trait));
        mapUnitToTrait.removeAll(trait);
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
        return mapUnitToTrait.get(unit).stream().toList();
    }

    /**
     * Get all active Units of a Trait.
     * @param trait {@code Trait} to find
     * @return an unmodifiable List
     */
    public List<Unit> unitOf(Trait trait){
        return mapTraitToUnit.get(trait).stream().toList();
    }
}
