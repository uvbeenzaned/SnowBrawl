package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;

public class Kits {
	
	private static Set<Kit> kits = new HashSet<Kit>();
	private static Map<String, String> playerkits = new HashMap<String, String>();

	public static void loadAllKitsFromConfig()
	{
		for(String kit : Configurations.getKitsconfig().getKeys(false))
		{
			String itemstring = Configurations.getKitsconfig().getConfigurationSection(kit).getString("item");
			int amount = Configurations.getKitsconfig().getConfigurationSection(kit).getInt("amount");
			String description = Configurations.getKitsconfig().getConfigurationSection(kit).getString("description");
			kits.add(new Kit(kit, itemstring, amount, description));
		}
	}
	
	public static Set<Kit> getKits()
	{
		return kits;
	}
	
	public static Kit getKit(String kit)
	{
		Kit newkit = null;
		for(Kit k : kits) {
			if(k.getName() == kit) {
				newkit = k;
			}
		}
		return newkit;
	}
	
	public static String getKitDescription(String kit)
	{
		return Configurations.getKitsconfig().getConfigurationSection(kit + ".").getString("description");
	}

	public static void givePlayerKit(Player p, String kit)
	{
		p.getInventory().addItem(Kits.getKit(kit).getItem());
	}
	
	public static String getPlayerKit(Player p)
	{
		return playerkits.get(p.getName());
	}
	
	public static void setPlayerKit(Player p, String kit)
	{
		playerkits.put(p.getName(), kit);
	}
	
	public static void removePlayerKit(Player p)
	{
		playerkits.put(p.getName(), null);
	}
}
