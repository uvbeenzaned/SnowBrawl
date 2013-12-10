package co.networkery.uvbeenzaned.SnowBrawl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.Timer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Utilities {

	private static TreeMap<String, Integer> reloadplayers = new TreeMap<String, Integer>();
	private static ArrayList<String> playerstoremove = new ArrayList<String>();

	private static ActionListener reloadTaskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			for (Entry<String, Integer> p : reloadplayers.entrySet()) {
				if (p.getValue() != 0) {
					Chat.sendPPM(String.valueOf(p.getValue()),
							Bukkit.getPlayer(p.getKey()));
					p.setValue(p.getValue().intValue() - 1);
				} else {
					if (TeamCyan.hasPlayer(Bukkit.getPlayer(p.getKey()))
							|| TeamLime.hasPlayer(Bukkit.getPlayer(p.getKey()))) {
						Bukkit.getPlayer(p.getKey()).getInventory()
								.remove(Material.SNOW_BALL);
					}
					giveSnowballs(Bukkit.getPlayer(p.getKey()));
					playerstoremove.add(p.getKey());
				}
			}
			for (String p : playerstoremove) {
				reloadplayers.remove(p);
			}
			playerstoremove.clear();
			if (reloadplayers.isEmpty()) {
				timer.stop();
			}
		}
	};

	private static Timer timer = new Timer(1000, reloadTaskPerformer);;

	public static void reloadSnowballs(Player p) {
		if (!timer.isRunning()) {
			timer.setRepeats(true);
			timer.start();
		}
		if (!reloadplayers.containsKey(p.getName())) {
			p.getInventory().remove(Material.SNOW_BALL);
			reloadplayers.put(p.getName(),
					Settings.getSnowballReloadDelay() / 1000);
			Chat.sendPPM("Reloading in...", p);
		} else {
			Chat.sendPPM("You are already reloading, please wait!", p);
		}
	}

	public static void giveSnowballs(Player p) {
		p.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 64));
	}

	public static String convertArenaArgsToString(String[] args, int startpoint) {
		String aname = "";
		for (int i = startpoint; i < args.length; i++) {
			aname = aname + args[i] + " ";
		}
		return aname.trim();
	}
}
