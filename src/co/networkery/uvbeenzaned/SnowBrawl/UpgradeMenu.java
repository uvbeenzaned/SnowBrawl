/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Networkery
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
