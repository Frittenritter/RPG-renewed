package me.frittenritter.rpgreloaded.abilities;

import org.bukkit.configuration.ConfigurationSection;

import me.frittenritter.rpgreloaded.RPGReloaded;
import me.frittenritter.rpgreloaded.handlers.ConfigHandler;

public class Ability {
	
	protected RPGReloaded plugin;
	String name;				//Display name of the Ability (Can be changed in config without data loss)
	double xp;					//Current xp (of the current level, not total)
	double neededxp;			//XP needed for next lvl
	int level;					//Curren level (Ability objects are created per player)
	int maxLevel;				//Maximum level defined in config
	ConfigurationSection cs;	//The section of ability config which is for this Ability
	
	//Constructor for initial creation of abilities
	public Ability(String configSectionName) {
		plugin = ConfigHandler.plugin;
		cs = plugin.config("abilities").getConfigurationSection("abilities." + configSectionName);
		this.name = cs.getString("display-name");
		maxLevel = plugin.config("abilities").getInt("main.maxLevel");
		neededxp = cs.getDouble("needed-experience.first-level");
	}
	
	//Constructor for loading existing Abilities from FlatFile with set values
	public Ability(String configSectionName, int level, double xp) {
		plugin = ConfigHandler.plugin;
		cs = plugin.config("abilities").getConfigurationSection("abilities." + configSectionName);
		this.name = cs.getString("display-name");
		maxLevel = plugin.config("abilities").getInt("main.maxLevel");
		this.level = level;
		this.xp = xp;
		neededxp = cs.getDouble("needed-experience.first-level");
		for(int i = 1; i < level; i++)
			neededxp = neededxp * cs.getDouble("needed-experience.level-modificator-percent");
		
	}
	
	//Method for refreshing level Status. Should be called on every change in experience.
	void refreshLevel() {
		while(xp > neededxp) {
			level++;
			xp = xp - neededxp;
			neededxp = neededxp * cs.getDouble("needed-experience.level-modificator-percent");
		}
		
	}
	
	void setXp(double xp) {
		this.xp = xp;
		refreshLevel();
	}
	void addXp(double xpAdded) {
		xp = xp + xpAdded;
		refreshLevel();
	}
	double getXp() {
		return xp;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getMaxLevel() {
		return maxLevel;
	}
	
	public int getAbilityPoints() {
		//Return basic ability points + level * ability points per level (-1)
		return (plugin.config("abilities").getInt("main.ability-points-basic") + (level-1) * plugin.config("abilities").getInt("main.ability-points-gain"));
	}
	
	public String getName() {
		return name;
	}
	
	public void setupListeners() {
		//Method to be used by subclasses on init
	}
	
	
	
	public double getXpNeeded(int level) {
		return neededxp;
	}
	
}
