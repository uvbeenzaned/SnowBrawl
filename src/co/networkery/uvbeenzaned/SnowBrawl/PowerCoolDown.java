package co.networkery.uvbeenzaned.SnowBrawl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.Timer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PowerCoolDown {

	private static TreeMap<String, Integer> cooldownplayers = new TreeMap<String, Integer>();
	private static ArrayList<String> playerstoremove = new ArrayList<String>();

	private static ActionListener coolDownTaskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
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
					// Chat.sendPPM(String.valueOf(sec),
					// Bukkit.getPlayer(p.getKey()));
					// Chat.sendPPM(String.valueOf((float) ((float) 100 / sec) *
					// ((float) p.getValue().intValue() / 100)),
					// Bukkit.getPlayer(p.getKey()));
					// Chat.sendPPM(String.valueOf(p.getValue().intValue()),
					// Bukkit.getPlayer(p.getKey()));
				} else {
					Bukkit.getPlayer(p.getKey()).setLevel(0);
					Bukkit.getPlayer(p.getKey()).setExp(0);
					pw.apply();
					playerstoremove.add(p.getKey());
				}
			}
			for (String p : playerstoremove) {
				cooldownplayers.remove(p);
			}
			playerstoremove.clear();
			if (cooldownplayers.isEmpty()) {
				timer.stop();
			}
		}
	};

	private static Timer timer = new Timer(1000, coolDownTaskPerformer);

	public static void start(Player p, int ms) {
		if (!timer.isRunning()) {
			timer.setRepeats(true);
			timer.start();
		}
		if (!cooldownplayers.containsKey(p.getName())) {
			cooldownplayers.put(p.getName(), ms / 1000);
			Chat.sendPPM(ms / 1000 + " second cooldown...", p);
		} else {
			Chat.sendPPM("Please wait until the cooldown process is over!", p);
		}
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
