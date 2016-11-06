package me.frittenritter.rpgreloaded.abilities;

public interface Ability {
	
	void refreshLevel();
	
	void setXp(double xp);
	void addXp(double xpAdded);
	double getXp();
	
	public int getLevel();
	
	public int getMaxLevel();
	public int setMaxLevel();
	
	public void setAbilityPoints(int points);
	public void addAbilityPoints(int addedPoints);
	public void removeAbilityPoints(int removedPoints);
	public double getAbilityPoints();
	
	public String getName();
	
	
	
	public double getXpNeeded(int level);
	
}
