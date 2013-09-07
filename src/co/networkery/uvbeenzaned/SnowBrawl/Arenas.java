package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.HashSet;
import java.util.Set;

public class Arenas {

	private static Set<Arena> al = new HashSet<Arena>();
	
	public static void loadAllArenasFromFile()
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
	
	public static void addArena(Arena a)
	{
		al.add(a);
	}
	
	public static void removeArena(Arena a)
	{
		al.remove(a);
	}
}
