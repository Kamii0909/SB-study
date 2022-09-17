package com.kien.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Table;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import com.kien.demo.model.*;



public class TftGraph {

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

        for(Unit ui: unitsCache)
            for(Unit uj: unitsCache)
                path.put(ui, uj, new Path().pathLength(100).next(null));
            
                

        for(Unit ui: unitsCache){
            path.get(ui, ui).pathLength(0);
            for(Unit uj: mapUnitToTrait){
                distance.put(ui, uj, 1);
                path.put(ui, uj, uj);
            }
        }

        for(Unit uk: unitsCache){
            for(Unit ui: unitsCache){
                for(Unit uj: unitsCache){
                    if(distance.get(ui, uj) > distance.get(ui, uk) + distance.get(uk, uj)){
                        distance.put(ui, uj, distance.get(ui, uk) + distance.get(uk, uj));
                        path.put(ui, uj, path.get(ui, uk));
                    }
                }
            }
        }
    }

    public List<Unit> getPath(Unit src, Unit des){
        List<Unit> result = new ArrayList<>();
        result.add(src);
        while(!src.equals(des)){
            src = path.get(src, des);
            result.add(src);
        }
        return result;
    }

    public MutableValueGraph<Unit, Trait> getGraph() {
        return graph;
    }

    public Table<Unit, Unit, Integer> getDistance() {
        return distance;
    }

    public Table<Unit, Unit, Unit> getPath() {
        return path;
    }
}
