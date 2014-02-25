package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Round {

	private static ArrayList<String> shuffle = new ArrayList<String>();
	private static Map<String, Integer> leads = new HashMap<String, Integer>();
	private static Random r = new Random();
	private static boolean gameactive = false;

	public static void startTimerRound() {
		if (!isGameActive() && !Clock.isRunning()) {
			Clock.startTimer();
		}
	}

	public static void startRandomMap() {
		if (!isGameActive() && !Clock.isRunning()) {
			if (!shuffle.isEmpty()) {
				startMap(Arena.getInstanceFromConfig(shuffle.get(0)));
				shuffle.remove(0);
			} else {
				generateMapLineup();
				startMap(Arena.getInstanceFromConfig(shuffle.get(0)));
				shuffle.remove(0);
			}
		}
	}

	public static void startMap(Arena a) {
		if (!isGameActive() && !Clock.isRunning()) {
			TeamCyan.teleportAllPlayersToArena(a);
			TeamLime.teleportAllPlayersToArena(a);
			String arenamsg = ChatColor.GOLD + "[" + ChatColor.DARK_PURPLE + "Arena" + ChatColor.GOLD + "] " + ChatColor.RESET + a.getName();
			Chat.sendAllTeamsMsg(arenamsg);
			Chat.sendAllTeamsMsg("    Description: " + a.getDescription());
			Chat.sendAllTeamsMsg("    Author(s): " + a.getAuthorsString());
			setGameActive(true);
		}
	}

	public static void generateMapLineup() {
		shuffle.addAll(Configurations.getArenasconfig().getKeys(false));
		shuffleMapLineup();
	}

	public static ArrayList<String> getMapLineup() {
		return shuffle;
	}

	public static void addMapToLineup(String name) {
		shuffle.add(name);
	}

	public static void removeMapFromLineup(String name) {
		shuffle.remove(name);
	}

	public static void shuffleMapLineup() {
		r.setSeed(System.currentTimeMillis());
		Collections.shuffle(shuffle, r);
	}

	public static Map<String, Integer> getLeads() {
		return leads;
	}

	public static void giveLeadPoints() {
		if (!getLeads().isEmpty()) {
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
			Chat.sendAllTeamsMsg(winner + ChatColor.RESET + " got the lead and was awarded " + ChatColor.GOLD + String.valueOf(pts * pts) + ChatColor.RESET + " points.");
		}
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
