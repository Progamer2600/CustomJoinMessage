package me.progamer260.customjoinmessage;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		loadConfig();
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "CustomJoinMessage Loaded!");
	}

	public void loadConfig() {
		this.getConfig().options().copyDefaults(true);
		this.getConfig().addDefault("Message", "%player% has joined the game!");
		this.saveConfig();
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		System.out.println("WORKS");
		String message = getConfig().getString("Message");
		String playerMessage = ChatColor.translateAlternateColorCodes('&',
				message.replace("%player%", event.getPlayer().getName()));

		event.setJoinMessage(playerMessage);
	}

}
