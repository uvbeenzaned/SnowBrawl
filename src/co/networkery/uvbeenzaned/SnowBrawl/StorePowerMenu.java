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
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

import java.util.ArrayList;
import java.util.List;

public class StorePowerMenu implements IMenu {

    Inventory i;

    public StorePowerMenu(Player p) {
        i = Bukkit.createInventory(null, 18, "[SnowBrawl] Store: Buy Powers");
        int slot = 0;
        Power pw = null;
        for (Powers pws : Powers.values()) {
            pw = new Power(pws, p);
            if (pw.getType() != Powers.NONE) {
                ItemStack priceditem = pw.getItemWithTitle();
                ItemMeta priceditemmeta = priceditem.getItemMeta();
                List<String> info = new ArrayList<String>();
                info.add(ChatColor.GREEN + "Cost: " + String.valueOf(pw.getPrice()));
                info.add(ChatColor.BLUE + "Click for more info about this power.");
                info.add(ChatColor.LIGHT_PURPLE + "Shift-click this power to buy it!");
                priceditemmeta.setLore(info);
                priceditem.setItemMeta(priceditemmeta);
                i.setItem(slot, priceditem);
                slot++;
            }
        }
        i.setItem(17, getCloseButton());
    }

    public static ItemStack getCloseButton() {
        Dye red = new Dye();
        red.setColor(DyeColor.RED);
        ItemStack close = red.toItemStack(1);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(ChatColor.RED + "Close Store Power Menu");
        close.setItemMeta(closemeta);
        return close;
    }

    @Override
    public Inventory getMenu() {
        return i;
    }
}
