package me.frittenritter.rpgreloaded.handlers.files;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;

import me.frittenritter.rpgreloaded.RPGReloaded;

public class ConfigFile extends FileConfiguration {
	File configFile;
	FileConfiguration config;
	RPGReloaded plugin;
	String configname;
	
	public ConfigFile(String configname,RPGReloaded plugin) {
		this.plugin = plugin;
		this.configname = configname;
		configFile = new File("plugins/RPG_Reloaded/config/" + configname + ".yml");
		reloadConfig();
		saveDefaultConfig();
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	public File getFile() {
		return configFile;
	}
	public void saveConfig() {
		if (config == null || configFile == null) {
	        return;
	    }
	    try {
	        getConfig().save(configFile);
	    } catch (IOException ex) {
	        plugin.getLogger().severe("Could not save config to " + configFile);
	    }
	}
	
	public void saveDefaultConfig() {
		//Save default config
	    if (!configFile.exists()) {            
	         plugin.saveResource("config/" + configname + ".yml", false);
	     }
	}
	
	public void reloadConfig() {
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
	    } else plugin.getLogger().severe("The configuration " + configname + " within the .jar could not be found!");
	    
	}
	
	public String getString(String path) {
		return config.getString(path);
	}
	public boolean getBoolean(String path) {
		return config.getBoolean(path);
	}
	public int getInt(String path) {
		return config.getInt(path);
	}
	public double getDouble(String path) {
		return config.getDouble(path);
	}

	@Override
	protected String buildHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadFromString(String arg0) throws InvalidConfigurationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String saveToString() {
		// TODO Auto-generated method stub
		return null;
	}
}
