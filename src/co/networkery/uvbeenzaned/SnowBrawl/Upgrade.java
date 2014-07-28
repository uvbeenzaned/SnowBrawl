package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Upgrade implements IAddon {

	private Upgrades upgrade;
	private String upgradename;
	private Player player;
	private String description;
	private double price;

	public Upgrade(Upgrades u, Player p) {
		upgrade = u;
		upgradename = u.toString();
		player = p;
		fetchConfiguration();
	}

	private void fetchConfiguration() {
		if (Configurations.getUpgradesconfig().contains(upgradename)) {
			setDescription(Configurations.getUpgradesconfig().getConfigurationSection(upgradename).getString("description"));
			setPrice(Configurations.getUpgradesconfig().getConfigurationSection(upgradename).getDouble("price"));
		} else {
			Configurations.getUpgradesconfig().createSection(upgradename);
			Configurations.getUpgradesconfig().getConfigurationSection(upgradename).set("description", "This upgrade is missing a description.");
			Configurations.getUpgradesconfig().getConfigurationSection(upgradename).set("price", (double) 0.0);
			Configurations.saveUpgradesConfig();
		}

	}

	public IAddonDefs getType() {
		return upgrade;
	}

	public void set(IAddonDefs a) {
		upgrade = (Upgrades) a;
	}

	public String getName() {
		return upgradename;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player p) {
		player = p;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String d) {
		description = d;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double pr) {
		price = pr;
	}

	public ArrayList<String> getInfo() {
		ArrayList<String> info = new ArrayList<String>();
		info.add(upgradename + ":");
		info.add("    Description: " + description);
		if (Store.isEnabled()) {
			info.add("    Cost: " + price);
			Stats s = new Stats(player);
			if (!s.ownsUpgrade(upgrade)) {
				info.add(ChatColor.BLUE + "    You can purchase this upgrade by using /sb store buy upgrade " + upgrade.toString() + ".");
			} else {
				info.add(ChatColor.GREEN + "    You own this upgrade.");
			}
		} else {
			info.add("    Cost: 0.0");
			info.add(ChatColor.GREEN + "    Anyone can use this upgrade.");
		}
		return info;
	}

	public List<Powers> getPowerRequirements() {
		List<Powers> powers = new ArrayList<Powers>();
		switch (upgrade) {
		case BINOCULARS:
			powers.add(Powers.NONE);
			break;
		case BURN_SAVE:
			powers.add(Powers.NONE);
			break;
		/*
		 * case EXTENDED_SNIPER_MAGAZINE: powers.add(Powers.SNIPER); break; case
		 * HIGHER_ERUPTION_DENSITY: powers.add(Powers.ERUPTION); break; case
		 * POWER_RELOAD_TIME_REDUCTION: for(Powers pws : Powers.values()) {
		 * if(!pws.equals(Powers.NONE)) powers.add(pws); } break; case
		 * SNIPER_RIFLE_SILENCER: powers.add(Powers.SNIPER); break;
		 */
		case SOFT_FALL_BOOTS:
			powers.add(Powers.NONE);
			break;
		default:
			break;

		}
		return powers;
	}
	
	public List<Powers> getPowerConflicts() {
		List<Powers> powers = new ArrayList<Powers>();
		switch(upgrade) {
		case BINOCULARS:
			powers.add(Powers.SNIPER);
			break;
		default:
			break;
		}
		return powers;
	}

	public void apply() {
		switch (upgrade) {
		case BINOCULARS:
			addToInventory(binoculars());
			break;
		case BURN_SAVE:
			/*
			 * // no function on normal apply break; case
			 * EXTENDED_SNIPER_MAGAZINE: // no function required break; case
			 * HIGHER_ERUPTION_DENSITY: // no function required break; case
			 * POWER_RELOAD_TIME_REDUCTION: // no function required break; case
			 * SNIPER_RIFLE_SILENCER: // no function required break;
			 */
		case SOFT_FALL_BOOTS:
			player.getInventory().setBoots(softFallBoots());
			break;
		default:
			break;
		}
	}

	public void applySpecific() {
		switch (upgrade) {
		case BURN_SAVE:
			applyPlayerEffects(burnSave());
			break;
		default:
			break;
		}
	}

	public ItemStack getItem() {
		switch (upgrade) {
		case BINOCULARS:
			return binoculars();
		case SOFT_FALL_BOOTS:
			return softFallBoots();
		default:
			return null;
		}
	}

	public ItemStack getItemWithTitle() {
		switch (upgrade) {
		case BINOCULARS:
			return binoculars();
		case SOFT_FALL_BOOTS:
			return softFallBoots();
		default:
			ItemStack i = new ItemStack(Material.ANVIL, 1);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(upgradename);
			i.setItemMeta(im);
			return i;
		}
	}

	// upgrade functions

	private ItemStack binoculars() {
		ItemStack i = new ItemStack(Material.GLASS_BOTTLE, 1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("Binoculars");
		i.setItemMeta(im);
		return i;
	}

	private PotionEffect burnSave() {
		PotionEffect pe = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100, 0);
		return pe;
	}

	private ItemStack softFallBoots() {
		ItemStack i = new ItemStack(Material.DIAMOND_BOOTS, 1);
		i.addEnchantment(Enchantment.PROTECTION_FALL, 3);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("Soft Fall Boots");
		i.setItemMeta(im);
		return i;
	}

	private void addToInventory(ItemStack i) {
		if (getPlayer().getInventory().contains(i.getType())) {
			getPlayer().getInventory().remove(i.getType());
		}
		getPlayer().getInventory().addItem(i);
	}

	private void applyPlayerEffects(PotionEffect pe) {
		getPlayer().addPotionEffect(pe);
	}
}
