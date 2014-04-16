package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.BookMeta;
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
				if (e.getItem().isSimilar(PowerMenu.getInteractItem())) {
					if (!s.getPurchasedPowers().isEmpty() || !Store.isEnabled()) {
						PowerMenu pm = new PowerMenu(e.getPlayer());
						e.getPlayer().openInventory(pm.getMenu());
						e.setCancelled(true);
					} else {
						Chat.sendPPM("You have not purchased any powers to switch to!", e.getPlayer());
					}
				}
				if (e.getItem().isSimilar(StoreMenu.getInteractItem())) {
					e.getPlayer().openInventory(StoreMenu.getMenu());
					e.setCancelled(true);
				}
				if (e.getItem().getType().equals(Material.WRITTEN_BOOK)) {
					BookMeta bm = (BookMeta) Round.getLineupBook().getItemMeta();
					if (bm.getDisplayName().equals("Round line-up...")) {
						Round.giveLineupBook(e.getPlayer());
					}
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
					Stats s = new Stats((Player) e.getWhoClicked());
					if (e.getCurrentItem().isSimilar(PowerMenu.getInteractItem())) {
						if (!s.getPurchasedPowers().isEmpty() || !Store.isEnabled()) {
							PowerMenu pm = new PowerMenu((Player) e.getWhoClicked());
							e.getWhoClicked().openInventory(pm.getMenu());
						} else {
							Chat.sendPPM("You have not purchased any powers to switch to!", (Player) e.getWhoClicked());
						}
						e.setCancelled(true);
					}
					if (e.getCurrentItem().isSimilar(StoreMenu.getInteractItem())) {
						e.getWhoClicked().openInventory(StoreMenu.getMenu());
						e.setCancelled(true);
					}
					if (e.getCurrentItem().getType().equals(Material.WRITTEN_BOOK)) {
						BookMeta bm = (BookMeta) Round.getLineupBook().getItemMeta();
						if (bm.getDisplayName().equals("Round line-up...")) {
							e.setCancelled(true);
						}
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
							if (e.getCurrentItem().isSimilar(PowerMenu.getCloseButton())) {
								e.getWhoClicked().closeInventory();
							}
						}
						if (e.getInventory().getTitle().equals("[SnowBrawl] Store")) {
							if (e.getCurrentItem().isSimilar(StoreMenu.getStorePowerButton())) {
								StorePowerMenu spm = new StorePowerMenu((Player) e.getWhoClicked());
								e.getWhoClicked().openInventory(spm.getMenu());
							}
							if (e.getCurrentItem().isSimilar(StoreMenu.getCloseButton())) {
								e.getWhoClicked().closeInventory();
							}
						}
						if (e.getInventory().getTitle().equals("[SnowBrawl] Store: Buy Powers")) {
							for (Powers pws : Powers.values()) {
								ItemMeta im = e.getCurrentItem().getItemMeta();
								if (im.getDisplayName().equals(pws.toString())) {
									if (e.isShiftClick()) {
										Store.purchasePower((Player) e.getWhoClicked(), (Player) e.getWhoClicked(), pws);
										e.getWhoClicked().closeInventory();
									} else {
										Power pw = new Power(pws, (Player) e.getWhoClicked());
										Chat.sendPPM(pw.getPowerInfo().get(0), (Player) e.getWhoClicked());
										Chat.sendPM(pw.getPowerInfo().get(1), (Player) e.getWhoClicked());
										Chat.sendPM(pw.getPowerInfo().get(2), (Player) e.getWhoClicked());
										Chat.sendPM(pw.getPowerInfo().get(3), (Player) e.getWhoClicked());
										e.getWhoClicked().closeInventory();
									}
								}
							}
							if (e.getCurrentItem().isSimilar(StorePowerMenu.getCloseButton())) {
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
