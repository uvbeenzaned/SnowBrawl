package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class Store {

	private static boolean storeenabled;
	private static Economy serverecon = null;

	public Store(Plugin pl) {
		if (Settings.getStoreEnabled()) {
			if (setupEconomy(pl)) {
				storeenabled = true;
			} else {
				storeenabled = false;
			}
		} else {
			storeenabled = false;
		}
	}

	public static boolean isEnabled() {
		return storeenabled;
	}

	public static void setEnabled(boolean enabled) {
		storeenabled = enabled;
		if (enabled) {
			Stats s = null;
			for (String p : Configurations.getPlayersconfig().getKeys(false)) {
				s = new Stats(p);
				if (!s.getError()) {
					if (!s.getPurchasedPowers().contains(s.getPower().toString()) && s.getPower().getType() != Powers.NONE) {
						s.setPower(Powers.NONE);
					}
					for (String eup : s.getEnabledUpgrades()) {
						if (!s.getPurchasedUpgrades().contains(eup)) {
							s.disableUpgrade(Upgrades.valueOf(eup));
						}
					}
				}
			}
		}
	}

	private static boolean setupEconomy(Plugin pl) {
		if (pl.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = pl.getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		serverecon = rsp.getProvider();
		return serverecon != null;
	}

	private static Economy getEconomy() {
		return serverecon;
	}

	public static void purchasePower(Player sender, Player p, Powers pws) {
		if (getEconomy() != null) {
			Stats s = new Stats(p);
			Power pw = new Power(pws, p);
			EconomyResponse er = null;
			if (!s.ownsPower(pws) && !pws.equals(Powers.NONE)) {
				if (getEconomy().hasAccount(sender.getName())) {
					if (getEconomy().getBalance(sender.getName()) >= pw.getPrice()) {
						er = getEconomy().withdrawPlayer(sender.getName(), pw.getPrice());
						if (er.transactionSuccess()) {
							s.addPower(pws);
							Chat.sendPPM("You have just purchased the power " + pws.toString() + " for " + String.valueOf(pw.getPrice()) + " dollars.", sender);
						} else {
							Chat.sendPPM("There was a problem while making your transaction! Please report this error to the admin(s):", sender);
							Chat.sendPM("    " + er.errorMessage, sender);
						}
					} else {
						Chat.sendPPM("You do not have enough funds to buy this power!", sender);
					}
				}
			} else {
				Chat.sendPPM("You have already purchased the power " + pws.toString() + "!", sender);
			}
		} else {
			Chat.sendPPM("This server does not have a Vault supported economy!  Please report this error to the admin(s).", sender);
		}
	}

	public static void purchaseUpgrade(Player sender, Player p, Upgrades u) {
		if (getEconomy() != null) {
			Stats s = new Stats(p);
			Upgrade up = new Upgrade(u, p);
			EconomyResponse er = null;
			if (!s.ownsUpgrade(u)) {
				if (getEconomy().hasAccount(sender.getName())) {
					if (getEconomy().getBalance(sender.getName()) >= up.getPrice()) {
						er = getEconomy().withdrawPlayer(sender.getName(), up.getPrice());
						if (er.transactionSuccess()) {
							s.addUpgrade(u);
							Chat.sendPPM("You have just purchased the upgrade " + u.toString() + " for " + String.valueOf(up.getPrice()) + " dollars.", sender);
						} else {
							Chat.sendPPM("There was a problem while making your transaction! Please report this error to the admin(s):", sender);
							Chat.sendPM("    " + er.errorMessage, sender);
						}
					} else {
						Chat.sendPPM("You do not have enough funds to buy this upgrade!", sender);
					}
				}
			} else {
				Chat.sendPPM("You have already purchased the upgrade " + u.toString() + "!", sender);
			}
		} else {
			Chat.sendPPM("This server does not have a Vault supported economy!  Please report this error to the admin(s).", sender);
		}
	}
}
