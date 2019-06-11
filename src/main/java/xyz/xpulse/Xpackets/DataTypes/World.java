/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.DataTypes;


/**
 * The Class World.
 */
public class World {
	
	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The formatted name. */
	private String formattedName;
	
	/** The meta. */
	private String[] meta = new String[] {""};
	
	/** The ip. */
	private String ip;
	
	/**
	 * Instantiates a new world.
	 *
	 * @param ip the ip
	 * @param id the id
	 * @param name the name
	 * @param meta the meta
	 */
	public World(String ip, int id, String name, String[] meta) {
		this.id = id;
		this.name = name;
		this.ip = ip;
		if (meta != null && meta.length != 0) this.meta = meta;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}
	
	/**
	 * Instantiates a new world.
	 *
	 * @param ip the ip
	 * @param id the id
	 * @param name the name
	 * @param meta the meta
	 * @param formattedName the formatted name
	 */
	public World(String ip, int id, String name, String[] meta, String formattedName) {
		this.id = id;
		this.name = name;
		this.ip = ip;
		if (meta != null) this.meta = meta;
		this.formattedName = formattedName;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the formatted name.
	 *
	 * @return the formatted name
	 */
	public String getFormattedName() {
		return formattedName;
	}

	/**
	 * Gets the meta.
	 *
	 * @return the meta
	 */
	public String[] getMeta() {
		return meta;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIP() {
		return ip;
	}
}
