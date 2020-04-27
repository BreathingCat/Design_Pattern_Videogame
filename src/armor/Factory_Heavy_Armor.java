package armor;

import java.util.*;

public class Factory_Heavy_Armor implements Abstract_Factory_Armor {

	@Override
	public Armor getHeadArmor() {
		return new Head_Armor("heavy", 29, 52, 100, 
				new HashMap<String, Integer> () {{
					
				}},
				new HashMap<String, Integer> () {{
					put("ATTACK", (-2));
				}},
				new HashMap<String, Integer> () {{
					
				}}
				);
	}

	@Override
	public Armor getTorsoArmor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Armor getArmArmor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Armor getLegArmor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Armor getFeetArmor() {
		// TODO Auto-generated method stub
		return null;
	}

}
