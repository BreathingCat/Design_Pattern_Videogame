package races;

import java.util.Map;

import armor.Armor;
import character.Character;
import dmg_calc.DAMAGE_CALC_SINGLETON;
import weapon.Weapon;

public class Ranger extends Character {

	public Ranger (String name, Weapon main, 
			Map<String, Integer> attributes, Map<String, Integer> combat_skills, Map<String, Armor> equipment) {
		super(name, "RANGER", main, attributes, combat_skills, equipment);
	}
	
}
