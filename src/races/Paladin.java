package races;

import java.util.*;
import armor.*;
import character.Character;
import dmg_calc.DAMAGE_CALC_SINGLETON;
import weapon.*;

public class Paladin extends Character {

	public Paladin (String name, Weapon main, 
			Map<String, Integer> attributes, Map<String, Integer> combat_skills, Map<String, Armor> equipment) {
		super(name, "PALADIN", main, attributes, combat_skills, equipment);
	}
	
}
