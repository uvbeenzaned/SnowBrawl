package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class Clock {

	private static Plugin p = null;

	public Clock(Plugin pl) {
		p = pl;
	}

	private static int cntdwn = 0;
	private static int printerval = 10;
	private static int taskid = -1;

	private static void schedule() {
		taskid = p.getServer().getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
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
					schedule();
				} else {
					if (cntdwn <= 0) {
						Round.startRandomMap();
					}
				}
			}
		}, 20L);
	}

	public static void startTimer() {
		if (!Round.isGameActive() && !isRunning()) {
			cntdwn = Settings.getRoundstartdelay() / 1000;
			schedule();
		}
	}

	public static void stopTimer() {
		p.getServer().getScheduler().cancelTask(taskid);
		taskid = -1;
	}

	public static boolean isRunning() {
		if(taskid == -1) {
			return false;
		} else {
			return p.getServer().getScheduler().isCurrentlyRunning(taskid);
		}
	}
}
