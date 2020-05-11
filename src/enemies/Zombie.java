package enemies;

import java.util.HashMap;
import java.util.Map;

import armor.Armor;
import armor.Factory_Heavy_Armor;
import character.Character;
import weapon.Mace;

public class Zombie extends Character {

	public Zombie(String name, Map<String, Integer> attributes,
			Map<String, Integer> combat_skills) {
		super(name, "ZOMBIE", new Mace(), attributes, combat_skills,
				new HashMap<String, Armor> () {{
					Factory_Heavy_Armor fac = new Factory_Heavy_Armor();
					put("HEAD", fac.getHeadArmor());
					put("TORSO", fac.getTorsoArmor());
					put("LEG", fac.getLegArmor());
				}});
	}


}
