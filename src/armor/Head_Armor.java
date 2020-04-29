package armor;

import java.util.Map;

public class Head_Armor extends Armor {

	public Head_Armor(String type) {
		super();
		this.body_part = "head";
		this.type = type;
	}
	
	public Head_Armor(String type, int blunt_resistance, int cut_resistance, int coverage) {
		super(blunt_resistance, cut_resistance, coverage);
		this.body_part = "head";
		this.type = type;
	}
	
	public Head_Armor(String type, int blunt_resistance, int cut_resistance, int coverage, Map<String, Integer> attributes, Map<String, Integer> combat_skills, Map<String, Integer> weapon_skills) {
		super(blunt_resistance, cut_resistance, coverage, attributes, combat_skills, weapon_skills);
		this.body_part = "head";
		this.type = type;
	}
	
}
