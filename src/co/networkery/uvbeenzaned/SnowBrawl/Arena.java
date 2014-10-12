/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Networkery
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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

    /**
     * Creates a new instance of an arena.
     *
     * @param player      The player who is creating the arena.
     * @param name        The title of the arena.
     * @param description The description of the arena.
     * @param authors     The authors of the arena.
     */
    public Arena(Player player, String name, String description, List<String> authors) {
        if (player != null) {
            setSender(player);
        }
        setName(name);
        setDescription(description);
        setAuthors(authors);
    }

    /**
     * An empty constructor for filling in later.
     */
    public Arena() {

    }

    /**
     * Creates an instance of an Arena from an existing entry in the config file.
     *
     * @param name
     * @return
     */
    public static Arena getInstanceFromConfig(String name) {
        for (String arenaname : Configurations.getArenasconfig().getKeys(false)) {
            if (arenaname.equalsIgnoreCase(name)) {
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

    /**
     * Get the player who made this arena object.
     *
     * @return The player who created this object.
     */
    public Player getSender() {
        return Bukkit.getServer().getPlayer(sender);
    }

    /**
     * Set the player who made this arena object.
     *
     * @param p The player to set as the sender of this object.
     */
    public void setSender(Player p) {
        sender = p.getName();
    }

    /**
     * Get the title of this arena.
     *
     * @return The title of this arena.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the title of this arena.
     *
     * @param n The title to set this arena to.
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Get the description of this arena.
     *
     * @return The description of this arena.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of this arena.
     *
     * @param d The description to give this arena.
     */
    public void setDescription(String d) {
        description = d;
    }

    /**
     * Add an author to this arena.
     *
     * @param a The name of the author to add.
     */
    public void addAuthor(String a) {
        authors.add(a);
    }

    /**
     * Get all the authors of this arena.
     *
     * @return A list of all of the authors of this arena.
     */
    public List<String> getAuthors() {
        return authors;
    }

    /**
     * Set a list of pre-compiled authors to this arena.
     *
     * @param a A list of authors to set to this arena.
     */
    public void setAuthors(List<String> a) {
        authors = a;
    }

    /**
     * Get a list of authors of this arena formatted as a string.
     *
     * @return A string of all the authors of this arena.
     */
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

    /**
     * Get the cyan spawn point in this arena.
     *
     * @return The cyan side of this arena.
     */
    public Location getCyanSide() {
        return cyanspawn;
    }

    /**
     * Set the cyan spawn point of this arena.
     *
     * @param l A location to set as the cyan spawn point for this arena.
     */
    public void setCyanSide(Location l) {
        cyanspawn = l;
    }

    /**
     * Get the lime spawn point in this arena.
     *
     * @return The lime side of this arena.
     */
    public Location getLimeSide() {
        return limespawn;
    }

    /**
     * Set the lime spawn point of this arena.
     *
     * @param l A location to set as the lime spawn point for this arena.
     */
    public void setLimeSide(Location l) {
        limespawn = l;
    }

    /**
     * Check if this arena is enabled for map rotation.
     *
     * @return True if enabled, false if not.
     */
    public boolean getEnabled() {
        return enabled;
    }

    /**
     * Set this arena to enabled/disabled for map rotation.
     *
     * @param e True to enable, false to disable.
     */
    public void setEnabled(boolean e) {
        enabled = e;
    }

    /**
     * Validate this arena's parameters.
     *
     * @return True if passed check, false if not.
     */
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

    /**
     * Save this arena instance to config.
     */
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

    /**
     * Delete this arena instance from configuration.
     */
    public void delete() {
        Configurations.getArenasconfig().set(name, null);
        Configurations.saveArenasConfig();
    }
}
