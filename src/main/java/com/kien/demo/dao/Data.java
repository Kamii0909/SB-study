package com.kien.demo.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import com.kien.demo.model.Trait;
import com.kien.demo.model.Unit;


public class Data {
    
    public static void init(){
        initData();
        initDistance();
    }

    public static void initData(){
		for(String s: Data.allTraitName){
			new Trait(s);
		}

		for(String[] s: Data.allUnitsName){
			ArrayList<Trait> traits = new ArrayList<>();
			for(int i = 1; i < s.length; i++){
				traits.add(Data.allTraits.get(s[i].hashCode()));
			}
			new Unit(s[0], traits);
		}
	}

	/**initDistance get path length for all units
	 * @see https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm
	 */
	public static void initDistance(){
		int[][] distance = Data.distance;
		int[][] next = Data.next;


		//Fill distance[][] with 100
		for(int[] i: distance){
			Arrays.fill(i, 100);
		}

		for(int[] i: next){
			Arrays.fill(i, 100);
		}

		Collection<Unit> allUnitsCollection = Data.allUnits.values();
		int pi, pj, pk;

		//Fill distance[i][i] = 0
		//Fill distance[i][j] = 1 for every unit connecting each other
		for(Unit u: allUnitsCollection){
			pi = u.getPosition();
			distance[pi][pi] = 0;
			next[pi][pi] = pi;

			for(Unit other: u.getConnectedUnit()){
				pj = other.getPosition();
				distance[pi][pj] = 1;
				next[pi][pj] = pj;
			}
		}

		//Fill distance[i][j] with min(distance[i][j], distance[i][k] + distance[k][j])
		for(Unit uk: allUnitsCollection){
			pk = uk.getPosition();
			for(Unit ui: allUnitsCollection){
				pi = ui.getPosition();
				for(Unit uj: allUnitsCollection){
					pj = uj.getPosition();
					if(distance[pi][pj] > distance[pi][pk] + distance[pk][pj]){
						distance[pi][pj] = distance[pi][pk] + distance[pk][pj];
						next[pi][pj] = next[pi][pk];
					}
				}
			}
		}
	}

    public final static HashMap<Integer, Trait> allTraits = new HashMap<>();
    public final static HashMap<Integer, Unit> allUnits = new HashMap<>();
    public final static HashMap<String, Integer> unitPosition = new HashMap<>();
    public final static int[][] distance = new int[58][58];
    public final static int[][] next = new int[58][58];




    static String[] allTraitName = {"Astral", "Guild", "Jade", "Mirage", "Ragewing",
        "Revel", "Scalescorn", "Shimmerscale", "Tempest", "Trainer", "Whispers",
        "Assassin", "Bard", "Bruiser", "Cannoneer", "Cavalier", "Dragon",
        "Dragonmancer", "Evoker", "Guardian", "Legend", "Mage", "Mystic",
        "Shapeshifter", "Spell-Thief", "Starcaller", "Swiftshot", "Warrior"};
        
        
    static String[][] allUnitsName = {
            {"Aatrox", "Shimmerscale", "Warrior"},
            {"Anivia", "Jade", "Evoker", "Legend"},
            {"Ao Shin", "Tempest", "Dragon"},
            {"Ashe", "Jade", "Swiftshot", "Dragonmancer"},
            {"Aurelion Sol", "Astral", "Evoker", "Dragon"},
            {"Bard", "Guild", "Mystic", "Bard"},
            {"Braum", "Scalescorn", "Guardian"},
            {"Corki", "Revel", "Cannoneer"},
            {"Daeja", "Mirage", "Dragon"},
            {"Diana", "Scalescorn", "Assassin"},
            {"Elise", "Whispers", "Shapeshifter"},
            {"Ezreal", "Tempest", "Swiftshot"},
            {"Gnar", "Jade", "Shapeshifter"},
            {"Hecarim", "Ragewing", "Cavalier"},
            {"Heimerdinger", "Trainer", "Mage"},
            {"Idas", "Shimmerscale", "Guardian", "Dragon"},
            {"Illaoi", "Astral", "Bruiser"},
            {"Jinx", "Revel", "Cannoneer"},
            {"Karma", "Jade", "Dragonmancer"},
            {"Kayn", "Ragewing", "Assassin", "Shimmerscale"},
            {"Leesin", "Tempest", "Dragonmancer"},
            {"Leona", "Mirage", "Guardian"},
            {"Lillia", "Scalescorn", "Cavalier", "Mage"},
            {"Lulu", "Trainer", "Mystic", "Evoker"},
            {"Nami", "Astral", "Mage", "Mystic"},
            {"Neeko", "Jade", "Shapeshifter"},
            {"Nidalee", "Astral", "Shapeshifter"},
            {"Nunu", "Mirage", "Cavalier"},
            {"Olaf", "Scalescorn", "Bruiser", "Warrior"},
            {"Ornn", "Tempest", "Bruiser", "Legend"},
            {"Pyke", "Whispers", "Assassin"},
            {"Qiyana", "Tempest", "Assassin"},
            {"Ryze", "Guild", "Mage"},
            {"Sejuani", "Guild", "Cavalier"},
            {"Senna", "Ragewing", "Cannoneer"},
            {"Sett", "Ragewing", "Dragonmancer"},
            {"Shen", "Ragewing", "Bruiser", "Warrior"},
            {"Shi Oh Yu", "Jade", "Mystic", "Dragon"},
            {"Shyvana", "Ragewing", "Shapeshifter"},
            {"Skarner", "Astral", "Bruiser"},
            {"Sona", "Revel", "Evoker"},
            {"Soraka", "Jade", "Starcaller"},
            {"Swain", "Ragewing", "Dragonmancer", "Shapeshifter"},
            {"Syfen", "Whispers", "Bruiser", "Dragon"},
            {"Sylas", "Whispers", "Bruiser", "Mage"},
            {"Tahm Kench", "Revel", "Bruiser"},
            {"Talon", "Guild", "Assassin"},
            {"Taric", "Jade", "Guardian"},
            {"Thresh", "Whispers", "Guardian"},
            {"Tristana", "Trainer", "Cannoneer"},
            {"Twitch", "Guild", "Swiftshot"},
            {"Varus", "Astral", "Swiftshot"},
            {"Vladimir", "Astral", "Mage"},
            {"Volibear", "Shimmerscale", "Dragonmancer", "Legend"},
            {"Xayah", "Ragewing", "Swiftshot"},
            {"Yasuo", "Mirage", "Dragonmancer", "Warrior"},
            {"Yone", "Mirage", "Warrior"},
            {"Zoe", "Spell-Thief", "Shimmerscale", "Mage"}
    };
}
