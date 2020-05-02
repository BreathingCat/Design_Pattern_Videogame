package character;

import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import armor.*;
import weapon.*;

public abstract class Character {

	protected String name = "Unnamed";
	
	protected final static String char_stats = "src/character/stats.txt";
	
	// Character stats, subdivided in ATTR, WEAPON SKILLS and COMBAT SKILLS
	protected Map<String, Map<String, Integer>> stats = new HashMap<String, Map<String, Integer>> () {{
		put("attributes", new HashMap<String, Integer>());
		put("combat_skills", new HashMap<String, Integer>());
	}};
	
	// Health
	public Body_Health health = null;
	
	// Armor
	public Map<String, Armor> equipment = new HashMap<String, Armor> () {{
		put("head", null);
		put("torso", null);
		put("leg", null);
	}};
	
	// Weapons
	public Weapon main_weapon = null;
	public Weapon side_weapon = null;
	
	public abstract Map<String, Integer> getAttributes ();
	public abstract Map<String, Integer> getCombatSkills ();
	
	public Character () {
		JSONParser parser = new JSONParser();	
		try {
			JSONObject json_stats = (JSONObject)parser.parse(new FileReader(Character.char_stats));

			// Attributes
			for(Iterator it = ((JSONArray)json_stats.get("ATTRIBUTES")).iterator(); it.hasNext();) {
				this.stats.get("attributes").put((String)it.next(), 0);
			}
			
			// Combat Skills
			for(Iterator it = ((JSONArray)json_stats.get("COMBAT_SKILLS")).iterator(); it.hasNext();) {
				this.stats.get("combat_skills").put((String)it.next(), 0);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
