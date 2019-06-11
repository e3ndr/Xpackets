/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.networking;


/**
 * The Class ClientConnection.
 */
public class ClientConnection {
	
	/** The id. */
	private int id;
	
	/** The server. */
	private NetworkServer server;
	
	/**
	 * Instantiates a new client connection.
	 *
	 * @param id the id
	 * @param server the server
	 */
	public ClientConnection(int id, NetworkServer server) {
		this.id = id;
		this.server = server;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Send.
	 *
	 * @param msg the msg
	 */
	public void send(byte[] msg) {
		server.send(msg, id);
	}
}
