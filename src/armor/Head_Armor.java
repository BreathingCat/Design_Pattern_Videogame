package armor;

import java.util.Map;

public class Head_Armor extends Armor {

	public Head_Armor(String type) {
		super();
		this.body_part = "HEAD";
		this.type = type;
	}
	
	public Head_Armor(String type, int resistance, int coverage) {
		super(resistance, coverage);
		this.body_part = "HEAD";
		this.type = type;
	}
	
	public Head_Armor(String type, int resistance, int coverage, Map<String, Integer> attributes, Map<String, Integer> combat_skills) {
		super(resistance, coverage, attributes, combat_skills);
		this.body_part = "HEAD";
		this.type = type;
	}
	
}
