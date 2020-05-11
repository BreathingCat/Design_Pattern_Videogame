package combat;

import character.Character;

public class DecoratorToughness extends CombatDecorator {

	public DecoratorToughness(CombatInterface component) {
		super(component);
	}
	
	protected void combatToughness(Character defender) {
		if(defender.getAttributes().get("TOUGHNESS") >= 10) {
			System.out.println(defender.name + " has an iron-skin due to high level TOUGHNESS!");
		}		
	}
	
	public boolean combat(Character attacker, Character defender) {
		this.combatToughness(defender);
		return super.combat(attacker, defender);
		
	}
	
}
