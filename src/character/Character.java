package character;

import java.util.*;
import java.io.*;
import armor.*;
import weapon.*;

public abstract class Character {

	protected String name = "Unnamed";
	
	public Map<String, Map<String, Integer>> stats = new Hashtable<String, Map<String, Integer>> () {{
		put("attributes", new Hashtable<String, Integer>());
		put("weapon_skills", new Hashtable<String, Integer>());
		put("combat_skills", new Hashtable<String, Integer>());
		
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
	
	
	protected Body_Health health = new Body_Health();
	
	protected Map<String, Armor> equipment = new Hashtable<String, Armor> () {{
		put("head", null);
		put("torso", null);
		put("arms", null);
		put("legs", null);
		put("feet", null);
	}};
	
	protected Weapon main_weapon = null;
	protected Weapon secondary_weapon = null;
	
}
