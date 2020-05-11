package races;

import java.util.Map;

import armor.Armor;
import character.Character;
import dmg_calc.DAMAGE_CALC_SINGLETON;
import weapon.Weapon;

public class Soldier extends Character {

	public Soldier (String name, Weapon main,
			Map<String, Integer> attributes, Map<String, Integer> combat_skills, Map<String, Armor> equipment) {
		super(name, "SOLDIER", main, attributes, combat_skills, equipment);
	}
	
}
