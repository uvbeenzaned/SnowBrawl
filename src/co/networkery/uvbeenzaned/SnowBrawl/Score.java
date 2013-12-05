package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.entity.Player;

public class Score {

	public static void giveStandardPoints(Player p) {
		int currentpoints = Configurations.getStatsconfig().getConfigurationSection(p.getName()).getInt("points");
		int standardpoints = Configurations.getMainConfig().getInt("standard-points");
		Configurations.getStatsconfig().getConfigurationSection(p.getName()).set("points", currentpoints + standardpoints);
	}

	public static void removeStandardPoints(Player p) { 
		int currentpoints = Configurations.getStatsconfig().getConfigurationSection(p.getName()).getInt("points");
		int standardpoints = Configurations.getMainConfig().getInt("standard-points");
		Configurations.getStatsconfig().getConfigurationSection(p.getName()).set("points", currentpoints - standardpoints);
	}
}
