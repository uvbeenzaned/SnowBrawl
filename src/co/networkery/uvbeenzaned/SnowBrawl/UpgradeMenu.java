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
	
	public UpgradeMenu(Player p) {
		i = Bukkit.createInventory(null, 18, "[SnowBrawl] Store: Buy Upgrades");
		int slot = 0;
		Power pw = null;
		for (Powers pws : Powers.values()) {
			pw = new Power(pws, p);
			if (pw.getType() != Powers.NONE) {
				ItemStack priceditem = pw.getPowerItemWithTitle();
				ItemMeta priceditemmeta = priceditem.getItemMeta();
				List<String> info = new ArrayList<String>();
				info.add(ChatColor.GREEN + "Cost: " + String.valueOf(pw.getPrice()));
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
