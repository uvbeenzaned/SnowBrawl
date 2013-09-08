package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Round {
	
	private static Map<String, Integer> leads = new HashMap<String, Integer>();
	private static Random r = new Random();
	private static int l = 0;
	private static boolean gameactive = false;
	
	public static void startTimerRound() {
		Clock.startClock();
	}

	public static void startRandomMap() {
		int arenaamount = Configurations.getArenasconfig().getKeys(false).size();
		r.setSeed(System.currentTimeMillis());
		int mapnum = r.nextInt(arenaamount);
		while(mapnum == l)
		{
			mapnum = r.nextInt();
		}
		Arena a = Arena.getInstanceFromConfig(Configurations.getArenasconfig().getKeys(false).toArray(new String[0])[mapnum]);
		TeamCyan.teleportAllPlayersToArena(a, Teams.CYAN);
		TeamLime.teleportAllPlayersToArena(a, Teams.LIME);
		String arenamsg = ChatColor.GOLD + "[" + ChatColor.DARK_PURPLE + "Arena" + ChatColor.GOLD + "] " + ChatColor.RESET + a.getName();
		TeamCyan.sendTeamMsg(arenamsg);
		TeamCyan.sendTeamMsg(a.getDescription());
		TeamLime.sendTeamMsg(arenamsg);
		TeamLime.sendTeamMsg(a.getDescription());
		setGameActive(true);
	}
	
	public static void startMap(Arena a)
	{
		TeamCyan.teleportAllPlayersToArena(a, Teams.CYAN);
		TeamLime.teleportAllPlayersToArena(a, Teams.LIME);
		String arenamsg = ChatColor.GOLD + "[" + ChatColor.DARK_PURPLE + "Arena" + ChatColor.GOLD + "] " + ChatColor.RESET + a.getName();
		TeamCyan.sendTeamMsg(arenamsg);
		TeamCyan.sendTeamMsg(a.getDescription());
		TeamLime.sendTeamMsg(arenamsg);
		TeamLime.sendTeamMsg(a.getDescription());
		setGameActive(true);
	}
	
	public static Map<String, Integer> getLeads() {
		return leads;
	}
	
	public static void setLeads(Map<String, Integer> leads) {
		Round.leads = leads;
	}
	
	public static void clearLeads() {
		getLeads().clear();
	}
	
	public static void setPlayerLead(Player p, int pts) {
		getLeads().put(p.getName(), getLeads().get(p.getName()) + pts);
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
