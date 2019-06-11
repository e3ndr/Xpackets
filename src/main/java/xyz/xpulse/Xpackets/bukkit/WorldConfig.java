/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.bukkit;

import java.util.ArrayList;


/**
 * The Class WorldConfig.
 */
public class WorldConfig {
	
	/** The world names. */
	private static ArrayList<String[]> worldNames = new ArrayList<String[]>();
	
	/**
	 * Adds the world.
	 *
	 * @param localName the local name
	 * @param publicName the public name
	 */
	public static void addWorld(String localName, String publicName) {
		worldNames.add(new String[] {localName, publicName});
	}
	
	/**
	 * Gets the local name.
	 *
	 * @param publicName the public name
	 * @return the local name
	 */
	public static String getLocalName(String publicName) {
		for (String[] names : worldNames) {
			if (names[1].equals(publicName)) return names[0];
		}
		return null;
	}
	
	/**
	 * Reset names.
	 */
	public static void resetNames() {
		worldNames = new ArrayList<String[]>();
	}
	
}
