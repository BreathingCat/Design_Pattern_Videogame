package character;

import java.util.*;
import armor.*;
import weapon.*;

public class Paladin extends Character {

	public Paladin (String name, Weapon main, Weapon side, Map<String, Integer> attributes, Map<String, Integer> combat_skills, Map<String, Armor> equipment) {
		super(name, "PALADIN", main, side, attributes, combat_skills, equipment);
	}
	
}
