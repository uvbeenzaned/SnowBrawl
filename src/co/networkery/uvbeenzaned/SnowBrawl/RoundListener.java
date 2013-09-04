package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class RoundListener implements Listener{
	
	public RoundListener(JavaPlugin p)
	{
		p.getServer().getPluginManager().registerEvents(this, p);
	}

}
