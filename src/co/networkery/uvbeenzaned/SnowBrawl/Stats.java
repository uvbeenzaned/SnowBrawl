package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

	public void checkShotDistanceRecord(String method, long distance) {
		if (method == "Snowball") {
			if (Configurations.getPlayersconfig().getConfigurationSection(player).getLong("snowball-record-distance") < distance) {
				Configurations.getPlayersconfig().getConfigurationSection(player).set("snowball-record-distance", distance);
			}
		}
		if (method == "Sniper Rifle") {
			if (Configurations.getPlayersconfig().getConfigurationSection(player).getLong("sniper-record-distance") < distance) {
				Configurations.getPlayersconfig().getConfigurationSection(player).set("sniper-record-distance", distance);
			}
		}
	}

	public long getShotDistanceRecord(String method) {
		if (method == "Snowball") {
			return Configurations.getPlayersconfig().getConfigurationSection(player).getLong("snowball-record-distance");
		}
		if (method == "Sniper Rifle") {
			return Configurations.getPlayersconfig().getConfigurationSection(player).getLong("sniper-record-distance");
		}
		return 0;
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
			return getPower().getType().equals(p);
		}
		return false;
	}

	public boolean ownsPower(Powers p) {
		if (getPurchasedPowers().contains(p.toString())) {
			return true;
		}
		return false;
	}

	public boolean ownsPowers(List<Powers> powers) {
		boolean result = false;
		for (Powers pws : powers) {
			if (!ownsPower(pws)) {
				result = false;
				break;
			} else {
				result = ownsPower(pws);
			}
		}
		return result;
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

	public Power getPower() {
		if (Configurations.getPlayersconfig().getConfigurationSection(player).getString("power") != null) {
			for (Powers pw : Powers.values()) {
				if (pw.equalsName(Configurations.getPlayersconfig().getConfigurationSection(player).getString("power"))) {
					return new Power(pw, Bukkit.getPlayer(player));
				}
			}
		}
		return new Power(Powers.NONE, Bukkit.getPlayer(player));
	}

	public void setPower(Powers p) {
		Configurations.getPlayersconfig().getConfigurationSection(player).set("power", p.toString());
		Configurations.savePlayersConfig();
	}

	public boolean usingUpgrade(Upgrades u) {
		if (getEnabledUpgrades().contains(u.toString())) {
			return true;
		}
		return false;
	}

	public boolean ownsUpgrade(Upgrades u) {
		if (getPurchasedUpgrades().contains(u.toString())) {
			return true;
		}
		return false;
	}

	public List<String> getPurchasedUpgrades() {
		if (Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("purchased-upgrades") != null && !Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("purchased-upgrades").isEmpty()) {
			return Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("purchased-upgrades");
		}
		return new ArrayList<String>();
	}

	public void addUpgrade(Upgrades u) {
		List<String> pupgds = getPurchasedUpgrades();
		if (Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("purchased-upgrades") != null) {
			if (!getPurchasedUpgrades().contains(u.toString())) {
				pupgds.add(u.toString());
				Configurations.getPlayersconfig().getConfigurationSection(player).set("purchased-upgrades", pupgds);
				Configurations.savePlayersConfig();
			}
		} else {
			Configurations.getPlayersconfig().getConfigurationSection(player).createSection("purchased-upgrades");
			pupgds.add(u.toString());
			Configurations.getPlayersconfig().getConfigurationSection(player).set("purchased-upgrades", pupgds);
			Configurations.savePlayersConfig();
		}
	}

	public List<String> getEnabledUpgrades() {
		if (Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("enabled-upgrades") != null && !Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("enabled-upgrades").isEmpty()) {
			return Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("enabled-upgrades");
		}
		return new ArrayList<String>();
	}

	public void enableUpgrade(Upgrades u) {
		if (getPurchasedUpgrades().contains(u.toString()) || !Store.isEnabled()) {
			List<String> enabledupgrades = Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("enabled-upgrades");
			enabledupgrades.add(u.toString());
			Configurations.getPlayersconfig().getConfigurationSection(player).set("enabled-upgrades", enabledupgrades);
			Configurations.savePlayersConfig();
		}
	}

	public void disableUpgrade(Upgrades u) {
		List<String> enabledupgrades = Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("enabled-upgrades");
		enabledupgrades.remove(u.toString());
		Configurations.getPlayersconfig().getConfigurationSection(player).set("enabled-upgrades", enabledupgrades);
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
		String enabledupgrades = "";
		String purchasedupgrades = "";
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
		for (String u : getEnabledUpgrades()) {
			enabledupgrades += u + ", ";
		}
		for (String u : getPurchasedUpgrades()) {
			purchasedupgrades += u + ", ";
		}
		s.add("    Rank: " + getLastRank());
		s.add("    Points: " + String.valueOf(getPoints()));
		s.add("    Hits: " + String.valueOf(getHits()));
		s.add("    Losses: " + String.valueOf(getLosses()));
		s.add("    H/L ratio: " + String.valueOf(getKDRatio()));
		s.add("    Power: " + getPower().getName());
		s.add("    Purchased powers: " + purchasedpowers);
		s.add("    Enabled upgrades: " + enabledupgrades);
		s.add("    Purchased upgrades: " + purchasedupgrades);
		s.add("    Snowballs thrown: " + String.valueOf(getSnowballsThrown()));
		s.add("    Snowball distance record: " + String.valueOf(getShotDistanceRecord("Snowball")) + " blocks");
		s.add("    Sniper distance record: " + String.valueOf(getShotDistanceRecord("Sniper Rifle")) + " blocks");
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
		String sbrecordplayer = "";
		String sniperrecordplayer = "";
		long snowballdistancerecord = 0;
		long sniperdistancerecord = 0;
		for (String p : Configurations.getPlayersconfig().getKeys(false)) {
			points += Configurations.getPlayersconfig().getConfigurationSection(p).getInt("points");
			hits += Configurations.getPlayersconfig().getConfigurationSection(p).getInt("hits");
			if (Configurations.getPlayersconfig().getConfigurationSection(p).getInt("hits") > 0 && Configurations.getPlayersconfig().getConfigurationSection(p).getInt("losses") > 0) {
				kd += (float) (Configurations.getPlayersconfig().getConfigurationSection(p).getInt("hits") / Configurations.getPlayersconfig().getConfigurationSection(p).getInt("losses"));
				plcount++;
			}
			sbthrown += Configurations.getPlayersconfig().getConfigurationSection(p).getInt("snowballs-thrown");
			if (snowballdistancerecord < Configurations.getPlayersconfig().getConfigurationSection(p).getLong("snowball-record-distance")) {
				snowballdistancerecord = Configurations.getPlayersconfig().getConfigurationSection(p).getLong("snowball-record-distance");
				sbrecordplayer = p;
			}
			if (sniperdistancerecord < Configurations.getPlayersconfig().getConfigurationSection(p).getLong("sniper-record-distance")) {
				sniperdistancerecord = Configurations.getPlayersconfig().getConfigurationSection(p).getLong("sniper-record-distance");
				sniperrecordplayer = p;
			}
		}
		kd = (float) kd / plcount;
		s.add("Global Stats:");
		s.add("    Total Points: " + String.valueOf(points));
		s.add("    Total Hits: " + String.valueOf(hits));
		s.add("    Avg. H/L ratio: " + String.valueOf(kd));
		s.add("    Total Snowballs thrown: " + String.valueOf(sbthrown));
		s.add("    Record Snowball Shot Distance: " + sbrecordplayer + ChatColor.RESET + " - " + String.valueOf(snowballdistancerecord) + " blocks");
		s.add("    Record Sniper Shot Distance: " + sniperrecordplayer + ChatColor.RESET + " - " + String.valueOf(sniperdistancerecord) + " blocks");
		return s;
	}

}
