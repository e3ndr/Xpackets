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
 * The Class IncommingPlayer.
 */
public class IncommingPlayer {
	
	/** The uuid. */
	private UUID uuid;
	
	/** The world. */
	private String world;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IncommingPlayer: [" + uuid.toString() + ", " + world + "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IncommingPlayer) {
			IncommingPlayer ip = (IncommingPlayer) obj;
			if (ip.getUuid().equals(uuid) && ip.getWorld().equals(world)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Instantiates a new incomming player.
	 *
	 * @param uuid the uuid
	 * @param world the world
	 */
	public IncommingPlayer(UUID uuid, String world) {
		this.uuid = uuid;
		this.world = world;
	}
	
	/**
	 * Gets the world.
	 *
	 * @return the world
	 */
	public String getWorld() {
		return world;
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
