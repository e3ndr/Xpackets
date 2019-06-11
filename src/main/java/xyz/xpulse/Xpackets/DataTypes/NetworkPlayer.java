/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.DataTypes;

import java.util.UUID;


/**
 * The Class NetworkPlayer.
 */
public class NetworkPlayer {
	
	/** The username. */
	private String username;
	
	/** The uuid. */
	private UUID uuid;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NetworkPlayer: [" + uuid.toString() + ", " + username + "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NetworkPlayer) {
			NetworkPlayer np = (NetworkPlayer) obj;
			if (np.getUsername().equals(username) && np.getUuid().equals(uuid)) return true;
		}
		
		return false;
	}
	
	/**
	 * Instantiates a new network player.
	 *
	 * @param username the username
	 * @param uuid the uuid
	 */
	public NetworkPlayer(String username, UUID uuid) {
		this.username = username;
		this.uuid = uuid;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Gets the uuid.
	 *
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}
}
