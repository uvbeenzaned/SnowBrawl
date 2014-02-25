package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Arenas implements Listener {

	private static Map<String, Integer> wizardplayers = new HashMap<String, Integer>();
	private static List<Arena> arenawizardlist = new ArrayList<Arena>();

	public Arenas(JavaPlugin p) {
		p.getServer().getPluginManager().registerEvents(this, p);
	}

	public static List<String> getNameList() {
		List<String> n = new ArrayList<String>();
		for (String key : Configurations.getArenasconfig().getKeys(false)) {
			Arena a = Arena.getInstanceFromConfig(key);
			n.add(a.getName());
		}
		return n;
	}

	public static void startArenaWizard(Player p) {
		wizardplayers.put(p.getName(), 1);
		Arena a = new Arena();
		a.setSender(p);
		arenawizardlist.add(a);
		Chat.sendPPM("Please type an arena name to use:", p);
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (wizardplayers.containsKey(e.getPlayer().getName())) {
			e.setCancelled(true);
			Player p = e.getPlayer();
			Arena arenatoremove = new Arena();
			if (e.getMessage() != "cancel") {
				for (Arena a : arenawizardlist) {
					if (p.getName() == a.getSender().getName()) {
						switch ((int) wizardplayers.get(p.getName())) {
						case 1:
							a.setName(e.getMessage());
							Chat.sendPPM("Added arena name!", p);
							wizardplayers.put(p.getName(), 2);
							Chat.sendPPM("Please type a brief description of this new arena:", p);
							break;
						case 2:
							a.setDescription(e.getMessage());
							Chat.sendPPM("Added arena description!", p);
							wizardplayers.put(p.getName(), 3);
							Chat.sendPPM("Please type the author(s) of this arena (space each author out):", p);
							break;
						case 3:
							List<String> authors = new ArrayList<String>();
							for (String s : e.getMessage().split(" ")) {
								authors.add(s);
							}
							a.setAuthors(authors);
							Chat.sendPPM("Added authors to arena!", p);
							wizardplayers.put(p.getName(), 4);
							Chat.sendPPM("Please locate Cyan's" + ChatColor.RESET + " spawn location and stand there, then type the word set.", p);
							break;
						case 4:
							if (e.getMessage().equalsIgnoreCase("set")) {
								a.setCyanSide(p.getLocation());
								Chat.sendPPM("Set Cyan's" + ChatColor.RESET + " spawn point!", p);
								wizardplayers.put(p.getName(), 5);
								Chat.sendPPM("Please locate Lime's" + ChatColor.RESET + " spawn location and stand there, then type the word set.", p);
								break;
							} else {
								Chat.sendPPM("Try typing the word set please!", p);
								break;
							}
						case 5:
							if (e.getMessage().equalsIgnoreCase("set")) {
								a.setLimeSide(p.getLocation());
								Chat.sendPPM("Set Lime's" + ChatColor.RESET + "  spawn point!", p);
								Chat.sendPPM("Saved arena successfully!", p);
								a.save();
								if (!Round.getMapLineup().contains(a.getName())) {
									Round.addMapToLineup(a.getName());
								}
								arenatoremove = a;
								wizardplayers.remove(p.getName());
								break;
							} else {
								Chat.sendPPM("Try typing the word set please!", p);
								break;
							}
						}
					}
				}
				arenawizardlist.remove(arenatoremove);
			} else {
				for (Arena a : arenawizardlist) {
					if (a.getSender() == e.getPlayer()) {
						arenatoremove = a;
					}
				}
				arenawizardlist.remove(arenatoremove);
				wizardplayers.remove(e.getPlayer().getName());
				Chat.sendPPM("Cancelled arena wizard!", p);
			}
		}
	}

}
