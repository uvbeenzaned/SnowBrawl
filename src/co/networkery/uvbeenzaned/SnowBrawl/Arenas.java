package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.Set;

import org.bukkit.Location;

public class Arenas {

	public static Set<String> getArenaNames()
	{
		return Configurations.getArenasconfig().getKeys(false);
	}
}
