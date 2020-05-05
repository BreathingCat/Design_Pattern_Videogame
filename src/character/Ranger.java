package character;

import java.util.Map;

import armor.Armor;
import weapon.Weapon;

public class Ranger extends Character {

	public Ranger (String name, Weapon main, Weapon side, Map<String, Integer> attributes, Map<String, Integer> combat_skills, Map<String, Armor> equipment) {
		super(name, "RANGER", main, side, attributes, combat_skills, equipment);
	}
	
}
