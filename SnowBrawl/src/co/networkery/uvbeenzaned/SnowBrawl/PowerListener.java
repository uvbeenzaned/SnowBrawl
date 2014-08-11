package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;

public class PowerListener implements Listener {

    public PowerListener(JavaPlugin p) {
        p.getServer().getPluginManager().registerEvents(this, p);
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            Player p = (Player) e.getEntity().getShooter();
            if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
                Stats s = new Stats(p);
                if (p.getItemInHand().getType() == Material.POTION) {
                    if (s.usingPower(Powers.SLOWDOWN)) {
                        if (Potion.fromItemStack(p.getItemInHand()).getType() == PotionType.SLOWNESS) {
                            if (e.getEntityType() == EntityType.SPLASH_POTION) {
                                e.getEntity().setVelocity(p.getLocation().getDirection().normalize().multiply(5));
                            }
                        }
                    }
                    if (s.usingPower(Powers.SPONTANEOUS_COMBUSTION)) {
                        if (Potion.fromItemStack(p.getItemInHand()).getType() == PotionType.POISON) {
                            if (e.getEntityType() == EntityType.SPLASH_POTION) {
                                e.getEntity().setVelocity(p.getLocation().getDirection().normalize().multiply(5));
                            }
                        }
                    }
                    if (s.usingPower(Powers.BLINDNESS)) {
                        if (Potion.fromItemStack(p.getItemInHand()).getType() == PotionType.REGEN) {
                            if (e.getEntityType() == EntityType.SPLASH_POTION) {
                                p.getInventory().remove(p.getItemInHand());
                                e.getEntity().setVelocity(p.getLocation().getDirection().normalize().multiply(5));
                            }
                        }
                    }
                }
                if (e.getEntityType() == EntityType.SNOWBALL) {
                    if (s.usingPower(Powers.VELOCITY)) {
                        e.getEntity().setVelocity(p.getLocation().getDirection().normalize().multiply(2));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            Player p = (Player) e.getEntity().getShooter();
            Stats s = new Stats(p);
            if (Potion.fromItemStack(e.getPotion().getItem()).getType() == PotionType.SLOWNESS) {
                if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
                    if (s.usingPower(Powers.SLOWDOWN)) {
                        if (!e.getAffectedEntities().isEmpty()) {
                            Chat.sendPPM("Slowdown affected:", p);
                            for (Entity en : e.getAffectedEntities()) {
                                if (en.getType() == EntityType.PLAYER) {
                                    if (TeamCyan.hasArenaPlayer((Player) en) || TeamLime.hasArenaPlayer((Player) en))
                                        Chat.sendPM("    " + ((Player) en).getName(), p);
                                }
                            }
                        } else {
                            Chat.sendPPM("Slowdown affected no players!", p);
                        }
                    }
                }
            }
            if (Potion.fromItemStack(e.getPotion().getItem()).getType() == PotionType.POISON) {
                if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
                    if (s.usingPower(Powers.SPONTANEOUS_COMBUSTION)) {
                        if (!e.getAffectedEntities().isEmpty()) {
                            Chat.sendPPM("Spontaneous Combustion affected:", p);
                            for (Entity en : e.getAffectedEntities()) {
                                if (en.getType() == EntityType.PLAYER) {
                                    if (TeamCyan.hasArenaPlayer((Player) en) || TeamLime.hasArenaPlayer((Player) en))
                                        ((Player) en).setFireTicks(200);
                                    Chat.sendPM("    " + ((Player) en).getName(), p);
                                }
                            }
                        } else {
                            Chat.sendPPM("Spontaneous Combustion affected no players!", p);
                        }
                    }
                }
            }
            if (Potion.fromItemStack(e.getPotion().getItem()).getType() == PotionType.REGEN) {
                if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
                    if (s.usingPower(Powers.BLINDNESS)) {
                        if (!e.getAffectedEntities().isEmpty()) {
                            Chat.sendPPM("Blindness affected:", p);
                            for (Entity en : e.getAffectedEntities()) {
                                if (en.getType() == EntityType.PLAYER) {
                                    if (TeamCyan.hasArenaPlayer((Player) en) || TeamLime.hasArenaPlayer((Player) en))
                                        Chat.sendPM("    " + ((Player) en).getName(), p);
                                }
                            }
                        } else {
                            Chat.sendPPM("Blindness affected no players!", p);
                        }
                    }
                }
            }
            if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
                PowerCoolDown.start(p, s.getPower().time());
            }
        }
    }

    @EventHandler
    public void onPlayerShootBow(EntityShootBowEvent e) {
        if (e.getEntity().getType() == EntityType.PLAYER) {
            Player p = (Player) e.getEntity();
            Stats s = new Stats(p);
            if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
                if (s.usingPower(Powers.SNIPER)) {
                    e.getProjectile().setVelocity(p.getLocation().getDirection().normalize().multiply(20));
                    p.getLocation().getWorld().createExplosion(p.getLocation(), 0F);
                    Location smoke = p.getLocation();
                    smoke.setY(smoke.getY() + 1);
                    for (int i = 0; i < 8; i++)
                        p.getWorld().playEffect(smoke, Effect.SMOKE, i);
                    PowerCoolDown.start(p, s.getPower().time());
                }
            }
        }
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        Stats s = new Stats(p);
        if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
            if (s.usingPower(Powers.SNIPER)) {
                PotionEffect pe = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 100);
                if (e.isSneaking()) {
                    if (p.getItemInHand().getType() == Material.BOW) {
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

    @EventHandler
    public void onPlayerSwitchItem(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        Stats s = new Stats(p);
        if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
            if (s.usingPower(Powers.SNIPER)) {
                if (e.getPlayer().getInventory().getItem(e.getNewSlot()) != null && e.getPlayer().getInventory().getItem(e.getNewSlot()).getType() == Material.BOW) {
                    if (p.isSneaking()) {
                        PotionEffect pe = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 100);
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

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            Player p = (Player) e.getEntity().getShooter();
            if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
                if (e.getEntity() instanceof Arrow) {
                    Arrow arrow = (Arrow) e.getEntity();
                    Location smoke = arrow.getLocation();
                    for (int i = 0; i < 8; i++)
                        p.getWorld().playEffect(smoke, Effect.SMOKE, i);
                    arrow.remove();
                }
                ArrayList<Integer> specialSbIds = new ArrayList<Integer>();
                Stats s = new Stats(p);
                if (s.usingPower(Powers.ERUPTION)) {
                    if (e.getEntity() instanceof Egg) {
                        Egg egg = (Egg) e.getEntity();
                        double pitch = (90 * Math.PI) / 180;
                        double yaw = (90 * Math.PI) / 180;
                        double x = Math.sin(pitch) * Math.cos(yaw);
                        double y = Math.sin(pitch) * Math.sin(yaw);
                        double z = Math.cos(pitch);
                        Vector vector = new Vector(x, y, z);
                        int maxoffset = 4;
                        if (s.getEnabledUpgrades().contains(Upgrades.HIGHER_ERUPTION_DENSITY.toString())) {
                            maxoffset = 6;
                        }
                        Random r = new Random(System.currentTimeMillis());
                        for (int offset = 0; offset < maxoffset; offset++) {
                            for (BlockFace bf : BlockFace.values()) {
                                if (!bf.equals(BlockFace.SELF)) {
                                    vector = new Vector((x + bf.getModX()) / offset, (y + bf.getModY()), (z + bf.getModZ() + r.nextInt(2)) / offset);
                                    Location l = egg.getLocation().getBlock().getRelative(bf).getLocation();
                                    p.getWorld().createExplosion(l, 0F);
                                    Snowball sb = (Snowball) p.getWorld().spawnEntity(l, EntityType.SNOWBALL);
                                    specialSbIds.add(sb.getEntityId());
                                    sb.setFireTicks(1200);
                                    sb.setShooter(p);
                                    sb.setVelocity(vector.normalize());
                                }
                            }
                        }
                        PowerCoolDown.start(p, s.getPower().time());
                    }
                    if (e.getEntity() instanceof Snowball) {
                        Snowball sb = (Snowball) e.getEntity();
                        if (specialSbIds.contains(sb.getEntityId())) {
                            specialSbIds.remove(sb.getEntityId());
                            Location l = sb.getLocation();
                            if (sb.getLocation().getBlock().getType() != Material.AIR) {
                                while (l.getBlock().getType() != Material.AIR) {
                                    l.add(0, 1, 0);
                                }
                            }
                            sb.getLocation().getBlock().getRelative(BlockFace.DOWN).setType(Material.FIRE);
                        }
                    }
                }

            }
        }
    }

    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent e) {
        if (TeamCyan.hasArenaPlayer(e.getPlayer()) || TeamLime.hasArenaPlayer(e.getPlayer())) {
            e.setHatching(false);
        }
    }

    @EventHandler
    public void onItemInteract(PlayerInteractEvent e) {
        if (TeamCyan.hasArenaPlayer(e.getPlayer()) || TeamLime.hasArenaPlayer(e.getPlayer())) {
            if (e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD && e.getAction() == Action.LEFT_CLICK_AIR) {
                if (new Stats(e.getPlayer()).usingPower(Powers.SMITE)) {
                    ArrayList<String> players = new ArrayList<String>();
                    if (TeamCyan.hasArenaPlayer(e.getPlayer())) {
                        players.addAll(TeamLime.getPlayersinarena());
                    } else if (TeamLime.hasArenaPlayer(e.getPlayer())) {
                        players.addAll(TeamCyan.getPlayersinarena());
                    }
                    int playeramount = players.size();
                    Random r = new Random();
                    r.setSeed(System.currentTimeMillis());
                    int randnum = r.nextInt(playeramount);
                    int playernum = 0;
                    for (String pl : players) {
                        if (randnum == playernum) {
                            Bukkit.getPlayer(pl).getWorld().strikeLightningEffect(Bukkit.getPlayer(pl).getLocation());
                            Bukkit.getPlayer(pl).damage(17);
                            Chat.sendPPM("You've been smitten by " + e.getPlayer().getName() + ChatColor.RESET + "!", Bukkit.getPlayer(pl));
                            Chat.sendPPM("You've just smitten " + pl + ChatColor.RESET + "!", e.getPlayer());
                            break;
                        }
                        playernum++;
                    }
                    e.getPlayer().getInventory().remove(Material.BLAZE_ROD);
                }
                Stats s = new Stats(e.getPlayer());
                if (s.usingPower(Powers.SMITE)) {
                    PowerCoolDown.start(e.getPlayer(), 60000);
                }
            }
        }
    }

}
