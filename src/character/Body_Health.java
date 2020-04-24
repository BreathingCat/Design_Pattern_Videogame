package character;

import java.util.*;

public class Body_Health {

	public int blood_level = 100;
	public int max_blood_level = 100;
	public int total_bleed_rate = 0;
	
	public Map<String, Map<String, Integer>> body_parts = new HashMap<String, Map<String, Integer>> () {{
		put("head", new HashMap<String, Integer> () {{
			put("max_hp", null);
			put("current_hp", null);
			put("bleed_rate", null);
			put("damaged_points", null);
		}});
		
		put("torso", new HashMap<String, Integer> () {{
			put("max_hp", null);
			put("current_hp", null);
			put("bleed_rate", null);
			put("damaged_points", null);
		}});
		
		put("arm_right", new HashMap<String, Integer> () {{
			put("max_hp", null);
			put("current_hp", null);
			put("bleed_rate", null);
			put("damaged_points", null);
		}});
		
		put("arm_left", new HashMap<String, Integer> () {{
			put("max_hp", null);
			put("current_hp", null);
			put("bleed_rate", null);
			put("damaged_points", null);
		}});
		
		put("leg_right", new HashMap<String, Integer> () {{
			put("max_hp", null);
			put("current_hp", null);
			put("bleed_rate", null);
			put("damaged_points", null);
		}});
		
		put("leg_left", new HashMap<String, Integer> () {{
			put("max_hp", null);
			put("current_hp", null);
			put("bleed_rate", null);
			put("damaged_points", null);
		}});
		
	}};
	
	public Body_Health(Class<?> race) {
		int head_hp, torso_hp, arm_hp, leg_hp;
		
	}
	
	
}
