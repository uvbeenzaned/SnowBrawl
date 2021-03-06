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
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UpgradeListener implements Listener {

    public UpgradeListener(JavaPlugin p) {
        p.getServer().getPluginManager().registerEvents(this, p);
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        Stats s = new Stats(p);
        if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
            if (!s.getError()) {
                if (s.usingUpgrade(Upgrades.BINOCULARS)) {
                    Upgrade u = new Upgrade(Upgrades.BINOCULARS, p);
                    PotionEffect pe = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 100);
                    if (e.isSneaking()) {
                        if (p.getItemInHand().isSimilar(u.getItem())) {
                            p.addPotionEffect(pe, false);
                        }
                    } else {
                        if (p.hasPotionEffect(PotionEffectType.SLOW)) {
                            p.removePotionEffect(PotionEffectType.SLOW);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerSwitchItem(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        Stats s = new Stats(p);
        if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
            if (!s.getError()) {
                if (s.usingUpgrade(Upgrades.BINOCULARS)) {
                    Upgrade u = new Upgrade(Upgrades.BINOCULARS, p);
                    PotionEffect pe = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 100);
                    if (e.getPlayer().isSneaking() && e.getPlayer().getInventory().getItem(e.getNewSlot()) != null && e.getPlayer().getInventory().getItem(e.getNewSlot()).isSimilar(u.getItem())) {
                        if (p.getItemInHand().isSimilar(u.getItem())) {
                            p.addPotionEffect(pe, false);
                        }
                    } else {
                        if (p.hasPotionEffect(PotionEffectType.SLOW)) {
                            p.removePotionEffect(PotionEffectType.SLOW);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntityType().equals(EntityType.PLAYER)) {
            Player p = (Player) e.getEntity();
            Stats s = new Stats(p);
            if (!s.getError()) {
                if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
                    if (!StaticData.hasUsedRoundBurnSave(p)) {
                        if (s.usingUpgrade(Upgrades.BURN_SAVE)) {
                            if (e.getCause().equals(DamageCause.LAVA) || e.getCause().equals(DamageCause.FIRE) || e.getCause().equals(DamageCause.FIRE_TICK)) {
                                e.setCancelled(true);
                                Upgrade u = new Upgrade(Upgrades.BURN_SAVE, p);
                                u.applySpecific();
                                StaticData.useBurnSave(p);
                                Chat.sendPPM(ChatColor.MAGIC + "|" + ChatColor.RESET + " You have just used your one-time burn save! " + ChatColor.MAGIC + "|", p);
                            }
                        }
                    }
                }
            }
        }
    }

}