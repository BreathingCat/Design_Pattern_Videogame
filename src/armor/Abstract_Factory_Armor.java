package armor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class Abstract_Factory_Armor {
	
	protected HashMap<String, Integer> getArmorStatsFromFile(String bodyPart, String stats, String file) {
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject armor_json = (JSONObject)((JSONObject)((JSONObject)parser.parse(new FileReader(file))).get(bodyPart)).get(stats);
			HashMap<String, Integer> data = new HashMap<String, Integer> ();
			
			for (Iterator it = armor_json.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				data.put(key, ((Long)armor_json.get(key)).intValue());
			}
			
			return data;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	public abstract Armor getHeadArmor();
	public abstract Armor getTorsoArmor();
	public abstract Armor getLegArmor();
	
}
