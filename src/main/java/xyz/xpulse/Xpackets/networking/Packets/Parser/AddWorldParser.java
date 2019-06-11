/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.networking.Packets.Parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import xyz.xpulse.Xpackets.Settings;
import xyz.xpulse.Xpackets.DataTypes.World;

/**
 * The Class AddWorldParser.
 */
public class AddWorldParser implements PacketParser {

	/* (non-Javadoc)
	 * @see xyz.xpulse.Xpackets.networking.Packets.Parser.PacketParser#process(java.lang.String, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void process(String payload, int id) {
		try {
			JSONObject jo = (JSONObject) new JSONParser().parse(payload);
			World world = new World((String) jo.get("ip"), id, (String) jo.get("WorldName"), (String[]) ((JSONArray) jo.get("WorldMeta")).toArray(new String[0]), (String) jo.get("FormattedName"));
			Settings.worlds.add(world);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
