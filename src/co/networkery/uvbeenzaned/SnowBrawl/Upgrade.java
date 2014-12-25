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

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Upgrade implements IAddon {

    private Upgrades upgrade;
    private String upgradename;
    private Player player;
    private String description;
    private double price;

    public Upgrade(Upgrades u, Player p) {
        upgrade = u;
        upgradename = u.toString();
        player = p;
        fetchConfiguration();
    }

    private void fetchConfiguration() {
        if (Configurations.getUpgradesconfig().contains(upgradename)) {
            setDescription(Configurations.getUpgradesconfig().getConfigurationSection(upgradename).getString("description"));
            setPrice(Configurations.getUpgradesconfig().getConfigurationSection(upgradename).getDouble("price"));
        } else {
            Configurations.getUpgradesconfig().createSection(upgradename);
            Configurations.getUpgradesconfig().getConfigurationSection(upgradename).set("description", "This upgrade is missing a description.");
            Configurations.getUpgradesconfig().getConfigurationSection(upgradename).set("price", (double) 0.0);
            Configurations.saveUpgradesConfig();
        }

    }

    public IAddonDefs getType() {
        return upgrade;
    }

    public void set(IAddonDefs a) {
        upgrade = (Upgrades) a;
    }

    public String getName() {
        return upgradename;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player p) {
        player = p;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String d) {
        description = d;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double pr) {
        price = pr;
    }

    public ArrayList<String> getInfo() {
        ArrayList<String> info = new ArrayList<String>();
        info.add(upgradename + ":");
        info.add("    Description: " + description);
        if (Store.isEnabled()) {
            info.add("    Cost: " + price);
            Stats s = new Stats(player);
            if (!s.ownsUpgrade(upgrade)) {
                info.add(ChatColor.BLUE + "    You can purchase this upgrade by using /sb store buy upgrade " + upgrade.toString() + ".");
            } else {
                info.add(ChatColor.GREEN + "    You own this upgrade.");
            }
        } else {
            info.add("    Cost: 0.0");
            info.add(ChatColor.GREEN + "    Anyone can use this upgrade.");
        }
        return info;
    }

    public List<Powers> getPowerRequirements() {
        List<Powers> powers = new ArrayList<Powers>();
        switch (upgrade) {
            case BINOCULARS:
                powers.add(Powers.NONE);
                break;
            case BURN_SAVE:
                powers.add(Powers.NONE);
                break;
            case HIGHER_ERUPTION_DENSITY:
                powers.add(Powers.ERUPTION);
                break;
            case POWER_RELOAD_TIME_REDUCTION:
                powers.add(Powers.NONE);
                break;
            case SOFT_FALL_BOOTS:
                powers.add(Powers.NONE);
                break;
            default:
                break;

        }
        return powers;
    }

    public List<Powers> getPowerConflicts() {
        List<Powers> powers = new ArrayList<Powers>();
        switch (upgrade) {
            case BINOCULARS:
                powers.add(Powers.SNIPER);
                break;
            default:
                break;
        }
        return powers;
    }

    public void apply() {
        switch (upgrade) {
            case BINOCULARS:
                addToInventory(binoculars());
                break;
            case BURN_SAVE:
                break;
            case HIGHER_ERUPTION_DENSITY:
                break;
            case POWER_RELOAD_TIME_REDUCTION:
                break;
            case SOFT_FALL_BOOTS:
                player.getInventory().setBoots(softFallBoots());
                break;
            default:
                break;
        }
    }

    public void applySpecific() {
        switch (upgrade) {
            case BURN_SAVE:
                applyPlayerEffects(burnSave());
                break;
            default:
                break;
        }
    }

    public ItemStack getItem() {
        switch (upgrade) {
            case BINOCULARS:
                return binoculars();
            case SOFT_FALL_BOOTS:
                return softFallBoots();
            default:
                return null;
        }
    }

    public ItemStack getItemWithTitle() {
        switch (upgrade) {
            case BINOCULARS:
                return binoculars();
            case SOFT_FALL_BOOTS:
                return softFallBoots();
            default:
                ItemStack i = new ItemStack(Material.ANVIL, 1);
                ItemMeta im = i.getItemMeta();
                im.setDisplayName(upgradename);
                i.setItemMeta(im);
                return i;
        }
    }

    // upgrade functions

    private ItemStack binoculars() {
        ItemStack i = new ItemStack(Material.GLASS_BOTTLE, 1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName("Binoculars");
        i.setItemMeta(im);
        return i;
    }

    private PotionEffect burnSave() {
        PotionEffect pe = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100, 0);
        return pe;
    }

    private ItemStack softFallBoots() {
        ItemStack i = new ItemStack(Material.DIAMOND_BOOTS, 1);
        i.addEnchantment(Enchantment.PROTECTION_FALL, 3);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName("Soft Fall Boots");
        i.setItemMeta(im);
        return i;
    }

    private void addToInventory(ItemStack i) {
        if (getPlayer().getInventory().contains(i.getType())) {
            getPlayer().getInventory().remove(i.getType());
        }
        getPlayer().getInventory().addItem(i);
    }

    private void applyPlayerEffects(PotionEffect pe) {
        getPlayer().addPotionEffect(pe);
    }
}
