package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.plugin.java.JavaPlugin;

public class SB extends JavaPlugin{
	
	public void onEnable()
	{
		getCommand("snowbrawl").setExecutor(new SBCommandExecutor(this));
	}
	
	public void onDisable()
	{
		
	}
	
}
