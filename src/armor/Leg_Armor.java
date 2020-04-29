package armor;

import java.util.Map;

public class Leg_Armor extends Armor {

	public Leg_Armor(String type) {
		super();
		this.body_part = "leg";
		this.type = type;
	}
	
	public Leg_Armor(String type, int blunt_resistance, int cut_resistance, int coverage) {
		super(blunt_resistance, cut_resistance, coverage);
		this.body_part = "leg";
		this.type = type;
	}
	
	public Leg_Armor(String type, int blunt_resistance, int cut_resistance, int coverage, Map<String, Integer> attributes, Map<String, Integer> combat_skills, Map<String, Integer> weapon_skills) {
		super(blunt_resistance, cut_resistance, coverage, attributes, combat_skills, weapon_skills);
		this.body_part = "leg";
		this.type = type;
	}
	
}
