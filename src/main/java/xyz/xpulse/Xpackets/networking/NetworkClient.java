/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.networking;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage.KeepAlive;
import com.esotericsoftware.kryonet.FrameworkMessage.Ping;
import com.esotericsoftware.kryonet.Listener;

import xyz.xpulse.Xpackets.Settings;
import xyz.xpulse.Xpackets.networking.Packets.NetworkPacket;
import xyz.xpulse.Xpackets.networking.Packets.PacketIncommingPlayer;
import xyz.xpulse.Xpackets.networking.Packets.PacketWorldList;


/**
 * The Class NetworkClient.
 */
public class NetworkClient {
	
	/** The client. */
	private Client client;
	
	/**
	 * Connect.
	 *
	 * @param address the address
	 * @param port the port
	 * @param timeout the timeout
	 * @return true, if successful
	 */
	public boolean connect(String address, int port, int timeout) {
		try {
			Settings.packets.add(new PacketIncommingPlayer());
			Settings.packets.add(new PacketWorldList());
			
			client = new Client();
			client.getKryo().register(byte[].class);
			client.start();
			client.addListener(new Listener() {
				@Override
				public void received(Connection conn, Object recieved) {
					if ((recieved instanceof Ping) || (recieved instanceof KeepAlive)) return;
					try {
						String rec = new String((byte[]) recieved, StandardCharsets.UTF_8);
						JSONObject jo = (JSONObject) new JSONParser().parse(rec);
						String type = (String) jo.get("Type");
						
						for(NetworkPacket p : Settings.packets) {
							if (p.getType().equals(type)) {
								p.getParser().process(rec, conn.getID());
								break;
							}
						}
					} catch (ParseException e) {
						return;
					}
					System.out.println("S: " + new String((byte[]) recieved, StandardCharsets.UTF_8));
				}
			});
			client.connect(timeout, address, port);
			
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
	 */
	public void send(byte[] msg) {
		client.sendTCP(msg);
	}
	
	/**
	 * Close.
	 */
	public void close() {
		client.close();
	}

	/**
	 * Checks if is connected.
	 *
	 * @return true, if is connected
	 */
	public boolean isConnected() {
		return client.isConnected();
	}
}
