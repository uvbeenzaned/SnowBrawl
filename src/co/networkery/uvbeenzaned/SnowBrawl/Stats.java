package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Stats {

	private boolean error = false;
	private String player;

	public Stats(Player p) {
		if (!Configurations.getPlayersconfig().contains(p.getName())) {
			Configurations.getPlayersconfig().createSection(p.getName());
		}
		player = p.getName();
	}

	public Stats(String p, Player sender) {
		if (Configurations.getPlayersconfig().contains(p)) {
			player = p;
		} else {
			error = true;
			if (sender != null)
				Chat.sendPPM("There are no players with the name " + p + " in the records.", sender);
		}
	}

	public Stats(String p) {
		if (Configurations.getPlayersconfig().contains(p)) {
			player = p;
		} else {
			error = true;
		}
	}

	public boolean getError() {
		return error;
	}

	public int getPoints() {
		return Configurations.getPlayersconfig().getConfigurationSection(player).getInt("points");
	}

	public void setPoints(int p) {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("points", p);
		Configurations.savePlayersConfig();
		Board.updatePlayer(Bukkit.getPlayer(player));
	}

	public void addPoints(int p) {
		setPoints(getPoints() + p);
	}

	public void removePoints(int p) {
		setPoints(getPoints() - p);
	}

	public void giveTeamPoints() {
		int multiply = 0;
		if (TeamCyan.hasPlayer(Bukkit.getPlayer(player)))
			multiply = TeamLime.getPlayers().size();
		if (TeamLime.hasPlayer(Bukkit.getPlayer(player)))
			multiply = TeamCyan.getPlayers().size();
		addPoints(Settings.getTeamPoints() * multiply);
	}

	public int getHits() {
		return Configurations.getPlayersconfig().getConfigurationSection(player).getInt("hits");
	}

	public void setHits(int k) {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("hits", k);
		Configurations.savePlayersConfig();
	}

	public void incrementHitsCount() {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("hits", getHits() + 1);
		Configurations.savePlayersConfig();
		Board.updatePlayer(Bukkit.getPlayer(player));
	}

	public int getLosses() {
		return Configurations.getPlayersconfig().getConfigurationSection(player).getInt("losses");
	}

	public void setLosses(int d) {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("losses", d);
		Configurations.savePlayersConfig();
	}

	public void incrementLossCount() {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("losses", getLosses() + 1);
		Configurations.savePlayersConfig();
	}

	public float getKDRatio() {
		return (float) getHits() / getLosses();
	}

	public int getSnowballsThrown() {
		return Configurations.getPlayersconfig().getConfigurationSection(player).getInt("snowballs-thrown");
	}

	public void setSnowballsThrown(int a) {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("snowballs-thrown", a);
		Board.updatePlayer(Bukkit.getPlayer(player));
	}

	public void addSnowballsThrown(int a) {
		setSnowballsThrown(getSnowballsThrown() + a);
	}

	public void removeSnowballsThrown(int a) {
		setSnowballsThrown(getSnowballsThrown() - a);
	}

	public String getLastRank() {
		if (Configurations.getPlayersconfig().getConfigurationSection(player).getString("last-rank") != null) {
			return Configurations.getPlayersconfig().getConfigurationSection(player).getString("last-rank");
		} else {
			return "N/A";
		}
	}

	public void setRank(String r) {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("last-rank", r);
	}

	public boolean usingPower(Powers p) {
		if (Configurations.getPlayersconfig().getConfigurationSection(player).getString("power") != null) {
			if (getPower().equalsName(p.toString())) {
				return true;
			}
			return false;
		}
		return false;
	}

	public boolean ownsPower(Powers p) {
		if (getPurchasedPowers().contains(p.toString())) {
			return true;
		}
		return false;
	}

	public List<String> getPurchasedPowers() {
		if (Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("purchased-powers") != null) {
			return Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("purchased-powers");
		}
		return new ArrayList<String>();
	}

	public void addPower(Powers p) {
		List<String> ppws = getPurchasedPowers();
		if (Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("purchased-powers") != null) {
			if (!getPurchasedPowers().contains(p.toString())) {
				ppws.add(p.toString());
				Configurations.getPlayersconfig().getConfigurationSection(player).set("purchased-powers", ppws);
				Configurations.savePlayersConfig();
			}
		} else {
			Configurations.getPlayersconfig().getConfigurationSection(player).createSection("purchased-powers");
			ppws.add(p.toString());
			Configurations.getPlayersconfig().getConfigurationSection(player).set("purchased-powers", ppws);
			Configurations.savePlayersConfig();
		}
	}

	public Powers getPower() {
		if (Configurations.getPlayersconfig().getConfigurationSection(player).getString("power") != null) {
			for (Powers pw : Powers.values()) {
				if (pw.equalsName(Configurations.getPlayersconfig().getConfigurationSection(player).getString("power"))) {
					return pw;
				}
			}
		}
		return Powers.NONE;
	}

	public void setPower(Powers p) {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("power", p.toString());
		Configurations.savePlayersConfig();
	}

	public ArrayList<String> getArenasList() {
		ArrayList<String> arenas = new ArrayList<String>();
		for (String n : Arenas.getNameList()) {
			Arena a = Arena.getInstanceFromConfig(n);
			if (a.getAuthors().contains(player)) {
				arenas.add(a.getName());
			}
		}
		if (!arenas.isEmpty()) {
			return arenas;
		}
		return new ArrayList<String>();
	}

	public ArrayList<String> getAllStats() {
		ArrayList<String> s = new ArrayList<String>();
		String purchasedpowers = "";
		s.add("Stats for " + player + ":");
		if (Bukkit.getOfflinePlayer(player).isOnline()) {
			if (TeamCyan.hasPlayer(player)) {
				s.add("    Team: CYAN");
			}
			if (TeamLime.hasPlayer(player)) {
				s.add("    Team: LIME");
			}
			if (!TeamCyan.hasPlayer(player) && !TeamLime.hasPlayer(player))
				s.add("    Team: N/A");
		} else {
			s.add("    Team: N/A (player offline)");
		}
		for (String pw : getPurchasedPowers()) {
			purchasedpowers += pw + ", ";
		}
		s.add("    Rank: " + getLastRank());
		s.add("    Points: " + String.valueOf(getPoints()));
		s.add("    Hits: " + String.valueOf(getHits()));
		s.add("    Losses: " + String.valueOf(getLosses()));
		s.add("    H/L ratio: " + String.valueOf(getKDRatio()));
		s.add("    Power: " + getPower().toString());
		s.add("    Purchased powers: " + purchasedpowers);
		s.add("    Snowballs thrown: " + String.valueOf(getSnowballsThrown()));
		s.add("    Arenas created/assisted: " + String.valueOf(getArenasList().size()));
		return s;
	}

	public static ArrayList<String> getGlobalStats() {
		ArrayList<String> s = new ArrayList<String>();
		int plcount = 0;
		int points = 0;
		int hits = 0;
		float kd = 0;
		int sbthrown = 0;
		for (String p : Configurations.getPlayersconfig().getKeys(false)) {
			points += Configurations.getPlayersconfig().getConfigurationSection(p).getInt("points");
			hits += Configurations.getPlayersconfig().getConfigurationSection(p).getInt("hits");
			if (Configurations.getPlayersconfig().getConfigurationSection(p).getInt("hits") > 0 && Configurations.getPlayersconfig().getConfigurationSection(p).getInt("losses") > 0) {
				kd += (float) (Configurations.getPlayersconfig().getConfigurationSection(p).getInt("hits") / Configurations.getPlayersconfig().getConfigurationSection(p).getInt("losses"));
				plcount++;
			}
			sbthrown += Configurations.getPlayersconfig().getConfigurationSection(p).getInt("snowballs-thrown");
		}
		kd = (float) kd / plcount;
		s.add("Global Stats:");
		s.add("    Total Points: " + String.valueOf(points));
		s.add("    Total Hits: " + String.valueOf(hits));
		s.add("    Avg. H/L ratio: " + String.valueOf(kd));
		s.add("    Total Snowballs thrown: " + String.valueOf(sbthrown));
		return s;
	}

}
