package me.frittenritter.rpgreloaded.entities;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.frittenritter.rpgreloaded.RPGReloaded;
import me.frittenritter.rpgreloaded.abilities.Ability;
import me.frittenritter.rpgreloaded.handlers.FlatfileHandler;
import me.frittenritter.rpgreloaded.handlers.files.FlatFile;

public class RPGPlayer {
	Player player;
	FlatFile playerFile;
	RPGReloaded plugin;
	//HashMap "abilities" Represents <Ability-name, xp>
	Map<String, Ability> abilities;
	
	public RPGPlayer(RPGReloaded plugin, UUID uuid) {
		this.plugin = plugin;
		player = Bukkit.getPlayer(uuid);
		playerFile = FlatfileHandler.useFile("players", player.getName());
	}
	
	public Ability getAbility(String abilityname) {
		return abilities.get(abilityname);
	}
	
}
