package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class Kit{
	
	private static Map<String, String> playerkits = new HashMap<String, String>();

	public static void givePlayerKit(Player p, String kit)
	{
		p.getInventory().addItem(Kits.getKit(kit));
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
