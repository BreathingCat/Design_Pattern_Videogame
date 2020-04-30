package armor;

import java.util.Map;

public class Leg_Armor extends Armor {

	public Leg_Armor(String type) {
		super();
		this.body_part = "leg";
		this.type = type;
	}
	
	public Leg_Armor(String type, int resistance, int coverage) {
		super(resistance, coverage);
		this.body_part = "leg";
		this.type = type;
	}
	
	public Leg_Armor(String type, int resistance, int coverage, Map<String, Integer> attributes, Map<String, Integer> combat_skills) {
		super(resistance, coverage, attributes, combat_skills);
		this.body_part = "leg";
		this.type = type;
	}
	
}
