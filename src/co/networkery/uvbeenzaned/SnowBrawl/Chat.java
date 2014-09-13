package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {

    private static String format = ChatColor.GOLD + "[" + ChatColor.AQUA + "SnowBrawl" + ChatColor.GOLD + "] " + ChatColor.RESET;
    private static String nopermissionerrormsg = "You do not have permission to use that!";

    /**
     * Prepend the special SnowBrawl label to a string.
     *
     * @param s The string to format.
     * @return The formatted string.
     */
    public static String formatString(String s) {
        return format + s;
    }

    /**
     * Send a formatted team color message to a player.
     *
     * @param msg The message to send.
     * @param p   The player to send the message to.
     */
    public static void sendPPM(String msg, Player p) {
        String nmsg = "";
        for (String s : msg.split(" ")) {
            if (s.toLowerCase().contains("cyan")) {
                nmsg = nmsg + ChatColor.AQUA + s + " ";
            }
            if (s.toLowerCase().contains("lime")) {
                nmsg = nmsg + ChatColor.GREEN + s + " ";
            }
            if (TeamCyan.stringContainsPlayer(s)) {
                nmsg = nmsg + ChatColor.AQUA + s + " ";
            }
            if (TeamLime.stringContainsPlayer(s)) {
                nmsg = nmsg + ChatColor.GREEN + s + " ";
            }
            if (!s.toLowerCase().contains("cyan") && !s.toLowerCase().contains("lime") && !TeamCyan.stringContainsPlayer(s) && !TeamLime.stringContainsPlayer(s)) {
                nmsg = nmsg + s + " ";
            }
        }
        if (p != null) {
            if (p.isValid() && !p.isDead()) {
                p.sendMessage(format + nmsg);
            }
        }
    }

    /**
     * Send a plain message to a player.
     *
     * @param msg The message to send.
     * @param p   The player to send the message to.
     */
    public static void sendPM(String msg, Player p) {
        p.sendMessage(msg);
    }

    /**
     * Get the default permission error message.
     *
     * @return The permission error message.
     */
    public static String standardPermissionErrorMessage() {
        return nopermissionerrormsg;
    }

    /**
     * Send all of team cyan formatted team color message.
     *
     * @param msg The message to send.
     */
    public static void sendTeamCyanMsg(String msg) {
        for (String p : TeamCyan.getPlayers()) {
            Chat.sendPPM(msg, Bukkit.getServer().getPlayer(p));
        }
    }

    /**
     * Send all of team lime formatted team color message.
     *
     * @param msg The message to send.
     */
    public static void sendTeamLimeMsg(String msg) {
        for (String p : TeamLime.getPlayers()) {
            Chat.sendPPM(msg, Bukkit.getServer().getPlayer(p));
        }
    }

    /**
     * Send all teams a formatted team message.
     *
     * @param msg
     */
    public static void sendAllTeamsMsg(String msg) {
        for (String p : TeamCyan.getPlayers()) {
            Chat.sendPPM(msg, Bukkit.getServer().getPlayer(p));
        }
        for (String p : TeamLime.getPlayers()) {
            Chat.sendPPM(msg, Bukkit.getServer().getPlayer(p));
        }
    }

    /**
     * Send all teams a message that is not formatted.
     *
     * @param msg The message to send.
     */
    public static void sendAllTeamsMsgNoFormat(String msg) {
        for (String p : TeamCyan.getPlayers()) {
            Chat.sendPM(msg, Bukkit.getServer().getPlayer(p));
        }
        for (String p : TeamLime.getPlayers()) {
            Chat.sendPM(msg, Bukkit.getServer().getPlayer(p));
        }
    }
}
