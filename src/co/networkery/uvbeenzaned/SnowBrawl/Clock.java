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
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class Clock {

    private static Plugin p = null;
    private static int cntdwn = 0;
    private static BukkitTask task = null;
    private static boolean running = false;

    /**
     * The game clock constructor.
     *
     * @param pl The plugin to initialize this class with.
     */
    public Clock(Plugin pl) {
        p = pl;
    }

    /**
     * The central game clock scheduler.
     */
    private static void schedule() {
        task = p.getServer().getScheduler().runTaskTimerAsynchronously(p, new Runnable() {
            public void run() {
                if (cntdwn > 0) {
                    if (cntdwn == 5 || cntdwn == 4 || cntdwn == 3 || cntdwn == 2 || cntdwn == 1) {
                        Board.prependScoreBoardTitle(ChatColor.BLUE + "Round " + ChatColor.RED + "-> " + String.valueOf(cntdwn), true);
                    } else {
                        Board.prependScoreBoardTitle(ChatColor.BLUE + "Round " + ChatColor.RED + "-> " + ChatColor.BLUE + String.valueOf(cntdwn), true);
                    }
                    for (String p : TeamCyan.getPlayers())
                        Utilities.checkPlayerInventoryItems(Bukkit.getPlayer(p));
                    for (String p : TeamLime.getPlayers())
                        Utilities.checkPlayerInventoryItems(Bukkit.getPlayer(p));
                    cntdwn--;
                } else {
                    if (cntdwn <= 0) {
                        stopTimer();
                        Round.startRandomMap();
                    }
                }
            }
        }, 0L, 20L);
    }

    /**
     * Start the round game clock.
     */
    public static void startTimer() {
        if (!isRunning()) {
            cntdwn = Settings.getRoundstartdelay() / 1000;
            running = true;
            schedule();
        }
    }

    /**
     * Stop the game clock.
     */
    public static void stopTimer() {
        if (task != null && isRunning()) {
            task.cancel();
            running = false;
            Board.resetScoreBoardTitle();
        }

    }

    /**
     * Check to see if the game clock is running.
     *
     * @return True if the game clock is running, false if not.
     */
    public static boolean isRunning() {
        return running;
    }
}
