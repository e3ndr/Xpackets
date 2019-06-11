/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.bungee;

import java.util.UUID;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;


/**
 * The Class BungeeUtils.
 */
public class BungeeUtils {
	
	/**
	 * Send player.
	 *
	 * @param uuid The uuid of the player to send.
	 * @param serverName The name of the server to send the player to.
	 * @deprecated Use {@link BungeeUtils#sendPlayer(UUID, ServerInfo)} instead.
	 */
	public static void sendPlayer(UUID uuid, String serverName) {
		ServerInfo target = ProxyServer.getInstance().getServerInfo(serverName);
		ProxyServer.getInstance().getPlayer(uuid).connect(target);
	}
	
	/**
	 * Send player.
	 *
	 * @param uuid The uuid of the player to send.
	 * @param info The ServerInfo of the server to send the player to.
	 */
	public static void sendPlayer(UUID uuid, ServerInfo info) {
		ProxyServer.getInstance().getPlayer(uuid).connect(info);
	}
}
