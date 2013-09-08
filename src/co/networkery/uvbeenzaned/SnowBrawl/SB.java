package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class SB extends JavaPlugin{
	
	public void onEnable()
	{
		getCommand("snowbrawl").setExecutor(new SBCommandExecutor());
		Configurations.loadAllConfigurations(this);
		new TeamCyan(this);
		TeamCyan.setName("Cyan");
		TeamCyan.setColor(ChatColor.AQUA);
		new TeamLime(this);
		TeamLime.setName("Lime");
		TeamLime.setColor(ChatColor.GREEN);
		new Arenas(this);
		new GameListener(this);
		new ExtrasListener(this);
		//Kits.loadAllKitsFromConfig();
	}
	
	public void onDisable()
	{
		Configurations.saveAllConfigurations();
	}
	
}
