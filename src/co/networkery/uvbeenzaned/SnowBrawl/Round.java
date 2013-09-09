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
		if(!isGameActive()) {
			Clock.startClock();
		}
	}

	public static void startRandomMap() {
		int arenaamount = Configurations.getArenasconfig().getKeys(false).size();
		r.setSeed(System.currentTimeMillis());
		int randnum = r.nextInt(arenaamount);
		while(randnum == l)
		{
			randnum = r.nextInt();
		}
		int mapnum = 0;
		for(String as : Configurations.getArenasconfig().getKeys(false))
		{
			if(mapnum == randnum)
			{
				Arena a = Arena.getInstanceFromConfig(as);
				TeamCyan.teleportAllPlayersToArena(a);
				TeamLime.teleportAllPlayersToArena(a);
				String arenamsg = ChatColor.GOLD + "[" + ChatColor.DARK_PURPLE + "Arena" + ChatColor.GOLD + "] " + ChatColor.RESET + a.getName();
				Chat.sendAllTeamsMsg(arenamsg);
				Chat.sendAllTeamsMsg(a.getDescription());
				Chat.sendAllTeamsMsg(a.getAuthorsString());
				setGameActive(true);
				break;
			}
			mapnum++;
		}
	}
	
	public static void startMap(Arena a)
	{
		TeamCyan.teleportAllPlayersToArena(a);
		TeamLime.teleportAllPlayersToArena(a);
		String arenamsg = ChatColor.GOLD + "[" + ChatColor.DARK_PURPLE + "Arena" + ChatColor.GOLD + "] " + ChatColor.RESET + a.getName();
		Chat.sendAllTeamsMsg(arenamsg);
		Chat.sendAllTeamsMsg(a.getDescription());
		Chat.sendAllTeamsMsg(a.getAuthorsString());
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
