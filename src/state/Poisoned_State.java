package state;

import character.Character;

public class Poisoned_State extends Character_State{

	protected int attributes_modifier = -2;
	
	public Poisoned_State (Character parent) {
		super(parent, 2);
		this.damage_per_turn = 2;
	}

	@Override
	public void execute() {
		if(this.activated) {
			System.out.println(this.parent.name +" is poisoned! All attributes are reduced by 2 and receives " + this.damage_per_turn + " damage per turn");
			System.out.println("Turns left: " + String.valueOf(this.turns_left - 1));
			parent.damage(this.damage_per_turn);
		}
	}
	
	public int getDamagePerTurn() {
		return this.damage_per_turn;
	}
	
	public int getAttributesModifier() {
		return this.attributes_modifier;
	}
	
}
