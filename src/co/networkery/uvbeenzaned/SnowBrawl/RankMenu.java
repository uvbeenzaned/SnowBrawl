package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

public class RankMenu {

	static Inventory i;

	public static void initialize() {
		i = Bukkit.createInventory(null, 18, "[SnowBrawl] Ranks and Colors");
		i.setItem(0, Rank.colorArmor(255, 0, 0));
		i.setItem(1, Rank.colorArmor(255, 74, 0));
		i.setItem(2, Rank.colorArmor(255, 119, 0));
		i.setItem(3, Rank.colorArmor(255, 195, 0));
		i.setItem(4, Rank.colorArmor(255, 255, 0));
		i.setItem(5, Rank.colorArmor(204, 255, 0));
		i.setItem(6, Rank.colorArmor(0, 255, 0));
		i.setItem(7, Rank.colorArmor(0, 255, 255));
		i.setItem(8, Rank.colorArmor(0, 0, 255));
		i.setItem(9, Rank.colorArmor(89, 0, 255));
		i.setItem(10, Rank.colorArmor(255, 0, 255));
		i.setItem(11, Rank.colorArmor(255, 0, 78));
		i.setItem(12, Rank.colorArmor(0, 0, 0));
		i.setItem(13, Rank.colorArmor(96, 96, 96));
		i.setItem(14, Rank.colorArmor(160, 160, 160));
		i.setItem(15, Rank.colorArmor(255, 255, 255));
		i.setItem(17, getCloseButton());
	}

	public static Inventory getMenu() {
		return i;
	}

	public static ItemStack getCloseButton() {
		Dye red = new Dye();
		red.setColor(DyeColor.RED);
		ItemStack close = red.toItemStack(1);
		ItemMeta closemeta = close.getItemMeta();
		closemeta.setDisplayName(ChatColor.RED + "Close Ranks Menu");
		close.setItemMeta(closemeta);
		return close;
	}
}
