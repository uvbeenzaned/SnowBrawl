package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

public class UpgradeMenu implements IMenu {

	Inventory i;
	Stats s;

	public UpgradeMenu(Player p) {
		i = Bukkit.createInventory(null, 18, "[SnowBrawl] Enable/Disable Upgrades");
		s = new Stats(p);
		int slot = 0;
		Upgrade u = null;
		if (Store.isEnabled()) {
			for (Upgrades upgs : Upgrades.values()) {
				if (s.ownsUpgrade(upgs)) {
					u = new Upgrade(upgs, p);
					ItemStack item = u.getItemWithTitle();
					ItemMeta itemmeta = item.getItemMeta();
					List<String> info = new ArrayList<String>();
					if (s.usingUpgrade(upgs)) {
						info.add(ChatColor.GREEN + "Enabled");
						info.add(ChatColor.BLUE + "Click to enable this power!");
					} else {
						info.add(ChatColor.RED + "Disabled");
						info.add(ChatColor.BLUE + "Click to disable this power!");
					}
					itemmeta.setLore(info);
					item.setItemMeta(itemmeta);
					i.setItem(slot, item);
					slot++;
				}
			}
		} else {
			for (Upgrades upgs : Upgrades.values()) {
				u = new Upgrade(upgs, p);
				ItemStack item = u.getItemWithTitle();
				ItemMeta itemmeta = item.getItemMeta();
				List<String> info = new ArrayList<String>();
				if (s.usingUpgrade(upgs)) {
					info.add(ChatColor.GREEN + "Enabled");
					info.add(ChatColor.BLUE + "Click to enable this power!");
				} else {
					info.add(ChatColor.RED + "Disabled");
					info.add(ChatColor.BLUE + "Click to disable this power!");
				}
				itemmeta.setLore(info);
				item.setItemMeta(itemmeta);
				i.setItem(slot, item);
				slot++;
			}
		}
		i.setItem(17, getCloseButton());
	}

	@Override
	public Inventory getMenu() {
		return i;
	}

	public static ItemStack getCloseButton() {
		Dye red = new Dye();
		red.setColor(DyeColor.RED);
		ItemStack close = red.toItemStack(1);
		ItemMeta closemeta = close.getItemMeta();
		closemeta.setDisplayName(ChatColor.RED + "Close Upgrade Menu");
		close.setItemMeta(closemeta);
		return close;
	}

}
