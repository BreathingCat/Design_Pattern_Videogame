package enemies;

import java.util.HashMap;

import character.Character;

public class Factory_Enemy_Normal implements Abstract_Factory_Enemy {

	public Enemy getSkeleton() {
		return new Skeleton("Skeleton",
				new HashMap<String, Integer> () {{
					put("STRENGTH", 5);
					put("DEXTERITY", 5);
					put("PERCEPTION", 5);
					put("TOUGHNESS", 5);
				}},
				new HashMap<String, Integer> () {{
					put("ATTACK", 5);
					put("DEFENSE", 5);
					put("DODGE", 5);
				}});
	}

	public Enemy getZombie() {
		return new Skeleton("Zombie",
				new HashMap<String, Integer> () {{
					put("STRENGTH", 5);
					put("DEXTERITY", 5);
					put("PERCEPTION", 5);
					put("TOUGHNESS", 5);
				}},
				new HashMap<String, Integer> () {{
					put("ATTACK", 5);
					put("DEFENSE", 5);
					put("DODGE", 5);
				}});
	}

}
