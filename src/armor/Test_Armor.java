package armor;

import org.json.simple.parser.*;
import org.json.simple.*;

import java.io.*;


public class Test_Armor {

	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject)parser.parse(new FileReader("src/armor/stats/HEAVY_ARMOR.txt"));
			System.out.println(((JSONObject)(json.get("HEAD_ARMOR"))).get("CUT_RESISTANCE"));
			System.out.println(json.entrySet().getClass());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
