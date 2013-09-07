package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;

public class Help {

	private static final String cmd = "/snowbrawl";
	
	public static void printHelp(Player p) {
		Set<String> cmds = new HashSet<String>();
		Chat.sendPPM("SnowBrawl Help: ", p);
		if(p.isOp())
		{
			cmds.addAll(getOpCommands());
		}
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
	
	public static Set<String> getOpCommands() {
		Set<String> cmds = new HashSet<String>();
		cmds.add(cmd + "start <\"arena name\"> - Starts a new timer round unless map name is provided.");
		cmds.add(cmd + "stop - Halts all round progress and shuts down game clock.");
		cmds.add(cmd + "join <cyan/lime> - Forces join to selected team.");
		cmds.add(cmd + "config - Command to access all configuration options.");
		cmds.add(cmd + "\tset-lobby-spawn-location - Set the lobby spawn location to the current standing location.");
		cmds.add(cmd + "\tset-round-start-delay [seconds] - Set wait period in between rounds in seconds.");
		cmds.add(cmd + "\tset-team-points [points] - Set points received per winning player per round for the winning team.");
		cmds.add(cmd + "arena - Command to access all arena options.");
		cmds.add(cmd + "\tlist - Shows a list of all arenas.");
		cmds.add(cmd + "\twarp [\"arena name\"] - Teleports you to the specified arena.");
		// [\"arena name\"] [\"arena description\"] [author1,author2,etc...] (quotes required)
		cmds.add(cmd + "\tadd - Starts arena wizard.");
		//cmds.add(cmd + "\tsetside - Only use this command when wizard asks.");
		cmds.add(cmd + "\tremove [\"arena name\"] - Removes specified arena from configuration.");
		return cmds;
	}
	
}
