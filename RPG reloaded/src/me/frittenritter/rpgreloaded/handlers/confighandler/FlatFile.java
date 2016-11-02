package me.frittenritter.rpgreloaded.handlers.confighandler;

import java.io.File;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.frittenritter.rpgreloaded.RPGReloaded;

public class FlatFile extends FileConfiguration  {
	private File file;
	FileConfiguration fileconfig;
	RPGReloaded plugin;
	String filename;
	
	public FlatFile(String subfolder, String filename, RPGReloaded plugin) {
		this.plugin = plugin;
		this.filename = filename;
		if(subfolder != null) 
			file = new File(plugin.getDataFolder() +"data/" + subfolder +"/", filename + ".yml"); 
		else 
			file = new File(plugin.getDataFolder() +"data/", filename + ".yml");
		
		fileconfig = YamlConfiguration.loadConfiguration(file);
	}
	
	public FileConfiguration getFileConfig() {
		return fileconfig;
	}
	public File getFile() {
		return file;
	}
	
	
	public void set(String branch, String entry) {
		fileconfig.set(branch, entry);
	}
	public void set(String branch, boolean entry) {
		fileconfig.set(branch, entry);
	}
	public void set(String branch, int entry) {
		fileconfig.set(branch, entry);
	}
	public void set(String branch, double entry) {
		fileconfig.set(branch, entry);
	}
	public String getString(String path) {
		return fileconfig.getString(path);
	}
	public boolean getBoolean(String path) {
		return fileconfig.getBoolean(path);
	}
	public int getInt(String path) {
		return fileconfig.getInt(path);
	}
	public double getDouble(String path) {
		return fileconfig.getDouble(path);
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
