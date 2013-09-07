package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Arenas implements Listener{

	private static Set<Arena> al = new HashSet<Arena>();
	
	public Arenas(JavaPlugin p)
	{
		p.getServer().getPluginManager().registerEvents(this, p);
	}
	
	public static void loadAllArenasFromConfig()
	{
		for(String key : Configurations.getArenasconfig().getKeys(false))
		{
			Arena.getInstanceFromConfig(key);
		}
	}
	
	public static Set<String> getNameList()
	{
		Set<String> n = new HashSet<String>();
		for(Arena a : al)
		{
			n.add(a.getName());
		}
		return n;
	}
	
	public static Set<Arena> getArenas()
	{
		return al;
	}
	
	public static Arena[] getArenasArray()
	{
		return al.toArray(new Arena[0]);
	}
	
	public static void addArena(Arena a)
	{
		al.add(a);
	}
	
	public static void removeArena(Arena a)
	{
		al.remove(a);
	}
	
	public static void startArenaWizard()
	{
		
	}
}
