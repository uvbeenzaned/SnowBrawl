package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.configuration.file.FileConfiguration;

public class Configurations {

	private static Configuration config;
	private static Configuration arenasconfig;
	private static Configuration playersconfig;
	private static Configuration powersconfig;

	public static void loadAllConfigurations(SB sb) {
		setMainConfig(new Configuration(sb, "config.yml"));
		setArenasconfig(new Configuration(sb, "arenas.yml"));
		setPlayersconfig(new Configuration(sb, "players.yml"));
		setPowersconfig(new Configuration(sb, "powers.yml"));
		config.saveDefaultConfig();
		arenasconfig.saveDefaultConfig();
		playersconfig.saveDefaultConfig();
		powersconfig.saveDefaultConfig();
	}

	public static void reloadAllConfigurations() {
		config.reloadConfig();
		arenasconfig.reloadConfig();
		playersconfig.reloadConfig();
		powersconfig.reloadConfig();
	}

	public static void saveAllConfigurations() {
		config.saveConfig();
		arenasconfig.saveConfig();
		playersconfig.saveConfig();
		powersconfig.saveConfig();
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
	 * @param config
	 *            the config to set
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
		arenasconfig.saveConfig();
	}

	/**
	 * @param arenasconfig
	 *            the arenasconfig to set
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
	 * @param playersconfig
	 *            the playersconfig to set
	 */
	public static void setPlayersconfig(Configuration playersconfig) {
		Configurations.playersconfig = playersconfig;
	}

	/**
	 * @return the powersconfig
	 */
	public static FileConfiguration getPowersconfig() {
		return powersconfig.getConfig();
	}

	public static void savePowersConfig() {
		powersconfig.saveConfig();
	}

	/**
	 * @param powersconfig
	 *            the powersconfig to set
	 */
	public static void setPowersconfig(Configuration powersconfig) {
		Configurations.powersconfig = powersconfig;
	}
}
