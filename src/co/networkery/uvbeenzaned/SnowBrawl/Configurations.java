package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.configuration.file.FileConfiguration;

public class Configurations {

    private static Configuration config;
    private static Configuration arenasconfig;
    private static Configuration playersconfig;
    private static Configuration powersconfig;
    private static Configuration upgradesconfig;

    /**
     * Load all configurations for every module.
     * @param sb The main SnowBrawl class.
     */
    public static void loadAllConfigurations(SB sb) {
        setMainConfig(new Configuration(sb, "config.yml"));
        setArenasconfig(new Configuration(sb, "arenas.yml"));
        setPlayersconfig(new Configuration(sb, "players.yml"));
        setPowersconfig(new Configuration(sb, "powers.yml"));
        setUpgradesconfig(new Configuration(sb, "upgrades.yml"));
        config.saveDefaultConfig();
        arenasconfig.saveDefaultConfig();
        playersconfig.saveDefaultConfig();
        powersconfig.saveDefaultConfig();
        upgradesconfig.saveDefaultConfig();
    }

    /**
     * Reload all configurations for every module.
     */
    public static void reloadAllConfigurations() {
        config.reloadConfig();
        arenasconfig.reloadConfig();
        playersconfig.reloadConfig();
        powersconfig.reloadConfig();
        upgradesconfig.reloadConfig();
    }

    /**
     * Save all configurations for every module.
     */
    public static void saveAllConfigurations() {
        config.saveConfig();
        arenasconfig.saveConfig();
        playersconfig.saveConfig();
        powersconfig.saveConfig();
        upgradesconfig.saveConfig();
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

    public static void saveMainConfig() {
        config.saveConfig();
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

    public static void saveArenasConfig() {
        arenasconfig.saveConfig();
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

    public static void savePlayersConfig() {
        playersconfig.saveConfig();
    }

    /**
     * @return the powersconfig
     */
    public static FileConfiguration getPowersconfig() {
        return powersconfig.getConfig();
    }

    /**
     * @param powersconfig the powersconfig to set
     */
    public static void setPowersconfig(Configuration powersconfig) {
        Configurations.powersconfig = powersconfig;
    }

    public static void savePowersConfig() {
        powersconfig.saveConfig();
    }

    /**
     * @return the upgradesconfig
     */
    public static FileConfiguration getUpgradesconfig() {
        return upgradesconfig.getConfig();
    }

    /**
     * @param upgradesconfig the upgradesconfig to set
     */
    public static void setUpgradesconfig(Configuration upgradesconfig) {
        Configurations.upgradesconfig = upgradesconfig;
    }

    public static void saveUpgradesConfig() {
        upgradesconfig.saveConfig();
    }
}