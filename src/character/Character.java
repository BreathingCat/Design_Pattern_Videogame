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
  	private void getCharacterStatsFromFile() {
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
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
  	
  	private void getRaceStatsFromFile(String race) {
  		JSONParser parser = new JSONParser();	
		try {
			JSONObject json_stats = (JSONObject)parser.parse(new FileReader(Character.char_stats));
			
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
		
		this.getCharacterStatsFromFile();
		this.getRaceStatsFromFile(race);
		
		this.stats.get("ATTRIBUTES").putAll(attributes);
		this.stats.get("COMBAT_SKILLS").putAll(combat_skills);
		
		this.equipment.putAll(equipment);
			
	}
	
	private void addRacialModifiers(String stats, Map<String, Integer> dict) {
		for(Map.Entry<String, Integer> pair : this.racial_stats_mult.get(stats).entrySet()) {
			dict.put(pair.getKey(), pair.getValue() + dict.get(pair.getKey()));
		}
	}
	
	private void addEquipmentModifiers(Map<String, Integer> dict) {
		for(Map.Entry<String, Armor> armor : this.equipment.entrySet()) {
			for(Map.Entry<String, Integer> pair : this.equipment.get(armor.getKey()).getAttributesModifier().entrySet()) {
				dict.put(pair.getKey(), pair.getValue() + dict.get(pair.getKey()));
			}
		}	
	}
	
	private void addStateModifiers(Map<String, Integer> dict) {
		for(Map.Entry<String, Character_State> pair : this.state.entrySet()) {
			if(pair.getValue().getState() && pair.getValue().getAttributesModifier() != 0) {
				for(Map.Entry<String, Integer> pair_attr : dict.entrySet()) {
					dict.put(pair_attr.getKey(), pair_attr.getValue() + pair.getValue().getAttributesModifier());
				}
			}
		}
	}
	
	public Map<String, Integer> getAttributes () {
		Map<String, Integer> modified_attr = new HashMap(this.stats.get("ATTRIBUTES"));
		
		// Racial modifiers
		this.addRacialModifiers("ATTRIBUTES", modified_attr);
		
		// Equipment modifiers
		this.addEquipmentModifiers(modified_attr);	
		
		// State modifiers
		this.addStateModifiers(modified_attr);
		
		return modified_attr;
	}
	
	public Map<String, Integer> getCombatSkills () {
		Map<String, Integer> modified_combat_skills = new HashMap(this.stats.get("COMBAT_SKILLS"));
		
		// Racial modifier
		this.addRacialModifiers("COMBAT_SKILLS", modified_combat_skills);
		
		// Equipment modifiers
		this.addEquipmentModifiers(modified_combat_skills);
		
		// State modifiers
		this.addStateModifiers(modified_combat_skills);
		
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
	
	private boolean computeAttack(CombatDecorator attack, Character objective) {
		DecoratorToughness attack_iron_skin = new DecoratorToughness(attack);	
		return attack.combat(this, objective);
	}
	
	protected boolean attackSuper(Character objective) {
		System.out.println(this.name + " is attacking " + objective.name + "!");
		CombatConcreteComponent attack = new CombatConcreteComponent();
		if(this.main_weapon.getType() == "ranged") {
			DecoratorPerception attack_accurate = new DecoratorPerception(attack);
			return this.computeAttack(attack_accurate, objective);
		}
		else {
			DecoratorStrength attack_strong = new DecoratorStrength(attack);
			DecoratorDexterity attack_precise = new DecoratorDexterity(attack_strong);
			return this.computeAttack(attack_precise, objective);
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
