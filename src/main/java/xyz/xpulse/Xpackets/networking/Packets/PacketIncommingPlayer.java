/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.networking.Packets;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.json.simple.JSONObject;

import xyz.xpulse.Xpackets.networking.Packets.Parser.IncommingPlayerParser;
import xyz.xpulse.Xpackets.networking.Packets.Parser.PacketParser;


/**
 * The Class PacketIncommingPlayer.
 */
public class PacketIncommingPlayer implements NetworkPacket {
	
	/** The jo. */
	private JSONObject jo;
	
	/* (non-Javadoc)
	 * @see xyz.xpulse.Xpackets.networking.Packets.NetworkPacket#toByte()
	 */
	@Override
	public byte[] toByte() {
		return jo.toJSONString().getBytes(StandardCharsets.UTF_8);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return jo.toJSONString();
	}
	
	/* (non-Javadoc)
	 * @see xyz.xpulse.Xpackets.networking.Packets.NetworkPacket#create(java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void create(Object... args) {
		jo = new JSONObject();
		
		jo.put("Type", "IncommingPlayer");
		jo.put("UUID", ((UUID) args[0]).toString());
		jo.put("World", (String) args[1]);
		
	}

	/* (non-Javadoc)
	 * @see xyz.xpulse.Xpackets.networking.Packets.NetworkPacket#getType()
	 */
	@Override
	public String getType() {
		return "IncommingPlayer";
	}

	/* (non-Javadoc)
	 * @see xyz.xpulse.Xpackets.networking.Packets.NetworkPacket#getParser()
	 */
	@Override
	public PacketParser getParser() {
		return new IncommingPlayerParser();
	}

}
