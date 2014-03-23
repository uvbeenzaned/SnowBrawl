package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class PowerCoolDown {

	private static Plugin p = null;

	public PowerCoolDown(Plugin pl) {
		p = pl;
	}

	private static TreeMap<String, Integer> cooldownplayers = new TreeMap<String, Integer>();
	private static ArrayList<String> playerstoremove = new ArrayList<String>();
	private static BukkitTask task = null;
	private static boolean running = false;

	private static void schedule() {
		task = p.getServer().getScheduler().runTaskTimerAsynchronously(p, new Runnable() {
			public void run() {
				for (Entry<String, Integer> p : cooldownplayers.entrySet()) {
					Stats s = new Stats(Bukkit.getPlayer(p.getKey()));
					Power pw = new Power(s.getPower(), Bukkit.getPlayer(p.getKey()));
					if (p.getValue() != 0) {
						int sec = pw.time() / 1000;
						float val = (float) ((float) 100 / sec) * ((float) p.getValue().intValue() / 100);
						p.setValue(p.getValue().intValue() - 1);
						if (val < 1) {
							Bukkit.getPlayer(p.getKey()).setExp(val);
						}
					} else {
						Bukkit.getPlayer(p.getKey()).setLevel(0);
						Bukkit.getPlayer(p.getKey()).setExp(0);
						pw.apply();
						Utilities.reloadSound(Bukkit.getPlayer(p.getKey()));
						playerstoremove.add(p.getKey());
					}
				}
				for (String p : playerstoremove) {
					cooldownplayers.remove(p);
				}
				playerstoremove.clear();
				if (cooldownplayers.isEmpty()) {
					stopCoolDownTimer();
				}
			}
		}, 20L, 20L);
	}

	public static void start(Player p, int ms) {
		if (!cooldownplayers.containsKey(p.getName())) {
			if (!isTimerRunning()) {
				schedule();
				running = true;
			}
			cooldownplayers.put(p.getName(), ms / 1000);
			Chat.sendPPM(ms / 1000 + " second cooldown...", p);
		} else {
			Chat.sendPPM("Please wait until the cooldown process is over!", p);
		}
	}

	public static void stopCoolDownTimer() {
		if (isTimerRunning()) {
			task.cancel();
			running = false;
		}
	}

	public static boolean isTimerRunning() {
		return running;
	}

	public static boolean hasCoolDownPlayer(Player p) {
		return cooldownplayers.containsKey(p.getName());
	}

	public static void removeCoolDownPlayer(Player p) {
		if (hasCoolDownPlayer(p)) {
			p.setLevel(0);
			p.setExp(0);
			cooldownplayers.remove(p.getName());
		}
	}
}
