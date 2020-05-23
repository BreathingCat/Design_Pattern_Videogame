package enemies;

import java.util.List;
import java.util.Map;

import armor.Armor;
import character.Character;
import weapon.Weapon;

public abstract class Enemy extends Character {

	public Enemy(String name, String race, Weapon main, Map<String, Integer> attributes,
			Map<String, Integer> combat_skills, Map<String, Armor> equipment) {
		super(name, race, main, attributes, combat_skills, equipment);
	}
	
	public abstract void executeStrategy(List<Character> player_characters, Combat_Strategy strategy);

}
