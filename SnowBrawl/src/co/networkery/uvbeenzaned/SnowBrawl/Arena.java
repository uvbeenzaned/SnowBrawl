package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class Arena {

    private String sender;
    private String name;
    private String description;
    private List<String> authors;
    private Location cyanspawn;
    private Location limespawn;
    private boolean enabled;

    public Arena(Player player, String name, String description, List<String> authors) {
        if (player != null) {
            setSender(player);
        }
        setName(name);
        setDescription(description);
        setAuthors(authors);
    }

    public Arena() {

    }

    public static Arena getInstanceFromConfig(String name) {
        for(String arenaname : Configurations.getArenasconfig().getKeys(false)) {
            if(arenaname.equalsIgnoreCase(name)) {
                Arena a = new Arena();
                a.setName(arenaname);
                a.setDescription(Configurations.getArenasconfig().getString(arenaname + ".description"));
                a.setAuthors(Configurations.getArenasconfig().getStringList(arenaname + ".authors"));
                a.setCyanSide(LocationSerializer.str2loc(Configurations.getArenasconfig().getString(arenaname + ".cyanspawn")));
                a.setLimeSide(LocationSerializer.str2loc(Configurations.getArenasconfig().getString(arenaname + ".limespawn")));
                a.enabled = Configurations.getArenasconfig().getBoolean(arenaname + ".enabled");
                return a;
            }
        }
        return null;
    }

    public Player getSender() {
        return Bukkit.getServer().getPlayer(sender);
    }

    public void setSender(Player p) {
        sender = p.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String d) {
        description = d;
    }

    public void addAuthor(String a) {
        authors.add(a);
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> a) {
        authors = a;
    }

    public String getAuthorsString() {
        String authorstring = "";
        boolean first = true;
        for (String author : getAuthors()) {
            if (!first) {
                authorstring = authorstring + ChatColor.RESET + ", " + author;
            } else {
                authorstring = authorstring + author;
                first = false;
            }
        }
        return authorstring.trim();
    }

    public Location getCyanSide() {
        return cyanspawn;
    }

    public void setCyanSide(Location l) {
        cyanspawn = l;
    }

    public Location getLimeSide() {
        return limespawn;
    }

    public void setLimeSide(Location l) {
        limespawn = l;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean e) {
        enabled = e;
    }

    private boolean tryPass() {
        if (cyanspawn != null) {
            if (limespawn != null) {
                if (name != "") {
                    if (description != "") {
                        if (!authors.isEmpty()) {
                            return true;
                        } else {
                            Chat.sendPPM("Missing at least one author name!", getSender());
                        }
                    } else {
                        Chat.sendPPM("Missing an arena description!", getSender());
                    }
                } else {
                    Chat.sendPPM("Missing an arena name!", getSender());
                }
            } else {
                Chat.sendPPM("Missing a lime side!", getSender());
            }
        } else {
            Chat.sendPPM("Missing a cyan side!", getSender());
        }
        return false;
    }

    public void save() {
        if (tryPass()) {
            Configurations.getArenasconfig().createSection(name);
            Configurations.getArenasconfig().set(name + ".cyanspawn", LocationSerializer.loc2str(cyanspawn));
            Configurations.getArenasconfig().set(name + ".limespawn", LocationSerializer.loc2str(limespawn));
            Configurations.getArenasconfig().set(name + ".description", description);
            Configurations.getArenasconfig().set(name + ".authors", authors);
            Configurations.getArenasconfig().set(name + ".enabled", enabled);
            Configurations.saveArenasConfig();
        }
    }

    public void delete() {
        Configurations.getArenasconfig().set(name, null);
        Configurations.saveArenasConfig();
    }
}
