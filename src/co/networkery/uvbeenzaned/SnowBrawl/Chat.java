package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {

	private static String format = ChatColor.GOLD + "[" + ChatColor.AQUA + "SnowBrawl" + ChatColor.GOLD + "] " + ChatColor.RESET;
	private static String nopermissionerrormsg = "You do not have permission to use that!";

	public static String formatString(String s) {
		return format + s;
	}

	public static void sendPPM(String msg, Player p) {
		String nmsg = "";
		for (String s : msg.split(" ")) {
			if (s.toLowerCase().contains("cyan")) {
				nmsg = nmsg + ChatColor.AQUA + s + " ";
			}
			if (s.toLowerCase().contains("lime")) {
				nmsg = nmsg + ChatColor.GREEN + s + " ";
			}
			if (TeamCyan.stringContainsPlayer(s)) {
				nmsg = nmsg + ChatColor.AQUA + s + " ";
			}
			if (TeamLime.stringContainsPlayer(s)) {
				nmsg = nmsg + ChatColor.GREEN + s + " ";
			}
			if (!s.toLowerCase().contains("cyan") && !s.toLowerCase().contains("lime") && !TeamCyan.stringContainsPlayer(s) && !TeamLime.stringContainsPlayer(s)) {
				nmsg = nmsg + s + " ";
			}
		}
		if (p != null) {
			if (p.isValid() && !p.isDead()) {
				p.sendMessage(format + nmsg);
			}
		}
	}

	public static void sendPM(String msg, Player p) {
		p.sendMessage(msg);
	}

	public static String standardPermissionErrorMessage() {
		return nopermissionerrormsg;
	}

	public static void sendTeamCyanMsg(String msg) {
		for (String p : TeamCyan.getPlayers()) {
			Chat.sendPPM(msg, Bukkit.getServer().getPlayer(p));
		}
	}

	public static void sendTeamLimeMsg(String msg) {
		for (String p : TeamLime.getPlayers()) {
			Chat.sendPPM(msg, Bukkit.getServer().getPlayer(p));
		}
	}

	public static void sendAllTeamsMsg(String msg) {
		for (String p : TeamCyan.getPlayers()) {
			Chat.sendPPM(msg, Bukkit.getServer().getPlayer(p));
		}
		for (String p : TeamLime.getPlayers()) {
			Chat.sendPPM(msg, Bukkit.getServer().getPlayer(p));
		}
	}
	
	public static void sendAllTeamsMsgNoFormat(String msg) {
		for (String p : TeamCyan.getPlayers()) {
			Chat.sendPM(msg, Bukkit.getServer().getPlayer(p));
		}
		for (String p : TeamLime.getPlayers()) {
			Chat.sendPM(msg, Bukkit.getServer().getPlayer(p));
		}
	}
}
