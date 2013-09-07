package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.configuration.file.FileConfiguration;

public class Configurations {

	private static Configuration config;
	private static Configuration arenasconfig;
	private static Configuration playersconfig;
	private static Configuration ranksconfig;
	private static Configuration kitsconfig;
	private static Configuration statsconfig;
	
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
	
	public static void saveAllConfigurations()
	{
		config.saveConfig();
		arenasconfig.saveConfig();
		playersconfig.saveConfig();
		ranksconfig.saveConfig();
		kitsconfig.saveConfig();
		statsconfig.saveConfig();
	}

	/**
	 * @return the config
	 */
	public static FileConfiguration getMainConfig() {
		return config.getConfig();
	}
	
	public static void saveMainConfig() {
		config.saveConfig();
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
	
	public static void saveArenasConfig() {
		config.saveConfig();
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
	
	public static void savePlayersConfig() {
		playersconfig.saveConfig();
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
	
	public static void saveRanksConfig() {
		ranksconfig.saveConfig();
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
	
	public static void saveKitsConfig() {
		kitsconfig.saveConfig();
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
	
	public static void saveStatsConfig() {
		statsconfig.saveConfig();
	}

	/**
	 * @param statsconfig the statsconfig to set
	 */
	public static void setStatsconfig(Configuration statsconfig) {
		Configurations.statsconfig = statsconfig;
	}
}
