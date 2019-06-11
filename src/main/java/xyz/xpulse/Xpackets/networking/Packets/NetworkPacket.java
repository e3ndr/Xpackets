/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.networking.Packets;

import xyz.xpulse.Xpackets.networking.Packets.Parser.PacketParser;


/**
 * The Interface NetworkPacket.
 */
public interface NetworkPacket {
	
	/**
	 * To byte.
	 *
	 * @return the byte[]
	 */
	public byte[] toByte();
	
	/**
	 * Creates the.
	 *
	 * @param args the args
	 */
	public void create(Object... args);
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType();
	
	/**
	 * Gets the parser.
	 *
	 * @return the parser
	 */
	public PacketParser getParser();
}
