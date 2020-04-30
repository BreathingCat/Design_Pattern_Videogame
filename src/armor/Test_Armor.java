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
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject)parser.parse(new FileReader("src/armor/stats/HEAVY_ARMOR.txt"));
			
			for (Iterator it = json.keySet().iterator(); it.hasNext();) {
				
				String key = (String) it.next();
				System.out.println(key);
				System.out.println(json.get(key));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
