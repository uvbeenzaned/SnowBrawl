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
