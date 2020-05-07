package state;

import java.util.*;

import character.Character;
import character.Character_State;

public class Disoriented_State extends Character_State {

	protected Map<String, Integer> combat_skills_penalty = new HashMap<String, Integer>() {{
		put("ATTACK", -2);
		put("DEFENSE", -2);
		put("DODGE", -2);
	}};
	
	public Disoriented_State(Character parent) {
		super(parent, 3);
	}
	
	@Override
	public void execute() {
		if(this.activated) {
			System.out.println(this.parent.name + " is disoriented! All combat skills are reduced by 2");
			System.out.println("Turns left: " + String.valueOf(this.turns_left));
		}

	}

}
