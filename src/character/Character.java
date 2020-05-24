package character;

import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import armor.*;
import combat.*;
import dmg_calc.DAMAGE_CALC_SINGLETON;
import state.Character_State;
import weapon.*;
import state.*;

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
	
	// Race
	public Map<String, Map<String, Integer>> racial_stats_mult = new HashMap<String, Map<String, Integer>> () {{
		put("ATTRIBUTES", new HashMap<String, Integer>());
		put("COMBAT_SKILLS", new HashMap<String, Integer>());			
	}};
	
	// State
	public Map<String, Character_State> state;
	
	// Pointer to damage calculator
	public static DAMAGE_CALC_SINGLETON dmg_calculator = new DAMAGE_CALC_SINGLETON();
	
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
	
	public Character (String name, String race, Weapon main, 
			Map<String, Integer> attributes, Map<String, Integer> combat_skills, Map<String, Armor> equipment) {
		
		this.name = name;
		
		this.main_weapon = main;
		
		this.state = new HashMap<String, Character_State> ();
		this.state.put("DISORIENT", new Disoriented_State(this));
		this.state.put("BLEED", new Bleed_State(this));
		this.state.put("STUN", new Stunned_State(this));
		this.state.put("POISON", new Poisoned_State(this));
		
		this.getStatsFromFile(race);
		
		this.stats.get("ATTRIBUTES").putAll(attributes);
		this.stats.get("COMBAT_SKILLS").putAll(combat_skills);
		
		this.equipment.putAll(equipment);
			
	}
	
	public Map<String, Integer> getAttributes () {
		Map<String, Integer> modified_attr = new HashMap(this.stats.get("ATTRIBUTES"));
		
		// Racial modifiers
		for(Map.Entry<String, Integer> pair : this.racial_stats_mult.get("ATTRIBUTES").entrySet()) {
			modified_attr.put(pair.getKey(), pair.getValue() + modified_attr.get(pair.getKey()));
		}
		
		// Equipment modifiers
		for(Map.Entry<String, Armor> armor : this.equipment.entrySet()) {
			for(Map.Entry<String, Integer> pair : this.equipment.get(armor.getKey()).getAttributesModifier().entrySet()) {
				modified_attr.put(pair.getKey(), pair.getValue() + modified_attr.get(pair.getKey()));
			}
		}		
		
		// State modifiers
		for(Map.Entry<String, Character_State> pair : this.state.entrySet()) {
			if(pair.getValue().getState() && pair.getValue().getAttributesModifier() != 0) {
				
				int modifier = pair.getValue().getAttributesModifier();
				
				for(Map.Entry<String, Integer> pair_attr : modified_attr.entrySet()) {
					modified_attr.put(pair_attr.getKey(), pair_attr.getValue() + modifier);
				}
			}
		}
		
		return modified_attr;
	}
	
	public Map<String, Integer> getCombatSkills () {
		Map<String, Integer> modified_combat_skills = new HashMap(this.stats.get("COMBAT_SKILLS"));
		
		// Racial modifier
		for(Map.Entry<String, Integer> pair : this.racial_stats_mult.get("COMBAT_SKILLS").entrySet()) {
			modified_combat_skills.put(pair.getKey(), pair.getValue() + modified_combat_skills.get(pair.getKey()));
		}
		
		// Equipment modifiers
		for(Map.Entry<String, Armor> armor : this.equipment.entrySet()) {
			for(Map.Entry<String, Integer> pair : this.equipment.get(armor.getKey()).getCombatSkillsModifier().entrySet()) {
				modified_combat_skills.put(pair.getKey(), pair.getValue() + modified_combat_skills.get(pair.getKey()));
			}
		}
		
		// State modifiers
		for(Map.Entry<String, Character_State> pair : this.state.entrySet()) {
			if(pair.getValue().getState() && pair.getValue().getCombatSkillsModifier() != 0) {
				
				int modifier = pair.getValue().getCombatSkillsModifier();
				
				for(Map.Entry<String, Integer> pair_combat : modified_combat_skills.entrySet()) {
					modified_combat_skills.put(pair_combat.getKey(), pair_combat.getValue() + modifier);
				}
			}
		}
		return modified_combat_skills;
	}

	public void stun() {
		this.state.get("STUN").activate();
	}
	
	public void disorient() {
		this.state.get("DISORIENT").activate();
	}
	
	public void poison() {
		this.state.get("POISON").activate();
	}
	
	public void bleed() {
		this.state.get("BLEED").activate();
	}
	
	public void effectsNextTurn() {
		for(Map.Entry<String, Character_State> pair : this.state.entrySet()) {
			pair.getValue().effectNextTurn();
		}
	}
	
	public void damage(int damage) {
		if(this.current_hp - damage < 0 ) {
			this.current_hp = 0;
		} else {
			this.current_hp -= damage;
		}
	}
	
	public void heal(int heal) {
		if(this.current_hp + heal > this.max_hp) {
			this.current_hp = this.max_hp;
		} else {
			this.current_hp += heal;
		}
	}
	
	public int getCurrentHp() {
		return this.current_hp;
	}
	
	public int getMaxHp() {
		return this.max_hp;
	}
	
	private boolean attack_help(CombatDecorator attack, Character objective) {
		DecoratorToughness attack_iron_skin = new DecoratorToughness(attack);	
		return attack.combat(this, objective);
	}
	
	protected boolean attackSuper(Character objective) {
		System.out.println(this.name + " is attacking " + objective.name + "!");
		CombatConcreteComponent attack = new CombatConcreteComponent();
		if(this.main_weapon.getType() == "ranged") {
			DecoratorPerception attack_accurate = new DecoratorPerception(attack);
			return this.attack_help(attack_accurate, objective);
		}
		else {
			DecoratorStrength attack_strong = new DecoratorStrength(attack);
			DecoratorDexterity attack_precise = new DecoratorDexterity(attack_strong);
			return this.attack_help(attack_precise, objective);
		}
	}
	
	public boolean attack(Character objective) {		
		return this.attackSuper(objective);
	}
	
	public boolean isAlive() {
		if(this.getCurrentHp() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
