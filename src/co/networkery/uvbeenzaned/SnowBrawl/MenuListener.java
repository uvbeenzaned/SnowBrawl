package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import org.zonedabone.magicchest.api.InventorySortEvent;

public class MenuListener implements Listener{

	public MenuListener(Plugin p) {
		p.getServer().getPluginManager().registerEvents(this, p);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(e.getInventory().getTitle().startsWith("[SB]") || e.getInventory().getTitle().startsWith("[SnowBrawl]")) {
			if(e.getInventory().getTitle().equals("[SnowBrawl] - Ranks and Colors")) {
				if(e.getSlot() == 17) {
					if(e.getCurrentItem().isSimilar(RankMenu.getCloseButton())) {
						e.getWhoClicked().closeInventory();
					}
				}
			}
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventorySort(InventorySortEvent e) {
		if(e.getInventory().getTitle().startsWith("[SB]") || e.getInventory().getTitle().startsWith("[SnowBrawl]")) {
			e.setCancelled(true);
		}
	}
	
}
