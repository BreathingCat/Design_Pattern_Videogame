package weapon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class Weapon {

	protected int range = 0;
	protected int damage = 0;
	protected String type = null;
	
	protected final static String weapon_stats = "src/weapon/stats/stats.txt";
	
	protected Map<String, Integer> effects = new HashMap<String, Integer> ();
	
	protected Weapon (String subweapon, String type) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject)((JSONObject)parser.parse(new FileReader(this.weapon_stats))).get(subweapon);
			this.range = ((Long)json.get("RANGE")).intValue();
			this.damage = ((Long)json.get("DAMAGE")).intValue();		
			this.type = type;
				
			for (Iterator it = ((JSONObject)json.get("EFFECTS")).keySet().iterator(); it.hasNext();) {
				String key = (String)it.next();
				this.effects.put(key, (((Long)((JSONObject)json.get("EFFECTS")).get(key)).intValue()));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
	}
	
	public int getRange() {
		return this.range;
	}
	
	public int getDamage() {
		return this.damage;
	}

	public Map<String, Integer> getEffects(){
		return this.effects;
	}
	
}
