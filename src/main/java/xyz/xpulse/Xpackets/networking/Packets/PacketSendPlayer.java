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

import xyz.xpulse.Xpackets.networking.Packets.Parser.PacketParser;
import xyz.xpulse.Xpackets.networking.Packets.Parser.SendPlayerParser;


/**
 * The Class PacketSendPlayer.
 */
public class PacketSendPlayer implements NetworkPacket {
	
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
		UUID uuid = (UUID) args[0];
		String world = (String) args[1];
		jo = new JSONObject();
		
		jo.put("Type", "SendPlayer");
		jo.put("UUID", uuid.toString());
		jo.put("World", world);
	}

	/* (non-Javadoc)
	 * @see xyz.xpulse.Xpackets.networking.Packets.NetworkPacket#getType()
	 */
	@Override
	public String getType() {
		return "SendPlayer";
	}

	/* (non-Javadoc)
	 * @see xyz.xpulse.Xpackets.networking.Packets.NetworkPacket#getParser()
	 */
	@Override
	public PacketParser getParser() {
		return new SendPlayerParser();
	}

}
