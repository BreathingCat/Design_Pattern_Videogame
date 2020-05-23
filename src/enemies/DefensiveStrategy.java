package enemies;

import java.util.ArrayList;

import character.Character;

public class DefensiveStrategy implements Combat_Strategy {

	public void execute(Enemy character, ArrayList<Character> player_characters) {	
		System.out.println(character.name + " is weakened! Summons dark eneries and heals itself for 10 hp!");
		character.heal(10);
		
		for(Character item : player_characters) {
			if(item.isAlive()) {
				character.attack(item);
				break;
			}
		}
		
	}

}
