package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Arena {
	
	private Player sender;
	private Location cyan_spawn;
	private Location lime_spawn;
	private String name = "";
	private String description = "";
	private Set<String> authors;
	
	public Arena(Player p, String name, String description)
	{
		this.sender = p;
		this.name = name;
		this.description = description;
	}
	
	public void setCyanSide(Location l)
	{
		cyan_spawn = l;
	}
	
	public void setLimeSide(Location l)
	{
		lime_spawn = l;
	}
	
	public void setAuthors(Set<String> a)
	{
		authors = a;
	}
	
	public void addAuthor(String a)
	{
		authors.add(a);
	}
	
	private boolean tryPass()
	{
		if(cyan_spawn != null) {
			if(lime_spawn != null) {
				if(name != "") {
					if(description != "") {
						if(!authors.isEmpty()) {
							return true;
						}
						else {
							Chat.sendPPM("Missing at least one author name!", sender);
						}
					}
					else {
						Chat.sendPPM("Missing an arena description!", sender);
					}
				}
				else {
					Chat.sendPPM("Missing an arena name!", sender);
				}
			}
			else {
				Chat.sendPPM("Missing a lime side!", sender);
			}
		}
		else {
			Chat.sendPPM("Missing a cyan side!", sender);
		}
		return false;
	}
	
	public void finish()
	{
		if(tryPass()) {
			Configurations.getArenasconfig().createSection(name);
			Configurations.getArenasconfig().getConfigurationSection(name).createSection("cyan-spawn");
			Configurations.getArenasconfig().set(name + ".cyan-spawn", LocationSerializer.loc2str(cyan_spawn));
			Configurations.getArenasconfig().getConfigurationSection(name).createSection("lime-spawn");
			Configurations.getArenasconfig().set(name + ".lime-spawn", LocationSerializer.loc2str(lime_spawn));
			Configurations.getArenasconfig().getConfigurationSection(name).createSection("description");
			Configurations.getArenasconfig().set(name + ".descrition", description);
			Configurations.getArenasconfig().getConfigurationSection(name).createSection("authors");
			Configurations.getArenasconfig().set(name + ".authors", authors); //don't know if authors will save properly without serialization
		}
	}
}
