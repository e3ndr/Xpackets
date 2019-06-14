/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets;

import java.util.ArrayList;

import xyz.xpulse.Xpackets.DataTypes.World;
import xyz.xpulse.Xpackets.networking.Packets.NetworkPacket;


/**
 * The Class Settings.
 */
public class Settings {
	/** The version. */
	public static final String version = "3.0";
	
	/** The worlds. */
	public static ArrayList<World> worlds = new ArrayList<World>();
	
	/** The server type. */
	public static ServerType serverType;
	
	/** The packets. */
	public static ArrayList<NetworkPacket> packets = new ArrayList<NetworkPacket>();
	
}
