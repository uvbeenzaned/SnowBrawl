package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SBCommandExecutor implements CommandExecutor{

	private SB sb;
	
	public SBCommandExecutor(SB sb)
	{
		this.sb = sb;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{	
		if(sender instanceof Player)
		{
			switch(args[0])
			{
			case "
			}
		}
		else
		{
			
		}
		return false;
	}	
}
