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

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class GameListener implements Listener {

    /**
     * The constructor the game listener.
     *
     * @param p The plugin to initialize this class with.
     */
    public GameListener(JavaPlugin p) {
        p.getServer().getPluginManager().registerEvents(this, p);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        TeamCyan.leave(p);
        TeamLime.leave(p);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = (Player) e.getEntity();
        if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
            Stats s = new Stats(p);
            e.getDrops().clear();
            p.setHealth(p.getMaxHealth());
            TeamCyan.removeArenaPlayer(p);
            TeamLime.removeArenaPlayer(p);
            Utilities.checkTeams();
            s.removePoints(1);
            Chat.sendPPM(ChatColor.RED + "-1 " + ChatColor.RESET + "point for dying!", p);
            Rank.checkRank(p);
        } else if (TeamCyan.hasPlayer(p) || TeamLime.hasPlayer(p)) {
            e.getDrops().clear();
            p.setHealth(p.getMaxHealth());
            p.teleport(Lobby.getLobbyspawnlocation());
            Rank.checkRank(p);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        Player plhit = null;
        String method = "";
        if (e.getEntity() instanceof Player) {
            plhit = (Player) e.getEntity();
        }
        if (e.getDamager() instanceof Snowball || e.getDamager() instanceof Arrow) {
            Entity dgr = e.getDamager();
            Player plenemy = null;
            if (dgr instanceof Snowball) {
                if (((Snowball) e.getDamager()).getShooter() instanceof Player) {
                    plenemy = (Player) ((Snowball) e.getDamager()).getShooter();
                    method = "Snowball";
                }
            }
            if (dgr instanceof Arrow) {
                if (((Arrow) e.getDamager()).getShooter() instanceof Player) {
                    plenemy = (Player) ((Arrow) e.getDamager()).getShooter();
                    method = "Sniper Rifle";
                }
            }
            if (dgr.getType() == EntityType.PLAYER) {
                plenemy = (Player) dgr;
            }
            if (e.getEntity().getType().equals(EntityType.ITEM_FRAME) || e.getEntity().getType().equals(EntityType.PAINTING)) {
                if (plenemy != null) {
                    if (TeamCyan.hasPlayer(plenemy) || TeamLime.hasPlayer(plenemy)) {
                        e.setCancelled(true);
                    }
                }
            }
            if (plhit != plenemy && plhit != null) {
                if (TeamCyan.hasArenaPlayer(plhit) || TeamLime.hasArenaPlayer(plhit)) {
                    if (TeamCyan.hasArenaPlayer(plenemy) || TeamLime.hasArenaPlayer(plenemy)) {
                        if (!TeamCyan.hasArenaPlayer(plhit) || !TeamCyan.hasArenaPlayer(plenemy)) {
                            if (!TeamLime.hasArenaPlayer(plhit) || !TeamLime.hasArenaPlayer(plenemy)) {
                                if ((e.getEntity() instanceof Player && (e.getDamager() instanceof Snowball || e.getDamager() instanceof Arrow))) {
                                    e.setCancelled(true);
                                    Stats s = new Stats(plhit);
                                    if (s.usingPower(Powers.ABSORPTION) && Utilities.hasSnowballReloadPlayer(plhit) && StaticData.canUseAbsorption(plhit)) {
                                        plhit.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 1));
                                        Utilities.playerEnderAbsorptionEffect(plhit);
                                    } else {
                                        long shotdistance = Utilities.getBlockDistance(plenemy.getLocation(), plhit.getLocation());
                                        Chat.sendAllTeamsMsg(plenemy.getName() + ChatColor.RED + " -> " + plhit.getName() + ChatColor.GOLD + " [" + ChatColor.DARK_PURPLE + String.valueOf(shotdistance) + " blocks, " + method + ChatColor.GOLD + "]");
                                        s.removePoints(1);
                                        Chat.sendPPM(ChatColor.RED + "-1" + ChatColor.RESET + " point!", plhit);
                                        s.incrementLossCount();
                                        Utilities.playEffects(plenemy, plhit);
                                        Utilities.removeSnowballReloadPlayer(plhit);
                                        TeamCyan.removeArenaPlayer(plhit);
                                        TeamLime.removeArenaPlayer(plhit);
                                        Rank.checkRank(plhit);
                                        s = new Stats(plenemy);
                                        s.addPoints(1);
                                        Chat.sendPPM(ChatColor.GOLD + "+1" + ChatColor.RESET + " point!", plenemy);
                                        s.incrementHitsCount();
                                        s.checkShotDistanceRecord(method, shotdistance);
                                        Round.addPlayerLead(plenemy, 1);
                                        Rank.checkRank(plenemy);
                                        if (!TeamCyan.isArenaPlayersEmpty() && !TeamLime.isArenaPlayersEmpty()) {
                                            Chat.sendAllTeamsMsg(String.valueOf(TeamCyan.getPlayersinarena().size()) + " CYAN" + ChatColor.RESET + " vs " + String.valueOf(TeamLime.getPlayersinarena().size()) + " LIME");
                                        }
                                        Utilities.checkTeams();
                                    }
                                }
                                if ((e.getEntity() instanceof Player && e.getDamager() instanceof Player)) {
                                    e.setCancelled(true);
                                }
                            }
                        }
                    }
                }
            }
            if (e.getEntity() instanceof Entity && (e.getDamager() instanceof Snowball || e.getDamager() instanceof Arrow) && !(e.getEntity() instanceof Player) && e.getEntityType().isAlive()) {
                if (TeamCyan.hasArenaPlayer(plenemy) || TeamLime.hasArenaPlayer(plenemy)) {
                    e.setCancelled(true);
                    Entity mob = e.getEntity();
                    Firework fw = mob.getWorld().spawn(mob.getLocation(), Firework.class);
                    FireworkMeta fwm = fw.getFireworkMeta();
                    FireworkEffect effect = FireworkEffect.builder().withColor(Color.AQUA).with(Type.BALL_LARGE).build();
                    if (TeamCyan.hasArenaPlayer(plenemy)) {
                        effect = FireworkEffect.builder().withColor(Color.AQUA).with(Type.BALL_LARGE).withFlicker().withTrail().build();
                    }
                    if (TeamLime.hasArenaPlayer(plenemy)) {
                        effect = FireworkEffect.builder().withColor(Color.GREEN).with(Type.BALL_LARGE).withFlicker().withTrail().build();
                    }
                    fwm.addEffects(effect);
                    fwm.setPower(1);
                    fw.setFireworkMeta(fwm);
                    Stats s = new Stats(plenemy);
                    s.addPoints(1);
                    String mobname = mob.getType().toString().toLowerCase();
                    if (mobname.contains("_")) {
                        for (String mn : mobname.split("_")) {
                            mobname += mn + " ";
                        }
                    }
                    Chat.sendAllTeamsMsg(ChatColor.GOLD + "+1" + ChatColor.RESET + " point for " + plenemy.getName() + ChatColor.RED + " -> " + mobname + ChatColor.RESET + ChatColor.GOLD + " [" + ChatColor.DARK_PURPLE + String.valueOf(Utilities.getBlockDistance(plenemy.getLocation(), mob.getLocation())) + " blocks, " + method + ChatColor.GOLD + "]");
                    mob.remove();
                }
            }
        }
    }
}
