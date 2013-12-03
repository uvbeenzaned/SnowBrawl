package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.Random;

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
			if(args.length > 0) {
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
						case "set-snowball-reload-delay":
							if(args.length > 2)
								Settings.setSnowballReloadDelay(Integer.parseInt(args[2]), p);
							return true;
						}
						return false;
					}
					Chat.sendPPM(Chat.standardPermissionErrorMessage(), p);
					return true;
				case "arena":
					if(p.isOp() || p.hasPermission("SnowBrawl.arena"))
					{
						switch(args[1].toLowerCase())
						{
						case "list":
							String astring = "";
							Chat.sendPPM("Arena list:", p);
							for(String name : Arenas.getNameList()) {
								astring = astring + name + ", ";
							}
							Chat.sendPM(astring, p);
							return true;
						case "info":
							if(args.length > 2) {
								Arena a = Arena.getInstanceFromConfig(Utilities.convertArenaArgsToString(args, 2));
								if(a != null)
									Chat.sendPPM("Name: " + a.getName(), p);
									Chat.sendPPM("Description: " + a.getDescription(), p);
									Chat.sendPPM("Authors: " + a.getAuthorsString(), p);
									Chat.sendPPM("CYAN side: " + LocationSerializer.loc2str(a.getCyanSide()), p);
									Chat.sendPPM("LIME side: " + LocationSerializer.loc2str(a.getLimeSide()), p);
							}
							return true;
						case "warp":
							if(args.length > 2) {
								if(Arena.getInstanceFromConfig(Utilities.convertArenaArgsToString(args, 2)) != null)
									p.teleport(Arena.getInstanceFromConfig(Utilities.convertArenaArgsToString(args, 2)).getCyanSide());
							}
							return true;
						case "add":
							Arenas.startArenaWizard(p);
							return true;
						case "remove":
							if(args.length > 2) {
								if(Arena.getInstanceFromConfig(Utilities.convertArenaArgsToString(args, 2)) != null)
									Arena.getInstanceFromConfig(Utilities.convertArenaArgsToString(args, 2)).delete();
									Chat.sendPPM("Removed arena successfully!", p);
							}
							return true;
						}
					}
					Chat.sendPPM(Chat.standardPermissionErrorMessage(), p);
					return true;
				case "help":
					Help.printHelp(p);
					Help.printOpCommands(p);
					return true;
				case "start":
					if(p.isOp() && args.length > 1) {
						Round.startMap(Arena.getInstanceFromConfig(Utilities.convertArenaArgsToString(args, 1)));
					} else {
						Round.startTimerRound();
					}
					return true;
				case "stop":
					if(p.isOp()) {
						Clock.stopTimer();
					}
					return true;
				case "lobby":
					p.teleport(Lobby.getLobbyspawnlocation());
					return true;
				case "join":
					if((p.isOp() && args.length > 1)) {
						if(args[1].equalsIgnoreCase("cyan")) {
							TeamCyan.join(p);
							return true;
						}
						if(args[1].equalsIgnoreCase("lime")) {
							TeamLime.join(p);
							return true;
						}
					}
					if(TeamCyan.getPlayers().size() < TeamLime.getPlayers().size()){TeamCyan.join(p);return true;}
					if(TeamLime.getPlayers().size() < TeamCyan.getPlayers().size()){TeamLime.join(p);return true;}
					Random r = new Random(System.currentTimeMillis());
					int n = r.nextInt(10);
					if(n <= 5){TeamCyan.join(p);}
					if(n >= 5){TeamLime.join(p);}
					return true;
				case "leave":
					TeamCyan.leave(p);
					TeamLime.leave(p);
				}
			}
			else
			{
				Help.printHelp(p);
				Help.printOpCommands(p);
				return true;
			}
		}
		else
		{
			sender.sendMessage(Chat.formatString("You have to be a player to run commands here."));
			return true;
		}
		return false;
	}	
}
