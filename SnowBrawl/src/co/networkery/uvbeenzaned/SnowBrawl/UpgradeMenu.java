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

import java.util.ArrayList;
import java.util.List;

public class UpgradeMenu implements IMenu {

    Inventory i;
    Stats s;

    public UpgradeMenu(Player p) {
        i = Bukkit.createInventory(null, 18, "[SnowBrawl] E/D Upgrades");
        s = new Stats(p);
        int slot = 0;
        Upgrade u = null;
        if (!s.getError()) {
            if (Store.isEnabled()) {
                for (Upgrades upgs : Upgrades.values()) {
                    if (s.ownsUpgrade(upgs)) {
                        u = new Upgrade(upgs, p);
                        if (s.ownsPowers(u.getPowerRequirements()) || u.getPowerRequirements().contains(Powers.NONE)) {
                            ItemStack item = u.getItemWithTitle();
                            ItemMeta itemmeta = item.getItemMeta();
                            List<String> info = new ArrayList<String>();
                            if (s.usingUpgrade(upgs)) {
                                info.add(ChatColor.GREEN + "Enabled");
                                info.add(ChatColor.BLUE + "Click to disable this upgrade!");
                            } else {
                                info.add(ChatColor.RED + "Disabled");
                                info.add(ChatColor.BLUE + "Click to enable this upgrade!");
                            }
                            itemmeta.setLore(info);
                            item.setItemMeta(itemmeta);
                            i.setItem(slot, item);
                            slot++;
                        }
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
                        info.add(ChatColor.BLUE + "Click to disable this upgrade!");
                    } else {
                        info.add(ChatColor.RED + "Disabled");
                        info.add(ChatColor.BLUE + "Click to enable this upgrade!");
                    }
                    itemmeta.setLore(info);
                    item.setItemMeta(itemmeta);
                    i.setItem(slot, item);
                    slot++;
                }
            }
            i.setItem(17, getCloseButton());
        } else {
            Chat.sendPPM("You don't have any stats on this server and so you cannot enable or disable upgrades!", p);
        }
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

    public static ItemStack getInteractItem() {
        ItemStack interactitem = new ItemStack(Material.ANVIL, 1);
        ItemMeta interactitemmeta = interactitem.getItemMeta();
        interactitemmeta.setDisplayName("Enable/Disable Upgrades...");
        interactitem.setItemMeta(interactitemmeta);
        return interactitem;
    }

    public static void giveInteractItem(Player p) {
        p.getInventory().setItem(7, getInteractItem());
    }

    @Override
    public Inventory getMenu() {
        return i;
    }

}
