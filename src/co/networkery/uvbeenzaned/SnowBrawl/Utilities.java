package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Utilities{

	public static String convertArenaArgsToString(String[] args, int startpoint)
	{
		String aname = "";
		for(int i=startpoint; i<args.length; i++){
			aname = aname + args[i] + " ";
         }
		return aname.trim();
	}
	
	public static void givePlayerSnowballs(Player p)
	{
		p.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 64));
	}
	
}
