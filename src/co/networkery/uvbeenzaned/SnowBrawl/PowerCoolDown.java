package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class PowerCoolDown {

    private static Plugin p = null;
    private static TreeMap<String, Integer> cooldownplayers = new TreeMap<String, Integer>();
    private static ArrayList<String> playerstoremove = new ArrayList<String>();
    private static BukkitTask task = null;
    private static boolean running = false;
    public PowerCoolDown(Plugin pl) {
        p = pl;
    }

    private static void schedule() {
        task = p.getServer().getScheduler().runTaskTimerAsynchronously(p, new Runnable() {
            public void run() {
                for (Entry<String, Integer> p : cooldownplayers.entrySet()) {
                    Stats s = new Stats(Bukkit.getPlayer(p.getKey()));
                    if (p.getValue() != 0) {
                        int sec = s.getPower().time() / 1000;
                        float val = (float) ((float) 100 / sec) * ((float) p.getValue().intValue() / 100);
                        p.setValue(p.getValue().intValue() - 1);
                        if (val < 1) {
                            Bukkit.getPlayer(p.getKey()).setExp(val);
                        }
                    } else {
                        Bukkit.getPlayer(p.getKey()).setLevel(0);
                        Bukkit.getPlayer(p.getKey()).setExp(0);
                        s.getPower().apply();
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
            Stats s = new Stats(p);
            if (s.getPower().time() > 0) {
                if (!isTimerRunning()) {
                    schedule();
                    running = true;
                }
                int time = s.getPower().time();
                if (s.getEnabledUpgrades().contains(Upgrades.POWER_RELOAD_TIME_REDUCTION) && s.getPower().isTimeReducable()) {
                    double reducer = 0.85;
                    for (int r = 0; r < Round.getPlayerLead(p); r++) {
                        reducer -= 0.05;
                        time = (int) (time * reducer);
                    }
                }
                time /= 1000;
                cooldownplayers.put(p.getName(), time);
                Chat.sendPPM(time + " second power cooldown...", p);
            } else {
                Chat.sendPPM("Please wait until the cooldown process is over!", p);
            }
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
