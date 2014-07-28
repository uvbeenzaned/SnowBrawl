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

public class StoreMenu {

	static Inventory i;
	
	public static void initialize() {
		i = Bukkit.createInventory(null, 9, "[SnowBrawl] Store");
		i.setItem(0, getStorePowerButton());
		i.setItem(1, getStoreUpgradeButton());
		i.setItem(8, getCloseButton());
	}
	
	public static Inventory getMenu() {
		return i;
	}
	
	public static ItemStack getStorePowerButton() {
		ItemStack curritem = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
		ItemMeta curritemmeta = curritem.getItemMeta();
		curritemmeta.setDisplayName("Buy Powers...");
		curritem.setItemMeta(curritemmeta);
		return curritem;
	}
	
	public static ItemStack getStoreUpgradeButton() {
		ItemStack curritem = new ItemStack(Material.ANVIL, 1);
		ItemMeta curritemmeta = curritem.getItemMeta();
		curritemmeta.setDisplayName("Buy Upgrades...");
		curritem.setItemMeta(curritemmeta);
		return curritem;
	}
	
	public static ItemStack getCloseButton() {
		Dye red = new Dye();
		red.setColor(DyeColor.RED);
		ItemStack close = red.toItemStack(1);
		ItemMeta closemeta = close.getItemMeta();
		closemeta.setDisplayName(ChatColor.RED + "Close Store Menu");
		close.setItemMeta(closemeta);
		return close;
	}

	public static ItemStack getInteractItem() {
		ItemStack interactitem = new ItemStack(Material.CHEST, 1);
		ItemMeta interactitemmeta = interactitem.getItemMeta();
		interactitemmeta.setDisplayName("Store...");
		interactitem.setItemMeta(interactitemmeta);
		return interactitem;
	}

	public static void giveInteractItem(Player p) {
		p.getInventory().setItem(5, getInteractItem());
	}

}
