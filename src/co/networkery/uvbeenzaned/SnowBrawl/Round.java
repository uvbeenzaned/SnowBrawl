package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Round {

	private static Map<String, Integer> leads = new HashMap<String, Integer>();
	private static Random r = new Random();
	private static int l = 0;
	private static boolean gameactive = false;

	public static void startTimerRound() {
		if (!isGameActive() && !Clock.isRunning()) {
			Clock.startTimer();
		}
	}

	public static void startRandomMap() {
		if (!isGameActive() && !Clock.isRunning()) {
			int arenaamount = Configurations.getArenasconfig().getKeys(false)
					.size();
			r.setSeed(System.currentTimeMillis());
			int randnum = r.nextInt(arenaamount);
			if (arenaamount > 1) {
				while (randnum == l) {
					randnum = r.nextInt(arenaamount);
				}
			}
			int mapnum = 0;
			for (String as : Configurations.getArenasconfig().getKeys(false)) {
				if (randnum == mapnum) {
					l = mapnum;
					startMap(Arena.getInstanceFromConfig(as));
					break;
				}
				mapnum++;
			}
		}
	}

	public static void startMap(Arena a) {
		if (!isGameActive() && !Clock.isRunning()) {
			TeamCyan.teleportAllPlayersToArena(a);
			TeamLime.teleportAllPlayersToArena(a);
			String arenamsg = ChatColor.GOLD + "[" + ChatColor.DARK_PURPLE
					+ "Arena" + ChatColor.GOLD + "] " + ChatColor.RESET
					+ a.getName();
			Chat.sendAllTeamsMsg(arenamsg);
			Chat.sendAllTeamsMsg("    Description: " + a.getDescription());
			Chat.sendAllTeamsMsg("    Author(s): " + a.getAuthorsString());
			setGameActive(true);
		}
	}

	public static Map<String, Integer> getLeads() {
		return leads;
	}

	public static void giveLeadPoints() {
		String winner = "";
		int pts = 0;
		for (Entry<String, Integer> p : getLeads().entrySet()) {
			if (pts < p.getValue()) {
				pts = p.getValue();
				winner = p.getKey();
			}
		}
		Stats s = new Stats(Bukkit.getPlayer(winner));
		s.addPoints(pts * pts);
		clearLeads();
		Chat.sendAllTeamsMsg(winner + ChatColor.RESET
				+ " got the lead and was awarded " + ChatColor.RED
				+ String.valueOf(pts * pts) + ChatColor.RESET + " points.");
	}

	public static void setLeads(Map<String, Integer> leads) {
		Round.leads = leads;
	}

	public static void clearLeads() {
		getLeads().clear();
	}

	public static int getPlayerLead(Player p) {
		if (getLeads().containsKey(p.getName()))
			return getLeads().get(p.getName());
		if (!getLeads().containsKey(p.getName()))
			return 0;
		return 0;
	}

	public static void addPlayerLead(Player p, int pts) {
		getLeads().put(p.getName(), getPlayerLead(p) + pts);
	}

	public static void removePlayerLead(Player p) {
		getLeads().remove(p.getName());
	}

	public static void setGameActive(boolean b) {
		gameactive = b;
	}

	public static boolean isGameActive() {
		return gameactive;
	}
}
