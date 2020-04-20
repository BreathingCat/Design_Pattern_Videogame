package character;

import java.util.*;

public class Paladin extends Character {

	public static float racial_bleed_mult = (float) 0.8;
	
	protected static Map<String, Map<String, Integer>> racial_stats_mult = new HashMap<String, Map<String, Integer>> () {{
		put("attributes", new HashMap<String, Integer>());
		put("weapon_skills", new HashMap<String, Integer>());
		put("combat_skills", new HashMap<String, Integer>());			
	}};

	public Paladin (String name) {
		this.name = name;
	}
	
	public Paladin (String name, Map<String, Integer> attributes, Map <String, Integer> weapon_skills, Map<String, Integer> combat_skills) {
		this.name = name;
		
		// Update stats dict
		for (Map.Entry<String, Integer> pair : attributes.entrySet()) {
			this.stats.get("attributes").put(pair.getKey(), pair.getValue());
		} for (Map.Entry<String, Integer> pair : weapon_skills.entrySet()) {
			this.stats.get("weapon_skills").put(pair.getKey(), pair.getValue());
		} for (Map.Entry<String, Integer> pair : combat_skills.entrySet()) {
			this.stats.get("combat_skills").put(pair.getKey(), pair.getValue());
		} 
		
	}
	
	@Override
	public Map<String, Integer> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> getWeaponSkills() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> getCombatSkills() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
