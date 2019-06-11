/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets;


/**
 * The Class StaticLogger.
 */
public class StaticLogger {
	
	/**
	 * Log.
	 *
	 * @param msg the msg
	 */
	public static void log(String msg) {
		if (Settings.serverType == ServerType.Bukkit) {
			xyz.xpulse.Xpackets.bukkit.Xpackets.instance.getLogger().info(msg);
		} else {
			xyz.xpulse.Xpackets.bungee.Xpackets.instance.getLogger().info(msg);
		}
	}
	
	/**
	 * Warn.
	 *
	 * @param msg the msg
	 */
	public static void warn(String msg) {
		if (Settings.serverType == ServerType.Bukkit) {
			xyz.xpulse.Xpackets.bukkit.Xpackets.instance.getLogger().warning(msg);
		} else {
			xyz.xpulse.Xpackets.bungee.Xpackets.instance.getLogger().warning(msg);
		}
	}

}
