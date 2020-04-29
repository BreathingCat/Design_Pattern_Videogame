package armor;

import java.util.*;
import java.io.*;
import org.json.simple.*;


public class Factory_Heavy_Armor implements Abstract_Factory_Armor {

	public Armor getHeadArmor() {
		
		return new Head_Armor("heavy", 29, 52, 100, 
				new HashMap<String, Integer> () {{
					
				}},
				new HashMap<String, Integer> () {{
					
				}},
				new HashMap<String, Integer> () {{
					
				}}
				);
	}

	public Armor getTorsoArmor() {
		return new Torso_Armor("heavy", 29, 52, 100, 
				new HashMap<String, Integer> () {{
					put("PERCEPTION", (-2));
					put("TOUGHNESS", 2);
				}},
				new HashMap<String, Integer> () {{
					put("ATTACK", (-1));
					put("DEFENSE", 3);
				}},
				new HashMap<String, Integer> () {{
					put("CROSSBOWS", (-1));
				}}
				);
	}

	public Armor getArmArmor() {
		// TODO Auto-generated method stub
		return null;
	}

	public Armor getLegArmor() {
		return new Leg_Armor("heavy", 29, 52, 100, 
				new HashMap<String, Integer> () {{
					put("PERCEPTION", (-4));
				}},
				new HashMap<String, Integer> () {{
					put("ATTACK", (-2));
					put("DEFENSE", 1);
				}},
				new HashMap<String, Integer> () {{
					
				}}
				);
	}

	public Armor getBootArmor() {
		return new Boot_Armor("heavy", 29, 52, 100, 
				new HashMap<String, Integer> () {{
					put("PERCEPTION", (-4));
				}},
				new HashMap<String, Integer> () {{
					put("ATTACK", (-2));
					put("DEFENSE", 1);
				}},
				new HashMap<String, Integer> () {{
					
				}}
				);
	}

}
