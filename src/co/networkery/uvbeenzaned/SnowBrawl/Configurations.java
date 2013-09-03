package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.configuration.file.FileConfiguration;

public class Configurations {

	private Configuration config;
	private Configuration arenasconfig;
	private Configuration playersconfig;
	private Configuration ranksconfig;
	private Configuration kitsconfig;
	private Configuration statsconfig;
	
	public Configurations(SB sb) {
		setMainConfig(new Configuration(sb, "config.yml"));
		setArenasconfig(new Configuration(sb, "arenas.yml"));
		setPlayersconfig(new Configuration(sb, "players.yml"));
		setRanksconfig(new Configuration(sb, "ranks.yml"));
		setStatsconfig(new Configuration(sb, "stats.yml"));
	}

	/**
	 * @return the config
	 */
	public FileConfiguration getMainConfig() {
		return config.getConfig();
	}

	/**
	 * @param config the config to set
	 */
	public void setMainConfig(Configuration config) {
		this.config = config;
	}

	/**
	 * @return the arenasconfig
	 */
	public FileConfiguration getArenasconfig() {
		return arenasconfig.getConfig();
	}

	/**
	 * @param arenasconfig the arenasconfig to set
	 */
	public void setArenasconfig(Configuration arenasconfig) {
		this.arenasconfig = arenasconfig;
	}

	/**
	 * @return the playersconfig
	 */
	public FileConfiguration getPlayersconfig() {
		return playersconfig.getConfig();
	}

	/**
	 * @param playersconfig the playersconfig to set
	 */
	public void setPlayersconfig(Configuration playersconfig) {
		this.playersconfig = playersconfig;
	}

	/**
	 * @return the ranksconfig
	 */
	public FileConfiguration getRanksconfig() {
		return ranksconfig.getConfig();
	}

	/**
	 * @param ranksconfig the ranksconfig to set
	 */
	public void setRanksconfig(Configuration ranksconfig) {
		this.ranksconfig = ranksconfig;
	}

	/**
	 * @return the kitsconfig
	 */
	public FileConfiguration getKitsconfig() {
		return kitsconfig.getConfig();
	}

	/**
	 * @param kitsconfig the kitsconfig to set
	 */
	public void setKitsconfig(Configuration kitsconfig) {
		this.kitsconfig = kitsconfig;
	}

	/**
	 * @return the statsconfig
	 */
	public FileConfiguration getStatsconfig() {
		return statsconfig.getConfig();
	}

	/**
	 * @param statsconfig the statsconfig to set
	 */
	public void setStatsconfig(Configuration statsconfig) {
		this.statsconfig = statsconfig;
	}
}
