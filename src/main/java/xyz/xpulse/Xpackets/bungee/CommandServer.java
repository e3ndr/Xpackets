/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.bungee;

import java.util.ArrayList;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import xyz.xpulse.Xpackets.Settings;
import xyz.xpulse.Xpackets.XPUtils;
import xyz.xpulse.Xpackets.DataTypes.World;
import xyz.xpulse.Xpackets.networking.Packets.NetworkPacket;


/**
 * The Class CommandServer.
 */
public class CommandServer extends Command implements TabExecutor {

	/**
	 * Instantiates a new command server.
	 *
	 * @param name the name
	 */
	public CommandServer(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see net.md_5.bungee.api.plugin.Command#execute(net.md_5.bungee.api.CommandSender, java.lang.String[])
	 */
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("Xpackets.admin")) {
				if (BungeeVars.networkserver.isConnected()) {
					BungeeVars.networkserver.close();
				}
				Settings.packets = new ArrayList<NetworkPacket>();
				Settings.worlds = new ArrayList<World>();
				BungeeVars.networkserver.start();
				sender.sendMessage(new TextComponent("Reloaded xpackets."));
				return;
			}
			XPUtils.sendPlayer(((ProxiedPlayer) sender).getUniqueId(), args[0], BungeeVars.networkserver);
		} else {
			sender.sendMessage(new TextComponent("You must specify the world first!"));	
		}
	}

	/* (non-Javadoc)
	 * @see net.md_5.bungee.api.plugin.TabExecutor#onTabComplete(net.md_5.bungee.api.CommandSender, java.lang.String[])
	 */
	@Override
	public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
		if (args.length == 1) {
			ArrayList<String> list = new ArrayList<String>();
			for (World w : Settings.worlds) {
				list.add(w.getName());
			}
			return list;
		}
		return new ArrayList<String>();
	}

}
