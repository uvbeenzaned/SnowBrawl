package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UpgradeListener implements Listener {

	public UpgradeListener(JavaPlugin p) {
		p.getServer().getPluginManager().registerEvents(this, p);
	}

	@EventHandler
	public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		Stats s = new Stats(p);
		if (TeamCyan.hasArenaPlayer(p) || TeamLime.hasArenaPlayer(p)) {
			if (!s.getError()) {
				if (s.usingUpgrade(Upgrades.BINOCULARS)) {
					Upgrade u = new Upgrade(Upgrades.BINOCULARS, p);
					PotionEffect pe = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 100);
					if (e.isSneaking()) {
						if (!u.getInfo().equals(null) && p.getItemInHand() == u.getItem()) {
							p.addPotionEffect(pe, false);
						}
					} else {
						if (p.hasPotionEffect(PotionEffectType.SLOW)) {
							p.removePotionEffect(PotionEffectType.SLOW);
						}
					}
				}
			}
		}
	}
}
