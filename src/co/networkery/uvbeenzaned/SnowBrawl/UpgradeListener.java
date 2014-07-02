package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class UpgradeListener implements Listener {
	
	public UpgradeListener(JavaPlugin p) {
		p.getServer().getPluginManager().registerEvents(this, p);
	}
	
	@EventHandler
	public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
		Stats s = new Stats(e.getPlayer());
	}
}
