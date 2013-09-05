package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.Set;

public class Arenas {

	public static Set<String> getArenaNames()
	{
		return Configurations.getArenasconfig().getKeys(false);
	}
}
