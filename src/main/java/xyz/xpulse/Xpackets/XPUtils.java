/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import xyz.xpulse.Xpackets.DataTypes.NetworkPlayer;
import xyz.xpulse.Xpackets.DataTypes.World;
import xyz.xpulse.Xpackets.bungee.BungeeUtils;
import xyz.xpulse.Xpackets.networking.NetworkClient;
import xyz.xpulse.Xpackets.networking.NetworkServer;
import xyz.xpulse.Xpackets.networking.Packets.NetworkPacket;
import xyz.xpulse.Xpackets.networking.Packets.PacketIncommingPlayer;
import xyz.xpulse.Xpackets.networking.Packets.PacketSendPlayer;


/**
 * The Class XPUtils.
 */
public class XPUtils {
	
	/**
	 * Send player.
	 *
	 * @param uuid The UUID of the player to send.
	 * @param worldName Name of the configured world to send the player to
	 * @param server The NetworkServer
	 * @return true if successful, false if otherwise.
	 */
	public static boolean sendPlayer(UUID uuid, String worldName, NetworkServer server) {
		World world = getWorldByName(worldName);
		if (world == null) return false;
		
		NetworkPacket packet = new PacketIncommingPlayer();
		packet.create(uuid, worldName);
		server.send(packet.toByte(), world.getID());
		
		Map<String, ServerInfo> servers = ProxyServer.getInstance().getServers();
		for(ServerInfo si : servers.values()) {
			if(si.getAddress().toString().split("/")[1].split(":")[0].equals(world.getIP())) {
				BungeeUtils.sendPlayer(uuid, si);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Send player.
	 *
	 * @param uuid The UUID of the player to send.
	 * @param worldName Name of the configured world to send the player to
	 * @param client the client
	 * @return true if successful, false if otherwise.
	 */
	public static boolean sendPlayer(UUID uuid, String worldName, NetworkClient client) {
		NetworkPacket sp = new PacketSendPlayer();
		sp.create(uuid, worldName);
		client.send(sp.toByte());
		return true;
	}
	
	/**
	 * Gets the world by name.
	 *
	 * @param name Name of the world
	 * @return the {@code World}, null if otherwise
	 */
	public static World getWorldByName(String name) {
		for (World w : Settings.worlds) {
			if (w.getName().equalsIgnoreCase(name)) return w;
		}
		return null;
	}
	
	/**
	 * Gets the players.
	 *
	 * @return a list of {@code NetworkPlayer}s
	 */
	public static List<NetworkPlayer> getPlayers() {
		// TODO return list of NetworkPlayers
		return null;
	}
	
	/**
	 * Configured worlds.
	 *
	 * @return A list of {@code World}s
	 */
	public static List<World> configuredWorlds() {
		return Settings.worlds;
	}
}
