package me.frittenritter.rpgreloaded;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.frittenritter.rpgreloaded.handlers.ConfigHandler;

public class RPGReloaded extends JavaPlugin {
	ConfigHandler confighandler;
	FileConfiguration mainconfig;
	@Override
	public void onEnable() {
		confighandler = new ConfigHandler(this);
		mainconfig = confighandler.getConfig("main");
		
	}
	@Override
	public void onDisable() {
		
	}
	
	private void registerListeners() {
		
	}
	
}
