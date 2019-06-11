/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage.KeepAlive;
import com.esotericsoftware.kryonet.FrameworkMessage.Ping;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import xyz.xpulse.Xpackets.Settings;
import xyz.xpulse.Xpackets.StaticLogger;
import xyz.xpulse.Xpackets.DataTypes.World;
import xyz.xpulse.Xpackets.bungee.BungeeConfig;
import xyz.xpulse.Xpackets.bungee.BungeeVars;
import xyz.xpulse.Xpackets.networking.Packets.NetworkPacket;
import xyz.xpulse.Xpackets.networking.Packets.PacketAddWorld;
import xyz.xpulse.Xpackets.networking.Packets.PacketSendPlayer;
import xyz.xpulse.Xpackets.networking.Packets.PacketWorldList;


/**
 * The Class NetworkServer.
 */
public class NetworkServer {
	
	/** The server. */
	private Server server;
	
	/** The ns. */
	private NetworkServer ns; // Used internally
	
	/** The clients. */
	public static ArrayList<ClientConnection> clients = new ArrayList<ClientConnection>();
	
	/**
	 * Start.
	 *
	 * @return true, if successful
	 */
	public boolean start() {
		Settings.packets.add(new PacketAddWorld());
		Settings.packets.add(new PacketSendPlayer());
		
		try {
			(new Kryo()).register(byte[].class, 0);
			server = new Server();
			server.getKryo().register(byte[].class);
			server.start();
			server.bind(BungeeConfig.port);
			server.addListener(new Listener() {
				@Override
				public void connected(Connection conn) {
					clients.add(new ClientConnection(conn.getID(), ns));
					StaticLogger.log("Client: " + conn.getID() + " connected.");
				}
				
				@Override
				public void disconnected(Connection conn) {
					int id = conn.getID();
					for (Iterator<ClientConnection> iterator = clients.iterator(); iterator.hasNext();) {
						ClientConnection c = iterator.next();
						if (c.getID() == id) {
							iterator.remove();
						}
					}
					
					for (Iterator<World> iterator = Settings.worlds.iterator(); iterator.hasNext();) {
						World w = iterator.next();
						if (w.getID() == id) {
							iterator.remove();
						}
					}
					
					StaticLogger.log("Client: " + id + " disconnected.");
				}
				
				@Override
				public void received(Connection conn, Object recieved) {
					if ((recieved instanceof Ping) || (recieved instanceof KeepAlive)) return;
					System.out.println("C: " + new String((byte[]) recieved, StandardCharsets.UTF_8));
					try {
						String rec = new String((byte[]) recieved, StandardCharsets.UTF_8);
						JSONObject jo = (JSONObject) new JSONParser().parse(rec);
						String type = (String) jo.get("Type");
						
						if (type.equals("WorldList")) {
							NetworkPacket p = new PacketWorldList();
							p.create((Object) Settings.worlds.toArray(new World[0]));
							conn.sendTCP(p.toByte());
							return;
						}
						
						for(NetworkPacket p : Settings.packets) {
							if (p.getType().equals(type)) {
								p.getParser().process(rec, conn.getID());
								break;
							}
						}
					} catch (ParseException e) {
						return;
					}
				}
			});
			this.ns = this;
			BungeeVars.networkserver = this;
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Send.
	 *
	 * @param msg the msg
	 * @param id the id
	 */
	public void send(byte[] msg, int id) {
		server.sendToTCP(id, msg);
	}
	
	/**
	 * Gets the ip.
	 *
	 * @param id the id
	 * @return the ip
	 */
	public InetSocketAddress getIP(int id) {
		for (Connection c : server.getConnections()) {
			if (c.getID() == id) return c.getRemoteAddressTCP();
		}
		return null;
	}
	
	/**
	 * Checks if is connected.
	 *
	 * @return true, if is connected
	 */
	public boolean isConnected() {
		if (server.getConnections() == null || server.getConnections().length == 0) return false;
		return true;
	}
	
	/**
	 * Close.
	 */
	public void close() {
		server.close();
		try {
			server.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
		clients = new ArrayList<ClientConnection>();
	}
}