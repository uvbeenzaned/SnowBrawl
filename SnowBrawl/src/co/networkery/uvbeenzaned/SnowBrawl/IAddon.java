package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public interface IAddon {
    public IAddonDefs getType();

    public void set(IAddonDefs a);

    public String getName();

    public Player getPlayer();

    public void setPlayer(Player p);

    public String getDescription();

    public void setDescription(String d);

    public double getPrice();

    public void setPrice(double pr);

    public ArrayList<String> getInfo();

    public void apply();

    public ItemStack getItem();

    public ItemStack getItemWithTitle();
}