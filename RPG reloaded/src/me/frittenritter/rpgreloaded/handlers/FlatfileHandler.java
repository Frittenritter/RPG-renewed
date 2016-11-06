package me.frittenritter.rpgreloaded.handlers;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import me.frittenritter.rpgreloaded.RPGReloaded;
import me.frittenritter.rpgreloaded.handlers.files.FlatFile;

public class FlatfileHandler {
	
	public static RPGReloaded plugin;
	
	//Method will give an Flatfile Object, if its not already registered it will be created.
	public static FlatFile useFile(String subfolder, String filename) {
		if(hasFileLoaded(subfolder, filename))			//If the file is already loaded, use existing file
			return getFlatFile(subfolder, filename);	//Return the file which is loaded
		return newFlatFile(subfolder, filename);		//Create, register and return new File
		
	}
	
	
	//Create and register FlatFile object to be stored in /data/subfolder/filename.yml
	private static FlatFile newFlatFile(String subfolder, String filename) {
		FlatFile flatfile = new FlatFile(subfolder, filename, plugin);	//Creation of FlatFile object with reference flatfile
		saveToHashMap(subfolder, filename, flatfile);					//Save reference to the Hashmap. Key: subfolder and filename given
		return flatfile;												//Return flatfile reference
	}
	
	
	//Method for registering a new FlatFile in the HashMap 
	private static void saveToHashMap(String subfolder, String filename, FlatFile flatfile) {
		plugin.filelist.put(subfolder + "/" + filename, flatfile);	//Use subfolder and filename for the Key
	}
	
	
	//Return a FlatFile Object from the HashMap which is saved in a subfolder of the /data folder under the given name (Without ".yml")
	private static FlatFile getFlatFile(String subfolder, String filename) {
		return plugin.filelist.get(subfolder + "/" + filename + ".yml");
	}
	
	//Method to check whether a file is already loaded as FlatFile object
	private static boolean hasFileLoaded(String subfolder, String filename) {
		if(plugin.filelist.containsKey(subfolder + "/" + filename + ".yml")) {
			return true;
		} else
			return false;
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
		plugin.filelist.remove(subfolder + "/" + filename + ".yml");
	}
	
	//Method for saving all registered FlatFiles to the Hard drive
	public static void saveAllFiles() {
		if(plugin.filelist.isEmpty())
			return;
		Iterator<Entry<String, FlatFile>> it = plugin.filelist.entrySet().iterator();
		
		if(plugin.config("main").getBoolean("debug"))
	    while (it.hasNext()) { //Loop with debug
	        Entry<String, FlatFile> pair = it.next();
	        pair.getValue().saveFile();
	        plugin.info("File " + pair.getValue().getFile().getPath() + " has been saved!");
	        it.remove();
	    }
		else while (it.hasNext()) { //Loop without debug
	        Entry<String, FlatFile> pair = it.next();
	        pair.getValue().saveFile();;
	        
	        it.remove();
	    }
	    plugin.getLogger().info("Data saved!");
	}
}
