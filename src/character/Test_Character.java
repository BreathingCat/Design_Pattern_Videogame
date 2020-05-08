package character;

import armor.*;
import races.*;
import weapon.*;

import java.util.*;

public class Test_Character {

	public static void main(String[] args) {
		
		DAMAGE_CALC_SINGLETON calc = new DAMAGE_CALC_SINGLETON();
		
		Character test = new Cleric("Test", new BattleHammer(), 
				new HashMap<String, Integer> () {{
					put("STRENGTH", 10);
					put("DEXTERITY", 5);
					put("TOUGHNESS", 7);
					put("PERCEPTION", 1);
				}}, 
				new HashMap<String, Integer> () {{
					put("ATTACK", 6);
					put("DEFENSE", 5);
					put("DODGE", 1);
				}},
				new HashMap<String, Armor> () {{
					Abstract_Factory_Armor fac = new Factory_Heavy_Armor();
					put("HEAD", fac.getHeadArmor());
					put("TORSO", fac.getTorsoArmor());
					put("LEG", fac.getLegArmor());
				}}, calc);
		
		Character test2 = new Soldier("Test2", new Spear(),
				new HashMap<String, Integer> () {{
					put("STRENGTH", 4);
					put("DEXTERITY", 7);
					put("TOUGHNESS", 2);
					put("PERCEPTION", 1);
				}}, 
				new HashMap<String, Integer> () {{
					put("ATTACK", 8);
					put("DEFENSE", 0);
					put("DODGE", 10);
				}},
				new HashMap<String, Armor> () {{
					Abstract_Factory_Armor fac = new Factory_Medium_Armor();
					put("HEAD", fac.getHeadArmor());
					put("TORSO", fac.getTorsoArmor());
					put("LEG", fac.getLegArmor());
				}}, calc);
		
		Character test3 = new Ranger("Test3", new Crossbow(),
				new HashMap<String, Integer> () {{
					put("STRENGTH", 1);
					put("DEXTERITY", 4);
					put("TOUGHNESS", 1);
					put("PERCEPTION", 7);
				}}, 
				new HashMap<String, Integer> () {{
					put("ATTACK", 9);
					put("DEFENSE", 0);
					put("DODGE", 0);
				}},
				new HashMap<String, Armor> () {{
					Abstract_Factory_Armor fac = new Factory_Light_Armor();
					put("HEAD", fac.getHeadArmor());
					put("TORSO", fac.getTorsoArmor());
					put("LEG", fac.getLegArmor());
				}}, calc);
		
		
		
		/*System.out.println(test.getAttributes());
		System.out.println(test.getCombatSkills());
		
		System.out.println(test2.getAttributes());
		System.out.println(test2.getCombatSkills());*/
		
		/*test.disorient();
		test.poison();
		
		System.out.println(test.getAttributes());
		System.out.println(test.getCombatSkills());
		
		System.out.println(test.equipment.toString());
		System.out.println(test.current_hp);
		
		test.effectsNextTurn();
		test.effectsNextTurn();
		test.effectsNextTurn();*/
		
		// calc.attackDodged(test, test2);
		// calc.attackBlocked(test, test2);
		
		// calc.attackBlocked(test, test2);
		// calc.attackDodged(test, test2);
		
		calc.didHit(test3);
		calc.attackDodged(test3, test2);
		calc.attackBlocked(test3, test2);
		calc.computeDamage(test3, test2);
		
		calc.computeDamage(test, test3);
		
		/*if(test.dmg_calculator.didStun(test)) {
			System.out.println("Stun");
		}*/
		
	}

}
