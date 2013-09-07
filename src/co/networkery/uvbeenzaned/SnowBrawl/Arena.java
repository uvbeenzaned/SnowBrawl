package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Arena {
	
	private Player sender;
	private String name = "";
	private String description = "";
	private Set<String> authors;
	private Location cyan_spawn;
	private Location lime_spawn;	
	
	public Arena(Player player, String name, String description, Set<String> authors)
	{
		setSender(player);
		setName(name);
		setDescription(description);
		setAuthors(authors);
	}
	
	public static Arena getInstanceFromConfig(String name)
	{
		Arena a = new Arena(null, name, Configurations.getArenasconfig().getString(name + ".description"), null);
		a.setCyanSide(LocationSerializer.str2loc(Configurations.getArenasconfig().getString(name + ".cyan-side")));
		a.setLimeSide(LocationSerializer.str2loc(Configurations.getArenasconfig().getString(name + ".lime-side")));
		a.setAuthorsFromOtherArray(Configurations.getArenasconfig().getStringList(name + ".authors"));
		return a;
	}
	
	public void setSender(Player p)
	{
		sender = p;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public String getName()
	{ 
		return name;
	}
	
	public void setDescription(String d)
	{
		description = d;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setAuthors(Set<String> a)
	{
		authors = a;
	}
	
	public void setAuthorsFromOtherArray(List<String> l)
	{
		for(String i : l)
		{
			authors.add(i);
		}
	}
	
	public void addAuthor(String a)
	{
		authors.add(a);
	}
	
	public Set<String> getAuthors()
	{
		return authors;
	}
	
	public Location getCyanSide()
	{
		return cyan_spawn;
	}
	
	public void setCyanSide(Location l)
	{
		cyan_spawn = l;
	}
	
	public Location getLimeSide()
	{
		return lime_spawn;
	}	
	
	public void setLimeSide(Location l)
	{
		lime_spawn = l;
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
	
	public void save()
	{
		if(tryPass()) {
			Arenas.addArena(this);
			Configurations.getArenasconfig().createSection(name);
			Configurations.getArenasconfig().getConfigurationSection(name).createSection("cyan-spawn");
			Configurations.getArenasconfig().set(name + ".cyan-spawn", LocationSerializer.loc2str(cyan_spawn));
			Configurations.getArenasconfig().getConfigurationSection(name).createSection("lime-spawn");
			Configurations.getArenasconfig().set(name + ".lime-spawn", LocationSerializer.loc2str(lime_spawn));
			Configurations.getArenasconfig().getConfigurationSection(name).createSection("description");
			Configurations.getArenasconfig().set(name + ".descrition", description);
			Configurations.getArenasconfig().getConfigurationSection(name).createSection("authors");
			Configurations.getArenasconfig().set(name + ".authors", authors); //don't know if authors will save properly without serialization
			Configurations.saveArenasConfig();
		}
	}
	
	public void delete()
	{
		Configurations.getArenasconfig().set(name, null);
		if(Arenas.getArenas().contains(this))
		Arenas.removeArena(this);
	}
}
