package character;

import java.util.*;

public class Body_Health {

	public int blood_level = 100;
	public int max_blood_level = 100;
	public int total_bleed_rate = 0;
	
	public Map<String, Map<String, Integer>> body_parts = new Hashtable<String, Map<String, Integer>> () {{
		put("head", new Hashtable<String, Integer> () {{
			put("max_hp", null);
			put("current_hp", null);
			put("bleed_rate", null);
			put("damaged_points", null);
		}});
		
		put("torso", new Hashtable<String, Integer> () {{
			put("max_hp", null);
			put("current_hp", null);
			put("bleed_rate", null);
			put("damaged_points", null);
		}});
		
		put("arm_right", new Hashtable<String, Integer> () {{
			put("max_hp", null);
			put("current_hp", null);
			put("bleed_rate", null);
			put("damaged_points", null);
		}});
		
		put("arm_left", new Hashtable<String, Integer> () {{
			put("max_hp", null);
			put("current_hp", null);
			put("bleed_rate", null);
			put("damaged_points", null);
		}});
		
		put("leg_right", new Hashtable<String, Integer> () {{
			put("max_hp", null);
			put("current_hp", null);
			put("bleed_rate", null);
			put("damaged_points", null);
		}});
		
		put("leg_left", new Hashtable<String, Integer> () {{
			put("max_hp", null);
			put("current_hp", null);
			put("bleed_rate", null);
			put("damaged_points", null);
		}});
		
	}};
	
	
	
}
