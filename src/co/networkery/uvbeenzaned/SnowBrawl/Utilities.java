package co.networkery.uvbeenzaned.SnowBrawl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.Timer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Utilities {

	private static TreeMap<String, Integer> reloadplayers = new TreeMap<String, Integer>();
	private static ArrayList<String> playerstoremove = new ArrayList<String>();

	private static ActionListener reloadTaskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			for (Entry<String, Integer> p : reloadplayers.entrySet()) {
				if (p.getValue() != 0) {
					Chat.sendPPM(String.valueOf(p.getValue()), Bukkit.getPlayer(p.getKey()));
					p.setValue(p.getValue().intValue() - 1);
				} else {
					if (TeamCyan.hasPlayer(Bukkit.getPlayer(p.getKey())) || TeamLime.hasPlayer(Bukkit.getPlayer(p.getKey()))) {
						Bukkit.getPlayer(p.getKey()).getInventory().remove(Material.SNOW_BALL);
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

	private static Timer timer = new Timer(1000, reloadTaskPerformer);

	public static void reloadSnowballs(Player p) {
		if (!timer.isRunning()) {
			timer.setRepeats(true);
			timer.start();
		}
		if (!reloadplayers.containsKey(p.getName())) {
			if (!p.getInventory().containsAtLeast(new ItemStack(Material.SNOW_BALL), 64)) {
				p.getInventory().remove(Material.SNOW_BALL);
				Stats s = new Stats(p);
				if (!s.usingPower(Powers.INSTA_RELOAD)) {
					reloadplayers.put(p.getName(), Settings.getSnowballReloadDelay() / 1000);
					Chat.sendPPM("Reloading in...", p);
				} else {
					giveSnowballs(p);
					Chat.sendPPM("Insta-reloaded!", p);
				}
			} else {
				Chat.sendPPM("Your snowball stack is already full!", p);
			}
		} else {
			Chat.sendPPM("You are already reloading, please wait!", p);
		}
	}

	public static void removeSnowballReloadPlayer(Player p) {
		if (reloadplayers.containsKey(p.getName()))
			reloadplayers.remove(p.getName());
	}

	public static void stopReloadTimer() {
		timer.stop();
	}

	public static void giveSnowballs(Player p) {
		p.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 64));
	}

	public static void checkTeams() {
		if (TeamCyan.isArenaPlayersEmpty()) {
			stopReloadTimer();
			Chat.sendAllTeamsMsg("Team LIME" + ChatColor.RESET + " wins!");
			TeamCyan.teleportAllPlayersToLobby();
			TeamLime.teleportAllPlayersToLobby();
			TeamLime.awardTeamPoints();
			Round.giveLeadPoints();
			Round.setGameActive(false);
			Round.startTimerRound();
		} else {
			if (TeamLime.isArenaPlayersEmpty()) {
				stopReloadTimer();
				Chat.sendAllTeamsMsg("Team CYAN" + ChatColor.RESET + " wins!");
				TeamCyan.teleportAllPlayersToLobby();
				TeamLime.teleportAllPlayersToLobby();
				TeamCyan.awardTeamPoints();
				Round.giveLeadPoints();
				Round.setGameActive(false);
				Round.startTimerRound();
			}
		}
	}

	public static void playEffects(Player hit, Player dead) {
		hit.getWorld().playSound(hit.getLocation(), Sound.NOTE_PIANO, 10, 1);
		hit.getWorld().playSound(hit.getLocation(), Sound.NOTE_PIANO, 10, 2);
		hit.getWorld().playSound(hit.getLocation(), Sound.NOTE_PIANO, 10, 3);
		hit.getWorld().playSound(hit.getLocation(), Sound.NOTE_PIANO, 10, 4);
		Location l = dead.getLocation();
		dead.getWorld().playEffect(l, Effect.ENDER_SIGNAL, 0);
		l.setY(dead.getLocation().getY() + 1);
		dead.getWorld().playEffect(l, Effect.ENDER_SIGNAL, 0);
	}

	public static void reloadSound(Player p) {
		if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
			Stats s = new Stats(p);
			if (s.usingPower(Powers.SNIPER)) {
				p.getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
			} else {
				p.getWorld().playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
			}
		}
	}

	public static String convertArgsToString(String[] args, int startpoint) {
		String aname = "";
		for (int i = startpoint; i < args.length; i++) {
			aname = aname + args[i] + " ";
		}
		return aname.trim();
	}

	public static List<String> getPowersList() {
		List<String> pwl = new ArrayList<String>();
		for (Powers pw : Powers.values()) {
			pwl.add(pw.toString().toLowerCase());
		}
		return pwl;
	}

	public static long getBlockDistance(Location a, Location b) {
		long sqdist = (long) a.distanceSquared(b);
		return (long) Math.sqrt(sqdist);
	}
}
