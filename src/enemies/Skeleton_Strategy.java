package enemies;

import character.Character;
import java.util.ArrayList; 

public class Skeleton_Strategy implements Combat_Strategy {

	public void execute(Enemy character, ArrayList<Character> player_characters) {
		System.out.println("Spooky skeleton uuu...");
		
		for(Character item : player_characters) {
			if(item.isAlive()) {
				System.out.println(character.name + " is going to attack " + item.name + "!");
				character.attack(item);
				break;
			}
		}
		
	}

}
