package character;

import java.util.*;
import java.io.*;
import armor.*;
import weapon.*;

public class Paladin extends Character {

	public Paladin (String name, Weapon main, Weapon side, Map<String, Integer> attributes, Map<String, Integer> combat_skills) {
		super(name, "PALADIN", main, side, attributes, combat_skills);
	}

	
	@Override
	public Map<String, Integer> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> getCombatSkills() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
