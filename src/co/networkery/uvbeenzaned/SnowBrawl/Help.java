/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Networkery
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Help {

    private static final String cmd = "    /sb ";
    private static String version = "";

    /**
     * The constructor the help class.
     * @param pl The plugin to initialize this class with.
     */
    public Help(Plugin pl) {
        version = pl.getDescription().getVersion().toString();
    }

    /**
     * Print the plugin commands to a player.
     * @param p The player to print to.
     */
    public static void printHelp(Player p) {
        List<String> cmds = new ArrayList<String>();
        Chat.sendPM(ChatColor.DARK_PURPLE + "<" + ChatColor.GOLD + "----" + ChatColor.AQUA + "SnowBrawl" + ChatColor.GOLD + "----" + ChatColor.DARK_PURPLE + ">", p);
        Chat.sendPM(" v" + ChatColor.GOLD + version + ChatColor.RESET + " - Made by " + ChatColor.AQUA + "uvbeenzaned " + ChatColor.RESET + " and " + ChatColor.GREEN + "Toxic_Fuel" + ChatColor.RESET + ".", p);
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
        cmds.add(cmd + "round - Command to access all round operations and info.");
        cmds.add("        lineup - Shows the next 5 maps coming up.");
        for (String c : cmds) {
            Chat.sendPM(c, p);
        }
    }

    /**
     * Print special commands to ops.
     * @param p The player to print to.
     */
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
