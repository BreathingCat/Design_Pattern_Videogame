package character;

import java.util.Map;

import armor.Armor;
import weapon.Weapon;

public class Cleric extends Character {

	public Cleric (String name, Weapon main, Weapon side, Map<String, Integer> attributes, Map<String, Integer> combat_skills, Map<String, Armor> equipment) {
		super(name, "CLERIC", main, side, attributes, combat_skills, equipment);
	}
	
}
