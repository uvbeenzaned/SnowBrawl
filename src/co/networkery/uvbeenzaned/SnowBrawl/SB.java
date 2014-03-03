package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class SB extends JavaPlugin {

	@SuppressWarnings("unused")
	private Arenas as;
	@SuppressWarnings("unused")
	private GameListener gl;
	@SuppressWarnings("unused")
	private ExtrasListener el;
	@SuppressWarnings("unused")
	private PowerListener pl;
	@SuppressWarnings("unused")
	private Board b;
	@SuppressWarnings("unused")
	private TeleportFixThree tpf;
	@SuppressWarnings("unused")
	private Help h;

	public void onEnable() {
		getCommand("snowbrawl").setExecutor(new SBCommandExecutor());
		Configurations.loadAllConfigurations(this);
		TeamCyan.setName("Cyan");
		TeamCyan.setColor(ChatColor.AQUA);
		TeamLime.setName("Lime");
		TeamLime.setColor(ChatColor.GREEN);
		as = new Arenas(this);
		gl = new GameListener(this);
		el = new ExtrasListener(this);
		pl = new PowerListener(this);
		b = new Board();
		tpf = new TeleportFixThree(this);
		h = new Help(this);
		if(!Arenas.getNameList().isEmpty()) {
			Round.generateMapLineup();
		}
	}

	public void onDisable() {
		Configurations.saveAllConfigurations();
	}

}
