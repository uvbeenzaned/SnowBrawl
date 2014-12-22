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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamLime {

    private static String name;
    private static ChatColor color;
    private static List<String> players = new ArrayList<String>();
    private static List<String> playersinarena = new ArrayList<String>();
    private static List<String> deadplayers = new ArrayList<String>();

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        TeamLime.name = name;
    }

    public static ChatColor getColor() {
        return color;
    }

    public static void setColor(ChatColor color) {
        TeamLime.color = color;
    }

    public static void join(Player p) {
        if (!hasPlayer(p) && !TeamCyan.hasPlayer(p)) {
            if (hasDeadPlayer(p)) {
                removeDeadPlayer(p);
            }
            addPlayer(p);
            if (p.getWorld() != Lobby.getLobbyspawnlocation().getWorld()) {
                p.teleport(Lobby.getLobbyspawnlocation());
            }
            Rank.checkRank(p);
            Chat.sendAllTeamsMsg(p.getName() + ChatColor.RESET + " has joined team LIME.");
            Board.addPlayer(p);
            PowerMenu.giveInteractItem(p);
            UpgradeMenu.giveInteractItem(p);
            StoreMenu.giveInteractItem(p);
            Round.giveLineupBook(p);
            if (!TeamCyan.isEmpty()) {
                Round.startTimerRound();
            } else {
                Chat.sendPPM("Team CYAN" + ChatColor.RESET + " has no players! Please wait until someone joins to play.", p);
            }
        } else {
            Chat.sendPPM("You're already on a team!  Please leave to join another.", p);
        }
    }

    public static void leave(Player p) {
        if (hasPlayer(p)) {
            removePlayer(p);
            Board.removePlayer(p);
            Chat.sendPPM("You've left team LIME.", p);
            Chat.sendTeamLimeMsg(p.getName() + ChatColor.RESET + " has left team LIME.");
            p.teleport(Lobby.getLobbyspawnlocation());
            p.getInventory().setChestplate(new ItemStack(Material.AIR));
            p.getInventory().remove(Material.SNOW_BALL);
            p.getInventory().remove(Material.ENCHANTED_BOOK);
            p.getInventory().remove(Material.ANVIL);
            p.getInventory().remove(Material.CHEST);
            p.getInventory().remove(Material.WRITTEN_BOOK);
            if (TeamLime.isEmpty() && !TeamCyan.isEmpty()) {
                Round.setGameActive(false);
                Clock.stopTimer();
                TeamCyan.teleportAllPlayersToLobby();
                for (String tp : TeamCyan.getPlayers()) {
                    Chat.sendPPM("There are no players on team LIME" + ChatColor.RESET + " anymore.  Please wait for someone to join!", Bukkit.getPlayer(tp));
                }
            }
        }
    }

    public static boolean isEmpty() {
        return getPlayers().isEmpty();
    }

    public static List<String> getPlayers() {
        return players;
    }

    public static void setPlayers(List<String> players) {
        TeamLime.players = players;
    }

    public static void addPlayer(Player p) {
        players.add(p.getName());
    }

    public static void removePlayer(Player p) {
        removeArenaPlayer(p);
        if (PowerCoolDown.hasCoolDownPlayer(p)) {
            PowerCoolDown.removeCoolDownPlayer(p);
        }
        players.remove(p.getName());
    }

    public static boolean hasPlayer(Player p) {
        return getPlayers().contains(p.getName());
    }

    public static boolean hasPlayer(String p) {
        return getPlayers().contains(p);
    }

    public static boolean stringContainsPlayer(String s) {
        for (String p : getPlayers()) {
            if (s.toLowerCase().contains(p.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getPlayersinarena() {
        return playersinarena;
    }

    public static void setPlayersinarena(List<String> playersinarena) {
        TeamLime.playersinarena = playersinarena;
    }

    public static void addArenaPlayer(Player p) {
        playersinarena.add(p.getName());
    }

    public static void removeArenaPlayer(Player p) {
        if (hasArenaPlayer(p)) {
            playersinarena.remove(p.getName());
            if (PowerCoolDown.hasCoolDownPlayer(p)) {
                PowerCoolDown.removeCoolDownPlayer(p);
            }
            for (PotionEffect pe : p.getActivePotionEffects()) {
                p.removePotionEffect(pe.getType());
            }
            p.setHealth((double) 20);
            p.getInventory().clear();
            p.getInventory().setBoots(new ItemStack(Material.AIR));
            Rank.checkRank(p);
            p.teleport(Lobby.getLobbyspawnlocation());
            Board.outPlayer(p);
            PowerMenu.giveInteractItem(p);
            UpgradeMenu.giveInteractItem(p);
            StoreMenu.giveInteractItem(p);
            Round.giveLineupBook(p);
        }
    }

    public static boolean hasArenaPlayer(Player p) {
        return getPlayersinarena().contains(p.getName());
    }

    public static boolean isArenaPlayersEmpty() {
        return playersinarena.isEmpty();
    }

    public static List<String> getDeadplayers() {
        return deadplayers;
    }

    public static void setDeadplayers(List<String> deadplayers) {
        TeamLime.deadplayers = deadplayers;
    }

    public static void addDeadPlayer(Player p) {
        deadplayers.add(p.getName());
        removePlayer(p);
        leave(p);
    }

    public static void removeDeadPlayer(Player p) {
        deadplayers.remove(p.getName());
    }

    public static boolean hasDeadPlayer(Player p) {
        return getDeadplayers().contains(p.getName());
    }

    public static void teleportAllPlayersToArena(Arena a) {
        for (String p : getPlayers()) {
            if (!hasArenaPlayer(Bukkit.getServer().getPlayer(p))) {
                Stats s = new Stats(Bukkit.getServer().getPlayer(p));
                addArenaPlayer(Bukkit.getServer().getPlayer(p));
                Bukkit.getServer().getPlayer(p).setGameMode(GameMode.SURVIVAL);
                //Bukkit.getServer().getPlayer(p).setFlying(false);
                Bukkit.getServer().getPlayer(p).getInventory().clear();
                Utilities.giveSnowballs(Bukkit.getServer().getPlayer(p));
                Rank.checkRank(Bukkit.getPlayer(p));
                s.getPower().apply();
                for (String upgs : s.getEnabledUpgrades()) {
                    for (Upgrades ups : Upgrades.values()) {
                        if (upgs.equalsIgnoreCase(ups.toString())) {
                            Upgrade u = new Upgrade(ups, Bukkit.getPlayer(p));
                            if (!u.getPowerConflicts().contains(s.getPower().getType())) {
                                u.apply();
                            } else {
                                s.disableUpgrade(ups);
                            }
                        }
                    }
                }
                Round.giveLineupBook(Bukkit.getServer().getPlayer(p));
                Bukkit.getServer().getPlayer(p).teleport(a.getLimeSide());
                ChunkStuckFix.checkPlayerStuck(400);
            }
        }
    }

    public static void teleportAllPlayersToLobby() {
        for (String p : getPlayers()) {
            if (hasArenaPlayer(Bukkit.getServer().getPlayer(p))) {
                removeArenaPlayer(Bukkit.getServer().getPlayer(p));
            }
        }
    }

    public static String getRandomPlayer() {
        Random r = new Random(System.currentTimeMillis());
        int rnum = r.nextInt(getPlayers().size());
        for (int i = 0; i < getPlayers().size(); i++) {
            if (i == rnum) {
                return getPlayers().get(i - 1);
            }
        }
        return null;
    }

    public static void awardTeamPoints() {
        for (String p : getPlayers()) {
            Stats s = new Stats(Bukkit.getPlayer(p));
            s.giveTeamPoints();
        }
        Chat.sendAllTeamsMsg(ChatColor.GOLD + "+" + String.valueOf(Settings.getTeamPoints() * TeamCyan.getPlayers().size()) + ChatColor.RESET + " points for all of team LIME!");
        checkAllPlayersRanks();
    }

    private static void checkAllPlayersRanks() {
        for (String p : getPlayers()) {
            Rank.checkRank(Bukkit.getPlayer(p));
        }
    }
}