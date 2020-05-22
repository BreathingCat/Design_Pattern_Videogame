package enemies;

import character.Character;
import enemies.*;

import java.util.HashMap;

public class Factory_Enemy_Weak implements Abstract_Factory_Enemy {

	public Enemy getSkeleton() {
		return new Skeleton("Weak Skeleton",
				new HashMap<String, Integer> () {{
					put("STRENGTH", 3);
					put("DEXTERITY", 3);
					put("PERCEPTION", 3);
					put("TOUGHNESS", 3);
				}},
				new HashMap<String, Integer> () {{
					put("ATTACK", 3);
					put("DEFENSE", 3);
					put("DODGE", 3);
				}});
	}

	public Enemy getZombie() {
		return new Zombie("Weak Zombie",
				new HashMap<String, Integer> () {{
					put("STRENGTH", 3);
					put("DEXTERITY", 3);
					put("PERCEPTION", 3);
					put("TOUGHNESS", 3);
				}},
				new HashMap<String, Integer> () {{
					put("ATTACK", 3);
					put("DEFENSE", 3);
					put("DODGE", 3);
				}});
	}

}
