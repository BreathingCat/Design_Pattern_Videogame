package armor;

import java.util.Map;

public class Torso_Armor extends Armor {

	public Torso_Armor(String type) {
		super();
		this.body_part = "torso";
		this.type = type;
	}
	
	public Torso_Armor(String type, int resistance, int coverage) {
		super(resistance, coverage);
		this.body_part = "torso";
		this.type = type;
	}
	
	public Torso_Armor(String type, int resistance, int coverage, Map<String, Integer> attributes, Map<String, Integer> combat_skills) {
		super(resistance, coverage, attributes, combat_skills);
		this.body_part = "torso";
		this.type = type;
	}
	
}
