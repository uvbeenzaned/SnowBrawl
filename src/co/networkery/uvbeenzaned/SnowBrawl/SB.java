package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class SB extends JavaPlugin{
	
	@SuppressWarnings("unused")
	private Arenas as;
	@SuppressWarnings("unused")
	private GameListener gl;
	@SuppressWarnings("unused")
	private ExtrasListener el;
	
	public void onEnable()
	{
		getCommand("snowbrawl").setExecutor(new SBCommandExecutor());
		Configurations.loadAllConfigurations(this);
		TeamCyan.setName("Cyan");
		TeamCyan.setColor(ChatColor.AQUA);
		TeamLime.setName("Lime");
		TeamLime.setColor(ChatColor.GREEN);
		as = new Arenas(this);
		gl = new GameListener(this);
		el = new ExtrasListener(this);
		//Kits.loadAllKitsFromConfig();
	}
	
	public void onDisable()
	{
		Configurations.saveAllConfigurations();
	}
	
}
