package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

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
        cmds.add(cmd + "join <cyan/lime> - Joins you to a random team or to cyan or lime.");
        cmds.add(cmd + "leave - Removes you from your current team.");
        cmds.add(cmd + "power - Command to access all power options.");
        cmds.add("        set - Set a special power to use in rounds.");
        cmds.add("        list - List special powers that can be used in round.");
        cmds.add("        info <power name> - Get information about a certain power.");
        cmds.add(cmd + "upgrade - Command to access all upgrade options.");
        cmds.add("        list - List all available upgrades.");
        cmds.add("        enable <upgrade name> - Enable an upgrade.");
        cmds.add("        disable <upgrade name> - Disable an upgrade.");
        cmds.add(cmd + "store - Command for accessing all store options.");
        cmds.add("        buy power <power name> - Purchases the power for use in game if funds are sufficient.");
        cmds.add(cmd + "stats - Shows plugin stats and also is used for other stats below.");
        cmds.add("        <player> - Shows a players stats.");
        cmds.add(cmd + "ranks - Opens a menu of rank chestplates with info about each rank.");
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
            cmds.add("        list [player name] - Shows a list of all arenas or with the addition of [player name] you can see all of the arenas that one player made/assisted in.");
            cmds.add("        info <arena name> - Shows information about the specified arena.");
            cmds.add("        warp <arena name> - Teleports you to the specified arena.");
            cmds.add("        add - Starts arena wizard.");
            cmds.add("        remove <arena name> - Removes specified arena from configuration.");
            cmds.add("        toggle <arena name> - Enable/Disable the map from circulation.");
            cmds.add("        scroll - Turns on quick scroll mode to find an arena quickly.");
            cmds.add(cmd + "store - Command for accessing all store options.");
            cmds.add("        toggle - Turn the Store off/on making all the powers free or purchasable.");
            for (String c : cmds) {
                Chat.sendPM(c, p);
            }
        }
    }

}
