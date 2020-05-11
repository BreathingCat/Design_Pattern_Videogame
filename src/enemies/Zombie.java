package enemies;

import java.util.HashMap;
import java.util.Map;

import armor.Armor;
import armor.Factory_Heavy_Armor;
import character.Character;
import weapon.Mace;

public class Zombie extends Enemy {

	public Zombie(String name, Map<String, Integer> attributes,
			Map<String, Integer> combat_skills) {
		super(name, "ZOMBIE", new Mace(), attributes, combat_skills,
				new HashMap<String, Armor> () {{
					Factory_Heavy_Armor fac = new Factory_Heavy_Armor();
					put("HEAD", fac.getHeadArmor());
					put("TORSO", fac.getTorsoArmor());
					put("LEG", fac.getLegArmor());
				}});
	}

	@Override
	public void execute_Template(Character[] player_characters) {
		System.out.println(this.name + "'s turn starts!");
		
		if(this.current_hp > 75) {
			System.out.println(this.name + " has a strong binding to the dark forces. Heals for 4 hp!");
			this.heal(4);
		} else if(this.current_hp < 25 && this.isAlive()) {
			System.out.println(this.name + " binding is weakened! Unit degrades over time for 4 hp!");
			this.damage(4);
		}
		
		if(this.isAlive()) {
			
		} else {
			System.out.println(this.name + " is dead!");
		}
		
	}


}
