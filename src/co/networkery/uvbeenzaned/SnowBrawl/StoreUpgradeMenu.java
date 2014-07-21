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

public class StoreUpgradeMenu implements IMenu {
	
	Inventory i;
	
	public StoreUpgradeMenu(Player p) {
		i = Bukkit.createInventory(null, 18, "[SnowBrawl] Store: Buy Upgrades");
		int slot = 0;
		Upgrade u = null;
		for (Upgrades upgs : Upgrades.values()) {
			u = new Upgrade(upgs, p);
			if (u.getType() != Powers.NONE) {
				ItemStack priceditem = u.getItemWithTitle();
				ItemMeta priceditemmeta = priceditem.getItemMeta();
				List<String> info = new ArrayList<String>();
				info.add(ChatColor.GREEN + "Cost: " + String.valueOf(u.getPrice()));
				info.add(ChatColor.BLUE + "Click for more info about this upgrade.");
				info.add(ChatColor.LIGHT_PURPLE + "Shift-click this upgrade to buy it!");
				priceditemmeta.setLore(info);
				priceditem.setItemMeta(priceditemmeta);
				i.setItem(slot, priceditem);
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
		closemeta.setDisplayName(ChatColor.RED + "Close Store Upgrade Menu");
		close.setItemMeta(closemeta);
		return close;
	}

}
