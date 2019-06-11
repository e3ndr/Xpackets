/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.bungee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * The Class BungeeConfig.
 */
public class BungeeConfig {
	
	/** The file. */
	private static File file = new File("plugins/Xpackets/config.txt");
	
	/** The port. */
	public static int port = 8100;
	
	/**
	 * Inits the.
	 */
	public static void init() {
		try {
			if (!file.exists()) {
				new File("plugins/Xpackets/").mkdirs();
				PrintWriter writer = new PrintWriter(file, "UTF-8");
				writer.println("port: 8100");
				writer.close();
			}
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String textFile = "";
			String currentLine = reader.readLine();
			while (currentLine != null) {
	    	        textFile += currentLine = "\n";
	    	        currentLine = reader.readLine();
			}
			reader.close();
			
			String[] text = textFile.split("\n");
			
			for (String s : text ) {
				if (s.contains("port:")) {
					String[] config = s.split(":");
					port = Integer.parseInt(config[1].replace(" ", ""));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
