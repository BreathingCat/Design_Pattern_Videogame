package armor;

import java.util.Map;

public class Arm_Armor extends Armor {

	public Arm_Armor(String type) {
		super();
		this.body_part = "arm";
		this.type = type;
	}
	
	public Arm_Armor(String type, int blunt_resistance, int cut_resistance, int coverage) {
		super(blunt_resistance, cut_resistance, coverage);
		this.body_part = "arm";
		this.type = type;
	}
	
	public Arm_Armor(String type, int blunt_resistance, int cut_resistance, int coverage, Map<String, Integer> attributes, Map<String, Integer> combat_skills) {
		super(blunt_resistance, cut_resistance, coverage, attributes, combat_skills);
		this.body_part = "arm";
		this.type = type;
	}
	
}
