package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GameListener implements Listener{
	
	public GameListener(JavaPlugin p)
	{
		p.getServer().getPluginManager().registerEvents(this, p);
	}
	
	@EventHandler
	public void playerLeave(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		TeamCyan.leave(p);
		TeamLime.leave(p);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e)
	{
		if(e.getEntityType() == EntityType.PLAYER) {
			Player p = (Player)e.getEntity();
			if(TeamCyan.hasPlayer(p)) {
				TeamCyan.addDeadPlayer(p);
			}
			if(TeamLime.hasPlayer(p)) {
				TeamLime.addDeadPlayer(p);
			}
			TeamCyan.leave(p);
			TeamLime.leave(p);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void playerRespawn(PlayerRespawnEvent e)
	{
		Player p = e.getPlayer();
		if(TeamCyan.hasDeadPlayer(p)) {
			e.setRespawnLocation(Lobby.getLobbyspawnlocation());
			TeamCyan.join(p);
		} else {
			e.setRespawnLocation(p.getWorld().getSpawnLocation());
		}
		if(TeamLime.hasDeadPlayer(p)) {
			e.setRespawnLocation(Lobby.getLobbyspawnlocation());
			TeamLime.join(p);
		} else {
			e.setRespawnLocation(p.getWorld().getSpawnLocation());
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e)
	{
		if ((e.getEntity() instanceof Player && e.getDamager() instanceof Snowball))
		{
			Player plhit = (Player)e.getEntity();
			Snowball sb = (Snowball)e.getDamager();
			Player plenemy = (Player)sb.getShooter();
			if(plhit != plenemy)
			{
				if(TeamCyan.hasArenaPlayer(plhit) || TeamLime.hasArenaPlayer(plhit))
				{
					if(TeamCyan.hasArenaPlayer(plenemy) || TeamLime.hasArenaPlayer(plenemy))
					{
						if(!TeamCyan.hasArenaPlayer(plhit) || !TeamCyan.hasArenaPlayer(plenemy))
						{
							if(!TeamLime.hasArenaPlayer(plhit) || !TeamLime.hasArenaPlayer(plenemy))
							{
								e.setCancelled(true);
								TeamCyan.removeArenaPlayer(plhit);
								TeamLime.removeArenaPlayer(plhit);
								if(TeamCyan.isArenaPlayersEmpty()) {
									Chat.sendAllTeamsMsg("Team LIME wins!");
									TeamLime.teleportAllPlayersToLobby();
									Round.setGameActive(false);
									Round.startTimerRound();
								} else {
									if(TeamLime.isArenaPlayersEmpty()) {
										Chat.sendAllTeamsMsg("Team CYAN wins!");
										TeamCyan.teleportAllPlayersToLobby();
										Round.setGameActive(false);
										Round.startTimerRound();
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
