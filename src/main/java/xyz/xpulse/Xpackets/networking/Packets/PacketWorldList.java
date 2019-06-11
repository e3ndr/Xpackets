/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.networking.Packets;

import java.nio.charset.StandardCharsets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import xyz.xpulse.Xpackets.DataTypes.World;
import xyz.xpulse.Xpackets.networking.Packets.Parser.PacketParser;
import xyz.xpulse.Xpackets.networking.Packets.Parser.WorldListParser;


/**
 * The Class PacketWorldList.
 */
public class PacketWorldList implements NetworkPacket {
	
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
	 * @see xyz.xpulse.Xpackets.networking.Packets.NetworkPacket#create(java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void create(Object... args) {
		World[] worlds = (World[]) args[0];
		jo = new JSONObject();
		JSONArray ja = new JSONArray();
		
		for (World w : worlds) {
			NetworkPacket aw = new PacketAddWorld();
			aw.create(w);
			ja.add(aw.toString());
		}
		
		jo.put("Type", "WorldList");
		jo.put("List", (Object) ja);
	}

	/* (non-Javadoc)
	 * @see xyz.xpulse.Xpackets.networking.Packets.NetworkPacket#getType()
	 */
	@Override
	public String getType() {
		return "WorldList";
	}

	/* (non-Javadoc)
	 * @see xyz.xpulse.Xpackets.networking.Packets.NetworkPacket#getParser()
	 */
	@Override
	public PacketParser getParser() {
		return new WorldListParser();
	}
	
}
