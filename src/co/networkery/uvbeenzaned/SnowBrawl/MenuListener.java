package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.zonedabone.magicchest.api.InventorySortEvent;

public class MenuListener implements Listener {

	public MenuListener(Plugin p) {
		p.getServer().getPluginManager().registerEvents(this, p);
	}

	@EventHandler
	public void onMenuItemClick(PlayerInteractEvent e) {
		if (TeamCyan.hasPlayer(e.getPlayer()) || TeamLime.hasPlayer(e.getPlayer())) {
			if (e.getItem() != null) {
				Stats s = new Stats(e.getPlayer());
				if (!s.getPurchasedPowers().isEmpty()) {
					if (PowersMenu.getInteractItem().isSimilar(e.getItem())) {
						PowersMenu pm = new PowersMenu(e.getPlayer());
						e.getPlayer().openInventory(pm.getMenu());
					}
				} else {
					Chat.sendPPM("You have not purchased any powers to switch to!", e.getPlayer());
				}
			}
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getSlot() >= 0 && e.getCurrentItem() != null) {
			if (e.getWhoClicked() instanceof Player) {
				if (TeamCyan.hasPlayer((Player) e.getWhoClicked()) || TeamLime.hasPlayer((Player) e.getWhoClicked())) {
					if (e.getSlotType().equals(SlotType.ARMOR) && e.getCurrentItem().getType().equals(Material.LEATHER_CHESTPLATE)) {
						e.getWhoClicked().openInventory(RankMenu.getMenu());
					}
					if (e.getCurrentItem().isSimilar(PowersMenu.getInteractItem())) {
						e.setCancelled(true);
						PowersMenu pm = new PowersMenu((Player) e.getWhoClicked());
						e.getWhoClicked().openInventory(pm.getMenu());
					}
				}
				if (e.getInventory().getTitle().startsWith("[SB]") || e.getInventory().getTitle().startsWith("[SnowBrawl]")) {
					e.setCancelled(true);
					if (e.getCurrentItem() != null) {
						if (e.getInventory().getTitle().contains("Ranks and Colors")) {
							if (e.getCurrentItem().isSimilar(RankMenu.getCloseButton())) {
								e.getWhoClicked().closeInventory();
							}
						}
						if (e.getInventory().getTitle().contains("Switch Powers")) {
							for (Powers pw : Powers.values()) {
								ItemMeta im = e.getCurrentItem().getItemMeta();
								if (im.getDisplayName().equals(pw.toString())) {
									Stats s = new Stats((Player) e.getWhoClicked());
									s.setPower(pw);
									e.getWhoClicked().closeInventory();
									Chat.sendPPM("Your power has been changed to: " + s.getPower().toString(), (Player) e.getWhoClicked());
								}
							}
							if (e.getCurrentItem().isSimilar(PowersMenu.getCloseButton())) {
								e.getWhoClicked().closeInventory();
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onInventorySort(InventorySortEvent e) {
		if (e.getInventory().getTitle().startsWith("[SB]") || e.getInventory().getTitle().startsWith("[SnowBrawl]")) {
			e.setCancelled(true);
		}
	}

}
