package me.frittenritter.rpgreloaded.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.frittenritter.rpgreloaded.RPGReloaded;


public class CustomItem {
	protected ItemStack item;				//ItemStack Object for the Item
	RPGReloaded plugin;
	ArrayList<String> lore;

	//									IMPORTANT
	//The item Lore will NOT be initialized by Creating an object without given lore.
	//It has to be added using the addLineToLore method after initializing the Object.
	//This Class also is designed as a Superclass, so subclasses have to attach a lore.
	//									IMPORTANT
	
	public CustomItem(RPGReloaded plugin, String name, Material mat) {
		this.plugin = plugin;
		item = new ItemStack(mat);											//Item stack will be initialized by creating a new itemStack
		item = ItemManager.setItemMeta(item, name);							//Display name of item is set
		lore = new ArrayList<String>();										//Lore initialization
	}
	
	public CustomItem(RPGReloaded plugin, String name, Material mat, short durability) {
		this.plugin = plugin;
		item = new ItemStack(mat);											//Item stack will be initialized by creating a new itemStack
		item.setDurability(durability);										//Set item durability, e.g. for bonemeal
		item = ItemManager.setItemMeta(item, name);							//Display name of item is set
		lore = new ArrayList<String>();										//Lore initialization
	}

	public ItemStack getItemStack() {
		return item;
	}
	
	//Method for removing line from lore (If existing, else just skip)
	public void removeLineFromLore(String entry) {
		if(getItemMeta().getLore().contains(entry))		//Only if lore contains given line
			getItemMeta().getLore().remove(entry);		//Remove given line from Lore
	}
	//Method for getting Line of lore, if line is not set return null
	public String getLoreLine(int index) {
		return getItemMeta().getLore().get(index);
	}
	//Method for setting line of lore
	public void setLoreLine(int line, String value) {
		int index = line - 1;									//The index is zero-based, so its 1 less than the line which to change
		if(item.getItemMeta().hasLore())						//Check whether item has a lore
			lore = (ArrayList<String>) getItemMeta().getLore();	//If it has lore, use existing lore instead of a new List
		lore.add(index, value);									//Setup the List
		ItemManager.setItemMeta(item, lore);					//Set the List as lore for the item
	}
	
	//Simplified Method that Mimics the setItemMeta Method, will set given list as lore.
	public void setLore(List<String> lore) {
		ItemManager.setItemMeta(item, lore);
	}
	
	//Remove all lines of lore
	public void resetLore() {
		List<String> emptyLore = new ArrayList<String>();	//Create empty List
		ItemManager.setItemMeta(item, emptyLore);			//Set Empty List as lore
	}
	//Returns display name of the item
	public String getName() {
		return getItemMeta().getDisplayName();
	}
	//Returns the item Metae
	public ItemMeta getItemMeta() {
		return ItemManager.getItemMeta(item);
	}
	
	//Enchantment management methods
	//Whether the item has the given ench
	public boolean hasEnchantment(Enchantment ench) {
		return getItemMeta().hasEnchant(ench);
	} 
	//Add enchantment to the item
	public void addEnchant(Enchantment ench, int level, boolean ignoreMaxEnchantLevel) {
		getItemMeta().addEnchant(ench, level, ignoreMaxEnchantLevel);
	}
}
