/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.bukkit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import xyz.xpulse.Xpackets.XPUtils;
import xyz.xpulse.Xpackets.DataTypes.IncommingPlayer;


/**
 * The Class CommandXPackets.
 */
public class CommandXPackets implements CommandExecutor, TabCompleter {

	/* (non-Javadoc)
	 * @see org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender, org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("xpackets.admin")) {
			if (args.length == 1) {
				if (args[0].equals("listlocal")) {
					sender.sendMessage("Configured worlds:");
					for (org.bukkit.World w : Bukkit.getWorlds()) sender.sendMessage("  " + w.getName());
					
					return true;
				} else if (args[0].equals("reload")) {
					if (Xpackets.client.isConnected()) {
						Xpackets.client.close();
					}
					Variables.incommingPlayers = new ArrayList<IncommingPlayer>();
					Config.init();
					sender.sendMessage("Reloaded xpackets.");
					return true;
				} else {
					return false;
				}
			} else if(args.length == 3) {
				if (args[0].equals("send")) {
					Player p = Bukkit.getPlayer(args[1]);
					if (p != null) {
						XPUtils.sendPlayer(p.getUniqueId(), args[2], Xpackets.client);
						return true;
					} else {
						sender.sendMessage("That player is not online.");
						return false;
					}
				} else {
					return false;
				}
			} else {
				sender.sendMessage("&7Xpackets! Version: &e" + Xpackets.instance.getDescription().getVersion());
				return true;
			}
		} else {
			sender.sendMessage("&7Xpackets! Version: &e" + Xpackets.instance.getDescription().getVersion());
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see org.bukkit.command.TabCompleter#onTabComplete(org.bukkit.command.CommandSender, org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (sender.hasPermission("xpackets.admin") && args.length == 1) {
			ArrayList<String> al = new ArrayList<String>();
			al.add("listlocal");
			al.add("reload");
			al.add("send");
			return al;
		} else if (sender.hasPermission("xpackets.admin") && args.length == 2) {
			if (args[0].equals("send")) {
				return null;
			}
		}
		return new ArrayList<String>();
		
	}

}
