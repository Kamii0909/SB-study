package com.kien.demo.dao;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.kien.demo.dao.localdb.TraitData;
import com.kien.demo.dao.localdb.UnitData;
import com.kien.demo.model.Trait;
import com.kien.demo.model.Unit;


public class LocalDatabase {
    private final TraitData traits;
    private final UnitData units;

	//cache for default query
	private final LinkedHashMap<Trait, Set<Unit>> traitUnits = new LinkedHashMap<>();
	private final LinkedHashMap<Unit, Set<Unit>> connectedUnits = new LinkedHashMap<>();
	private final int[][] unitDistance = new int[58][58], unitPath = new int[58][58];

	//unmodifiable list for accessing unit ID value
	private final List<Unit> unitId;

	LocalDatabase(TraitData traits, UnitData units){
		this.traits = traits;
		this.units = units;
		unitId = units.map().values().stream().toList();
	}

	public void ignoreATraitInPathCalculation(Trait trait){
		for(Unit unit: traitUnits.get(trait)){

		}
	}
    
    public void init(){
        initData();
        initDistance();
    }

	/** This method populate cache memory for fast loading:
	 * <p>  - All units with a specific trait
	 * <p>  - All units that shares at least a trait with another unit
	 */
	private void initData(){
		units.all().stream().forEach(u -> {
			unitId.add(u);
			connectedUnits.put(u, new LinkedHashSet<>());
		});
		
		traits.all().stream().forEach(t -> traitUnits.put(t, new LinkedHashSet<>()));

		List<Unit> cache = units.all();
		for(Unit u: cache){
			for(Trait trait: u.getTraits()){
				traitUnits.get(trait).add(u);
			}
		}
		for(Unit u: cache){
			for(Trait trait: u.getTraits()){
				connectedUnits.get(u).addAll(traitUnits.get(trait));
			}
			connectedUnits.get(u).remove(u);
		}
	}

	/**initDistance get path length for all units
	 * @see https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm
	 */
	public void initDistance(){
		//Fill distance[][] with 100
		Arrays.fill(unitDistance[0], 100);
		System.arraycopy(unitDistance[0], 0, unitPath[0], 0, 58);

		for(int i = 1; i < 58; i++){
			System.arraycopy(unitDistance[0], 0, unitDistance[i], 0, 58);
			System.arraycopy(unitDistance[0], 0, unitPath[i], 0, 58);
		}

		Collection<Unit> allUnits = units.all();
		int pi, pj, pk;

		//Fill distance[i][i] = 0
		//Fill distance[i][j] = 1 for every unit connecting each other
		for(Unit u: allUnits){
			pi = unitId.indexOf(u);
			unitDistance[pi][pi] = 0;
			unitPath[pi][pi] = pi;

			for(Unit other: connectedUnits.get(u)){
				pj = unitId.indexOf(other);
				unitDistance[pi][pj] = 1;
				unitPath[pi][pj] = pj;
			}
		}

		//Fill distance[i][j] with min(distance[i][j], distance[i][k] + distance[k][j])
		for(Unit uk: allUnits){
			pk = unitId.indexOf(uk);
			for(Unit ui: allUnits){
				pi = unitId.indexOf(ui);
				for(Unit uj: allUnits){
					pj = unitId.indexOf(uj);
					if(unitDistance[pi][pj] > unitDistance[pi][pk] + unitDistance[pk][pj]){
						unitDistance[pi][pj] = unitDistance[pi][pk] + unitDistance[pk][pj];
						unitPath[pi][pj] = unitPath[pi][pk];
					}
				}
			}
		}
	}
}
