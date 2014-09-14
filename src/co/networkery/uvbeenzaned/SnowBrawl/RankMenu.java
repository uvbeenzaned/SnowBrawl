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
