/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.Util;

/**
 * The Class BukkitColor.
 */
public class BukkitColor {
	
	/**
	 * Strip color.
	 *
	 * @param msg the msg
	 * @return the string
	 */
	public static String stripColor(String msg) {
		return msg
				.replace("§4", "")
				.replace("§c", "")
				.replace("§6", "")
				.replace("§e", "")
				.replace("§2", "")
				.replace("§a", "")
				.replace("§b", "")
				.replace("§3", "")
				.replace("§1", "")
				.replace("§9", "")
				.replace("§d", "")
				.replace("§5", "")
				.replace("§f", "")
				.replace("§7", "")
				.replace("§8", "")
				.replace("§0", "")
				.replace("§l", "")
				.replace("§n", "")
				.replace("§o", "")
				.replace("§k", "")
				.replace("§m", "")
				.replace("§r", "");
	}
	
	/**
	 * Transform color.
	 *
	 * @param msg the msg
	 * @return the string
	 */
	public static String transformColor(String msg) {
		return msg
				.replace("&4", "§4")
				.replace("&c", "§c")
				.replace("&6", "§6")
				.replace("&e", "§e")
				.replace("&2", "§2")
				.replace("&a", "§a")
				.replace("&b", "§b")
				.replace("&3", "§3")
				.replace("&1", "§1")
				.replace("&9", "§9")
				.replace("&d", "§d")
				.replace("&5", "§5")
				.replace("&f", "§f")
				.replace("&7", "§7")
				.replace("&8", "§8")
				.replace("&0", "§0")
				.replace("&l", "§l")
				.replace("&n", "§n")
				.replace("&o", "§o")
				.replace("&k", "§k")
				.replace("&m", "§m")
				.replace("&r", "§r");
	}
}
