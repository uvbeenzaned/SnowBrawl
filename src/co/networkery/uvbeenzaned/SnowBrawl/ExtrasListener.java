package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
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
	
	@EventHandler
	public void onSnowballThrow(ProjectileLaunchEvent e)
	{
		if(e.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)e.getEntity().getShooter();
			if(e.getEntityType() == EntityType.SNOWBALL && (TeamCyan.hasPlayer(p) || TeamLime.hasPlayer(p)))
			{
				if(!p.getInventory().containsAtLeast(new ItemStack(Material.SNOW_BALL), 1)) {
					Utilities.reloadSnowballs(p);
				}
				Stats s = new Stats(p);
				s.addSnowballsThrown(1);
				if(p.isOp()) {
					e.getEntity().setFireTicks(600);
				}
			}
		}
	}
	
}
