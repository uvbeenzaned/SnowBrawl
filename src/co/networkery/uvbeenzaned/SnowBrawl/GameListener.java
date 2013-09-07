package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class GameListener implements Listener{
	
	public GameListener(JavaPlugin p)
	{
		p.getServer().getPluginManager().registerEvents(this, p);
	}

}
