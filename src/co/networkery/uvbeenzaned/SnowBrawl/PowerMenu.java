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

public class PowerMenu implements IMenu {

    Inventory i;
    Stats s;

    public PowerMenu(Player p) {
        s = new Stats(p);
        if (!s.getError()) {
            i = Bukkit.createInventory(null, 18, "[SnowBrawl] Switch Powers");
            int slot = 0;
            Power power = null;
            if (Store.isEnabled()) {
                for (Powers pws : Powers.values()) {
                    if (s.getPurchasedPowers().contains(pws.toString())) {
                        power = new Power(pws, p);
                        i.setItem(slot, power.getItemWithTitle());
                        slot++;
                    }
                }
                power = new Power(Powers.NONE, p);
                i.setItem(slot, power.getItemWithTitle());
            } else {
                for (Powers pw : Powers.values()) {
                    power = new Power(pw, p);
                    i.setItem(slot, power.getItemWithTitle());
                    slot++;
                }
            }
            i.setItem(17, getCloseButton());
        } else {
            Chat.sendPPM("You don't have any stats on this server and so you cannot switch powers!", p);
        }
    }

    public static ItemStack getCloseButton() {
        Dye red = new Dye();
        red.setColor(DyeColor.RED);
        ItemStack close = red.toItemStack(1);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(ChatColor.RED + "Close Power Menu");
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
        p.getInventory().setItem(6, getInteractItem());
    }

    public Inventory getMenu() {
        return i;
    }

}
