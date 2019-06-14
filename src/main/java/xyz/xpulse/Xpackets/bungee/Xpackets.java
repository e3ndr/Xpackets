/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.bungee;

import com.esotericsoftware.minlog.Log;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import xyz.xpulse.Xpackets.ServerType;
import xyz.xpulse.Xpackets.Settings;
import xyz.xpulse.Xpackets.networking.NetworkServer;


/**
 * The Class Xpackets.
 */
public class Xpackets extends Plugin {
	
	/** The instance. */
	public static Xpackets instance;
	
	/** The server. */
	public NetworkServer server;
	
	/* (non-Javadoc)
	 * @see net.md_5.bungee.api.plugin.Plugin#onEnable()
	 */
	@Override
	public void onEnable() {
		Log.set(Log.LEVEL_NONE);
		instance = this;
		Settings.serverType = ServerType.Bungee;
		BungeeConfig.init(this);
		server = new NetworkServer();
		server.start();

		if (BungeeConfig.world_switching) {
			ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandServer("server"));
		}
	}
	
	/* (non-Javadoc)
	 * @see net.md_5.bungee.api.plugin.Plugin#onDisable()
	 */
	@Override
	public void onDisable() {
		
	}
}
