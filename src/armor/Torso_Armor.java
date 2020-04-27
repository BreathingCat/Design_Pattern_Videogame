package armor;

import java.util.Map;

public class Torso_Armor extends Armor {

	public Torso_Armor(String type) {
		super();
		this.body_part = "torso";
		this.type = type;
	}
	
	public Torso_Armor(String type, int blunt_resistance, int cut_resistance, int coverage) {
		super(blunt_resistance, cut_resistance, coverage);
		this.body_part = "torso";
		this.type = type;
	}
	
	public Torso_Armor(String type, int blunt_resistance, int cut_resistance, int coverage, Map<String, Integer> attributes, Map<String, Integer> combat_skills) {
		super(blunt_resistance, cut_resistance, coverage, attributes, combat_skills);
		this.body_part = "torso";
		this.type = type;
	}
	
}
