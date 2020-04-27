package armor;

import java.util.*;
import java.io.*;

public abstract class Armor {

	protected Map<String, Map<String, Integer>> stats_modifier = new HashMap <String, Map<String, Integer>> () {{
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
	
	protected int blunt_resistance = 0;
	protected int cut_resistance = 0;
	protected int coverage = 0;
	
	protected String type = null;
	protected String body_part = null;
	
	public Armor() {;}
	
	public Armor(int blunt_resistance, int cut_resistance, int coverage) {
		this.blunt_resistance = blunt_resistance;
		this.cut_resistance = cut_resistance;
		this.coverage = coverage;
	}
	
	public Armor(int blunt_resistance, int cut_resistance, int coverage, Map<String, Integer> attributes, Map<String, Integer> combat_skills) {
		
		this(blunt_resistance, cut_resistance, coverage);
		
		for(Map.Entry<String, Integer> pair : attributes.entrySet()) {
			this.stats_modifier.get("attributes").put(pair.getKey(), pair.getValue());
		} 
		
		for(Map.Entry<String, Integer> pair : combat_skills.entrySet()) {
			this.stats_modifier.get("combat_skills").put(pair.getKey(), pair.getValue());
		}
	}

	public int getBluntResistance() {
		return blunt_resistance;
	}
	

	public int getCut_resistance() {
		return cut_resistance;
	}

	public void setCut_resistance(int cut_resistance) {
		this.cut_resistance = cut_resistance;
	}

	public int getCoverage() {
		return coverage;
	}
	
	public int getCutResistance() {
		return this.cut_resistance;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getBodyPart() {
		return this.body_part;
	}
	
	public Map<String, Integer> getAttributesModifier() {
		return this.stats_modifier.get("attributes");
	}
	
	
	public Map<String, Integer> getCombatSkillsModifier() {
		return this.stats_modifier.get("combat_skills");
	}
	
	
	public void setAttributesModifier(Map<String, Integer> attributes) {
		for(Map.Entry<String, Integer> pair : attributes.entrySet()) {
			this.stats_modifier.get("attributes").put(pair.getKey(), pair.getValue());
		} 
	}
	
	public void setCombatSkillsModifier(Map<String, Integer> combat_skills) {
		for(Map.Entry<String, Integer> pair : combat_skills.entrySet()) {
			this.stats_modifier.get("combat_skills").put(pair.getKey(), pair.getValue());
		} 
	}
	
}
