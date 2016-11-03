package me.frittenritter.rpgreloaded;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.frittenritter.rpgreloaded.handlers.ConfigHandler;
import me.frittenritter.rpgreloaded.handlers.FlatfileHandler;

public class RPGReloaded extends JavaPlugin {
	ConfigHandler confighandler;
	FlatfileHandler flatfilehandler;
	FileConfiguration mainconfig;
	@Override
	public void onEnable() {
		this.getLogger().info("Loading RPG Reloaded by Frittenritter");
		confighandler = new ConfigHandler(this);
		flatfilehandler = new FlatfileHandler(this);
		mainconfig = confighandler.newConfig("main");
		
		
	}
	@Override
	public void onDisable() {
		confighandler.saveAllConfigs();
		flatfilehandler.saveAllFiles();
		
	}
	//TODO:private void registerListeners()
	
}
