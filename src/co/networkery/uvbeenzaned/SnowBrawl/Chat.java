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
		String nmsg = "";
		if(msg.toLowerCase().contains("cyan") || msg.toLowerCase().contains("lime")) {
			for(String s : msg.split(" ")) {
				if(s.equalsIgnoreCase("cyan")) {
					nmsg = nmsg + ChatColor.AQUA + s + ChatColor.RESET + " ";
				} else {
					if(s.equalsIgnoreCase("lime")) {
						nmsg = nmsg + ChatColor.GREEN + s + ChatColor.RESET + " ";
					} else {
						nmsg = nmsg + s + " ";
					}
				}
			}
		}
		else {
			nmsg = msg;
		}
		p.sendMessage(format + nmsg);
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
