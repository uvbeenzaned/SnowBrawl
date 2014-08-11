package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class ChunkStuckFix {

    private static Plugin p = null;

    public ChunkStuckFix(Plugin pl) {
        p = pl;
    }

    private static void schedule(long ticks) {
        p.getServer().getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
            public void run() {
                for (String p : TeamCyan.getPlayersinarena()) {
                    if (Bukkit.getPlayer(p).getLocation().getBlock().getType() != Material.AIR) {
                        World w = Bukkit.getPlayer(p).getWorld();
                        int x = Bukkit.getPlayer(p).getLocation().getBlockX();
                        int y = Bukkit.getPlayer(p).getLocation().getBlockY();
                        int z = Bukkit.getPlayer(p).getLocation().getBlockZ();
                        while (Bukkit.getPlayer(p).getWorld().getBlockAt(x, y, z).getType() != Material.AIR) {
                            y++;
                        }
                        Bukkit.getPlayer(p).teleport(new Location(w, x, y, z));
                    }
                }
                for (String p : TeamLime.getPlayersinarena()) {
                    if (Bukkit.getPlayer(p).getLocation().getBlock().getType() != Material.AIR) {
                        World w = Bukkit.getPlayer(p).getWorld();
                        int x = Bukkit.getPlayer(p).getLocation().getBlockX();
                        int y = Bukkit.getPlayer(p).getLocation().getBlockY();
                        int z = Bukkit.getPlayer(p).getLocation().getBlockZ();
                        while (Bukkit.getPlayer(p).getWorld().getBlockAt(x, y, z).getType() != Material.AIR) {
                            y++;
                        }
                        Bukkit.getPlayer(p).teleport(new Location(w, x, y, z));
                    }
                }
            }
        }, ticks);
    }

    public static void checkPlayerStuck(int delay) {
        schedule((delay / 1000) * 20);
    }

}
