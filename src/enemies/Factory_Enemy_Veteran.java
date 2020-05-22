package enemies;

import java.util.HashMap;

import character.Character;

public class Factory_Enemy_Veteran implements Abstract_Factory_Enemy {

	public Enemy getSkeleton() {
		return new Skeleton("Veteran Skeleton",
				new HashMap<String, Integer> () {{
					put("STRENGTH", 7);
					put("DEXTERITY", 7);
					put("PERCEPTION", 7);
					put("TOUGHNESS", 7);
				}},
				new HashMap<String, Integer> () {{
					put("ATTACK", 7);
					put("DEFENSE", 7);
					put("DODGE", 7);
				}});
	}

	public Enemy getZombie() {
		return new Skeleton("Veteran Zombie",
				new HashMap<String, Integer> () {{
					put("STRENGTH", 7);
					put("DEXTERITY", 7);
					put("PERCEPTION", 7);
					put("TOUGHNESS", 7);
				}},
				new HashMap<String, Integer> () {{
					put("ATTACK", 7);
					put("DEFENSE", 7);
					put("DODGE", 7);
				}});
	}

}
