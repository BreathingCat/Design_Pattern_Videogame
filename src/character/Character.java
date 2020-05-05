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
		put("HEAD", new HashMap<String, Integer> ());
		put("TORSO", new HashMap<String, Integer> ());
		put("LEFT_ARM", new HashMap<String, Integer> ());
		put("RIGHT_ARM", new HashMap<String, Integer> ());
		put("LEFT_LEG", new HashMap<String, Integer> ());
		put("RIGHT_LEG", new HashMap<String, Integer> ());
	}};
	
	// Armor
	public Map<String, Armor> equipment = new HashMap<String, Armor> () {{
		put("HEAD", null);
		put("TORSO", null);
		put("LEG", null);
	}};
	
	// Weapons
	public Weapon main_weapon = null;
	public Weapon side_weapon = null;
	
	// Race
	public static float racial_bleed_mult = -1;
	
	public static Map<String, Map<String, Integer>> racial_stats_mult = new HashMap<String, Map<String, Integer>> () {{
		put("ATTRIBUTES", new HashMap<String, Integer>());
		put("COMBAT_SKILLS", new HashMap<String, Integer>());			
	}};
	
	// State
	protected Character_State state;
	
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
			this.racial_bleed_mult = Float.parseFloat((String)race_char.get("BLEED_MULT"));
			
			String[] body_parts_json = new String[] {"HEAD","TORSO","LEFT_ARM","RIGHT_ARM","LEFT_LEG","RIGHT_LEG"};
			
			for (String part : body_parts_json) {
				JSONObject item = (JSONObject)race_char.get(part);
				for (Iterator it = item.keySet().iterator(); it.hasNext();) {
					String key = (String) it.next();
					this.body_parts.get(part).put(key, ((Long)item.get(key)).intValue());
				}
			}
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	public Character (String name, String race, Weapon main, Weapon side, Map<String, Integer> attributes, Map<String, Integer> combat_skills, Map<String, Armor> equipment) {
		
		this.name = name;
		
		this.main_weapon = main;
		this.side_weapon = side;
		
		this.getStatsFromFile(race);
		
		this.stats.get("ATTRIBUTES").putAll(attributes);
		this.stats.get("COMBAT_SKILLS").putAll(combat_skills);
		
		this.equipment.putAll(equipment);
		
	}
	
	public Map<String, Integer> getAttributes () {
		Map<String, Integer> modified_attr = new HashMap(this.stats.get("ATTRIBUTES"));
		
		for(Map.Entry<String, Integer> pair : this.racial_stats_mult.get("ATTRIBUTES").entrySet()) {
			modified_attr.put(pair.getKey(), modified_attr.get(pair.getKey()) + this.racial_stats_mult.get("ATTRIBUTES").get(pair.getKey()));
		}
		
		return modified_attr;
	}
	
	public Map<String, Integer> getCombatSkills () {
		Map<String, Integer> modified_combat_skills = new HashMap(this.stats.get("COMBAT_SKILLS"));
		
		for(Map.Entry<String, Integer> pair : this.racial_stats_mult.get("COMBAT_SKILLS").entrySet()) {
			modified_combat_skills.put(pair.getKey(), modified_combat_skills.get(pair.getKey()) + this.racial_stats_mult.get("COMBAT_SKILLS").get(pair.getKey()));
		}
		
		return modified_combat_skills;
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
