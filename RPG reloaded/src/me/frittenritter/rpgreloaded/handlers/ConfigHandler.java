package me.frittenritter.rpgreloaded.handlers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

import me.frittenritter.rpgreloaded.RPGReloaded;
import me.frittenritter.rpgreloaded.handlers.confighandler.ConfigFile;

public class ConfigHandler {
	private Map<String, ConfigFile> configlist;
	private RPGReloaded plugin;
	public ConfigHandler(RPGReloaded plugin) {
		plugin = this.plugin;
		configlist = new HashMap<String, ConfigFile>();
	}
	//Getter for the FileConfig Objects of the different Configs (Which allow access to YAML config options)
	public FileConfiguration getConfig(String filename) {
		return configlist.get(filename).getConfig();
	}
	//Config initialization by name
	public ConfigFile newConfig(String filename) {
		ConfigFile conffile = new ConfigFile(filename, plugin);
		configlist.put(filename, conffile);
		
		return conffile;
	}
	//Getter for Config Objects saved in the HashMap
	public ConfigFile getConfigFile(String name) {
		return configlist.get(name);
	}
	
	public File getFile(ConfigFile conffile) {
		return conffile.getFile();
	}
	
	public void saveConfig(FileConfiguration fileconf) {
		
	}
	
	public void reloadConfig(File configfile) {
		
	}
	
	public void getConfig() {
		
	}
	
	public void saveDefaultConfig() {
		
	}
	
}
