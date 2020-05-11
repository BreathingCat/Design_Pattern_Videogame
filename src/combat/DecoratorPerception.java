package combat;

import character.Character;

public class DecoratorPerception extends CombatDecorator {

	public DecoratorPerception(CombatInterface component) {
		super(component);
	}
	
	protected void combatPerception(Character attacker) {
		if(attacker.getAttributes().get("PERCEPTION") >= 10) {
			System.out.println(attacker.name + " strikes an accurate attack due to high level PERCEPTION!");
		}		
	}
	
	public boolean combat(Character attacker, Character defender) {
		this.combatPerception(attacker);
		return super.combat(attacker, defender);
	}
	
}
