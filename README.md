Xpackets is a plugin that allows users to switch between worlds on a bungee server, that's right. WORLDS! While the xpulse team needed this out of necessity I thought it could be useful to anyone else out there!

Rather than */server serv1* and then running */warp interestingplace*.
You can do */server interestingplace*.

***This is a hybrid jar, it'll work in both BungeeCord and your own flavor of Bukkit!***

Caveats:
- Currently the name is provided by the world's name (eg. world) and cannot be customized in config, only through multiverse or spigot or flying unicorns.
- You must bind the ip in the config. If your bungee is configured to have *serv1* connect to *192.1.0.18*, it cannot be *localhost *or *serv1.xpulse.xyz*.
- Your typing skills will decrease as this is all tab completable.

**WARNING!**
Read through this entirely and set the configs PROPERLY, don't just say "doesn't work."
If you do need help i'm willing to assist with anything, however if you just say "bad plugin" or "no work" or "fortnite is better than minecraft" I will not respond.

**Working deployment:** _mc.xpulse.xyz check_ by running _/xpackets_

**Commands:**
/server <world>
/xpackets <send:list:reload> [player] *(Requires bukkit* *xpackets.admin)*
/server reload *(Requires bungee xpackets.admin)*

**API:**
Want to integrate?
Currently you will have to add the jar to your build path manually (no maven) and the javadoc comments are non existent.

*You should never attempt to send a packet directly, as the packets and names will change version to version.*
```java
XPUtils.sendPlayer(UUID, worldName, NetworkClient);
XPUtils.sendPlayer(UUID, worldName, NetworkServer);
XPUtils.getWorldByName(name);
World w = XPUtils.configuredWorlds();

NetworkServer ns = BungeeVars.networkserver;
NetworkClient ns = Variables.networkclient;
ArrayList<ClientConnection> cc = NetworkServer#clients;
```

**TODO:**
API documentation
security
