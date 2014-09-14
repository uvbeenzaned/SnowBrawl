/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Networkery
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class TeleportFixThree implements Listener {
    private final int TELEPORT_FIX_DELAY = 15; // ticks
    private Server server;
    private Plugin plugin;

    public TeleportFixThree(Plugin plugin) {
        this.plugin = plugin;
        this.server = plugin.getServer();
        server.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerTeleport(PlayerTeleportEvent event) {

        final Player player = event.getPlayer();
        final int visibleDistance = server.getViewDistance() * 16;

        // Fix the visibility issue one tick later
        server.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                // Refresh nearby clients
                final List<Player> nearby = getPlayersWithin(player, visibleDistance);

                // System.out.println("Applying fix ... " + visibleDistance);

                // Hide every player
                updateEntities(player, nearby, false);

                // Then show them again
                server.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        updateEntities(player, nearby, true);
                    }
                }, 1);
            }
        }, TELEPORT_FIX_DELAY);
    }

    private void updateEntities(Player tpedPlayer, List<Player> players, boolean visible) {
        // Hide or show every player to tpedPlayer
        // and hide or show tpedPlayer to every player.
        for (Player player : players) {
            if (visible) {
                tpedPlayer.showPlayer(player);
                player.showPlayer(tpedPlayer);
            } else {
                tpedPlayer.hidePlayer(player);
                player.hidePlayer(tpedPlayer);
            }
        }
    }

    private List<Player> getPlayersWithin(Player player, int distance) {
        List<Player> res = new ArrayList<Player>();
        int d2 = distance * distance;
        for (Player p : server.getOnlinePlayers()) {
            if (p != player && p.getWorld() == player.getWorld() && p.getLocation().distanceSquared(player.getLocation()) <= d2) {
                res.add(p);
            }
        }
        return res;
    }
}