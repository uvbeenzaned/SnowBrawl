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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

/**
 * Fix for falling through the world because chunks don't normally load fast enough to catch the player.
 */
public class ChunkStuckFix {

    private static Plugin p = null;

    public ChunkStuckFix(Plugin pl) {
        p = pl;
    }

    private static void schedule(long ticks) {
        p.getServer().getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
            public void run() {
                for (String p : TeamCyan.getPlayersinarena()) {
                    if (Bukkit.getPlayer(p).getLocation().getBlock().getType() != Material.AIR) {
                        World w = Bukkit.getPlayer(p).getWorld();
                        int x = Bukkit.getPlayer(p).getLocation().getBlockX();
                        int y = Bukkit.getPlayer(p).getLocation().getBlockY();
                        int z = Bukkit.getPlayer(p).getLocation().getBlockZ();
                        while (Bukkit.getPlayer(p).getWorld().getBlockAt(x, y, z).getType() != Material.AIR) {
                            y++;
                        }
                        Bukkit.getPlayer(p).teleport(new Location(w, x, y, z));
                    }
                }
                for (String p : TeamLime.getPlayersinarena()) {
                    if (Bukkit.getPlayer(p).getLocation().getBlock().getType() != Material.AIR) {
                        World w = Bukkit.getPlayer(p).getWorld();
                        int x = Bukkit.getPlayer(p).getLocation().getBlockX();
                        int y = Bukkit.getPlayer(p).getLocation().getBlockY();
                        int z = Bukkit.getPlayer(p).getLocation().getBlockZ();
                        while (Bukkit.getPlayer(p).getWorld().getBlockAt(x, y, z).getType() != Material.AIR) {
                            y++;
                        }
                        Bukkit.getPlayer(p).teleport(new Location(w, x, y, z));
                    }
                }
            }
        }, ticks);
    }

    public static void checkPlayerStuck(int delay) {
        schedule((delay / 1000) * 20);
    }

}
