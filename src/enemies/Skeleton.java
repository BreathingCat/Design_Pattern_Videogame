package enemies;

import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import weapon.Spear;
import armor.Armor;
import armor.Factory_Medium_Armor;
import character.Character;

public class Skeleton extends Enemy {

	public Skeleton(String name, Map<String, Integer> attributes,
			Map<String, Integer> combat_skills) {
		super(name, "SKELETON", new Spear(), attributes, combat_skills,
				new HashMap<String, Armor> () {{
					Factory_Medium_Armor fac = new Factory_Medium_Armor();
					put("HEAD", fac.getHeadArmor());
					put("TORSO", fac.getTorsoArmor());
					put("LEG", fac.getLegArmor());
				}});
	}

	@Override
	public void executeStrategy(List<Character> player_characters, Combat_Strategy strategy) {
		strategy.execute(this, new ArrayList<Character>(player_characters));
		
	}
	
	@Override
	public boolean attack(Character objective) {
		System.out.println("Skeleton does a spook! " + objective.name + " is spooked!");
		return this.attackSuper(objective);
	}

}
