package armor;

import java.util.*;
import java.io.*;

public abstract class Armor {

	protected Map<String, Map<String, Integer>> stats_modifier = new HashMap <String, Map<String, Integer>> () {{
		put("attributes", new HashMap<String, Integer> ());
		put("combat_skills", new HashMap<String, Integer> ());
	}};
	
	protected int resistance = 0;
	protected int coverage = 0;
	
	protected String type = null;
	protected String body_part = null;
	
	public Armor() {;}
	
	public Armor(int resistance, int coverage) {
		this.resistance = resistance;
		this.coverage = coverage;
	}
	
	public Armor(int resistance, int coverage, Map<String, Integer> attributes, Map<String, Integer> combat_skills) {
		
		this(resistance, coverage);
		
		for(Map.Entry<String, Integer> pair : attributes.entrySet()) {
			this.stats_modifier.get("attributes").put(pair.getKey(), pair.getValue());
		} 
		
		for(Map.Entry<String, Integer> pair : combat_skills.entrySet()) {
			this.stats_modifier.get("combat_skills").put(pair.getKey(), pair.getValue());
		}

	}

	public int getResistance() {
		return this.resistance;
	}

	public int getCoverage() {
		return coverage;
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
