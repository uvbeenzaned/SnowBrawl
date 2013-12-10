package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class GameListener implements Listener {

	public GameListener(JavaPlugin p) {
		p.getServer().getPluginManager().registerEvents(this, p);
	}

	@EventHandler
	public void playerLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		TeamCyan.leave(p);
		TeamLime.leave(p);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDeath(PlayerDeathEvent e) {
		if (e.getEntityType() == EntityType.PLAYER) {
			Player p = (Player) e.getEntity();
			if (TeamCyan.hasPlayer(p)) {
				TeamCyan.addDeadPlayer(p);
			} else {
				if (TeamLime.hasPlayer(p)) {
					TeamLime.addDeadPlayer(p);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void playerRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if (TeamCyan.hasDeadPlayer(p)) {
			e.setRespawnLocation(Lobby.getLobbyspawnlocation());
			TeamCyan.join(p);
		} else {
			if (TeamLime.hasDeadPlayer(p)) {
				e.setRespawnLocation(Lobby.getLobbyspawnlocation());
				TeamLime.join(p);
			} else {
				e.setRespawnLocation(p.getWorld().getSpawnLocation());
			}
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		Player plhit = null;
		if (e.getEntity() instanceof Player) {
			plhit = (Player) e.getEntity();
		}
		if (e.getDamager() instanceof Snowball) {
			if (((Snowball) e.getDamager()).getShooter() instanceof Player) {
				Snowball sb = (Snowball) e.getDamager();
				Player plenemy = (Player) sb.getShooter();
				if (plhit != plenemy && plhit != null) {
					if (TeamCyan.hasArenaPlayer(plhit)
							|| TeamLime.hasArenaPlayer(plhit)) {
						if (TeamCyan.hasArenaPlayer(plenemy)
								|| TeamLime.hasArenaPlayer(plenemy)) {
							if (!TeamCyan.hasArenaPlayer(plhit)
									|| !TeamCyan.hasArenaPlayer(plenemy)) {
								if (!TeamLime.hasArenaPlayer(plhit)
										|| !TeamLime.hasArenaPlayer(plenemy)) {
									if ((e.getEntity() instanceof Player && e
											.getDamager() instanceof Snowball)) {
										e.setCancelled(true);
										Stats s = new Stats(plhit);
										s.removePoints(1);
										s.incrementDeathCount();
										TeamCyan.removeArenaPlayer(plhit);
										TeamLime.removeArenaPlayer(plhit);
										Rank.checkRank(plhit.getName());
										s = new Stats(plenemy);
										s.addPoints(1);
										s.incrementKillsCount();
										Round.addPlayerLead(plenemy, 1);
										Rank.checkRank(plenemy.getName());
										Chat.sendAllTeamsMsg(plenemy.getName()
												+ ChatColor.RED
												+ " SNOWBRAWLED "
												+ plhit.getName() + ".");
										if (TeamCyan.isArenaPlayersEmpty()) {
											Chat.sendAllTeamsMsg("Team LIME"
													+ ChatColor.RESET
													+ " wins!");
											TeamCyan.teleportAllPlayersToLobby();
											TeamLime.teleportAllPlayersToLobby();
											TeamLime.awardTeamPoints();
											Round.giveLeadPoints();
											Round.setGameActive(false);
											Round.startTimerRound();
										} else {
											if (TeamLime.isArenaPlayersEmpty()) {
												Chat.sendAllTeamsMsg("Team CYAN"
														+ ChatColor.RESET
														+ " wins!");
												TeamCyan.teleportAllPlayersToLobby();
												TeamLime.teleportAllPlayersToLobby();
												TeamCyan.awardTeamPoints();
												Round.giveLeadPoints();
												Round.setGameActive(false);
												Round.startTimerRound();
											}
										}
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
						&& e.getDamager() instanceof Snowball
						&& !(e.getEntity() instanceof Player)) {
					if (TeamCyan.hasArenaPlayer(plenemy)
							|| TeamLime.hasArenaPlayer(plenemy)) {
						Entity mob = e.getEntity();
						mob.remove();
						Firework fw = mob.getWorld().spawn(mob.getLocation(),
								Firework.class);
						FireworkMeta fwm = fw.getFireworkMeta();
						FireworkEffect effect = FireworkEffect.builder()
								.withColor(Color.AQUA).with(Type.BALL_LARGE)
								.build();
						if (TeamCyan.hasArenaPlayer(plenemy)) {
							effect = FireworkEffect.builder()
									.withColor(Color.AQUA)
									.with(Type.BALL_LARGE).withFlicker()
									.withTrail().build();
						}
						if (TeamLime.hasArenaPlayer(plenemy)) {
							effect = FireworkEffect.builder()
									.withColor(Color.GREEN)
									.with(Type.BALL_LARGE).withFlicker()
									.withTrail().build();
						}
						fwm.addEffects(effect);
						fwm.setPower(1);
						fw.setFireworkMeta(fwm);
						Stats s = new Stats(plenemy);
						s.addPoints(1);
						Chat.sendAllTeamsMsg(ChatColor.GOLD + "+1"
								+ ChatColor.RESET + " bonus point for "
								+ plenemy.getName() + ChatColor.RESET
								+ "! Type: "
								+ mob.getType().toString().toLowerCase());
					}
				}
			}
		}
	}
}
