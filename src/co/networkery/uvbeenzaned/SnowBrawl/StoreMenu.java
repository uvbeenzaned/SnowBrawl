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
