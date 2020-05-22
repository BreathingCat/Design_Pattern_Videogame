package main;

import java.util.*;
import races.*;
import character.Character;
import weapon.*;
import armor.*;
import enemies.*;

public class main_game {
	
	public List<Enemy> generateEnemies(int difficulty) {
		Abstract_Factory_Enemy fac = null;
		switch(difficulty) {
		case 1:
			fac = new Factory_Enemy_Weak();
			break;
		case 2:
			fac = new Factory_Enemy_Normal();
			break;
		case 3:
			fac = new Factory_Enemy_Veteran();
			break;
		}
		
		List<Enemy> enemies = new ArrayList<Enemy>();
		
		enemies.add(fac.getZombie());
		enemies.add(fac.getSkeleton());
		enemies.add(fac.getSkeleton());
		
		return enemies;
	}
		
	public boolean teamAlive(List<Character> player_characters) {
		for (Character item: player_characters) {
			if(!item.isAlive()) {
				return false;
			}
		} return true;
	}
	
	public boolean enemyTeamAlive(List<Enemy> enemies) {
		for (Character item: enemies) {
			if(!item.isAlive()) {
				return false;
			}
		} return true;
	}

	public static void main(String[] args) {
		
		System.out.println("Welcome to the game! Lets create our 4 characters");
		Scanner scanner = new Scanner(System.in);
		
		List<Character> player_characters = new ArrayList<Character> ();
		
		for (int i = 0; i < 4; i++) {
			// Name
			System.out.println("Input name");
			String name = "";
			while(name.isEmpty()) {
				name = scanner.nextLine();
			}
					
			// Stats
			HashMap<String, Integer> attr = new HashMap<String, Integer> () {{
				Scanner attr_scanner = new Scanner(System.in);
				System.out.println("Introduce STRENGTH");
				put("STRENGTH", attr_scanner.nextInt());
				System.out.println("Introduce DEXTERITY");
				put("DEXTERITY", attr_scanner.nextInt());
				System.out.println("Introduce TOUGHNESS");
				put("TOUGHNESS", attr_scanner.nextInt());
				System.out.println("Introduce PERCEPTION");
				put("PERCEPTION", attr_scanner.nextInt());
			}};
							
			HashMap<String, Integer> combat_skills = new HashMap<String, Integer> () {{
				Scanner attr_scanner = new Scanner(System.in);
				System.out.println("Introduce ATTACK");
				put("ATTACK", attr_scanner.nextInt());
				System.out.println("Introduce DEFENSE");
				put("DEFENSE", attr_scanner.nextInt());
				System.out.println("Introduce DODGE");
				put("DODGE", attr_scanner.nextInt());
			}};
				
			// Weapon			
			System.out.println("Select weapon for " + name);
			System.out.println("1. BattleHammer");
			System.out.println("2. Crossbow");
			System.out.println("3. Dagger");
			System.out.println("4. LongSword");
			System.out.println("5. Mace");
			System.out.println("6. Spear");
			
			Weapon weapon = null;
				
			switch(scanner.nextInt()) {
			case 1:
				weapon = new BattleHammer();
				break;
			case 2:
				weapon = new Crossbow();
				break;
			case 3:
				weapon = new Dagger();
				break;
			case 4:
				weapon = new LongSword();
				break;
			case 5:
				weapon = new Mace();
				break;
			case 6:
				weapon = new Spear();
				break;
			default:
				System.out.println("I dont wanna do error checking dont be evil. You dont have a weapon and the game will crash. Shame on you.");
			}
			
			// Equipment
			HashMap<String, Armor> equipment = new HashMap<String, Armor>();
			System.out.println("Select equipment");
			System.out.println("1. Heavy Armor");
			System.out.println("2. Medium Armor");
			System.out.println("3. Light armor");
			
			Abstract_Factory_Armor fac = null;
			
			switch(scanner.nextInt()) {
			case 1:
				fac = new Factory_Heavy_Armor();
				break;
			case 2:
				fac = new Factory_Medium_Armor();
				break;
			case 3:
				fac = new Factory_Light_Armor();
				break;
			default:
				System.out.println("You are a disgrace. Die.");
			}
			
			equipment.put("HEAD", fac.getHeadArmor());
			equipment.put("TORSO", fac.getTorsoArmor());
			equipment.put("LEG", fac.getLegArmor());			
				
			// Race
			System.out.println("Select race");
			System.out.println("1. Paladin");
			System.out.println("2. Cleric");
			System.out.println("3. Soldier");
			System.out.println("4. Ranger");
	
			switch(scanner.nextInt()) {
			case 1:
				player_characters.add(new Paladin(name, weapon, attr, combat_skills, equipment));
				break;
			case 2:
				player_characters.add(new Cleric(name, weapon, attr, combat_skills, equipment));
				break;
			case 3:
				player_characters.add(new Soldier(name, weapon, attr, combat_skills, equipment));
				break;
			case 4:
				player_characters.add(new Ranger(name, weapon, attr, combat_skills, equipment));
				break;
			}
				
			System.out.println("Character " + name + " created!");
			System.out.println(player_characters.toString());
			
		}
		
		int difficulty = 1;
		
		System.out.println("Select difficulty: 1,2,3?");
		
		difficulty = scanner.nextInt();
		
		List<Enemy> enemies = new main_game().generateEnemies(difficulty);
		
		System.out.println(enemies.toString());

		while(new main_game().teamAlive(player_characters) && new main_game().enemyTeamAlive(enemies)) {
			// Player starts
			for(Character player_char : player_characters) {
				if(player_char.isAlive()) {
					if(player_char.state.get("STUN").getState()) {
						System.out.println(player_char.name + " is stunned! Turn is skipped!");
					}
					else {
						System.out.println("Its " + player_char.name + "'s turn!");
						System.out.println("Choose who to attack!");
						for(int i = 0; i < enemies.size(); i++) {
							System.out.println((i + 1) + ". " + enemies.get(i).name);
						}
						
						int enemy_pos = scanner.nextInt();
						
						player_char.attack(enemies.get(enemy_pos - 1));
						
						if(!enemies.get(enemy_pos - 1).isAlive()) {
							System.out.println(enemies.get(enemy_pos - 1).name + " is dead!");
							enemies.remove(enemy_pos - 1);
						}
					}
					
				}
				
			}
			
			for(Enemy enemy : enemies) {
				enemy.execute_Template(player_characters);
			}
		}
		
		System.out.println("Game over!");
					
	}

}
