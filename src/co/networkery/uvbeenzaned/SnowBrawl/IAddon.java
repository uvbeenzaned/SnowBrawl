package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;

import org.bukkit.entity.Player;

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
}
