package armor;

public class Factory_Medium_Armor extends Abstract_Factory_Armor {

	private final static String stats_file = "src/armor/MEDIUM_ARMOR.txt";

	public Armor getHeadArmor() {
		return new Head_Armor("medium", 
				// Attributes
				this.getArmorStatsFromFile("HEAD_ARMOR", "ATTRIBUTES", this.stats_file),
				// Combat skills
				this.getArmorStatsFromFile("HEAD_ARMOR", "COMBAT_SKILLS", this.stats_file)
					);
	}

	public Armor getTorsoArmor() {
		// Reads JSON file for all attributes of the heavy torso armor
		return new Torso_Armor("medium", 
				// Attributes
				this.getArmorStatsFromFile("TORSO_ARMOR", "ATTRIBUTES", this.stats_file),
				// Combat skills
				this.getArmorStatsFromFile("TORSO_ARMOR", "COMBAT_SKILLS", this.stats_file)
				);
	}

	public Armor getLegArmor() {
		// Reads JSON file for all attributes of the heavy leg armor
		return new Leg_Armor("medium", 
				// Attributes
				this.getArmorStatsFromFile("LEG_ARMOR", "ATTRIBUTES", this.stats_file),
				// Combat skills
				this.getArmorStatsFromFile("LEG_ARMOR", "COMBAT_SKILLS", this.stats_file)
				);
	}

}
