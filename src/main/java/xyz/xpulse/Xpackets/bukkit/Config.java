/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.bukkit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.configuration.file.YamlConfiguration;

import xyz.xpulse.Xpackets.DataTypes.World;
import xyz.xpulse.Xpackets.networking.NetworkClient;
import xyz.xpulse.Xpackets.networking.Packets.NetworkPacket;
import xyz.xpulse.Xpackets.networking.Packets.PacketAddWorld;
import xyz.xpulse.Xpackets.networking.Packets.PacketWorldList;


/**
 * The Class Config.
 */
public class Config {
	
	/** The encryption key. */
	public static String ENCRYPTION_KEY;
	
	/** The Timeout. */
	public static int Timeout = 5000;
	
	/** The Address. */
	public static String Address = null;
	
	/** The bind ip. */
	public static String BIND_IP = "localhost";
	
	/** The yml. */
	private static YamlConfiguration yml;
	
	/** The file. */
	private static File file = new File("plugins/Xpackets/config.yml");
	
	/**
	 * Inits the.
	 */
	public static void init() {
		if (!file.exists()) {
			yml = YamlConfiguration.loadConfiguration(file);
			yml.set("ip", "localhost:8100");
			yml.set("timeout", 5000);
			yml.set("worlds.world.name", "DefaultWorld");
			yml.set("worlds.world.meta", new String[] {"This is the spoopy text"});
			yml.set("worlds.world.stylizedname", "&CThe &7Formatted &R&BName");
			yml.set("bind-ip", "127.0.0.1");
			try {
				yml.save(file);
			} catch (IOException e) {
				Xpackets.instance.getLogger().log(Level.SEVERE, "Xpackets could not save config.");
				e.printStackTrace();
				return;
			}
		}
		yml = YamlConfiguration.loadConfiguration(file);
		
		Address = yml.getString("ip");
		Timeout = yml.getInt("timeout");
		BIND_IP = yml.getString("bind-ip");
		
		if (BIND_IP.contains(":")) {
			yml.set("bind-ip", BIND_IP.split(":")[0]);
			try {
				yml.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Set<String> keys = yml.getConfigurationSection("worlds").getKeys(false);
		ArrayList<World> worlds = new ArrayList<World>();
		for (String key : keys) {
			if (yml.getString("worlds." + key + ".name") == null) {
				yml.set("worlds." + key + ".name", key);
				try {
					yml.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			worlds.add(new World(BIND_IP, 0, yml.getString("worlds." + key + ".name"), yml.getStringList("worlds." + key + ".meta").toArray(new String[0]), yml.getString("worlds." + key + ".stylizedname")));
			WorldConfig.addWorld(key, yml.getString("worlds." + key + ".name"));
		}
		
		if (Address != null) {
			NetworkClient nc = new NetworkClient();
			if (!nc.connect(Address.split(":")[0], Integer.parseInt(Address.split(":")[1]), Timeout)) {
				Xpackets.instance.getLogger().log(Level.SEVERE, "Xpackets could not connect."); 
				return;
			}
			for (World w : worlds) {
				NetworkPacket aw = new PacketAddWorld();
				aw.create(w);
				Xpackets.instance.getLogger().info("Registered world: " + w.getName());
				nc.send(aw.toByte());
			}
			NetworkPacket wl = new PacketWorldList();
			wl.create((Object) new World[0]);
			nc.send(wl.toByte());
			Xpackets.client = nc;
		} else {
			Xpackets.instance.getLogger().log(Level.SEVERE, "Could not configure Xpackets client.");
		}
	}
}
