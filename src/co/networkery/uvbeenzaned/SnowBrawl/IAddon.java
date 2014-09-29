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

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public interface IAddon {

    /**
     * Get the type of addon this instance is.
     * @return Returns the type of addon this is.
     */
    public IAddonDefs getType();

    /**
     * Set what type of addon this is.
     * @param a The IAddonDefs this will be set to.
     */
    public void set(IAddonDefs a);

    /**
     * Get the name of the specific addon this is.
     * @return The name of this specific addon.
     */
    public String getName();

    /**
     * Get the player that this addon applies to.
     * @return The player that this addon applies to.
     */
    public Player getPlayer();

    /**
     * Set the player that this addon will apply to.
     * @param p The player to set this addon to.
     */
    public void setPlayer(Player p);

    /**
     * Get the description of this specific addon.
     * @return The descripption of this addon.
     */
    public String getDescription();

    /**
     * Set the description of this addon.
     * @param d The description to give to this addon.
     */
    public void setDescription(String d);

    /**
     * Get the cost of this addon for in-game purchasing.
     * @return The cost of this addon.
     */
    public double getPrice();

    /**
     * Set the cost of this addon for in-game purcahing.
     * @param pr The price to set this addon to.
     */
    public void setPrice(double pr);

    /**
     * A list of strings with information about this addon.
     * @return
     */
    public ArrayList<String> getInfo();

    /**
     * Apply the properties of this addon to the player that this addon applies to.
     */
    public void apply();

    /**
     * Get the item that represents this addon.
     * @return An ItemStack that represents this addon.
     */
    public ItemStack getItem();

    /**
     * Get the item that represents this addon but with a special title.
     * @return The specially titled item.
     */
    public ItemStack getItemWithTitle();
}
