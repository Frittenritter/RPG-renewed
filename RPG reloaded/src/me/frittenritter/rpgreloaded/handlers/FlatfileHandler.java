package me.frittenritter.rpgreloaded.handlers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

import me.frittenritter.rpgreloaded.RPGReloaded;
import me.frittenritter.rpgreloaded.handlers.confighandler.ConfigFile;
import me.frittenritter.rpgreloaded.handlers.confighandler.FlatFile;

public class FlatfileHandler {
	//HashMap to save all active flatfiles according to their path
	private Map<String, FlatFile> flatfilelist;
	
	private RPGReloaded plugin;
	
	//Constructor
	public FlatfileHandler(RPGReloaded plugin) {
		plugin = this.plugin;
		flatfilelist = new HashMap<String, FlatFile>();
	}
	//Getter for the FileConfig Objects of the different Configs (Which allow access to YAML config options)
	public FileConfiguration getFileConfig(String subfolder, String filename) {
		return getFlatFile(subfolder, filename).getFileConfig();
	}
	//Register FlatFile object to be stored in /data/subfolder/filename.yml
	public FlatFile newFlatFile(String subfolder, String filename) {
		FlatFile flatfile = new FlatFile(subfolder, filename, plugin);
		saveToHashMap(subfolder, filename, flatfile);
		return flatfile;
	}
	
	public File getFile(ConfigFile conffile) {
		return conffile.getFile();
	}
	
	private void saveToHashMap(String subfolder, String filename, FlatFile flatfile) {
		flatfilelist.put(plugin.getDataFolder() + "data/" + subfolder + "/" + filename, flatfile);
	}
	//Return a FlatFile Object from the HashMap which is saved in a subfolder of the /data folder under the given name (Without ".yml")
	public FlatFile getFlatFile(String subfolder, String filename) {
		return flatfilelist.get(plugin.getDataFolder() + "data/" + subfolder + "/" + filename + ".yml");
	}
	//Return a FlatFile Object from the HashMap which is saved in the /data folder under the given name (Without ".yml")
	public FlatFile getFlatFile(String filename) {
		return flatfilelist.get(plugin.getDataFolder() + "data/" + filename + ".yml");
	}
	//Method for saving and unregistering FlatFiles to prevent them from wasting cache Memory
	public void unregisterFlatFile(String subfolder, String filename) {
		FlatFile flatfile = getFlatFile(subfolder, filename);
		try {
			flatfile.save(flatfile.getFile());
		} catch (IOException e) {
			plugin.getLogger().severe("Could not save file " + subfolder + "/" + filename + ".yml");
			e.printStackTrace();
		}
		flatfilelist.remove(plugin.getDataFolder()+"/data" + subfolder + "/" + filename + ".yml");
	}
	
	public void saveDefaultConfig() {
		
	}
}
