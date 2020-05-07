package character;

import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import armor.*;
import weapon.*;

public abstract class Character {

	public String name = "Unnamed";
	
	protected final static String char_stats = "src/character/stats.txt";
	
	// Character stats, subdivided in ATTRIBUTES AND COMBAT SKILLS
	protected Map<String, Map<String, Integer>> stats = new HashMap<String, Map<String, Integer>> () {{
		put("ATTRIBUTES", new HashMap<String, Integer>());
		put("COMBAT_SKILLS", new HashMap<String, Integer>());
	}};
	
	// Health
	protected int max_hp = 0;
	protected int current_hp = 0;
	
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
	public static Map<String, Map<String, Integer>> racial_stats_mult = new HashMap<String, Map<String, Integer>> () {{
		put("ATTRIBUTES", new HashMap<String, Integer>());
		put("COMBAT_SKILLS", new HashMap<String, Integer>());			
	}};
	
	// State
	protected Character_State state;
	
	// Pointer to damage calculator
	protected DAMAGE_CALC_SINGLETON dmg_calculator;
	
	// Private function to read character stats from file
	// To ease the workload on more functions
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
			this.max_hp = ((Long)race_char.get("MAX_HP")).intValue();
			this.current_hp = this.max_hp;
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	public Character (String name, String race, Weapon main, Weapon side, 
			Map<String, Integer> attributes, Map<String, Integer> combat_skills, Map<String, Armor> equipment,
			DAMAGE_CALC_SINGLETON dmg_calculator) {
		
		this.dmg_calculator = dmg_calculator;
		
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
	
	public void damageCharacter(int damage) {
		if(this.current_hp - damage < 0 ) {
			this.current_hp = 0;
		} else {
			this.current_hp -= damage;
		}
	}
	
	public void healCharacter(int heal) {
		if(this.current_hp + heal > this.max_hp) {
			this.current_hp = this.max_hp;
		} else {
			this.current_hp += heal;
		}
	}
	
	public int getCurrentHp() {
		return this.current_hp;
	}
}
