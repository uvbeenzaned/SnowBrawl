package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {

	private static String format = ChatColor.GOLD + "[" + ChatColor.AQUA + "SnowBrawl" + ChatColor.GOLD + "] " + ChatColor.RESET;
	
	public static void sendPPM(String msg, Player p)
	{
		p.sendMessage(format + msg);
	}
	
	public static void sendPM(String msg, Player p)
	{
		p.sendMessage(msg);
	}
}
