package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Board {

	private static ScoreboardManager manager;
	private static Scoreboard board;
	private static Team cyan;
	private static Team lime;
	private static Objective scores;
	private static Objective kills;
	private static Objective snowballs;

	public Board() {
		manager = Bukkit.getScoreboardManager();
		board = manager.getNewScoreboard();
		cyan = board.registerNewTeam("CYAN");
		cyan.setDisplayName("CYAN");
		cyan.setPrefix(ChatColor.AQUA + "");
		lime = board.registerNewTeam("LIME");
		lime.setDisplayName("LIME");
		lime.setPrefix(ChatColor.GREEN + "");
		scores = board.registerNewObjective("score", "dummy");
		scores.setDisplaySlot(DisplaySlot.SIDEBAR);
		scores.setDisplayName(ChatColor.LIGHT_PURPLE + "[" + ChatColor.GREEN + "Score" + ChatColor.LIGHT_PURPLE + "]");
		kills = board.registerNewObjective("kills", "dummy");
		kills.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		kills.setDisplayName(ChatColor.LIGHT_PURPLE + "[" + ChatColor.RED + "Kills" + ChatColor.LIGHT_PURPLE + "]");
		snowballs = board.registerNewObjective("snowballs", "dummy");
		snowballs.setDisplaySlot(DisplaySlot.BELOW_NAME);
		snowballs.setDisplayName(ChatColor.LIGHT_PURPLE + "[" + ChatColor.GOLD + "Snowballs Thrown" + ChatColor.LIGHT_PURPLE + "]");
	}

	public static void addPlayer(Player p) {
		if (TeamCyan.hasPlayer(p))
			cyan.addPlayer(p);
		if (TeamLime.hasPlayer(p))
			lime.addPlayer(p);
		p.setScoreboard(board);
		updatePlayer(p);
	}

	public static void removePlayer(Player p) {
		if (TeamCyan.hasPlayer(p))
			cyan.removePlayer(p);
		if (TeamLime.hasPlayer(p))
			lime.removePlayer(p);
		board.resetScores(p);
		p.setScoreboard(manager.getNewScoreboard());
	}

	public static void updatePlayer(Player p) {
		Stats s = new Stats(p);
		Score score = scores.getScore(p);
		score.setScore(s.getPoints());
		Score kill = kills.getScore(p);
		kill.setScore(s.getLosses());
		Score snowball = snowballs.getScore(p);
		snowball.setScore(s.getSnowballsThrown());
	}

	public static void updateAllPlayers() {
		for (String p : TeamCyan.getPlayers()) {
			updatePlayer(Bukkit.getPlayer(p));
		}
		for (String p : TeamLime.getPlayers()) {
			updatePlayer(Bukkit.getPlayer(p));
		}
	}

}
