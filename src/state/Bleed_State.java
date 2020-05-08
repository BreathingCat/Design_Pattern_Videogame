package state;

import character.Character;

public class Bleed_State extends Character_State {

	protected int damage_per_turn = 8;
	
	public Bleed_State (Character parent) {
		super(parent, 3);
	}

	@Override
	public void execute() {
		if(this.activated) {
			System.out.println(this.parent.name +" is bleeding! Bleeds for " + this.damage_per_turn + " hp");
			System.out.println("Turns left: " + String.valueOf(this.turns_left - 1));
		}
	}
	
	public int getDamagePerTurn() {
		return this.damage_per_turn;
	}
	
}
