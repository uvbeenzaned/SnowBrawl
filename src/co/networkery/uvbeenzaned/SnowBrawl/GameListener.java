package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class GameListener implements Listener {

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
			e.getDrops().clear();
			p.setHealth(p.getMaxHealth());
			TeamCyan.removeArenaPlayer(p);
			TeamLime.removeArenaPlayer(p);
			Utilities.checkTeams();
			Rank.checkRank(p);
		} else if (TeamCyan.hasPlayer(p) || TeamLime.hasPlayer(p)) {
			TeamCyan.removePlayer(p);
			TeamLime.removePlayer(p);
		}
	}

	@EventHandler
	public void onPlayerShootBow(EntityShootBowEvent e) {
		if (e.getEntity().getType() == EntityType.PLAYER) {
			Player p = (Player) e.getEntity();
			if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
				if (new Stats(p).hasPower(Powers.SNIPER)) {
					e.getProjectile().setVelocity(
							p.getLocation().getDirection().normalize()
									.multiply(20));
					p.getLocation().getWorld()
							.createExplosion(p.getLocation(), 0F);
					Location smoke = p.getLocation();
					smoke.setY(smoke.getY() + 1);
					for (int i = 0; i < 8; i++)
						p.getWorld().playEffect(smoke, Effect.SMOKE, i);
					PowerCoolDown.start(p, 30000);
				}
			}
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		Player plhit = null;
		if (e.getEntity() instanceof Player) {
			plhit = (Player) e.getEntity();
		}
		if (e.getDamager() instanceof Snowball
				|| e.getDamager() instanceof Arrow) {
			Entity dgr = e.getDamager();
			Player plenemy = null;
			if (dgr instanceof Snowball) {
				if (((Snowball) e.getDamager()).getShooter() instanceof Player) {
					plenemy = (Player) ((Snowball) e.getDamager()).getShooter();
				}
			}
			if (dgr instanceof Arrow) {
				if (((Arrow) e.getDamager()).getShooter() instanceof Player) {
					plenemy = (Player) ((Arrow) e.getDamager()).getShooter();
				}
			}
			if (plhit != plenemy && plhit != null) {
				if (TeamCyan.hasArenaPlayer(plhit)
						|| TeamLime.hasArenaPlayer(plhit)) {
					if (TeamCyan.hasArenaPlayer(plenemy)
							|| TeamLime.hasArenaPlayer(plenemy)) {
						if (!TeamCyan.hasArenaPlayer(plhit)
								|| !TeamCyan.hasArenaPlayer(plenemy)) {
							if (!TeamLime.hasArenaPlayer(plhit)
									|| !TeamLime.hasArenaPlayer(plenemy)) {
								if ((e.getEntity() instanceof Player && (e
										.getDamager() instanceof Snowball || e
										.getDamager() instanceof Arrow))) {
									e.setCancelled(true);
									Stats s = new Stats(plhit);
									s.removePoints(1);
									s.incrementDeathCount();
									Utilities.playEffects(plenemy, plhit);
									TeamCyan.removeArenaPlayer(plhit);
									TeamLime.removeArenaPlayer(plhit);
									Rank.checkRank(plhit);
									s = new Stats(plenemy);
									s.addPoints(1);
									s.incrementKillsCount();
									Round.addPlayerLead(plenemy, 1);
									Rank.checkRank(plenemy);
									Chat.sendAllTeamsMsg(plenemy.getName()
											+ ChatColor.RED + " SNOWBRAWLED "
											+ plhit.getName() + ".");
									Utilities.checkTeams();
								}
								if ((e.getEntity() instanceof Player && e
										.getDamager() instanceof Player)) {
									e.setCancelled(true);
								}
							}
						}
					}
				}
			}
			if (e.getEntity() instanceof Entity
					&& (e.getDamager() instanceof Snowball || e.getDamager() instanceof Arrow)
					&& !(e.getEntity() instanceof Player)
					&& e.getEntityType().isAlive()) {
				if (TeamCyan.hasArenaPlayer(plenemy)
						|| TeamLime.hasArenaPlayer(plenemy)) {
					e.setCancelled(true);
					Entity mob = e.getEntity();
					mob.remove();
					Firework fw = mob.getWorld().spawn(mob.getLocation(),
							Firework.class);
					FireworkMeta fwm = fw.getFireworkMeta();
					FireworkEffect effect = FireworkEffect.builder()
							.withColor(Color.AQUA).with(Type.BALL_LARGE)
							.build();
					if (TeamCyan.hasArenaPlayer(plenemy)) {
						effect = FireworkEffect.builder().withColor(Color.AQUA)
								.with(Type.BALL_LARGE).withFlicker()
								.withTrail().build();
					}
					if (TeamLime.hasArenaPlayer(plenemy)) {
						effect = FireworkEffect.builder()
								.withColor(Color.GREEN).with(Type.BALL_LARGE)
								.withFlicker().withTrail().build();
					}
					fwm.addEffects(effect);
					fwm.setPower(1);
					fw.setFireworkMeta(fwm);
					Stats s = new Stats(plenemy);
					s.addPoints(1);
					Chat.sendAllTeamsMsg(ChatColor.GOLD + "+1"
							+ ChatColor.RESET + " bonus point for "
							+ plenemy.getName() + ChatColor.RESET + "! Type: "
							+ mob.getType().toString().toLowerCase());
				}
			}
		}
	}
}
