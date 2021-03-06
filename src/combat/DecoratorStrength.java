package combat;

import character.Character;

public class DecoratorStrength extends CombatDecorator {

	public DecoratorStrength(CombatInterface component) {
		super(component);
	}
	
	protected void combatStrength(Character attacker) {
		if(attacker.getAttributes().get("STRENGTH") >= 10) {
			System.out.println(attacker.name + " strikes a strong attack due to high level STRENGTH!");
		}
	}
	
	public boolean combat(Character attacker, Character defender) {
		this.combatStrength(attacker);
		return super.combat(attacker, defender);
	}
	
}
