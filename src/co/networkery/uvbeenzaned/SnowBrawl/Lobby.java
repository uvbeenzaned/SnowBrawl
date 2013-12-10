package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Lobby {

	public static Location getLobbyspawnlocation() {
		return LocationSerializer.str2loc(Configurations.getMainConfig()
				.getString("lobby-spawn-location"));
	}

	public static void setLobbyspawnlocation(Location l, Player sender) {
		Configurations.getMainConfig().set("lobby-spawn-location",
				LocationSerializer.loc2str(l));
		Configurations.saveMainConfig();
		if (sender != null)
			Chat.sendPPM("The lobby's spawn location has changed to "
					+ LocationSerializer.loc2str(l) + ".", sender);
	}
}
