package armor;

import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Factory_Heavy_Armor implements Abstract_Factory_Armor {
	
	private final static String stats_file = "src/armor/HEAVY_ARMOR.txt";

	public Armor getHeadArmor() {
		JSONParser parser = new JSONParser();
		try {
			final JSONObject head_armor_json = (JSONObject)((JSONObject)parser.parse(new FileReader(this.stats_file))).get("HEAD_ARMOR");
			
			// Reads JSON file for all attributes of the heavy head armor
			// Overengineered, yes, but more memory-efficient
			return new Head_Armor("heavy", 
					((Long)head_armor_json.get("RESISTANCE")).intValue(), 
					((Long)head_armor_json.get("COVERAGE")).intValue(), 
					// Attributes
					new HashMap<String, Integer> () {{
						JSONObject attributes_json = (JSONObject)head_armor_json.get("ATTRIBUTES");
						for (Iterator it = attributes_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, ((Long)attributes_json.get(key)).intValue());
						}
					}},
					// Combat skills
					new HashMap<String, Integer> () {{
						JSONObject combat_skills_json = (JSONObject)head_armor_json.get("COMBAT_SKILLS");
						for (Iterator it = combat_skills_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, ((Long)combat_skills_json.get(key)).intValue());
						}
					}}
					);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// If failed trying to read the stats file, it will return null
		// Must do this since is has to have a return outside the try/catch block
		return null;

	}

	public Armor getTorsoArmor() {
		JSONParser parser = new JSONParser();
		try {
			final JSONObject torso_armor_json = (JSONObject)((JSONObject)parser.parse(new FileReader(this.stats_file))).get("TORSO_ARMOR");
			
			// Reads JSON file for all attributes of the heavy torso armor
			// Overengineered, yes, but more memory-efficient
			return new Torso_Armor("heavy", 
					((Long)torso_armor_json.get("RESISTANCE")).intValue(),
					((Long)torso_armor_json.get("COVERAGE")).intValue(), 
					// Attributes
					new HashMap<String, Integer> () {{
						JSONObject attributes_json = (JSONObject)torso_armor_json.get("ATTRIBUTES");
						for (Iterator it = attributes_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, ((Long)attributes_json.get(key)).intValue());
						}
					}},
					// Combat skills
					new HashMap<String, Integer> () {{
						JSONObject combat_skills_json = (JSONObject)torso_armor_json.get("COMBAT_SKILLS");
						for (Iterator it = combat_skills_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, ((Long)combat_skills_json.get(key)).intValue());
						}
					}}
					);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// If failed trying to read the stats file, it will return null
		// Must do this since is has to have a return outside the try/catch block
		return null;

	}

	public Armor getLegArmor() {
		JSONParser parser = new JSONParser();
		try {
			final JSONObject leg_armor_json = (JSONObject)((JSONObject)parser.parse(new FileReader(this.stats_file))).get("LEG_ARMOR");
			
			// Reads JSON file for all attributes of the heavy leg armor
			// Overengineered, yes, but more memory-efficient
			return new Leg_Armor("heavy", 
					((Long)leg_armor_json.get("RESISTANCE")).intValue(),
					((Long)leg_armor_json.get("COVERAGE")).intValue(), 
					// Attributes
					new HashMap<String, Integer> () {{
						JSONObject attributes_json = (JSONObject)leg_armor_json.get("ATTRIBUTES");
						for (Iterator it = attributes_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, ((Long)attributes_json.get(key)).intValue());
						}
					}},
					// Combat skills
					new HashMap<String, Integer> () {{
						JSONObject combat_skills_json = (JSONObject)leg_armor_json.get("COMBAT_SKILLS");
						for (Iterator it = combat_skills_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, ((Long)combat_skills_json.get(key)).intValue());
						}
					}}
					);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// If failed trying to read the stats file, it will return null
		// Must do this since is has to have a return outside the try/catch block
		return null;
	}

}
