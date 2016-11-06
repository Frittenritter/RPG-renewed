package me.frittenritter.rpgreloaded.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import me.frittenritter.rpgreloaded.RPGReloaded;

public class CustomGenericItem extends CustomItem {
	
	
	//Class for creating items needed for Crafting
	public CustomGenericItem(RPGReloaded plugin, String name, String desc, Material mat) {
		super(plugin, name, mat);
		setLoreLine(1, ChatColor.GOLD.toString() + ChatColor.ITALIC.toString() + plugin.config("items").getString("generic-item-identifier"));
		setDescription(desc);
		return;
	}
	
	public CustomGenericItem(RPGReloaded plugin, String name, String desc, Material mat, short durability) {
		super(plugin, name, mat, durability);
		setLoreLine(1, ChatColor.GOLD.toString() + ChatColor.ITALIC.toString() + plugin.config("items").getString("generic-item-identifier"));
		setDescription(desc);
		return;
	}
	
	public void setDescription(String desc) {
		setLoreLine(2, ChatColor.GRAY.toString() + desc);
	}



}
