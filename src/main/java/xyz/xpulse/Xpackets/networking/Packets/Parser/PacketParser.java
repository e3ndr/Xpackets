/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.networking.Packets.Parser;


/**
 * The Interface PacketParser.
 */
public interface PacketParser {
	
	/**
	 * Process a packet.
	 *
	 * @param payload the payload
	 * @param id the id
	 */
	public void process(String payload, int id);
}
