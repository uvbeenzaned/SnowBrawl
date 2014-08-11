package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class Clock {

    private static Plugin p = null;
    private static int cntdwn = 0;
    private static BukkitTask task = null;
    private static boolean running = false;
    public Clock(Plugin pl) {
        p = pl;
    }

    private static void schedule() {
        task = p.getServer().getScheduler().runTaskTimerAsynchronously(p, new Runnable() {
            public void run() {
                if (cntdwn > 0) {
                    if (cntdwn == 5 || cntdwn == 4 || cntdwn == 3 || cntdwn == 2 || cntdwn == 1) {
                        Board.prependScoreBoardTitle(ChatColor.BLUE + "Round " + ChatColor.RED + "-> " + String.valueOf(cntdwn), true);
                    } else {
                        Board.prependScoreBoardTitle(ChatColor.BLUE + "Round " + ChatColor.RED + "-> " + ChatColor.BLUE + String.valueOf(cntdwn), true);
                    }
                    cntdwn--;
                } else {
                    if (cntdwn <= 0) {
                        stopTimer();
                        Round.startRandomMap();
                    }
                }
            }
        }, 20L, 20L);
    }

    public static void startTimer() {
        if (!isRunning()) {
            cntdwn = Settings.getRoundstartdelay() / 1000;
            running = true;
            schedule();
        }
    }

    public static void stopTimer() {
        if (task != null && isRunning()) {
            task.cancel();
            running = false;
            Board.resetScoreBoardTitle();
        }

    }

    public static boolean isRunning() {
        return running;
    }
}
