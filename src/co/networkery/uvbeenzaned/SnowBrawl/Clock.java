package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class Clock {

	private static Plugin p = null;

	public Clock(Plugin pl) {
		p = pl;
	}

	private static int cntdwn = 0;
	private static int printerval = 10;
	private static BukkitTask task = null;

	private static void schedule() {
		task = p.getServer().getScheduler().runTaskTimer(p, new Runnable() {
			public void run() {
				if (cntdwn > 0) {
					if (cntdwn % printerval == 0) {
						Chat.sendAllTeamsMsg(ChatColor.GREEN + "Round" + ChatColor.RED + " -> " + ChatColor.BLUE + String.valueOf(cntdwn));
					} else {
						if (cntdwn == 5 || cntdwn == 4 || cntdwn == 3 || cntdwn == 2 || cntdwn == 1) {
							Chat.sendAllTeamsMsg(ChatColor.RED + String.valueOf(cntdwn));
						}
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
		if (!Round.isGameActive() && !isRunning()) {
			cntdwn = Settings.getRoundstartdelay() / 1000;
			schedule();
		}
	}

	public static void stopTimer() {
		if (task != null)
			task.cancel();
	}

	public static boolean isRunning() {
		if (task != null) {
			return Bukkit.getScheduler().isCurrentlyRunning(task.getTaskId());
		} else {
			return false;
		}
	}
}
