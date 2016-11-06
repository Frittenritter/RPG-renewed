package me.frittenritter.rpgreloaded.items;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.frittenritter.rpgreloaded.RPGReloaded;
import me.frittenritter.rpgreloaded.handlers.ConfigHandler;
import net.md_5.bungee.api.ChatColor;

public class ItemManager {
	
	public static void registerItems() {
		RPGReloaded plugin = ConfigHandler.plugin;
		FileConfiguration ic = plugin.config("items");
		if(!ic.contains("items.1"))			//If there are no keys in the section items
			return;
		
		for(String key : ic.getConfigurationSection("items").getKeys(false)) {
			String name = ic.getString("items." + key + ".name");
			String desc = ic.getString("items." + key + ".desc");
			Material mat = Material.getMaterial(ic.getString("items." + key + ".material").toUpperCase());
			
			String type = ic.getString("items." + key + ".type");
			short durability = 0;
			if(ic.contains("items." + key + ".durability"))
				durability = (short) ic.getInt("items." + key + ".durability");
			CustomItem ci = null;
			
			//All the information have been collected.
			//Now create an CustomItem object depending on the type specified.
			if(type.equals("generic")) {
				if(durability == 0)
					ci = new CustomGenericItem(ConfigHandler.plugin, name, desc, mat);
				else
					ci = new CustomGenericItem(ConfigHandler.plugin, name, desc, mat, durability);
			}
			if(ci != null)
				plugin.items.put(name, ci);
			else
				plugin.severe("The item could not be initialized: " + name);
		}
	}
	
	public static ItemStack setItemMeta(ItemStack stack, String name, List<String> desc) {
		ItemMeta m = stack.getItemMeta();
	    m.setDisplayName(ChatColor.RESET + name);
	    m.setLore(desc);
	    stack.setItemMeta(m);

	    return stack;
	}
	
	public static ItemStack setItemMeta(ItemStack stack, List<String> desc) {
		ItemMeta m = stack.getItemMeta();
	    m.setLore(desc);
	    stack.setItemMeta(m);

	    return stack;
	}
	
	public static ItemStack setItemMeta(ItemStack stack, String name) {
		ItemMeta m = stack.getItemMeta();
	    m.setDisplayName(ChatColor.RESET + name);
	    stack.setItemMeta(m);

	    return stack;
	}
	
	public static ItemMeta getItemMeta(ItemStack stack) {
		ItemMeta m = stack.getItemMeta();
	    return m;
	}
	
	//Add item to given inventory. Returns false if adding item Fails.
	public static boolean addItemToInv(Inventory inv, ItemStack item) {
		int freeSlot = inv.firstEmpty();
		if(freeSlot == -1)
			return false;
		inv.addItem(item);
		return true;
	}
	
	public static boolean addItemToInv(Inventory inv, ItemStack item, int slot) {
		if(inv.getItem(slot) != null)		//If the Specified slot is taken 
			return false;
		inv.setItem(slot, item);
		return true;
	}
}
