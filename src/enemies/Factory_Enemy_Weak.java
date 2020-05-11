package enemies;

import character.Character;
import enemies.*;

import java.util.HashMap;

public class Factory_Enemy_Weak implements Abstract_Factory_Enemy {

	public Character getSkeleton() {
		return new Skeleton("Weak Skeleton",
				new HashMap<String, Integer> () {{
					
				}},
				new HashMap<String, Integer> () {{
					
				}});
	}

	public Character getZombie() {
		return new Zombie("Weak Zombie",
				new HashMap<String, Integer> () {{
					
				}},
				new HashMap<String, Integer> () {{
					
				}});
	}

}
