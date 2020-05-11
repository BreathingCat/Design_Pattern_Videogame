package combat;

import character.Character;

public abstract class CombatDecorator implements CombatInterface {

	protected CombatInterface component;
	
	public CombatDecorator(CombatInterface component) {
		this.component = component;
	}
	
	public void combat(Character attacker, Character defender) {
		this.component.combat(attacker, defender);

	}

}
