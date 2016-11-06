package me.frittenritter.rpgreloaded;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CE_Generic implements CommandExecutor {
	RPGReloaded plugin;
	public CE_Generic(RPGReloaded plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length < 1)					//At least one argument needed
			return true;
		if(!(sender instanceof Player))		//Sender has to be a player, else abort
			return true;
		
		Player player = (Player) sender;
		if(args[0].equalsIgnoreCase("getitem")) {
			if(args.length == 2 || args.length == 3) {				//Args length has to be 2 or 3: getitem, [name], <amount>
				if(!plugin.items.containsKey(args[1])) {
					sender.sendMessage("Gegenstand existiert nicht.");
					return true;
				}
				Inventory inv = player.getInventory();
				if(inv.firstEmpty() == -1)  {
					sender.sendMessage("Inventarplatz reicht nicht aus.");
					return true;
				}
				if(args.length == 2) {
					inv.addItem(plugin.items.get(args[1]).getItemStack());
					sender.sendMessage("Item " + plugin.items.get(args[1]).getName() + " erhalten.");
				}
				else {
					ItemStack stack = plugin.items.get(args[1]).getItemStack();
					stack.setAmount(Integer.parseInt(args[2]));
					inv.addItem(stack);

					sender.sendMessage("Item " + args[1] + " " + args[2] + " mal erhalten.");
				}
				return true;
			} else {
				sender.sendMessage("1 oder 2 Argumente benötigt: /rpgr getitem [Item], <Menge>");
				return true;
			}
		}
		return false;		//Command has not been executed properly
	}

}
