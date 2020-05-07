package state;

import character.Character;
import character.Character_State;

public class Poisoned_State extends Character_State{

	protected int damage_per_turn = 6;
	
	public Poisoned_State (Character parent) {
		super(parent, 2);
	}

	@Override
	public void execute() {
		if(this.activated) {
			System.out.println(this.parent.name +" is poisoned! All attributes are reduced by 2 and receives " + this.damage_per_turn + " damage per turn");
			System.out.println("Turns left: " + String.valueOf(this.turns_left));
		}
	}
	
	public int getDamagePerTurn() {
		return this.damage_per_turn;
	}
	
}
