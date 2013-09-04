package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.configuration.file.FileConfiguration;

public class Configurations {

	private static Configuration config;
	private static Configuration arenasconfig;
	private static Configuration playersconfig;
	private static Configuration ranksconfig;
	private static Configuration kitsconfig;
	private static Configuration statsconfig;
	
	public Configurations(SB sb)
	{
		loadAllConfigurations(sb);
	}
	
	public static void loadAllConfigurations(SB sb)
	{
		config.saveDefaultConfig();
		arenasconfig.saveDefaultConfig();
		playersconfig.saveDefaultConfig();
		ranksconfig.saveDefaultConfig();
		kitsconfig.saveDefaultConfig();
		statsconfig.saveDefaultConfig();
		setMainConfig(new Configuration(sb, "config.yml"));
		setArenasconfig(new Configuration(sb, "arenas.yml"));
		setPlayersconfig(new Configuration(sb, "players.yml"));
		setRanksconfig(new Configuration(sb, "ranks.yml"));
		setStatsconfig(new Configuration(sb, "stats.yml"));
	}
	
	public static void reloadAllConfigurations()
	{
		config.reloadConfig();
		arenasconfig.reloadConfig();
		playersconfig.reloadConfig();
		ranksconfig.reloadConfig();
		kitsconfig.reloadConfig();
		statsconfig.reloadConfig();
	}

	/**
	 * @return the config
	 */
	public static FileConfiguration getMainConfig() {
		return config.getConfig();
	}

	/**
	 * @param config the config to set
	 */
	public static void setMainConfig(Configuration config) {
		Configurations.config = config;
	}

	/**
	 * @return the arenasconfig
	 */
	public static FileConfiguration getArenasconfig() {
		return arenasconfig.getConfig();
	}

	/**
	 * @param arenasconfig the arenasconfig to set
	 */
	public static void setArenasconfig(Configuration arenasconfig) {
		Configurations.arenasconfig = arenasconfig;
	}

	/**
	 * @return the playersconfig
	 */
	public static FileConfiguration getPlayersconfig() {
		return playersconfig.getConfig();
	}

	/**
	 * @param playersconfig the playersconfig to set
	 */
	public static void setPlayersconfig(Configuration playersconfig) {
		Configurations.playersconfig = playersconfig;
	}

	/**
	 * @return the ranksconfig
	 */
	public static FileConfiguration getRanksconfig() {
		return ranksconfig.getConfig();
	}

	/**
	 * @param ranksconfig the ranksconfig to set
	 */
	public static void setRanksconfig(Configuration ranksconfig) {
		Configurations.ranksconfig = ranksconfig;
	}

	/**
	 * @return the kitsconfig
	 */
	public static FileConfiguration getKitsconfig() {
		return kitsconfig.getConfig();
	}

	/**
	 * @param kitsconfig the kitsconfig to set
	 */
	public static void setKitsconfig(Configuration kitsconfig) {
		Configurations.kitsconfig = kitsconfig;
	}

	/**
	 * @return the statsconfig
	 */
	public static FileConfiguration getStatsconfig() {
		return statsconfig.getConfig();
	}

	/**
	 * @param statsconfig the statsconfig to set
	 */
	public static void setStatsconfig(Configuration statsconfig) {
		Configurations.statsconfig = statsconfig;
	}
}
