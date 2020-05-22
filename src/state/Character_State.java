package state;

import character.Character;

public abstract class Character_State {

	protected int turns_left = 0;
	protected int turn_duration = 0;
	protected boolean activated = false;
	protected int damage_per_turn = 0;
	
	protected Character parent;
	
	public Character_State(Character parent, int turn_duration) {
		this.parent = parent;
		this.turn_duration = turn_duration;
	}
	
	public boolean getState() {
		return this.activated;
	}
	
	public int getDuration() {
		return this.turn_duration;
	}
	
	public int getTurnsLeft() {
		return this.turns_left;
	}
	
	public void effectNextTurn() {
		if(this.turns_left > 0) {
			this.execute();
			this.turns_left--;
		}
		
		else {
			this.activated = false;
		}
	}
	
	public void activate() {
		this.activated = true;
		this.turns_left = this.turn_duration;
	}
	
	public abstract void execute();

	public int getCombatSkillsModifier() {
		return 0;
	}

	public int getAttributesModifier() {
		return 0;
	}
	
}
