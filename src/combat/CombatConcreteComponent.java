package combat;

import character.Character;
import dmg_calc.DAMAGE_CALC_SINGLETON;

public class CombatConcreteComponent implements CombatInterface {

	public void combat(Character attacker, Character defender) {
		DAMAGE_CALC_SINGLETON calc = attacker.dmg_calculator;
		
		// Attack dodged
		if(!(calc.attackDodged(attacker, defender))) {
			// Attack blocked
			if(!(calc.attackBlocked(attacker, defender))) {
				// Ranged attacks
				if(attacker.main_weapon.getType() == "ranged") {
					// Attack hits
					if(calc.didHit(attacker)) {
						calc.computeDamage(attacker, defender);
					}
				} 
				// Melee attacks
				else {
					calc.computeDamage(attacker, defender);
				}
			}
		}
		

	}

}
