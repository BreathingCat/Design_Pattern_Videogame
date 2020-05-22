package enemies;

import java.util.ArrayList;

import character.Character;

public interface Combat_Strategy {

	public void execute(Enemy character, ArrayList<Character> player_characters);
	
}
