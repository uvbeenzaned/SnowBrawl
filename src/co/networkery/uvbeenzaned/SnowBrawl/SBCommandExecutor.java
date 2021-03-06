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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class SBCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length > 0) {
                switch (args[0].toLowerCase()) {
                    case "givearmor":
                        if (p.isOp()) {
                            p.getInventory().addItem(Rank.colorArmor(255, 0, 0));
                            p.getInventory().addItem(Rank.colorArmor(255, 74, 0));
                            p.getInventory().addItem(Rank.colorArmor(255, 119, 0));
                            p.getInventory().addItem(Rank.colorArmor(255, 195, 0));
                            p.getInventory().addItem(Rank.colorArmor(255, 255, 0));
                            p.getInventory().addItem(Rank.colorArmor(204, 255, 0));
                            p.getInventory().addItem(Rank.colorArmor(0, 255, 0));
                            p.getInventory().addItem(Rank.colorArmor(0, 255, 255));
                            p.getInventory().addItem(Rank.colorArmor(0, 0, 255));
                            p.getInventory().addItem(Rank.colorArmor(89, 0, 255));
                            p.getInventory().addItem(Rank.colorArmor(255, 0, 255));
                            p.getInventory().addItem(Rank.colorArmor(255, 0, 78));
                            p.getInventory().addItem(Rank.colorArmor(0, 0, 0));
                            p.getInventory().addItem(Rank.colorArmor(96, 96, 96));
                            p.getInventory().addItem(Rank.colorArmor(160, 160, 160));
                            p.getInventory().addItem(Rank.colorArmor(255, 255, 255));
                            return true;
                        }
                        return false;
                    case "config":
                        if (p.isOp() || p.hasPermission("SnowBrawl.config")) {
                            switch (args[1].toLowerCase()) {
                                case "set-lobby-spawn-location":
                                    Lobby.setLobbyspawnlocation(p.getLocation(), p);
                                    return true;
                                case "set-round-start-delay":
                                    if (args.length > 2) {
                                        Settings.setRoundstartdelay(Integer.parseInt(args[2]), p);
                                        return true;
                                    }
                                    return false;
                                case "set-team-points":
                                    if (args.length > 2) {
                                        Settings.setTeampoints(Integer.parseInt(args[2]), p);
                                        return true;
                                    }
                                    return false;
                                case "set-snowball-reload-delay":
                                    if (args.length > 2) {
                                        Settings.setSnowballReloadDelay(Integer.parseInt(args[2]), p);
                                        return true;
                                    }
                                    return false;
                            }
                            Help.printHelp(p);
                            Help.printOpCommands(p);
                            return true;
                        }
                        Chat.sendPPM(Chat.standardPermissionErrorMessage(), p);
                        return true;
                    case "stats":
                        if (args.length > 1) {
                            Stats s = new Stats(args[1], p);
                            if (!s.getError()) {
                                for (String msg : s.getAllStats()) {
                                    Chat.sendPPM(msg, p);
                                }
                                return true;
                            }
                        }
                        for (String msg : Stats.getGlobalStats()) {
                            Chat.sendPPM(msg, p);
                        }
                        return true;
                    case "ranks":
                        p.openInventory(RankMenu.getMenu());
                        return true;
                    case "round":
                        if (args.length > 1) {
                            switch (args[1].toLowerCase()) {
                                case "lineup":
                                    if (Round.getMapLineup().isEmpty()) {
                                        Round.generateMapLineup();
                                    }
                                    Chat.sendPPM("Next arenas coming up: ", p);
                                    for (int i = 0; i < 5; i++) {
                                        int read = i;
                                        read += 1;
                                        if (Round.getMapLineup().size() >= 5) {
                                            Chat.sendPM("    " + ChatColor.GOLD + String.valueOf(read) + ChatColor.RESET + ". " + Round.getMapLineup().get(i), p);
                                        } else {
                                            for (int j = 0; j < Round.getMapLineup().size(); j++) {
                                                read = j;
                                                read += 1;
                                                Chat.sendPM("    " + ChatColor.GOLD + String.valueOf(read) + ChatColor.RESET + ". " + Round.getMapLineup().get(j), p);
                                            }
                                            break;
                                        }
                                    }
                                    Chat.sendPM("    (arena circulation: " + ChatColor.GOLD + String.valueOf(Round.getMapLineup().size()) + ChatColor.RESET + "/" + ChatColor.BLUE + String.valueOf(Configurations.getArenasconfig().getKeys(false).size()) + ChatColor.RESET + ")", p);
                                    return true;
                            }
                        }
                        return false;
                    case "power":
                        if (args.length > 1) {
                            Stats s = new Stats(p);
                            switch (args[1].toLowerCase()) {
                                case "list":
                                    Chat.sendPPM("List of powers:", p);
                                    String pws = "";
                                    for (Powers pw : Powers.values()) {
                                        if (s.ownsPower(pw) || !Store.isEnabled()) {
                                            pws = pws + ChatColor.GREEN + pw.toString() + ChatColor.RESET + ", ";
                                        } else {
                                            pws = pws + ChatColor.DARK_GRAY + pw.toString() + ChatColor.RESET + ", ";
                                        }
                                    }
                                    Chat.sendPM(pws, p);
                                    return true;
                                case "info":
                                    if (args.length > 2) {
                                        if (Powers.toStringList().contains(Utilities.convertArgsToString(args, 2).toLowerCase())) {
                                            for (Powers value : Powers.values()) {
                                                if (value.equalsName(Utilities.convertArgsToString(args, 2))) {
                                                    Power pw = new Power(value, p);
                                                    Chat.sendPPM(pw.getInfo().get(0), p);
                                                    Chat.sendPM(pw.getInfo().get(1), p);
                                                    Chat.sendPM(pw.getInfo().get(2), p);
                                                    Chat.sendPM(pw.getInfo().get(3), p);
                                                    return true;
                                                }
                                            }
                                        }
                                        return false;
                                    }
                                    return false;
                                case "set":
                                    if (args.length > 2) {
                                        if (!TeamCyan.hasArenaPlayer(p) && !TeamLime.hasArenaPlayer(p)) {
                                            if (Powers.toStringList().contains(Utilities.convertArgsToString(args, 2).toLowerCase())) {
                                                for (Powers pw : Powers.values()) {
                                                    if (pw.equalsName(Utilities.convertArgsToString(args, 2))) {
                                                        if ((s.ownsPower(pw) || pw.equals(Powers.NONE)) || !Store.isEnabled()) {
                                                            s.setPower(pw);
                                                            Chat.sendPPM("Your power has been changed to: " + s.getPower().getType().toString(), p);
                                                            return true;
                                                        }
                                                        Chat.sendPPM("You do not own this power.  Please purchase it to use it! /" + cmd.getName() + " power info " + pw.toString() + ".", p);
                                                        return true;
                                                    }
                                                }
                                            }
                                            Chat.sendPPM("That power does not exist!  Please check /" + cmd.getName() + " power list.", p);
                                            return true;
                                        }
                                        Chat.sendPPM("You may not change your power during a round!", p);
                                        return true;
                                    }
                                    return false;
                            }
                            return false;
                        }
                        return false;
                    case "upgrade":
                        if (args.length > 1) {
                            Stats s = new Stats(p);
                            switch (args[1].toLowerCase()) {
                                case "list":
                                    Chat.sendPPM("List of upgrades:", p);
                                    String upl = "";
                                    for (Upgrades u : Upgrades.values()) {
                                        if (s.ownsUpgrade(u) || !Store.isEnabled()) {
                                            upl = upl + ChatColor.GREEN + u.toString() + ChatColor.RESET + ", ";
                                        } else {
                                            upl = upl + ChatColor.DARK_GRAY + u.toString() + ChatColor.RESET + ", ";
                                        }
                                    }
                                    Chat.sendPM(upl, p);
                                    return true;
                                case "info":
                                    if (args.length > 2) {
                                        if (Upgrades.toStringList().contains(Utilities.convertArgsToString(args, 2).toLowerCase())) {
                                            for (Upgrades value : Upgrades.values()) {
                                                if (value.equalsName(Utilities.convertArgsToString(args, 2))) {
                                                    Upgrade u = new Upgrade(value, p);
                                                    Chat.sendPPM(u.getInfo().get(0), p);
                                                    Chat.sendPM(u.getInfo().get(1), p);
                                                    Chat.sendPM(u.getInfo().get(2), p);
                                                    Chat.sendPM(u.getInfo().get(3), p);
                                                    return true;
                                                }
                                            }
                                        }
                                        return false;
                                    }
                                    return false;
                                case "enable":
                                    if (args.length > 2) {
                                        for (Upgrades u : Upgrades.values()) {
                                            if (u.toString().equalsIgnoreCase(Utilities.convertArgsToString(args, 2))) {
                                                if (s.ownsUpgrade(u) || !Store.isEnabled()) {
                                                    s.enableUpgrade(u);
                                                    Chat.sendPPM("Enabled " + u.toString() + " upgrade.", p);
                                                    return true;
                                                }
                                                Chat.sendPPM("You don't own the " + u.toString() + " upgrade!", p);
                                                return true;
                                            }
                                        }
                                        Chat.sendPPM("That upgrade doesn't exist!", p);
                                        return true;
                                    }
                                    break;
                                case "disable":
                                    if (args.length > 2) {
                                        for (Upgrades u : Upgrades.values()) {
                                            if (u.toString().equalsIgnoreCase(Utilities.convertArgsToString(args, 2))) {
                                                if (s.ownsUpgrade(u) || !Store.isEnabled()) {
                                                    s.disableUpgrade(u);
                                                    Chat.sendPPM("Disabled " + u.toString() + " upgrade.", p);
                                                    return true;
                                                }
                                                Chat.sendPPM("You don't own the " + u.toString() + " upgrade!", p);
                                                return true;
                                            }
                                        }
                                        Chat.sendPPM("That upgrade doesn't exist!", p);
                                        return true;
                                    }
                                    break;
                            }
                        }
                        return false;
                    case "arena":
                        if (p.isOp() || p.hasPermission("SnowBrawl.arena")) {
                            if (args.length > 1) {
                                switch (args[1].toLowerCase()) {
                                    case "list":
                                        if (args.length == 2) {
                                            String astring = "";
                                            int cnt = 0;
                                            Chat.sendPPM("Arena list:", p);
                                            for (String name : Arenas.getNameList()) {
                                                if (!Arena.getInstanceFromConfig(name).getEnabled()) {
                                                    astring = astring + ChatColor.DARK_GRAY + name + ChatColor.RESET + ", ";
                                                    cnt++;
                                                } else {
                                                    astring = astring + name + ", ";
                                                    cnt++;
                                                }
                                            }
                                            astring = astring + ChatColor.LIGHT_PURPLE + "[" + ChatColor.GOLD + cnt + ChatColor.LIGHT_PURPLE + "]";
                                            Chat.sendPM(astring, p);
                                            return true;

                                        } else if (args.length > 2) {
                                            Stats s = new Stats(args[2], p);
                                            if (!s.getError()) {
                                                if (!s.getArenasList().isEmpty()) {
                                                    String astring = "";
                                                    int cnt = 0;
                                                    Chat.sendPPM("Arenas created/assisted by " + args[2] + ChatColor.RESET + ":", p);
                                                    for (String name : s.getArenasList()) {
                                                        if (!Arena.getInstanceFromConfig(name).getEnabled()) {
                                                            astring = astring + ChatColor.DARK_GRAY + name + ChatColor.RESET + ", ";
                                                            cnt++;
                                                        } else {
                                                            astring = astring + name + ", ";
                                                            cnt++;
                                                        }
                                                    }
                                                    astring = astring + ChatColor.LIGHT_PURPLE + "[" + ChatColor.GOLD + cnt + ChatColor.LIGHT_PURPLE + "]";
                                                    Chat.sendPM(astring, p);
                                                    return true;
                                                }
                                                Chat.sendPPM("This player has not created or assisted in any arenas.", p);
                                                return true;
                                            }
                                            return true;
                                        }
                                        Chat.sendPPM(Chat.standardPermissionErrorMessage(), p);
                                        return true;
                                    case "info":
                                        if (args.length > 2) {
                                            Arena a = Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2));
                                            if (a != null && Configurations.getArenasconfig().contains(Utilities.convertArgsToString(args, 2)))
                                                Chat.sendPPM("Name: " + a.getName(), p);
                                            Chat.sendPPM("Description: " + a.getDescription(), p);
                                            Chat.sendPPM("Authors: " + a.getAuthorsString(), p);
                                            Chat.sendPPM("CYAN" + ChatColor.RESET + " side: " + LocationSerializer.loc2str(a.getCyanSide()), p);
                                            Chat.sendPPM("LIME" + ChatColor.RESET + " side: " + LocationSerializer.loc2str(a.getLimeSide()), p);
                                            return true;
                                        }
                                        return false;
                                    case "warp":
                                        if (args.length > 2) {
                                            if (!TeamCyan.hasArenaPlayer(p) && !TeamLime.hasArenaPlayer(p)) {
                                                if (Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2)) != null) {
                                                    p.teleport(Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2)).getCyanSide());
                                                    return true;
                                                }
                                                Chat.sendPPM("The arena name that you typed does not exist!", p);
                                                return true;
                                            }
                                            Chat.sendPPM("You may not warp to an arena during a round!", p);
                                            return true;
                                        }
                                        return false;
                                    case "add":
                                        Arenas.startArenaWizard(p);
                                        return true;
                                    case "remove":
                                        if (args.length > 2) {
                                            if (Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2)) != null) {
                                                if (Round.getMapLineup().contains(Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2)).getName())) {
                                                    Round.removeMapFromLineup(Utilities.convertArgsToString(args, 2));
                                                }
                                                Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2)).delete();
                                            }
                                            Chat.sendPPM("Removed arena successfully!", p);
                                            return true;
                                        } else {
                                            Chat.sendPPM("The arena \"" + Utilities.convertArgsToString(args, 2) + "\" does not exist in the arena list!", p);
                                        }
                                        return false;
                                    case "toggle":
                                        if (args.length > 2) {
                                            if (Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2)) != null) {
                                                Arena atoggle = Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 2));
                                                if (p.isOp() || atoggle.getAuthors().contains(p.getName())) {
                                                    if (atoggle.getEnabled()) {
                                                        atoggle.setEnabled(false);
                                                        atoggle.save();
                                                        if (Round.getMapLineup().contains(atoggle.getName())) {
                                                            Round.removeMapFromLineup(atoggle.getName());
                                                        }
                                                        Chat.sendPPM("Disabled " + atoggle.getName() + " and removed from current circulation.", p);
                                                        return true;
                                                    } else {
                                                        atoggle.setEnabled(true);
                                                        atoggle.save();
                                                        if (!Round.getMapLineup().contains(atoggle.getName())) {
                                                            Round.addMapToLineup(atoggle.getName());
                                                        }
                                                        Chat.sendPPM("Enabled " + atoggle.getName() + " for circulation.", p);
                                                        return true;
                                                    }
                                                }
                                                Chat.sendPPM("You are not allowed to disable an arena that you did not assist with/create!", p);
                                                return true;
                                            }
                                            Chat.sendPPM(Utilities.convertArgsToString(args, 2) + " does not exist in the config!", p);
                                            return true;
                                        }
                                }
                            }
                            Help.printHelp(p);
                            Help.printOpCommands(p);
                            return true;
                        }
                        Chat.sendPPM(Chat.standardPermissionErrorMessage(), p);
                        return true;
                    case "store":
                        if (args.length > 1) {
                            switch (args[1].toLowerCase()) {
                                case "buy":
                                    if (args.length > 2) {
                                        switch (args[2].toLowerCase()) {
                                            case "power":
                                                if (args.length >= 3) {
                                                    if (Powers.toStringList().contains(Utilities.convertArgsToString(args, 3).toLowerCase())) {
                                                        for (Powers pw : Powers.values()) {
                                                            if (pw.toString().equalsIgnoreCase(Utilities.convertArgsToString(args, 3))) {
                                                                Store.purchasePower(p, p, pw);
                                                                return true;
                                                            }
                                                        }
                                                    }
                                                    Chat.sendPPM("The power " + Utilities.convertArgsToString(args, 3) + " does not exist!", p);
                                                    return true;
                                                }
                                                return false;
                                            case "upgrade":
                                                if (Upgrades.toStringList().contains(Utilities.convertArgsToString(args, 3).toLowerCase())) {
                                                    for (Upgrades u : Upgrades.values()) {
                                                        if (u.toString().equalsIgnoreCase(Utilities.convertArgsToString(args, 3))) {
                                                            Store.purchaseUpgrade(p, p, u);
                                                            return true;
                                                        }
                                                    }
                                                }
                                                Chat.sendPPM("The upgrade " + Utilities.convertArgsToString(args, 3) + " does not exist!", p);
                                                return true;
                                        }
                                        return false;
                                    }
                                    return false;
                                case "toggle":
                                    if (p.isOp()) {
                                        if (Store.isEnabled()) {
                                            Store.setEnabled(false);
                                            Settings.setStoreEnabled(false, p);
                                            return true;
                                        } else {
                                            Store.setEnabled(true);
                                            Settings.setStoreEnabled(true, p);
                                            return true;
                                        }
                                    }
                                    Chat.sendPPM(Chat.standardPermissionErrorMessage(), p);
                                    return true;
                            }
                            Help.printHelp(p);
                            Help.printOpCommands(p);
                            return true;
                        }
                        Help.printHelp(p);
                        Help.printOpCommands(p);
                        return true;
                    case "help":
                        Help.printHelp(p);
                        Help.printOpCommands(p);
                        return true;
                    case "start":
                        if (p.isOp() && args.length > 1) {
                            Chat.sendPPM("Manually starting map...", p);
                            Arena a = Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 1));
                            if (a != null) {
                                Round.startMap(Arena.getInstanceFromConfig(Utilities.convertArgsToString(args, 1)));
                            } else {
                                Chat.sendPPM("The arena you are trying to start doesn't exist cornhole ( <- Toxic said it)!", p);
                            }
                        } else {
                            Round.startTimerRound();
                            Chat.sendPPM("Manually starting timer round...", p);
                        }
                        return false;
                    case "stop":
                        if (p.isOp()) {
                            Clock.stopTimer();
                            Round.setGameActive(false);
                            TeamCyan.teleportAllPlayersToLobby();
                            TeamLime.teleportAllPlayersToLobby();
                            Board.clearOutPlayers();
                            Chat.sendAllTeamsMsg(p.getName() + ChatColor.RESET + " has manually stopped all round progress!");
                            return true;
                        }
                        Chat.sendPPM(Chat.standardPermissionErrorMessage(), p);
                        return true;
                    case "lobby":
                        if (!TeamCyan.hasArenaPlayer(p) && !TeamLime.hasArenaPlayer(p))
                            p.teleport(Lobby.getLobbyspawnlocation());
                        return true;
                    case "join":
                        if ((args.length > 1)) {
                            if (args[1].equalsIgnoreCase("cyan")) {
                                TeamCyan.join(p);
                                return true;
                            }
                            if (args[1].equalsIgnoreCase("lime")) {
                                TeamLime.join(p);
                                return true;
                            }
                        }
                        if (TeamCyan.getPlayers().size() < TeamLime.getPlayers().size()) {
                            TeamCyan.join(p);
                            return true;
                        }
                        if (TeamLime.getPlayers().size() < TeamCyan.getPlayers().size()) {
                            TeamLime.join(p);
                            return true;
                        }
                        Random r = new Random(System.currentTimeMillis());
                        int n = r.nextInt(10);
                        if (n <= 5) {
                            TeamCyan.join(p);
                        }
                        if (n >= 5) {
                            TeamLime.join(p);
                        }
                        return true;
                    case "leave":
                        TeamCyan.leave(p);
                        TeamLime.leave(p);
                        return true;
                }
            } else {
                Help.printHelp(p);
                Help.printOpCommands(p);
                return true;
            }
        } else {
            sender.sendMessage(Chat.formatString("You have to be a player to run commands here."));
            return true;
        }
        return false;
    }
}
