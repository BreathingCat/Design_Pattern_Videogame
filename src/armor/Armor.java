package armor;

import java.util.*;
import java.io.*;

public abstract class Armor {

	public Map<String, Map<String, Integer>> stats_modifier = new HashMap <String, Map<String, Integer>> () {{
		put("attributes", new HashMap<String, Integer> ());
		put("combat_skills", new HashMap<String, Integer> ());
		
		try {
			
			// Attributes
			File attributes_file = new File("./src/character/attributes.txt");
			Scanner attributes_scanner = new Scanner(attributes_file);
			
			while(attributes_scanner.hasNextLine()) {
				get("attributes").put(attributes_scanner.nextLine(), 0);
			}
			attributes_scanner.close();
			
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
	
	public int blunt_resistance = 0;
	public int cut_resistance = 0;
	public int coverage = 0;
	
	public String type = null;
	
}
