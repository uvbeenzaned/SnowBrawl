package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.inventory.ItemStack;

public class Kits {
	
	private static SB sb;
	private static Configurations c = new Configurations(sb);
	private static Map<String, ItemStack> kits = new HashMap<String, ItemStack>();
	
	public Kits(SB sb)
	{
		Kits.sb = sb;
		initializeKitsFromConfig();
	}

	private void initializeKitsFromConfig()
	{
		for(String kit : c.getKitsconfig().getKeys(false))
		{
			String[] itemstring = c.getKitsconfig().getConfigurationSection(kit + ".").getString("item").split(":"); 
			int id = Integer.parseInt(itemstring[0]);
			short durability = Short.parseShort(itemstring[1]);
			int amount = c.getKitsconfig().getConfigurationSection(kit + ".").getInt("amount");
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
		return c.getKitsconfig().getConfigurationSection(kit + ".").getString("description");
	}
}
