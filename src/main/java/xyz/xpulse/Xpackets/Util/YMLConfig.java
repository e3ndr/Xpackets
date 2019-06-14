/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

/**
 * The Class YMLConfig.
 */
public class YMLConfig {
	
	/** The plugin. */
	private Plugin plugin;
	
	/** The file. */
	private File file;
	
	/** The config. */
	private Configuration config;

	/**
	 * Instantiates a new YML config.
	 *
	 * @param plugin the plugin
	 */
	public YMLConfig(Plugin plugin) {
		
		this.plugin = plugin;
		
		this.file = new File(getDataFolder(), "config.yml");
		
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
        }
        if (!file.exists()) {
            try {
            	file.createNewFile();
            	try (InputStream is = getResourceAsStream("bungeeconfig.yml");
           			OutputStream os = new FileOutputStream(file)) {
            		ByteStreams.copy(is, os);
                }
            } catch (IOException e) {
                throw new RuntimeException("Unable to create storage file", e);
            }
        }
	}
	
	/**
	 * Gets the config.
	 *
	 * @return the config
	 */
	public Configuration getConfig() {
		try {
			return (this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.file));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Save config.
	 */
	public void saveConfig() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(getDataFolder(), "config.yml"));
		} catch (IOException e) {
			e.printStackTrace();
			plugin.getLogger().severe("Couldn't save storage file!");
		}
	}
	
	/**
	 * Gets the data folder.
	 *
	 * @return the data folder
	 */
	private File getDataFolder() {
		return plugin.getDataFolder();
	}
	
	/**
	 * Gets the resource as stream.
	 *
	 * @param file the file
	 * @return the resource as stream
	 */
	private InputStream getResourceAsStream(String file) {
		return plugin.getResourceAsStream(file);
	}
	
}
