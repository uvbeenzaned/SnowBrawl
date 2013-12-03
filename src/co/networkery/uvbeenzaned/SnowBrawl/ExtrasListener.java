package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ExtrasListener implements Listener{

	public ExtrasListener(JavaPlugin p)
	{
		p.getServer().getPluginManager().registerEvents(this, p);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			if(TeamCyan.hasPlayer(e.getPlayer()) || TeamLime.hasPlayer(e.getPlayer()))
			{
				Utilities.reloadSnowballs(e.getPlayer());
			}
		}
	}
	
}
