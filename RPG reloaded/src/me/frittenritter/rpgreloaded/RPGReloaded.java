package me.frittenritter.rpgreloaded;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.frittenritter.rpgreloaded.handlers.ConfigHandler;
import me.frittenritter.rpgreloaded.handlers.FlatfileHandler;
import me.frittenritter.rpgreloaded.handlers.files.FlatFile;
import me.frittenritter.rpgreloaded.items.CustomItem;
import me.frittenritter.rpgreloaded.items.ItemManager;

public class RPGReloaded extends JavaPlugin {
	public Map<String, FlatFile> filelist;
	public Map<String, FileConfiguration> configlist;
	public Map<String, CustomItem> items;
	public FileConfiguration lang;
	@Override
	public void onEnable() {
		info("Loading RPG Reloaded by Frittenritter");
		
		ConfigHandler.plugin = this;
		FlatfileHandler.plugin = this;
		configlist = new HashMap<String, FileConfiguration>();
		filelist = new HashMap<String, FlatFile>();
		items = new HashMap<String, CustomItem>();
		
		ConfigHandler.initializeConfigs();					//Initialize config references
		ItemManager.registerItems();						//Initialize Custom Items from item-config
		registerCommands();									//Initialize CommandExecutors
		info("RPG Reloaded loaded successfully!");
	}
	@Override
	public void onDisable() {
		FlatfileHandler.saveAllFiles();
		
	}
	//TODO:private void registerListeners()
	
	//Register CommandExecutors
	private void registerCommands() {
		this.getCommand("rpgr").setExecutor(new CE_Generic(this));
	}
	
	public FileConfiguration config(String name) {
		if(configlist.get(name) == null) {
			info("The configuration " + name + " doesn't exist!");
			return null;
		}
			
		return configlist.get(name);
	}
	public void info(String msg) {
		this.getLogger().info(msg);
	}
	public void severe(String msg) {
		this.getLogger().severe(msg);
	}
	public void warning(String msg) {
		this.getLogger().warning(msg);
	}
}
