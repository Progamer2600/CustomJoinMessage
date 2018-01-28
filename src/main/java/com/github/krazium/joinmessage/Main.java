package com.github.krazium.joinmessage;

import org.bukkit.Bukkit;
//import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener /* , CommandExecutor */ {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		loadConfig();
		// this.getCommand("setjoinmessage").setExecutor(this);
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "CustomJoinMessage Loaded!");
	}

	public void loadConfig() {
		this.getConfig().options().copyDefaults(true);
		this.getConfig().addDefault("Join Message", "%player% has joined the game!");
		this.getConfig().addDefault("Leave Message", "%player% has left the game!");
		this.saveConfig();
	}
//TO BE ADDED
	/*
	 * public boolean onCommand(Command command, CommandSender sender, String label,
	 * String[] args) { if (command.getName().equalsIgnoreCase("setjoinmessage")) {
	 * System.out.println("WORKS"); if (args.length >= 0) { if
	 * (sender.hasPermission("customjoinmessage.admin")) {
	 * sender.sendMessage(ChatColor.GREEN + "Setting Message...");
	 * getConfig().set("Message", args); } } else { sender.sendMessage(ChatColor.RED
	 * + "Usage: /setjoinmessage <Message>"); } } return true; }
	 */

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

		String message = getConfig().getString("Join Message");
		String playerMessage = ChatColor.translateAlternateColorCodes('&',
				message.replace("%player%", event.getPlayer().getName()));
		if (message.contains("%player%")) {
			event.setJoinMessage(playerMessage);
		} else {
			event.setJoinMessage(ChatColor.RED + "Message must contain %player%!. "
					+ "Please contact an Administrator to fix the config.yml file");
		}
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {

		String message = getConfig().getString("Leave Message");
		String playerMessage = ChatColor.translateAlternateColorCodes('&',
				message.replace("%player%", event.getPlayer().getName()));
		if (message.contains("%player%")) {
			event.setQuitMessage(playerMessage);
		} else {
			event.setQuitMessage(ChatColor.RED + "Message must contain %player% "
					+ "Please contact an Administrator to fix the config.yml file");
		}

	}

}
