package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

public class PowersMenu implements IMenu {

	Inventory i;
	Stats s;

	public PowersMenu(Player p) {
		s = new Stats(p);
		if (!s.getError()) {
			i = Bukkit.createInventory(null, 18, "[SnowBrawl] Switch Powers");
			int slot = 0;
			Power power = null;
			if (Store.isEnabled()) {
				for (Powers pws : Powers.values()) {
					if (s.getPurchasedPowers().contains(pws.toString())) {
						power = new Power(pws, p);
						i.setItem(slot, power.getPowerItemWithInfo());
						slot++;
					}
				}
				power = new Power(Powers.NONE, p);
				i.setItem(slot, power.getPowerItemWithInfo());
			} else {
				for (Powers pw : Powers.values()) {
					power = new Power(pw, p);
					i.setItem(slot, power.getPowerItemWithInfo());
					slot++;
				}
			}
			i.setItem(17, getCloseButton());
		} else {
			Chat.sendPPM("You don't have any stats on this server and so you cannot switch powers!", p);
		}
	}

	public Inventory getMenu() {
		return i;
	}

	public static ItemStack getCloseButton() {
		Dye red = new Dye();
		red.setColor(DyeColor.RED);
		ItemStack close = red.toItemStack(1);
		ItemMeta closemeta = close.getItemMeta();
		closemeta.setDisplayName(ChatColor.RED + "Close Powers Menu");
		close.setItemMeta(closemeta);
		return close;
	}

	public static ItemStack getInteractItem() {
		ItemStack interactitem = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta interactitemmeta = interactitem.getItemMeta();
		interactitemmeta.setDisplayName("Switch Powers...");
		interactitem.setItemMeta(interactitemmeta);
		return interactitem;
	}

	public static void giveInteractItem(Player p) {
		p.getInventory().setItem(8, getInteractItem());
	}

}
