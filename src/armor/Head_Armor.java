package armor;

import java.util.Map;

public class Head_Armor extends Armor {

	public Head_Armor(String type) {
		super();
		this.body_part = "HEAD";
		this.type = type;
	}
	
	public Head_Armor(String type, Map<String, Integer> attributes, Map<String, Integer> combat_skills) {
		super(attributes, combat_skills);
		this.body_part = "HEAD";
		this.type = type;
	}
	
}
