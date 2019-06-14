/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import xyz.xpulse.Xpackets.Util.YMLConfig;

/**
 * The Class BungeeConfig.
 */
public class BungeeConfig {
	/** The port. */
	public static int port = 8100;
	
	/** The world switching. */
	public static boolean world_switching = true;
	
	/** The print debug. */
	public static boolean print_debug = false;
	
	/**
	 * Inits the.
	 *
	 * @param plugin the plugin
	 */
	public static void init(Plugin plugin) {
		YMLConfig yml = new YMLConfig(plugin);
		Configuration config = yml.getConfig();
		
		port = config.getInt("port", 8100);
		world_switching = config.getBoolean("enable-per-world-switching", true);
		print_debug = config.getBoolean("print-debug", false);
	}
}
