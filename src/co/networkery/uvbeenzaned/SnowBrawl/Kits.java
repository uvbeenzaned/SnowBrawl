package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.inventory.ItemStack;

public class Kits {
	
	private static Map<String, ItemStack> kits = new HashMap<String, ItemStack>();
	
	public Kits()
	{
		loadKitsFromConfig();
	}

	private void loadKitsFromConfig()
	{
		for(String kit : Configurations.getKitsconfig().getKeys(false))
		{
			String[] itemstring = Configurations.getKitsconfig().getConfigurationSection(kit + ".").getString("item").split(":"); 
			int id = Integer.parseInt(itemstring[0]);
			short durability = Short.parseShort(itemstring[1]);
			int amount = Configurations.getKitsconfig().getConfigurationSection(kit + ".").getInt("amount");
			ItemStack is = new ItemStack(id, amount, durability);
			kits.put(kit, is);
		}
	}
	
	public static Map<String, ItemStack> getKits()
	{
		return kits;
	}
	
	public static ItemStack getKit(String kit)
	{
		return kits.get(kit);
	}
	
	public static String getKitDescription(String kit)
	{
		return Configurations.getKitsconfig().getConfigurationSection(kit + ".").getString("description");
	}
}
