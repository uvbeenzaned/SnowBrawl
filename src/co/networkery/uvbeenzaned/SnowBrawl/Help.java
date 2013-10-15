package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Help {

	private static final String cmd = "/snowbrawl ";
	
	public static void printHelp(Player p) {
		List<String> cmds = new ArrayList<String>();
		Chat.sendPPM("SnowBrawl Help: ", p);
		Chat.sendPPM("Lime and Cyan test!", p);
		cmds.add(cmd + "help - Shows this help page.");
		cmds.add(cmd + "lobby - Teleports player to game lobby.");
		cmds.add(cmd + "join - Joins you to a random team.");
		cmds.add(cmd + "leave - Removes you from your current team.");
		cmds.add(cmd + "rank <player> - Shows your rank or another players rank.");
		cmds.add(cmd + "stats - Shows plugin stats.");
		cmds.add(cmd + "top <amount> - Shows top 5 players unless amount argument is provided.");
		for(String c : cmds) {
			Chat.sendPM(c, p);
		}
	}
	
	public static void printOpCommands(Player p) {
		if(p.isOp()) {
			List<String> cmds = new ArrayList<String>();
			cmds.add(cmd + "----Op commands----");
			cmds.add(cmd + "start <\"arena name\"> - Starts a new timer round unless map name is provided.");
			cmds.add(cmd + "stop - Halts all round progress and shuts down game clock.");
			cmds.add(cmd + "join <cyan/lime> - Forces join to selected team.");
			cmds.add(cmd + "config - Command to access all configuration options.");
			cmds.add(cmd + "    set-lobby-spawn-location - Set the lobby spawn location to the current standing location.");
			cmds.add(cmd + "    set-round-start-delay [milliseconds] - Set wait period in between rounds in milliseconds.");
			cmds.add(cmd + "    set-team-points [points] - Set points received per winning player per round for the winning team.");
			cmds.add(cmd + "arena - Command to access all arena options.");
			cmds.add(cmd + "    list - Shows a list of all arenas.");
			cmds.add(cmd + "    info [arena name] - Shows a list of all arenas.");
			cmds.add(cmd + "    warp [arena name] - Teleports you to the specified arena.");
			cmds.add(cmd + "    add - Starts arena wizard.");
			cmds.add(cmd + "    remove [arena name] - Removes specified arena from configuration.");
			for(String c : cmds) {
				Chat.sendPM(c, p);
			}
		}
	}
	
}
