package character;

import java.util.*;

public class Character_Paladin extends Character {

	public static float racial_bleed_mult = (float) 0.8;
	
	protected static Map<String, Map<String, Integer>> racial_stats_mult = new HashMap<String, Map<String, Integer>> () {{
		put("attributes", new HashMap<String, Integer>());
		put("weapon_skills", new HashMap<String, Integer>());
		put("combat_skills", new HashMap<String, Integer>());			
		put("armor_skills", new HashMap<String, Integer>());
	}};

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
