package state;

import character.Character;
import character.Character_State;

public class Stunned_State extends Character_State {
	
	public Stunned_State (Character parent) {
		super(parent, 2);
	}

	@Override
	public void execute() {
		if(this.activated) {
			System.out.println(this.parent.name +" is stunned! Turn is skipped");
			System.out.println("Turns left: " + String.valueOf(this.turns_left));
		}
	}

}
