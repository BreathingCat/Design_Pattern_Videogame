package combat;

import character.Character;
import dmg_calc.DAMAGE_CALC_SINGLETON;

public class CombatConcreteComponent implements CombatInterface {

	public boolean combat(Character attacker, Character defender) {
		DAMAGE_CALC_SINGLETON calc = attacker.dmg_calculator;
		
		// Attack dodged
		if(!(calc.attackDodged(attacker, defender))) {
			// Attack blocked
			if(!(calc.attackBlocked(attacker, defender))) {
				// Ranged attacks
				if(attacker.main_weapon.getType() == "ranged") {
					// Attack hits
					if(calc.didHit(attacker)) {
						defender.damage(calc.computeDamage(attacker, defender));
					}
				} 
				// Melee attacks
				else {
					defender.damage(calc.computeDamage(attacker, defender));
				}
				
				if(calc.didBleed(attacker)) {
					defender.bleed();
				}
				
				if(calc.didDisorient(attacker)) {
					defender.disorient();
				}
				
				if(calc.didPoison(attacker)) {
					defender.poison();
				}
				
				if(calc.didStun(attacker)) {
					defender.stun();
				}
				
				// Attacks kills defender returns true
				if(defender.getCurrentHp() == 0) {
					return true;
				} else {
					return false;
				}
			}		
			return false;
		}	
		return false;	
	}

}
