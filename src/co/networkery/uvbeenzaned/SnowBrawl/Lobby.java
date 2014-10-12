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

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Lobby {

    /**
     * Get the location that all game players will go to on spawn and when games are over.
     *
     * @return The location of the lobby.
     */
    public static Location getLobbyspawnlocation() {
        return LocationSerializer.str2loc(Configurations.getMainConfig().getString("lobby-spawn-location"));
    }

    /**
     * Set the location that all game players will go to on spawn and when games are over.
     *
     * @param l      A location to set.
     * @param sender The person who set this location.
     */
    public static void setLobbyspawnlocation(Location l, Player sender) {
        Configurations.getMainConfig().set("lobby-spawn-location", LocationSerializer.loc2str(l));
        Configurations.saveMainConfig();
        if (sender != null)
            Chat.sendPPM("The lobby's spawn location has changed to " + LocationSerializer.loc2str(l) + ".", sender);
    }
}
