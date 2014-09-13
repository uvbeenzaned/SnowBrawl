package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class Board {

    private static ScoreboardManager manager;
    private static Scoreboard board;
    private static Team cyan;
    private static Team lime;
    private static Team out;
    private static String scoredisplayname = ChatColor.LIGHT_PURPLE + "[" + ChatColor.GREEN + "Points" + ChatColor.LIGHT_PURPLE + "]";
    private static Objective scores;
    private static Objective kills;
    private static Objective snowballs;

    /**
     * Game scoreboard initializer.
     */
    public Board() {
        manager = Bukkit.getScoreboardManager();
        board = manager.getNewScoreboard();
        cyan = board.registerNewTeam("CYAN");
        cyan.setDisplayName("CYAN");
        cyan.setPrefix(ChatColor.AQUA + "");
        lime = board.registerNewTeam("LIME");
        lime.setDisplayName("LIME");
        lime.setPrefix(ChatColor.GREEN + "");
        out = board.registerNewTeam("OUT");
        out.setDisplayName("OUT");
        out.setPrefix(ChatColor.RED + "-" + ChatColor.DARK_GRAY);
        scores = board.registerNewObjective("score", "dummy");
        scores.setDisplaySlot(DisplaySlot.SIDEBAR);
        scores.setDisplayName(scoredisplayname);
        kills = board.registerNewObjective("kills", "dummy");
        kills.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        kills.setDisplayName(ChatColor.LIGHT_PURPLE + "[" + ChatColor.RED + "Kills" + ChatColor.LIGHT_PURPLE + "]");
        snowballs = board.registerNewObjective("snowballs", "dummy");
        snowballs.setDisplaySlot(DisplaySlot.BELOW_NAME);
        snowballs.setDisplayName(ChatColor.LIGHT_PURPLE + "[" + ChatColor.GOLD + "Snowballs Thrown" + ChatColor.LIGHT_PURPLE + "]");
    }

    /**
     * Add a player to the scoreboard.
     *
     * @param p The player to add to the scoreboard.
     */
    public static void addPlayer(Player p) {
        if (TeamCyan.hasPlayer(p))
            cyan.addPlayer(p);
        if (TeamLime.hasPlayer(p))
            lime.addPlayer(p);
        p.setScoreboard(board);
        updatePlayer(p);
    }

    /**
     * Remove a player from the scoreboard.
     *
     * @param p The player to remove from the scoreboard.
     */
    public static void removePlayer(Player p) {
        if (TeamCyan.hasPlayer(p))
            cyan.removePlayer(p);
        if (TeamLime.hasPlayer(p))
            lime.removePlayer(p);
        if (out.hasPlayer(p))
            out.removePlayer(p);
        board.resetScores(p);
        p.setScoreboard(manager.getNewScoreboard());
    }

    /**
     * Update a specific players stats on each board.
     *
     * @param p The player to update on the boards.
     */
    public static void updatePlayer(Player p) {
        Stats s = new Stats(p);
        Score score = scores.getScore(p);
        score.setScore(s.getPoints());
        Score kill = kills.getScore(p);
        kill.setScore(s.getHits());
        Score snowball = snowballs.getScore(p);
        snowball.setScore(s.getSnowballsThrown());
    }

    /**
     * Update every player on all the boards.
     */
    public static void updateAllPlayers() {
        for (String p : TeamCyan.getPlayers()) {
            updatePlayer(Bukkit.getPlayer(p));
        }
        for (String p : TeamLime.getPlayers()) {
            updatePlayer(Bukkit.getPlayer(p));
        }
    }

    /**
     * Display a player as out of the game.
     *
     * @param p The player to out.
     */
    public static void outPlayer(Player p) {
        if (cyan.hasPlayer(p))
            cyan.removePlayer(p);
        if (lime.hasPlayer(p))
            lime.removePlayer(p);
        out.addPlayer(p);
        updatePlayer(p);
    }

    /**
     * Clear all outed players.
     */
    public static void clearOutPlayers() {
        for (OfflinePlayer p : out.getPlayers()) {
            out.removePlayer(p);
            if (p.isOnline()) {
                addPlayer((Player) p);
            }
        }
    }

    /**
     * Append text to the end of the scoreboard title.
     *
     * @param t         The text to add.
     * @param overwrite True to overwrite the current scoreboard title with the new text, false to not overwrite the last title.
     */
    public static void appendScoreBoardTitle(String t, boolean overwrite) {
        if (overwrite) {
            scores.setDisplayName(scoredisplayname + " " + t);
        } else {
            scores.setDisplayName(scores.getDisplayName() + " " + t);
        }
    }

    /**
     * Prepend text to the beginning of the scoreboard title.
     *
     * @param t         The text to add.
     * @param overwrite True to overwrite the current scoreboard title with the new text, false to not overwrite the last title.
     */
    public static void prependScoreBoardTitle(String t, boolean overwrite) {
        if (overwrite) {
            scores.setDisplayName(t + " " + scoredisplayname);
        } else {
            scores.setDisplayName(t + " " + scores.getDisplayName());
        }
    }

    /**
     * Reset the scoreboard back to the default title.
     */
    public static void resetScoreBoardTitle() {
        scores.setDisplayName(scoredisplayname);
    }

}
