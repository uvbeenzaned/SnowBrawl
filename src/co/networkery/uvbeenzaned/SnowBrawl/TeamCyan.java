package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class TeamCyan{

	private static String name;
	private static ChatColor color;
	private static List<String> players = new ArrayList<String>();
	private static List<String> playersinarena = new ArrayList<String>();
	private static List<String> deadplayers = new ArrayList<String>();
	
	public static String getName() {
		return name;
	}
	
	public static void setName(String name) {
		TeamCyan.name = name;
	}
	public static ChatColor getColor() {
		return color;
	}
	
	public static void setColor(ChatColor color) {
		TeamCyan.color = color;
	}
	
	public static void join(Player p) {
		if(!hasPlayer(p) || !TeamLime.hasPlayer(p)) {
			if(hasDeadPlayer(p)) {
				removeDeadPlayer(p);
			}
			addPlayer(p);
			Chat.sendAllTeamsMsg(p.getName() + " has joined team CYAN.");
			p.teleport(Lobby.getLobbyspawnlocation());
			if(!TeamLime.isEmpty()) {
				Round.startTimerRound();
			} else {
				Chat.sendPPM("Team LIME has no players! Please wait until someone joins to play.", p);
			}
		} else {
			Chat.sendPPM("You're already on a team!  Please leave to join another.", p);
		}
	}
	
	public static void leave(Player p) {
		if(hasPlayer(p)) {
			removePlayer(p);
			Chat.sendPPM("You've left team CYAN.", p);
			Chat.sendTeamCyanMsg(p.getName() + " has left team CYAN.");
			p.teleport(Lobby.getLobbyspawnlocation());
		}
	}
	
	public static boolean isEmpty() {
		return getPlayers().isEmpty();
	}
	
	public static List<String> getPlayers() {
		return players;
	}
	
	public static void addPlayer(Player p) {
		players.add(p.getName());
	}
	
	public static void setPlayers(List<String> players) {
		TeamCyan.players = players;
	}
	
	public static void removePlayer(Player p) {
		removeArenaPlayer(p);
		players.remove(p);
	}
	
	public static boolean hasPlayer(Player p) {
		return getPlayers().contains(p.getName());
	}
	
	public static List<String> getPlayersinarena() {
		return playersinarena;
	}
	
	public static void addArenaPlayer(Player p) {
		playersinarena.add(p.getName());
	}
	
	public static void setPlayersinarena(List<String> playersinarena) {
		TeamCyan.playersinarena = playersinarena;
	}
	
	public static void removeArenaPlayer(Player p) {
		if(hasArenaPlayer(p)) {
			playersinarena.remove(p.getName());
			p.teleport(Lobby.getLobbyspawnlocation());
		}
	}
	
	public static boolean hasArenaPlayer(Player p) {
		return getPlayersinarena().contains(p.getName());
	}
	
	public static List<String> getDeadplayers() {
		return deadplayers;
	}
	
	public static void addDeadPlayer(Player p) {
		deadplayers.add(p.getName());
	}
	
	public static void setDeadplayers(List<String> deadplayers) {
		TeamCyan.deadplayers = deadplayers;
	}
	
	public static void removeDeadPlayer(Player p) {
		if(hasDeadPlayer(p)) {
			deadplayers.remove(p.getName());
		}
	}
	
	public static boolean hasDeadPlayer(Player p) {
		return getDeadplayers().contains(p.getName());
	}
	
	public static void teleportAllPlayersToArena(Arena a) {
		for(String p : getPlayers()) {
			if(!hasArenaPlayer(Bukkit.getServer().getPlayer(p))) {
				addArenaPlayer(Bukkit.getServer().getPlayer(p));
				Bukkit.getServer().getPlayer(p).getInventory().clear();
				Utilities.givePlayerSnowballs(Bukkit.getServer().getPlayer(p));
				Bukkit.getServer().getPlayer(p).teleport(a.getCyanSide());
			}
		}
	}
	
	public static void teleportAllPlayersToLobby() {
		for(String p : getPlayers()) {
			if(hasArenaPlayer(Bukkit.getServer().getPlayer(p))) {
				removeArenaPlayer(Bukkit.getServer().getPlayer(p));
				Bukkit.getServer().getPlayer(p).teleport(Lobby.getLobbyspawnlocation());
			}
		}
	}
}
