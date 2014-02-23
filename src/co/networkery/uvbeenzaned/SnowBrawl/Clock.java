package co.networkery.uvbeenzaned.SnowBrawl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.bukkit.ChatColor;

public class Clock {

	private static int cntdwn = 0;
	private static int printerval = 10;

	private static ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
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
	};

	private static Timer timer = new Timer(1000, taskPerformer);

	public static Timer getTimer() {
		return timer;
	}

	public static void startTimer() {
		if (!Round.isGameActive() && !isRunning()) {
			cntdwn = Settings.getRoundstartdelay() / 1000;
			timer = new Timer(1000, taskPerformer);
			timer.setRepeats(true);
			timer.start();
		}
	}

	public static void stopTimer() {
		timer.stop();
	}

	public static boolean isRunning() {
		return timer.isRunning();
	}
}
