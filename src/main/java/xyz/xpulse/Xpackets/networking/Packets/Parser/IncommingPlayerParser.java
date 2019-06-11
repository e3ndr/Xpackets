/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.networking.Packets.Parser;

import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import xyz.xpulse.Xpackets.DataTypes.IncommingPlayer;
import xyz.xpulse.Xpackets.bukkit.Variables;


/**
 * The Class IncommingPlayerParser.
 */
public class IncommingPlayerParser implements PacketParser {

	/* (non-Javadoc)
	 * @see xyz.xpulse.Xpackets.networking.Packets.Parser.PacketParser#process(java.lang.String, int)
	 */
	@Override
	public void process(String payload, int id) {
		try {
			JSONObject jo = (JSONObject) new JSONParser().parse(payload);
			Variables.incommingPlayers.add(new IncommingPlayer(UUID.fromString((String) jo.get("UUID")), (String) jo.get("World")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
