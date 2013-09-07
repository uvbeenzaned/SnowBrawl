package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Team {

	private static String name;
	private static ChatColor color;
	private static Set<String> players = new HashSet<String>();
	private static Set<String> playersinarena = new HashSet<String>();
	private static Set<String> deadplayers = new HashSet<String>();
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Team.name = name;
	}
	/**
	 * @return the color
	 */
	public ChatColor getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(ChatColor color) {
		Team.color = color;
	}
	public void join(Player p)
	{
		Team.addPlayer(p);
	}
	/**
	 * @return the players
	 */
	public Set<String> getPlayers() {
		return players;
	}
	public static void addPlayer(Player p)
	{
		Team.players.add(p.getName());
	}
	/**
	 * @param players the players to set
	 */
	public static void setPlayers(Set<String> players) {
		Team.players = players;
	}
	public static void removePlayer(Player p)
	{
		Team.players.remove(p.getName());
	}
	/**
	 * @return the playersinarena
	 */
	public Set<String> getPlayersinarena() {
		return playersinarena;
	}
	public static void addArenaPlayer(Player p)
	{
		Team.playersinarena.add(p.getName());
	}
	/**
	 * @param playersinarena the playersinarena to set
	 */
	public static void setPlayersinarena(Set<String> playersinarena) {
		Team.playersinarena = playersinarena;
	}
	public static void removeArenaPlayer(Player p)
	{
		Team.playersinarena.remove(p.getName());
	}
	/**
	 * @return the deadplayers
	 */
	public Set<String> getDeadplayers() {
		return deadplayers;
	}
	public static void addDeadPlayer(Player p)
	{
		Team.deadplayers.add(p.getName());
	}
	/**
	 * @param deadplayers the deadplayers to set
	 */
	public static void setDeadplayers(Set<String> deadplayers) {
		Team.deadplayers = deadplayers;
	}
	public static void removeDeadPlayer(Player p)
	{
		Team.deadplayers.remove(p.getName());
	}
	
}
