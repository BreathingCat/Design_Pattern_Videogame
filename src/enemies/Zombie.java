package enemies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import armor.Armor;
import armor.Factory_Heavy_Armor;
import character.Character;
import weapon.Mace;

public class Zombie extends Enemy {

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

	@Override
	public void execute_Template(List<Character> player_characters) {
		Combat_Strategy strategy = new Zombie_Strategy();
		strategy.execute(this, new ArrayList<Character>(player_characters));
		
	}


}
