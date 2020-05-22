package armor;

import java.util.Map;

public class Leg_Armor extends Armor {

	public Leg_Armor(String type) {
		super();
		this.body_part = "LEG";
		this.type = type;
	}

	public Leg_Armor(String type, Map<String, Integer> attributes, Map<String, Integer> combat_skills) {
		super(attributes, combat_skills);
		this.body_part = "LEG";
		this.type = type;
	}
	
}
