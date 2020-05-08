package armor;

import java.util.Map;

public class Torso_Armor extends Armor {

	public Torso_Armor(String type) {
		super();
		this.body_part = "TORSO";
		this.type = type;
	}
	
	public Torso_Armor(String type, Map<String, Integer> attributes, Map<String, Integer> combat_skills) {
		super(attributes, combat_skills);
		this.body_part = "TORSO";
		this.type = type;
	}
	
}
