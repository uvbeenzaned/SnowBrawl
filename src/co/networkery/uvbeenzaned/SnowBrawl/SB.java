package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.plugin.java.JavaPlugin;

public class SB extends JavaPlugin{
	
	public void onEnable()
	{
		getCommand("snowbrawl").setExecutor(new SBCommandExecutor());
		Configurations.loadAllConfigurations(this);
		new TeamCyan();
		new TeamLime();
		new GameListener(this);
		new ExtrasListener(this);
		Kits.loadAllKitsFromConfig();
	}
	
	public void onDisable()
	{
		Configurations.saveAllConfigurations();
	}
	
}
