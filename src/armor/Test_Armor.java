package armor;

import java.util.*;
import org.json.simple.parser.*;
import org.json.simple.*;

import java.io.*;

/*
 * Class used mainly for testing
 */

public class Test_Armor {

	
	public static void main(String[] args) {
		
		Factory_Heavy_Armor fac = new Factory_Heavy_Armor();
		
		Armor head = fac.getHeadArmor();
		
		System.out.println(head.getCombatSkillsModifier().toString());
		

	}

}
