package races;

import java.util.Map;

import armor.Armor;
import character.Character;
import character.DAMAGE_CALC_SINGLETON;
import weapon.Weapon;

public class Cleric extends Character {

	public Cleric (String name, Weapon main, Weapon side, 
			Map<String, Integer> attributes, Map<String, Integer> combat_skills, Map<String, Armor> equipment,
			DAMAGE_CALC_SINGLETON dmg_calculator) {
		super(name, "CLERIC", main, side, attributes, combat_skills, equipment, dmg_calculator);
	}
	
}
