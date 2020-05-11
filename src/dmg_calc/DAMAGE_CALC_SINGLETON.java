package dmg_calc;

import java.util.*;

import character.Character;

// This class DOES NOT damage character. It just computes the damage
// and returns it based on character attributes, equipment and weaponry,
// as well as on-hit effects and debuffs

// This class DOES NOT apply to any abilities who do direct damage, or
// healing spells.

// This class is to be used ONLY for computing damage
public class DAMAGE_CALC_SINGLETON {
	
	public boolean attackDodged(Character attacker, Character defender) {
		Random random = new Random();
		
		// Influenced by ATTACK of the attacker and DODGE of the defender
		int attck_attack = attacker.getCombatSkills().get("ATTACK");
		
		if(attck_attack < 0) {
			attck_attack = 0;
		}
		
		int deff_dodge = defender.getCombatSkills().get("DODGE");
		
		if(deff_dodge < 0) {
			deff_dodge = 0;
		}
		
		double prob_dodge = Math.log10(Math.abs(attck_attack - deff_dodge)/2 + 2.2);
		
		// Since the difference must be negative, we invert the probabilities
		// Could be called a false positive or a false negative
		
		// Dodge failed
		if (random.nextInt(101) < prob_dodge) {
			// False negative
			if(attck_attack - deff_dodge > 0) {
				System.out.println(defender.name + " dodges attack!");
				return true;
			}
			// Else is false (True negative)
			return false;
		} 
		// Dodge succeded
		else {
			// True positive
			if(attck_attack - deff_dodge < 0) {
				System.out.println(defender.name + " dodges attack!");
				return true;
			}
			// Else is false (false positive)
			return false;
		}
	}
	
	public boolean attackBlocked(Character attacker, Character defender) {
		Random random = new Random();
		
		// Influenced by ATTACK of the attacker and DEFENSE of the defender	
		int attck_attack = attacker.getCombatSkills().get("ATTACK");
		
		if(attck_attack < 0) {
			attck_attack = 0;
		}
		
		int deff_defense = defender.getCombatSkills().get("DEFENSE");
		
		if(deff_defense < 0) {
			deff_defense = 0;
		}
		
		double prob_block = Math.log10(Math.abs(attck_attack - deff_defense)/2 + 2.2);
		
		// Since the difference must be negative, we invert the probabilities
		// Could be called a false positive or a false negative
		
		// Block failed
		if (random.nextInt(101) < prob_block) {
			// False negative
			if(attck_attack - deff_defense > 0) {
				System.out.println(defender.name + " blocks attack!");
				return true;
			}
			// Else is false (True negative)
			return false;
		} 
		// Block succeded
		else {
			// True positive
			if(attck_attack - deff_defense < 0) {
				System.out.println(defender.name + " blocks attack!");
				return true;
			}
			// Else is false (false positive)
			return false;
		}}

	private int computeDamage_Melee(Character attacker, Character defender) {
		int base_raw_damage = attacker.main_weapon.getDamage();
		double additional_damage = 0;
		
		// Strong attack bonus
		if(attacker.getAttributes().get("STRENGTH") >= 10) {
			System.out.println("Strong attack bonus: +" + (base_raw_damage * 0.2) + " dmg");
			additional_damage = (base_raw_damage * 0.2);
		}
		// Precise attack bonus
		if(attacker.getAttributes().get("DEXTERITY") >= 10) {
			System.out.println("Precise attack bonus: +" + (base_raw_damage * 0.2) + " dmg");
			additional_damage += (base_raw_damage * 0.2);
		}
		
		int total_damage = (int)(base_raw_damage + additional_damage);

		int deff_toughness = defender.getAttributes().get("TOUGHNESS");
		
		// Check negative values for logarythmic function
		// Since were adding a + 1 to the formula x can be 0 (and it should)
		if (deff_toughness < 0) {
			deff_toughness = 0;
		}
		
		double percentage_damage_reduced = 0.8 * (Math.log10(((double)deff_toughness)/2.0 + 1));
		
		// Magistral resolve
		if (deff_toughness >= 10) {
			System.out.println("Iron-skin defense bonus +20% damage reduced");
			percentage_damage_reduced = 1.2 * percentage_damage_reduced;
		}
		
		int damage_reduced = (int)(total_damage * percentage_damage_reduced);
		
		System.out.println("Base damage: " + base_raw_damage);
		System.out.println("Total damage: " + total_damage);
		System.out.println("Damage reduction: " + damage_reduced);
		System.out.println(defender.name + " receives " + (total_damage - damage_reduced) + " damage");

		return total_damage - damage_reduced;
		
	}
	
	private int computeDamage_Ranged(Character attacker, Character defender) {
		int base_raw_damage = attacker.main_weapon.getDamage();
		double additional_damage = 0;
		
		// Accurate shot
		if(attacker.getAttributes().get("PERCEPTION") >= 10) {
			System.out.println("Accurate shot bonus: +" + (base_raw_damage * 0.2) + " dmg");
			additional_damage = base_raw_damage * 0.2;
		}
		
		int total_damage = (int)(base_raw_damage + additional_damage);

		int deff_toughness = defender.getAttributes().get("TOUGHNESS");
		
		// Check negative values for logarythmic function
		// Since were adding a + 1 to the formula x can be 0 (and it should)
		if (deff_toughness < 0) {
			deff_toughness = 0;
		}
		
		double percentage_damage_reduced = 0.8 * (Math.log10(((double)deff_toughness)/2.0 + 1));
		
		// Magistral resolve
		if (deff_toughness >= 10) {
			System.out.println("Iron-skin defense bonus +20% reduced damage");
			percentage_damage_reduced = 1.2 * percentage_damage_reduced;
		}
		
		int damage_reduced = (int)(total_damage * percentage_damage_reduced);
		
		System.out.println("Base damage: " + base_raw_damage);
		System.out.println("Total damage: " + total_damage);
		System.out.println("Damage reduction: " + damage_reduced);
		System.out.println(defender.name + " receives " + (total_damage - damage_reduced) + " damage");

		return total_damage - damage_reduced;
	}
	
	public int computeDamage(Character attacker, Character defender) {
		
		// We do not cache any of the characters stats since the HashMaps in which they are contained
		// Have max 4 elements and the search is very close to O(n) = 1
		
		int result = -1;
		
		if(attacker.main_weapon.getType() == "ranged") {
			result = this.computeDamage_Ranged(attacker, defender);
		} else {
			result = this.computeDamage_Melee(attacker, defender);
		}
		
		return result;
	}
	
	// Help function to avoid duplicating code
	private boolean helpEffects(String effect, Character attacker) {
		Random random = new Random();
		
		int prob = attacker.main_weapon.getEffects().get("STUN").intValue();
		
		if(random.nextInt(101) < prob) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean didStun(Character attacker) {
		return this.helpEffects("STUN", attacker);
	}
	
	public boolean didBleed(Character attacker) {
		return this.helpEffects("BLEED", attacker);
	}
	
	public boolean didDisorient(Character attacker) {
		return this.helpEffects("DISORIENT", attacker);
	}
	
	public boolean didPoison(Character attacker) {
		return this.helpEffects("POISON", attacker);
	}
	
	public boolean didHit(Character attacker) {
		Random random = new Random();
		
		int attck_perception = attacker.getAttributes().get("PERCEPTION");
		double prob_hit = (Math.log10(((double)attck_perception)/2.0 + 1.0));
		
		if(random.nextInt(101) < prob_hit) {
			System.out.println("Shot failed!");
			return false;
		} else {
			System.out.println(attacker.name + " takes a deep breath, aims and fires...");
			return true;
		}
		
	}
	
}
