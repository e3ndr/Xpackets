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
import xyz.xpulse.Xpackets.networking.Packets.Parser.AddWorldParser;
import xyz.xpulse.Xpackets.networking.Packets.Parser.PacketParser;


/**
 * The Class PacketAddWorld.
 */
public class PacketAddWorld implements NetworkPacket {
	
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
		World w = (World) args[0];
		
		jo = new JSONObject();
		JSONArray ja = new JSONArray();
		
		jo.put("Type", "AddWorld");
		jo.put("ip", w.getIP());
		jo.put("WorldName", w.getName());
		jo.put("FormattedName", w.getFormattedName());
		for(String s : w.getMeta()) {
			ja.add(s);
		}
		jo.put("WorldMeta", (Object) ja);
		
	}

	/* (non-Javadoc)
	 * @see xyz.xpulse.Xpackets.networking.Packets.NetworkPacket#getType()
	 */
	@Override
	public String getType() {
		return "AddWorld";
	}

	/* (non-Javadoc)
	 * @see xyz.xpulse.Xpackets.networking.Packets.NetworkPacket#getParser()
	 */
	@Override
	public PacketParser getParser() {
		return new AddWorldParser();
	}
}
