package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Stats {
	
	private String player;
	
	public Stats(Player p) {
		if(Configurations.getPlayersconfig().contains(p.getName())) {
			player = p.getName();
		}
	}
	
	private int getPoints() {
		return Configurations.getPlayersconfig().getConfigurationSection(player).getInt("points");
	}
	
	private void setPoints(int p) {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("points", p);
	}
	
	private void giveTeamPoints() {
		int standardpoints = Configurations.getMainConfig().getInt("team-points");
		Configurations.getPlayersconfig().getConfigurationSection(player).set("points", getPoints() + standardpoints);
	}

	private void removeTeamPoints() { 
		int standardpoints = Configurations.getMainConfig().getInt("team-points");
		Configurations.getPlayersconfig().getConfigurationSection(player).set("points", getPoints() - standardpoints);
	}
	
	private void giveLeadPoints() {
		int leadpoints = getPoints() * Round.getPlayerLead(Bukkit.getPlayer(player));
		Configurations.getPlayersconfig().getConfigurationSection(player).set("points", leadpoints);
	}
	
	private void incrementHitCount() {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("hits", getPoints() + 1);
	}
	
	private void incrementDeathCount() {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("deaths", getPoints() + 1);
	}
	
}
