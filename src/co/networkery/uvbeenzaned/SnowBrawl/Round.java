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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.*;
import java.util.Map.Entry;

public class Round {

    private static ArrayList<String> shuffle = new ArrayList<String>();
    private static Map<String, Integer> leads = new HashMap<String, Integer>();
    private static Random r = new Random();
    private static boolean gameactive = false;

    public static void startTimerRound() {
        if (!isGameActive() && !Clock.isRunning()) {
            Clock.startTimer();
        }
    }

    public static void startRandomMap() {
        if (!isGameActive() && !Clock.isRunning()) {
            if (!shuffle.isEmpty()) {
                startMap(Arena.getInstanceFromConfig(shuffle.get(0)));
                shuffle.remove(0);
            } else {
                generateMapLineup();
                startMap(Arena.getInstanceFromConfig(shuffle.get(0)));
                shuffle.remove(0);
            }
        }
    }

    public static void startMap(Arena a) {
        if (!isGameActive() && !Clock.isRunning()) {
            TeamCyan.teleportAllPlayersToArena(a);
            TeamLime.teleportAllPlayersToArena(a);
            String arenamsg = ChatColor.GOLD + "[" + ChatColor.DARK_PURPLE + "Arena" + ChatColor.GOLD + "] " + ChatColor.RESET + a.getName();
            Chat.sendAllTeamsMsg(arenamsg);
            Chat.sendAllTeamsMsg("    Description: " + a.getDescription());
            Chat.sendAllTeamsMsg("    Author(s): " + a.getAuthorsString());
            setGameActive(true);
        }
    }

    public static void generateMapLineup() {
        for (String map : Configurations.getArenasconfig().getKeys(false)) {
            if (Arena.getInstanceFromConfig(map).getEnabled()) {
                shuffle.add(map);
            }
        }
        shuffleMapLineup();
    }

    public static ArrayList<String> getMapLineup() {
        return shuffle;
    }

    public static void addMapToLineup(String name) {
        shuffle.add(name);
    }

    public static void removeMapFromLineup(String name) {
        shuffle.remove(name);
    }

    public static void shuffleMapLineup() {
        r.setSeed(System.currentTimeMillis());
        Collections.shuffle(shuffle, r);
    }

    public static void giveLineupBook(Player p) {
        p.getInventory().setItem(8, getLineupBook());
    }

    public static ItemStack getLineupBook() {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
        BookMeta bm = (BookMeta) book.getItemMeta();
        bm.setDisplayName("Round line-up...");
        bm.setAuthor("SnowBrawl");
        bm.setTitle("Round line-up...");
        StringBuilder bookmaps = new StringBuilder();
        if (getMapLineup().isEmpty()) {
            generateMapLineup();
        }
        bookmaps.append("Next arenas coming up:\n");
        for (int i = 0; i < 8; i++) {
            int read = i;
            read += 1;
            if (getMapLineup().size() >= 8) {
                bookmaps.append(String.valueOf(read) + ". " + getMapLineup().get(i) + "\n");
            } else {
                for (int j = 0; j < getMapLineup().size(); j++) {
                    read = j;
                    read += 1;
                    bookmaps.append(String.valueOf(read) + ". " + getMapLineup().get(j) + "\n");
                }
                break;
            }
        }
        bookmaps.append("(arena circulation: " + String.valueOf(getMapLineup().size()) + "/" + String.valueOf(Configurations.getArenasconfig().getKeys(false).size()) + ")\n");
        bm.addPage(bookmaps.toString());
        book.setItemMeta(bm);
        return book;
    }

    public static Map<String, Integer> getLeads() {
        return leads;
    }

    public static void setLeads(Map<String, Integer> leads) {
        Round.leads = leads;
    }

    public static void giveLeadPoints() {
        if (!getLeads().isEmpty()) {
            String winner = "";
            int pts = 0;
            for (Entry<String, Integer> p : getLeads().entrySet()) {
                if (pts < p.getValue()) {
                    pts = p.getValue();
                    winner = p.getKey();
                }
            }
            Stats s = new Stats(Bukkit.getPlayer(winner));
            s.addPoints(pts * pts);
            clearLeads();
            Chat.sendAllTeamsMsg(winner + ChatColor.RESET + " got the lead and was awarded " + ChatColor.GOLD + String.valueOf(pts * pts) + ChatColor.RESET + " points.");
        }
    }

    public static void clearLeads() {
        getLeads().clear();
    }

    public static int getPlayerLead(Player p) {
        if (getLeads().containsKey(p.getName()))
            return getLeads().get(p.getName());
        if (!getLeads().containsKey(p.getName()))
            return 0;
        return 0;
    }

    public static void addPlayerLead(Player p, int pts) {
        getLeads().put(p.getName(), getPlayerLead(p) + pts);
    }

    public static void removePlayerLead(Player p) {
        getLeads().remove(p.getName());
    }

    public static boolean isGameActive() {
        return gameactive;
    }

    public static void setGameActive(boolean b) {
        gameactive = b;
    }
}
