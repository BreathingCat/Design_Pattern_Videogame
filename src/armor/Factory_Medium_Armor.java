package armor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Factory_Medium_Armor implements Abstract_Factory_Armor {

	private String stats_file = "src/armor/stats/MEDIUM_ARMOR.txt";

	public Armor getHeadArmor() {
		JSONParser parser = new JSONParser();
		try {
			final JSONObject head_armor_json = (JSONObject)((JSONObject)parser.parse(new FileReader(this.stats_file))).get("HEAD_ARMOR");
			
			// Reads JSON file for all attributes of the medium head armor
			// Overengineered, yes, but more memory-efficient
			return new Head_Armor("medium", 
					(Integer)head_armor_json.get("BLUNT_RESISTANCE"),
					(Integer)head_armor_json.get("CUT_RESISTANCE"), 
					(Integer)head_armor_json.get("COVERAGE"), 
					// Attributes
					new HashMap<String, Integer> () {{
						JSONObject attributes_json = (JSONObject)head_armor_json.get("ATTRIBUTES");
						for (Iterator it = attributes_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, (Integer)attributes_json.get(key));
						}
					}},
					// Combat skills
					new HashMap<String, Integer> () {{
						JSONObject combat_skills_json = (JSONObject)head_armor_json.get("COMBAT_SKILLS");
						for (Iterator it = combat_skills_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, (Integer)combat_skills_json.get(key));
						}
					}},
					// Weapon skills
					new HashMap<String, Integer> () {{
						JSONObject weapon_skills_json = (JSONObject)head_armor_json.get("WEAPON_SKILLS");
						for (Iterator it = weapon_skills_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, (Integer)weapon_skills_json.get(key));
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
			
			// Reads JSON file for all attributes of the medium torso armor
			// Overengineered, yes, but more memory-efficient
			return new Torso_Armor("medium", 
					(Integer)torso_armor_json.get("BLUNT_RESISTANCE"),
					(Integer)torso_armor_json.get("CUT_RESISTANCE"), 
					(Integer)torso_armor_json.get("COVERAGE"), 
					// Attributes
					new HashMap<String, Integer> () {{
						JSONObject attributes_json = (JSONObject)torso_armor_json.get("ATTRIBUTES");
						for (Iterator it = attributes_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, (Integer)attributes_json.get(key));
						}
					}},
					// Combat skills
					new HashMap<String, Integer> () {{
						JSONObject combat_skills_json = (JSONObject)torso_armor_json.get("COMBAT_SKILLS");
						for (Iterator it = combat_skills_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, (Integer)combat_skills_json.get(key));
						}
					}},
					// Weapon skills
					new HashMap<String, Integer> () {{
						JSONObject weapon_skills_json = (JSONObject)torso_armor_json.get("WEAPON_SKILLS");
						for (Iterator it = weapon_skills_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, (Integer)weapon_skills_json.get(key));
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
			
			// Reads JSON file for all attributes of the medium leg armor
			// Overengineered, yes, but more memory-efficient
			return new Leg_Armor("medium", 
					(Integer)leg_armor_json.get("BLUNT_RESISTANCE"),
					(Integer)leg_armor_json.get("CUT_RESISTANCE"), 
					(Integer)leg_armor_json.get("COVERAGE"), 
					// Attributes
					new HashMap<String, Integer> () {{
						JSONObject attributes_json = (JSONObject)leg_armor_json.get("ATTRIBUTES");
						for (Iterator it = attributes_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, (Integer)attributes_json.get(key));
						}
					}},
					// Combat skills
					new HashMap<String, Integer> () {{
						JSONObject combat_skills_json = (JSONObject)leg_armor_json.get("COMBAT_SKILLS");
						for (Iterator it = combat_skills_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, (Integer)combat_skills_json.get(key));
						}
					}},
					// Weapon skills
					new HashMap<String, Integer> () {{
						JSONObject weapon_skills_json = (JSONObject)leg_armor_json.get("WEAPON_SKILLS");
						for (Iterator it = weapon_skills_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, (Integer)weapon_skills_json.get(key));
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

	public Armor getBootArmor() {
		JSONParser parser = new JSONParser();
		try {
			final JSONObject boot_armor_json = (JSONObject)((JSONObject)parser.parse(new FileReader(this.stats_file))).get("BOOT_ARMOR");
			
			// Reads JSON file for all attributes of the medium leg armor
			// Overengineered, yes, but more memory-efficient
			return new Leg_Armor("medium", 
					(Integer)boot_armor_json.get("BLUNT_RESISTANCE"),
					(Integer)boot_armor_json.get("CUT_RESISTANCE"), 
					(Integer)boot_armor_json.get("COVERAGE"), 
					// Attributes
					new HashMap<String, Integer> () {{
						JSONObject attributes_json = (JSONObject)boot_armor_json.get("ATTRIBUTES");
						for (Iterator it = attributes_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, (Integer)attributes_json.get(key));
						}
					}},
					// Combat skills
					new HashMap<String, Integer> () {{
						JSONObject combat_skills_json = (JSONObject)boot_armor_json.get("COMBAT_SKILLS");
						for (Iterator it = combat_skills_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, (Integer)combat_skills_json.get(key));
						}
					}},
					// Weapon skills
					new HashMap<String, Integer> () {{
						JSONObject weapon_skills_json = (JSONObject)boot_armor_json.get("WEAPON_SKILLS");
						for (Iterator it = weapon_skills_json.keySet().iterator(); it.hasNext();) {
							String key = (String) it.next();
							put(key, (Integer)weapon_skills_json.get(key));
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
