/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.bukkit;

import java.util.Collection;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.xpulse.Xpackets.ServerType;
import xyz.xpulse.Xpackets.Settings;
import xyz.xpulse.Xpackets.XPUtils;
import xyz.xpulse.Xpackets.DataTypes.IncommingPlayer;
import xyz.xpulse.Xpackets.networking.NetworkClient;

/**
 * The Class Xpackets.
 */
public class Xpackets extends JavaPlugin {
	
	/** The instance. */
	public static Xpackets instance;
	
	/** The client. */
	public static NetworkClient client;
	
	/* (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
	@Override
	public void onEnable() {
		instance = this;
		Settings.serverType = ServerType.Bukkit;
		Config.init();
		this.getCommand("xpackets").setExecutor(new CommandXPackets());
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				if (Variables.incommingPlayers.size() == 0) return;
				Collection<? extends Player> players = Bukkit.getOnlinePlayers();
				for (Player p : players) {
					for (Iterator<IncommingPlayer> iterator = Variables.incommingPlayers.iterator(); iterator.hasNext();) {
						IncommingPlayer ip = iterator.next();
						if (ip.getUuid().toString().equals(p.getUniqueId().toString())) {
							World w = Bukkit.getWorld(WorldConfig.getLocalName(ip.getWorld()));
							p.teleport(w.getSpawnLocation());
							System.out.println("Sent " + p.getName() + " to " + ip.getWorld() + "(" + XPUtils.getWorldByName(ip.getWorld()).getFormattedName() + "§r)");
							p.sendMessage("Sent you to " + XPUtils.getWorldByName(ip.getWorld()).getFormattedName());
							iterator.remove();
						}
					}
				}
			}
		}, 100, 10);
		
	}
	
	/* (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onDisable()
	 */
	@Override
	public void onDisable() {
		
	}
}
