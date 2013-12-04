package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Stats {
	
	private String player;
	
	public Stats(Player p) {
		if(Configurations.getPlayersconfig().contains(p.getName())) {
			player = p.getName();
		} else {
			Configurations.getPlayersconfig().createSection(p.getName());
			player = p.getName();
		}
	}
	
	public int getPoints() {
		return Configurations.getPlayersconfig().getConfigurationSection(player).getInt("points");
	}
	
	public void setPoints(int p) {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("points", p);
		Configurations.savePlayersConfig();
	}
	
	public void addPoints(int p) {
		setPoints(getPoints() + p);
	}
	
	public void removePoints(int p) {
		setPoints(getPoints() - p);
	}
	
	public void giveTeamPoints() {
		int standardpoints = Configurations.getMainConfig().getInt("team-points");
		int multiply = 0;
		if(TeamCyan.hasPlayer(Bukkit.getPlayer(player)))
			multiply = TeamLime.getPlayers().size();
		if(TeamLime.hasPlayer(Bukkit.getPlayer(player)))
			multiply = TeamCyan.getPlayers().size();
		addPoints(standardpoints * multiply);
	}
	
	public int getKills() {
		 return Configurations.getPlayersconfig().getConfigurationSection(player).getInt("kills");
	}
	
	public void incrementKillsCount() {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("kills", getKills() + 1);
		Configurations.savePlayersConfig();
	}
	
	public int getDeaths() {
		return Configurations.getPlayersconfig().getConfigurationSection(player).getInt("deaths");
	}
	
	public void incrementDeathCount() {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("deaths", getDeaths() + 1);
		Configurations.savePlayersConfig();
	}
	
	public float getKDRatio() {
		return (float) getKills() / getDeaths();
	}
	
	public int getSnowballsThrown() {
		return Configurations.getPlayersconfig().getConfigurationSection(player).getInt("snowballs-thrown");
	}
	
	public void setSnowballsThrown(int a) {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("snowballs-thrown", a);
	}
	
	public void addSnowballsThrown(int a) {
		setSnowballsThrown(getSnowballsThrown() + a);
	}
	
	public void removeSnowballsThrown(int a) {
		setSnowballsThrown(getSnowballsThrown() - a);
	}
	
	public ArrayList<String> getAllStats() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("Stats for " + player + ".");
		if(TeamCyan.hasPlayer(Bukkit.getPlayer(player)))
			s.add("    Team: CYAN");
		if(TeamLime.hasPlayer(Bukkit.getPlayer(player)))
			s.add("    Team: LIME");
		s.add("    Rank: N/A");
		s.add("    Points: " + String.valueOf(getPoints()));
		s.add("    Kills: " + String.valueOf(getKills()));
		s.add("    Deaths: " + String.valueOf(getDeaths()));
		s.add("    Kill to Death ratio: " + String.valueOf(getKDRatio()));
		s.add("    Snowballs thrown: " + String.valueOf(getSnowballsThrown()));
		return s;
	}
	
}
