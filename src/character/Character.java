package character;

import java.util.*;
import java.io.*;
import armor.*;
import weapon.*;

public abstract class Character {

	protected String name = "Unnamed";
	
	// Character stats, subdivided in ATTR, WEAPON SKILLS and COMBAT SKILLS
	public Map<String, Map<String, Integer>> stats = new HashMap<String, Map<String, Integer>> () {{
		put("attributes", new HashMap<String, Integer>());
		put("weapon_skills", new HashMap<String, Integer>());
		put("combat_skills", new HashMap<String, Integer>());
		
		try {
			
			// Attributes
			File attributes_file = new File("./src/character/attributes.txt");
			Scanner attributes_scanner = new Scanner(attributes_file);
			
			while(attributes_scanner.hasNextLine()) {
				get("attributes").put(attributes_scanner.nextLine(), 0);
			}
			attributes_scanner.close();
		
			// Weapon skills
			File weapon_skills_file = new File("./src/character/weapon_skills.txt");
			Scanner weapon_skills_scanner = new Scanner(weapon_skills_file);
			
			while(weapon_skills_scanner.hasNextLine()) {
				get("weapon_skills").put(weapon_skills_scanner.nextLine(), 0);
			}
			weapon_skills_scanner.close();
			
			// Combat skills
			File combat_skills_file = new File("./src/character/combat_skills.txt");
			Scanner combat_skills_scanner = new Scanner(combat_skills_file);
			
			while(combat_skills_scanner.hasNextLine()) {
				get("combat_skills").put(combat_skills_scanner.nextLine(), 0);
			}
			combat_skills_scanner.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}};
	
	// Health
	protected Body_Health health = null;
	
	// Armor
	protected Map<String, Armor> equipment = new HashMap<String, Armor> () {{
		put("head", null);
		put("torso", null);
		put("arms", null);
		put("legs", null);
		put("feet", null);
	}};
	
	// Weapons
	protected Weapon main_weapon = null;
	protected Weapon secondary_weapon = null;
	
	public abstract Map<String, Integer> getAttributes ();
	public abstract Map<String, Integer> getWeaponSkills ();
	public abstract Map<String, Integer> getCombatSkills ();
}
