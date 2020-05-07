package character;

import armor.*;
import races.Paladin;
import weapon.*;

import java.util.*;

public class Test_Character {

	public static void main(String[] args) {
		
		DAMAGE_CALC_SINGLETON calc = new DAMAGE_CALC_SINGLETON();

		Map<String, Integer> test_attr = new HashMap<String, Integer> () {{
			put("STRENGTH", 1);
			put("DEXTERITY", 1);
			put("TOUGHNESS", 1);
			put("PERCEPTION", 1);
		}};
		
		Map<String, Integer> test_combat_skills = new HashMap<String, Integer> () {{
			put("ATTACK", 1);
			put("DEFENSE", 1);
			put("DODGE", 1);
		}};
		
		Map<String, Armor> equipment = new HashMap<String, Armor> () {{
			Abstract_Factory_Armor fac = new Factory_Heavy_Armor();
			put("HEAD", fac.getHeadArmor());
			put("TORSO", fac.getTorsoArmor());
			put("LEG", fac.getLegArmor());
		}};	
		
		Character test = new Paladin("Test", new BattleHammer(), new Mace(), test_attr, test_combat_skills, equipment, calc);
		
		System.out.println(test.stats.get("COMBAT_SKILLS"));
		System.out.println(test.getCombatSkills());
		System.out.println(test.equipment.toString());
		System.out.println(test.current_hp);

	}

}
