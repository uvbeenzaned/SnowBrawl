package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.configuration.file.FileConfiguration;

public interface IConfiguration {

	public void reloadConfig();

	public FileConfiguration getConfig();

	public void saveConfig();

	public void saveDefaultConfig();

}