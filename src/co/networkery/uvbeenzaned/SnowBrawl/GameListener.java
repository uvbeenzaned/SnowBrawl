package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GameListener implements Listener{
	
	public GameListener(JavaPlugin p)
	{
		p.getServer().getPluginManager().registerEvents(this, p);
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
								//testing teleport here
								TeamCyan.teleportAllPlayersToLobby(Teams.CYAN);
								TeamLime.teleportAllPlayersToLobby(Teams.LIME);
							}
						}
					}
				}
			}
		}
	}

}
