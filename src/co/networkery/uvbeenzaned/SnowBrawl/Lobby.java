package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Location;

public class Lobby {

	private SB sb;
	private Location lobbyspawnlocation;
	
	public Lobby(SB sb)
	{
		this.sb = sb;
	}
	
	public static Location getLobbyspawnlocation()
	{
		return lobbyspawnlocation;
	}
}
