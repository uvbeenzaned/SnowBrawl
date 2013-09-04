package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ExtrasListener implements Listener{

	public ExtrasListener(JavaPlugin p)
	{
		p.getServer().getPluginManager().registerEvents(this, p);
	}
	
}
