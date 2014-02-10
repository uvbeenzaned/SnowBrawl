package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Help {

	private static final String cmd = "    /sb ";
	private static String version = "";
	
	public Help(Plugin pl) {
		version = pl.getDescription().getVersion().toString();
	}

	public static void printHelp(Player p) {
		List<String> cmds = new ArrayList<String>();
		Chat.sendPM(ChatColor.DARK_PURPLE + "<" + ChatColor.GOLD + "----" + ChatColor.AQUA + "SnowBrawl" + ChatColor.GOLD + "----" + ChatColor.DARK_PURPLE + ">", p);
		Chat.sendPM(" v" + ChatColor.GOLD + version + ChatColor.RESET + " - Made by " + ChatColor.AQUA + "uvbeenzaned " + ChatColor.RESET + "with the assistance of " + ChatColor.GREEN + "Toxic_Fuel" + ChatColor.RESET + ".", p);
		Chat.sendPM(ChatColor.DARK_PURPLE + "<" + ChatColor.GOLD + "-" + ChatColor.DARK_PURPLE + "> " + ChatColor.AQUA + "SnowBrawl Help" + ChatColor.DARK_PURPLE + " <" + ChatColor.GOLD + "-" + ChatColor.DARK_PURPLE + ">", p);
		cmds.add(cmd + "help - Shows this help page.");
		cmds.add(cmd + "lobby - Teleports player to game lobby.");
		cmds.add(cmd + "join - Joins you to a random team.");
		cmds.add(cmd + "leave - Removes you from your current team.");
		cmds.add(cmd + "power - Command to access all power options.");
		cmds.add("        set - Set a special power to use in rounds.");
		cmds.add("        list - List special powers that can be used in round.");
		cmds.add(cmd + "stats - Shows plugin stats and also is used for other stats below.");
		cmds.add("        <player> - Shows a players stats.");
		cmds.add("        top <amount> - Shows top 5 players unless amount argument is provided.");
		cmds.add(cmd + "round - Command to access all round operations and info.");
		cmds.add("        lineup - Shows the next 5 maps coming up.");
		for (String c : cmds) {
			Chat.sendPM(c, p);
		}
	}

	public static void printOpCommands(Player p) {
		if (p.isOp()) {
			List<String> cmds = new ArrayList<String>();
			Chat.sendPM(ChatColor.DARK_PURPLE + "<" + ChatColor.GOLD + "-" + ChatColor.DARK_PURPLE + "> " + ChatColor.AQUA + "OP Help" + ChatColor.DARK_PURPLE + " <" + ChatColor.GOLD + "-" + ChatColor.DARK_PURPLE + ">", p);
			cmds.add(cmd + "start <arena name> - Starts a new timer round unless map name is provided.");
			cmds.add(cmd + "stop - Halts all round progress and shuts down game clock.");
			cmds.add(cmd + "join <cyan/lime> - Forces join to selected team.");
			cmds.add(cmd + "config - Command to access all configuration options.");
			cmds.add("        set-lobby-spawn-location - Set the lobby spawn location to the current standing location.");
			cmds.add("        set-round-start-delay [milliseconds] - Set wait period in between rounds in milliseconds.");
			cmds.add("        set-team-points [points] - Set points received per winning player per round for the winning team.");
			cmds.add("        set-snowball-reload-delay [milliseconds] - Set the time it takes to reload a players snowballs in milliseconds.");
			cmds.add(cmd + "arena - Command to access all arena options.");
			cmds.add("        list - Shows a list of all arenas.");
			cmds.add("        info [arena name] - Shows a list of all arenas.");
			cmds.add("        warp [arena name] - Teleports you to the specified arena.");
			cmds.add("        add - Starts arena wizard.");
			cmds.add("        remove [arena name] - Removes specified arena from configuration.");
			for (String c : cmds) {
				Chat.sendPM(c, p);
			}
		}
	}

}
