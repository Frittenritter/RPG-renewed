package me.frittenritter.rpgreloaded.handlers;

import java.io.File;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;

import me.frittenritter.rpgreloaded.RPGReloaded;

public class ConfigHandler {
	public static RPGReloaded plugin;
	
	public static void initializeConfigs() {
		registerConfig("main");
		registerConfig("abilities");
		registerConfig("items");
		registerConfig("lang");
	}
	
	
	//Register a Configuration with a name
	private static void registerConfig(String configname) {
		File configFile;
		configFile = new File("plugins/RPG_Reloaded/config/" + configname + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
		if(!configFile.exists()) {
			setDefaults(config, configFile, configname);
			saveDefaultConfig(configFile, configname);
		}
		plugin.configlist.put(configname, config);
	}
	
	private static void setDefaults(FileConfiguration config, File configFile, String configname) {
		config = YamlConfiguration.loadConfiguration(configFile);

	    // Look for defaults in the jar
	    Reader defConfigStream;
	    if(plugin.getResource("config/" + configname + ".yml") != null) {
	    	try {
				defConfigStream = new InputStreamReader(plugin.getResource("config/" + configname + ".yml"), "UTF8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return;
			}
	    	YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	    	config.setDefaults(defConfig);
	    } else plugin.getLogger().severe("The configuration " + configname + ".yml within the .jar could not be found!");
	}
	
	private static void saveDefaultConfig(File configFile, String configname) {
	    if (!configFile.exists()) {
	         plugin.saveResource("config/" + configname + ".yml", false);
	     }
	}
	
}
