package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Team implements Listener{
	
	private static String name;
	private static ChatColor color;
	private static Set<String> players = new HashSet<String>();
	private static Set<String> playersinarena = new HashSet<String>();
	private static Map<String, Teams> deadplayers = new HashMap<String, Teams>();
	
	public Team(JavaPlugin p)
	{
		p.getServer().getPluginManager().registerEvents(this, p);
	}
	
	public static String getName() {
		return name;
	}
	
	public static void setName(String name) {
		Team.name = name;
	}
	public static ChatColor getColor() {
		return color;
	}
	
	public static void setColor(ChatColor color) {
		Team.color = color;
	}
	
	public static void join(Player p) {
		Team.addPlayer(p);
	}
	
	public static Set<String> getPlayers() {
		return players;
	}
	
	public static void addPlayer(Player p) {
		Team.players.add(p.getName());
	}
	
	public static void setPlayers(Set<String> players) {
		Team.players = players;
	}
	
	public static void removePlayer(Player p) {
		Team.players.remove(p.getName());
	}
	
	public static boolean hasPlayer(Player p) {
		return getPlayers().contains(p);
	}
	
	public Set<String> getPlayersinarena() {
		return playersinarena;
	}
	
	public static void addArenaPlayer(Player p) {
		Team.playersinarena.add(p.getName());
	}
	
	public static void setPlayersinarena(Set<String> playersinarena) {
		Team.playersinarena = playersinarena;
	}
	
	public static void removeArenaPlayer(Player p) {
		Team.playersinarena.remove(p.getName());
		p.teleport(Lobby.getLobbyspawnlocation());
	}
	
	public Map<String, Teams> getDeadplayers() {
		return deadplayers;
	}
	
	public static void addDeadPlayer(Player p, Teams t) {
		Team.deadplayers.put(p.getName(), t);
	}
	
	public static void setDeadplayers(Map<String, Teams> deadplayers) {
		Team.deadplayers = deadplayers;
	}
	
	public static void removeDeadPlayer(Player p) {
		Team.deadplayers.remove(p.getName());
	}
	
	public static void teleportAllPlayersToArena(Arena a, Teams t) {
		for(String p : getPlayers()) {
			if(t == Teams.CYAN)
				addArenaPlayer(Bukkit.getServer().getPlayer(p));
				Bukkit.getServer().getPlayer(p).teleport(a.getCyanSide());
			if(t == Teams.LIME)
				addArenaPlayer(Bukkit.getServer().getPlayer(p));
				Bukkit.getServer().getPlayer(p).teleport(a.getLimeSide());
		}
	}
	
	public static void sendTeamMsg(String msg) {
		for(String p : getPlayers()) {
			Chat.sendPPM(msg, Bukkit.getServer().getPlayer(p));
		}
	}
	
	//eventhandlers
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e)
	{
		if(e.getEntityType() == EntityType.PLAYER) {
			Player p = (Player)e.getEntity();
			if(TeamCyan.hasPlayer(p)) {
				addDeadPlayer(p, Teams.CYAN);
				return;
			}
			if(TeamLime.hasPlayer(p)) {
				addDeadPlayer(p, Teams.LIME);
				return;
			}
		}
	}
	
}
