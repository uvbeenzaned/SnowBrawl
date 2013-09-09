package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.entity.Player;

public class Settings {

	public static int getRoundstartdelay()
	{
		return Configurations.getMainConfig().getInt("round-start-delay");
	}
	
	public static void setRoundstartdelay(int s, Player sender)
	{
		Configurations.getMainConfig().set("round-start-delay", s);
		Configurations.saveMainConfig();
		if(sender != null)
			Chat.sendPPM("Changed round-start-delay to " + String.valueOf(s) + " seconds.", sender);
	}
	
	public static int getTeamPoints()
	{
		return Configurations.getMainConfig().getInt("team-points");
	}
	
	public static void setTeampoints(int pts, Player sender)
	{
		Configurations.getMainConfig().set("team-points", pts);
		Configurations.saveMainConfig();
		if(sender != null)
			Chat.sendPPM("Changed team-points to " + String.valueOf(pts) + " .", sender);
	}
}
