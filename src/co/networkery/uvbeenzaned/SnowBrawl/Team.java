package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class Team implements Listener{
//	
//	private static String name;
//	private static ChatColor color;
//	private static List<String> players = new ArrayList<String>();
//	private static List<String> playersinarena = new ArrayList<String>();
//	private static Map<String, Teams> deadplayers = new HashMap<String, Teams>();
//	
//	public Team(JavaPlugin p)
//	{
//		p.getServer().getPluginManager().registerEvents(this, p);
//	}
//	
//	public static String getName() {
//		return name;
//	}
//	
//	public static void setName(String name) {
//		Team.name = name;
//	}
//	public static ChatColor getColor() {
//		return color;
//	}
//	
//	public static void setColor(ChatColor color) {
//		Team.color = color;
//	}
//	
//	public static void join(Player p, Teams t) {
//		Team.addPlayer(p);
//		if(t == Teams.CYAN)
//			Chat.sendPPM("You've joined team CYAN.", p);
//			sendTeamMsg(p.getName() + " has joined team CYAN.");
//		if(t == Teams.LIME)
//			Chat.sendPPM("You've joined team LIME.", p);
//		sendTeamMsg(p.getName() + " has joined team LIME.");
//		p.teleport(Lobby.getLobbyspawnlocation());
//	}
//	
//	public static void leave(Player p, Teams t) {
//		if(hasPlayer(p))
//			removePlayer(p);
//		if(t == Teams.CYAN)
//			Chat.sendPPM("You've left team CYAN.", p);
//			sendTeamMsg(p.getName() + " has left team CYAN.");
//		if(t == Teams.LIME)
//			Chat.sendPPM("You've left team LIME.", p);
//		sendTeamMsg(p.getName() + " has left team LIME.");
//		p.teleport(Lobby.getLobbyspawnlocation());
//	}
//	
//	public static List<String> getPlayers() {
//		return players;
//	}
//	
//	public static void addPlayer(Player p) {
//		Team.players.add(p.getName());
//	}
//	
//	public static void setPlayers(List<String> players) {
//		Team.players = players;
//	}
//	
//	public static void removePlayer(Player p) {
//		getDeadplayers().remove(p.getName());
//		getPlayersinarena().remove(p.getName());
//		getPlayers().remove(p.getName());
//	}
//	
//	public static boolean hasPlayer(Player p) {
//		return getPlayers().contains(p.getName());
//	}
//	
//	public static List<String> getPlayersinarena() {
//		return playersinarena;
//	}
//	
//	public static void addArenaPlayer(Player p) {
//		Team.playersinarena.add(p.getName());
//	}
//	
//	public static void setPlayersinarena(List<String> playersinarena) {
//		Team.playersinarena = playersinarena;
//	}
//	
//	public static void removeArenaPlayer(Player p) {
//		Team.playersinarena.remove(p.getName());
//		p.teleport(Lobby.getLobbyspawnlocation());
//	}
//	
//	public static boolean hasArenaPlayer(Player p) {
//		return getPlayersinarena().contains(p.getName());
//	}
//	
//	public static Map<String, Teams> getDeadplayers() {
//		return deadplayers;
//	}
//	
//	public static void addDeadPlayer(Player p, Teams t) {
//		Team.deadplayers.put(p.getName(), t);
//	}
//	
//	public static void setDeadplayers(Map<String, Teams> deadplayers) {
//		Team.deadplayers = deadplayers;
//	}
//	
//	public static void removeDeadPlayer(Player p) {
//		Team.deadplayers.remove(p.getName());
//	}
//	
//	public static boolean hasDeadPlayer(Player p) {
//		return getDeadplayers().containsKey(p.getName());
//	}
//	
//	public static void teleportAllPlayersToArena(Arena a, Teams t) {
//		for(String p : getPlayers()) {
//			if(t == Teams.CYAN)
//				addArenaPlayer(Bukkit.getServer().getPlayer(p));
//				Bukkit.getServer().getPlayer(p).teleport(a.getCyanSide());
//			if(t == Teams.LIME)
//				addArenaPlayer(Bukkit.getServer().getPlayer(p));
//				Bukkit.getServer().getPlayer(p).teleport(a.getLimeSide());
//		}
//	}
//	
//	public static void teleportAllPlayersToLobby(Teams t) {
//		for(String p : getPlayers()) {
//			if(t == Teams.CYAN)
//				removeArenaPlayer(Bukkit.getServer().getPlayer(p));
//				Bukkit.getServer().getPlayer(p).teleport(Lobby.getLobbyspawnlocation());
//			if(t == Teams.LIME)
//				removeArenaPlayer(Bukkit.getServer().getPlayer(p));
//				Bukkit.getServer().getPlayer(p).teleport(Lobby.getLobbyspawnlocation());
//		}
//	}
//	
//	public static void sendTeamMsg(String msg) {
//		for(String p : getPlayers()) {
//			Chat.sendPPM(msg, Bukkit.getServer().getPlayer(p));
//		}
//	}
//	
//	//eventhandlers
//	@EventHandler
//	public void onPlayerDeath(PlayerDeathEvent e)
//	{
//		if(e.getEntityType() == EntityType.PLAYER) {
//			Player p = (Player)e.getEntity();
//			if(TeamCyan.hasPlayer(p)) {
//				addDeadPlayer(p, Teams.CYAN);
//				return;
//			}
//			if(TeamLime.hasPlayer(p)) {
//				addDeadPlayer(p, Teams.LIME);
//				return;
//			}
//		}
//	}
//	
}
