package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SBCommandExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{	
		if(sender instanceof Player)
		{
			Player p = (Player)sender;
			switch(args[0].toLowerCase())
			{
			case "config":
				if(p.isOp() || p.hasPermission("SnowBrawl.config"))
				{ 
					switch(args[1].toLowerCase())
					{
					case "set-lobby-spawn-location":
						Lobby.setLobbyspawnlocation(p.getLocation(), p);
						return true;
					case "set-round-start-delay":
						if(args.length > 2)
						Settings.setRoundstartdelay(Integer.parseInt(args[2]), p);
						return true;
					case "set-team-points":
						if(args.length > 2)
						Settings.setTeampoints(Integer.parseInt(args[2]), p);
						return true;
					}
					return false;
				}
				Chat.sendPPM(Chat.standardPermissionErrorMessage(), p);
				return true;
			}
		}
		else
		{

		}
		return false;
	}	
}
