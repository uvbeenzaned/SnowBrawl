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

import org.bukkit.entity.Player;

public class Settings {

    public static int getRoundstartdelay() {
        return Configurations.getMainConfig().getInt("round-start-delay");
    }

    public static void setRoundstartdelay(int s, Player sender) {
        Configurations.getMainConfig().set("round-start-delay", s);
        Configurations.saveMainConfig();
        if (sender != null)
            Chat.sendPPM("Changed round-start-delay to " + String.valueOf(s) + " milliseconds.", sender);
    }

    public static int getTeamPoints() {
        return Configurations.getMainConfig().getInt("team-points");
    }

    public static void setTeampoints(int pts, Player sender) {
        Configurations.getMainConfig().set("team-points", pts);
        Configurations.saveMainConfig();
        if (sender != null)
            Chat.sendPPM("Changed team-points to " + String.valueOf(pts) + " .", sender);
    }

    public static int getSnowballReloadDelay() {
        return Configurations.getMainConfig().getInt("snowball-reload-delay");
    }

    public static void setSnowballReloadDelay(int d, Player sender) {
        Configurations.getMainConfig().set("snowball-reload-delay", d);
        Configurations.saveMainConfig();
        if (sender != null)
            Chat.sendPPM("Changed snowball-reload-delay to " + String.valueOf(d) + " .", sender);
    }

    public static boolean getStoreEnabled() {
        return Configurations.getMainConfig().getBoolean("store-enabled");
    }

    public static void setStoreEnabled(boolean enabled, Player sender) {
        Configurations.getMainConfig().set("store-enabled", enabled);
        Configurations.saveMainConfig();
        if (sender != null)
            Chat.sendPPM("Set store enabled to " + String.valueOf(enabled) + ".", sender);
    }
}
