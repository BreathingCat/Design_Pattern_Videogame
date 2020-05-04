package character;

import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import armor.*;
import weapon.*;

public abstract class Character {

	protected String name = "Unnamed";
	
	protected final static String char_stats = "src/character/stats.txt";
	
	// Character stats, subdivided in ATTRIBUTES AND COMBAT SKILLS
	protected Map<String, Map<String, Integer>> stats = new HashMap<String, Map<String, Integer>> () {{
		put("ATTRIBUTES", new HashMap<String, Integer>());
		put("COMBAT_SKILLS", new HashMap<String, Integer>());
	}};
	
	// Health
	public int blood_level = 100;
	public int max_blood_level = 100;
	public int total_bleed_rate = 0;
	
	public Map<String, Map<String, Integer>> body_parts = new HashMap<String, Map<String, Integer>> () {{
		put("head", new HashMap<String, Integer> ());
		put("torso", new HashMap<String, Integer> ());
		put("left_arm", new HashMap<String, Integer> ());
		put("right_arm", new HashMap<String, Integer> ());
		put("left_leg", new HashMap<String, Integer> ());
		put("right_arm", new HashMap<String, Integer> ());
	}};
	
	// Armor
	public Map<String, Armor> equipment = new HashMap<String, Armor> () {{
		put("head", null);
		put("torso", null);
		put("leg", null);
	}};
	
	// Weapons
	public Weapon main_weapon = null;
	public Weapon side_weapon = null;
	
	public abstract Map<String, Integer> getAttributes ();
	public abstract Map<String, Integer> getCombatSkills ();
	
	// Race
	public static float racial_bleed_mult = -1;
	
	protected static Map<String, Map<String, Integer>> racial_stats_mult = new HashMap<String, Map<String, Integer>> () {{
		put("ATTRIBUTES", new HashMap<String, Integer>());
		put("COMBAT_SKILLS", new HashMap<String, Integer>());			
	}};
	
	private void getStatsFromFile(String race) {
		JSONParser parser = new JSONParser();	
		try {
			JSONObject json_stats = (JSONObject)parser.parse(new FileReader(Character.char_stats));

			// Attributes
			for(Iterator it = ((JSONArray)json_stats.get("ATTRIBUTES")).iterator(); it.hasNext();) {
				this.stats.get("ATTRIBUTES").put((String)it.next(), 0);
			}
			
			// Combat Skills
			for(Iterator it = ((JSONArray)json_stats.get("COMBAT_SKILLS")).iterator(); it.hasNext();) {
				this.stats.get("COMBAT_SKILLS").put((String)it.next(), 0);
			}
			
			// Racial data
			JSONObject race_char = (JSONObject)json_stats.get(race);
			
			// Racial attributes modifier
			JSONObject attributes_modifier = (JSONObject)((JSONObject)race_char.get("SKILLS_MULT")).get("ATTRIBUTES");
			for(Iterator it = attributes_modifier.keySet().iterator(); it.hasNext();) {
				String key = (String)it.next();
				this.racial_stats_mult.get("ATTRIBUTES").put(key, ((Long)attributes_modifier.get(key)).intValue());
			}
			
			// Racial combat skills modifier
			JSONObject combat_skills_modifier = (JSONObject)((JSONObject)race_char.get("SKILLS_MULT")).get("COMBAT_SKILLS");
			for(Iterator it = combat_skills_modifier.keySet().iterator(); it.hasNext();) {
				String key = (String)it.next();
				this.racial_stats_mult.get("COMBAT_SKILLS").put(key, ((Long)combat_skills_modifier.get(key)).intValue());
			}
			
			// Health
			this.racial_bleed_mult = (Long)race_char.get("BLEED_MULT");
			
			JSONObject head_health = (JSONObject)race_char.get("HEAD");
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	public Character (String name, String race, Weapon main, Weapon side, Map<String, Integer> attributes, Map<String, Integer> combat_skills) {
		
		this.name = name;
		
		this.main_weapon = main;
		this.side_weapon = side;
		
		this.getStatsFromFile(race);
		
		for (Map.Entry<String, Integer> pair : attributes.entrySet()) {
			this.stats.get("attributes").put(pair.getKey(), pair.getValue());
		} for (Map.Entry<String, Integer> pair : combat_skills.entrySet()) {
			this.stats.get("combat_skills").put(pair.getKey(), pair.getValue());
		} 
		
	}
	
	// Method for automatically creating a dictionary. Used to simplify code, so
	// i dont have to do it everytime
	public Map<String, Integer> generateAttributesDict(final int strength, final int dexterity, final int toughness, final int perception) {
		return new HashMap<String, Integer>(){{
			put("STRENGTH", strength);
			put("DEXTERITY", dexterity);
			put("TOUGHNESS", toughness);
			put("PERCEPTION", perception);
		}};
	}
	
	// Same as the attributes one
	public Map<String, Integer> generateCombatSkillsDict(final int attack, final int defense, final int dodge) {
		return new HashMap<String, Integer>(){{
			put("ATTACK", attack);
			put("DEFENSE", defense);
			put("DODGE", dodge);
		}};
	}
}
