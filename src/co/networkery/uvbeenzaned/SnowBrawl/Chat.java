package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {

	private static String format = ChatColor.GOLD + "[" + ChatColor.AQUA + "SnowBrawl" + ChatColor.GOLD + "] " + ChatColor.RESET;
	private static String nopermissionerrormsg = "You do not have permission to use that!";
	
	public static String formatString(String s)
	{
		return format + s;
	}
	
	public static void sendPPM(String msg, Player p)
	{
		p.sendMessage(format + msg);
	}
	
	public static void sendPM(String msg, Player p)
	{
		p.sendMessage(msg);
	}
	
	public static String standardPermissionErrorMessage()
	{
		return nopermissionerrormsg;
	}
}
