package state;

import character.Character;

public class Disoriented_State extends Character_State {

	protected int combat_skills_modifier = -2;
	
	public Disoriented_State(Character parent) {
		super(parent, 3);
	}
	
	@Override
	public void execute() {
		if(this.activated) {
			System.out.println(this.parent.name + " is disoriented! All combat skills are reduced by 2");
			System.out.println("Turns left: " + String.valueOf(this.turns_left - 1));
		}

	}
	
	public int getCombatSkillsModifier() {
		return this.combat_skills_modifier;
	}

}
