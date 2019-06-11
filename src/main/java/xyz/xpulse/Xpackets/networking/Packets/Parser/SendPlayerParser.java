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

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import xyz.xpulse.Xpackets.XPUtils;
import xyz.xpulse.Xpackets.bungee.BungeeVars;


/**
 * The Class SendPlayerParser.
 */
public class SendPlayerParser implements PacketParser {

	/* (non-Javadoc)
	 * @see xyz.xpulse.Xpackets.networking.Packets.Parser.PacketParser#process(java.lang.String, int)
	 */
	@Override
	public void process(String payload, int id) {
		try {
			JSONObject jo = (JSONObject) new JSONParser().parse(payload);
			UUID uuid = UUID.fromString((String) jo.get("UUID"));
			String world = (String) jo.get("World");
			
			if(XPUtils.sendPlayer(uuid, world, BungeeVars.networkserver)) {
				ProxyServer.getInstance().getPlayer(uuid).sendMessage(new TextComponent("Sending you to " + world));
			} else {
				ProxyServer.getInstance().getPlayer(uuid).sendMessage(new TextComponent("Could not send you to " + world));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
