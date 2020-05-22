package enemies;

import java.util.ArrayList;

import character.Character;

public class Zombie_Strategy implements Combat_Strategy {

	public void execute(Enemy character, ArrayList<Character> player_characters) {
		System.out.println(character.name + " starts turn!");
		
		if(character.getCurrentHp() > 75) {
			System.out.println(character.name + " binding to the chaos forces is strong! Heals for 4 hp");
			character.heal(4);
		}
		
		for(Character item : player_characters) {
			if(item.isAlive()) {
				character.attack(item);
				break;
			}
		}
		
	}

}
