package combat;

import character.Character;

public class DecoratorDexterity extends CombatDecorator {

	public DecoratorDexterity(CombatInterface component) {
		super(component);
	}
	
	protected void combatDexterity(Character attacker) {
		if(attacker.getAttributes().get("DEXTERITY") >= 10) {
			System.out.println(attacker.name + " strikes a precise attack due to high level DEXTERITY!");
		}	
	}
	
	public boolean combat(Character attacker, Character defender) {
		this.combatDexterity(attacker);
		return super.combat(attacker, defender);
	}
	
}
