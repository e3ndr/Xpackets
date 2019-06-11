/**
 * Made with <3 by Xpulse.
 * 
 * https://xpackets.xpulse.xyz
 * 
 * Licensed under Apache-2.0
 */
package xyz.xpulse.Xpackets.networking.Packets.Parser;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import xyz.xpulse.Xpackets.Settings;
import xyz.xpulse.Xpackets.DataTypes.World;


/**
 * The Class WorldListParser.
 */
public class WorldListParser implements PacketParser {

	/* (non-Javadoc)
	 * @see xyz.xpulse.Xpackets.networking.Packets.Parser.PacketParser#process(java.lang.String, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void process(String payload, int id) {
		try {
			Settings.worlds = new ArrayList<World>();
			JSONObject jo = (JSONObject) new JSONParser().parse(payload);
			String[] list = (String[]) ((JSONArray) jo.get("List")).toArray(new String[0]);
			for (String s : list) new AddWorldParser().process(s.replace("\\\"", "\""), id);	
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
