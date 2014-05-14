package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class Power {

	private Powers power;
	private String powername;
	private Player player;
	private String description;
	private double price;

	public Power(Powers p, Player pl) {
		setPower(p);
		setPlayer(pl);
		powername = p.toString();
		fetchPowerConfiguration();
	}

	private void fetchPowerConfiguration() {
		if (Configurations.getPowersconfig().contains(powername)) {
			setDescription(Configurations.getPowersconfig().getConfigurationSection(powername).getString("description"));
			setPrice(Configurations.getPowersconfig().getConfigurationSection(powername).getDouble("price"));
		} else {
			Configurations.getPowersconfig().createSection(powername);
			Configurations.getPowersconfig().getConfigurationSection(powername).set("description", "This power is missing a description.");
			Configurations.getPowersconfig().getConfigurationSection(powername).set("price", (double) 0.0);
			Configurations.savePowersConfig();
		}
	}

	public Powers getPower() {
		return power;
	}

	public void setPower(Powers power) {
		this.power = power;
	}

	public String getPowerName() {
		return powername;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<String> getPowerInfo() {
		ArrayList<String> info = new ArrayList<String>();
		info.add(powername + ":");
		info.add("    Description: " + description);
		if (Store.isEnabled()) {
			info.add("    Cost: " + price);
			Stats s = new Stats(player);
			if (!s.ownsPower(power)) {
				info.add(ChatColor.BLUE + "    You can purchase this power by using /sb store buy power " + power.toString() + ".");
			} else {
				info.add(ChatColor.GREEN + "    You own this power.");
			}
		} else {
			info.add("    Cost: 0.0");
			info.add(ChatColor.GREEN + "    Anyone can use this power.");
		}
		return info;
	}

	public void apply() {
		switch (power) {
		case SPEED:
			addToInventory(speed());
			break;
		case SLOWDOWN:
			addToInventory(slowdown());
			break;
		case BLINDNESS:
			addToInventory(blindness());
			break;
		case SPONTANEOUS_COMBUSTION:
			addToInventory(spontaneousCombustion());
			break;
		case INSTA_RELOAD:
			// no function required
			break;
		case SNIPER:
			addToInventory(sniperRifle());
			addToInventory(sniperAmmo());
			break;
		case SMITE:
			addToInventory(smite());
			break;
		case VELOCITY:
			// no function required
			break;
		case ERUPTION:
			addToInventory(eruption());
			break;
		default:
			break;
		}
	}

	public int time() {
		switch (power) {
		case SPEED:
			return 30000;
		case SLOWDOWN:
			return 0;
		case BLINDNESS:
			return 0;
		case SPONTANEOUS_COMBUSTION:
			return 0;
		case INSTA_RELOAD:
			return 0;
		case SNIPER:
			return 30000;
		case SMITE:
			return 60000;
		case VELOCITY:
			return 0;
		case ERUPTION:
			return 30000;
		default:
			return 0;
		}
	}
	
	public ItemStack getPowerItemWithTitle() {
		ItemStack itemwithinfo;
		ItemMeta im;
		switch (power) {
		case SPEED:
			itemwithinfo = speed();
			im = itemwithinfo.getItemMeta();
			im.setDisplayName("Speed");
			itemwithinfo.setItemMeta(im);
			return itemwithinfo;
		case SLOWDOWN:
			itemwithinfo = slowdown();
			im = itemwithinfo.getItemMeta();
			im.setDisplayName("Slowdown");
			itemwithinfo.setItemMeta(im);
			return itemwithinfo;
		case BLINDNESS:
			itemwithinfo = blindness();
			im = itemwithinfo.getItemMeta();
			im.setDisplayName("Blindness");
			itemwithinfo.setItemMeta(im);
			return itemwithinfo;
		case SPONTANEOUS_COMBUSTION:
			itemwithinfo = spontaneousCombustion();
			im = itemwithinfo.getItemMeta();
			im.setDisplayName("Spontaneous Combustion");
			itemwithinfo.setItemMeta(im);
			return itemwithinfo;
		case INSTA_RELOAD:
			itemwithinfo = new ItemStack(Material.SNOW_BALL, 1);
			im = itemwithinfo.getItemMeta();
			im.setDisplayName("Insta-reload");
			itemwithinfo.setItemMeta(im);
			return itemwithinfo;
		case SNIPER:
			itemwithinfo = sniperRifle();
			im = itemwithinfo.getItemMeta();
			im.setDisplayName("Sniper");
			itemwithinfo.setItemMeta(im);
			return itemwithinfo;
		case SMITE:
			itemwithinfo = smite();
			im = itemwithinfo.getItemMeta();
			im.setDisplayName("Smite");
			itemwithinfo.setItemMeta(im);
			return itemwithinfo;
		case VELOCITY:
			itemwithinfo = new ItemStack(Material.FIRE, 1);
			im = itemwithinfo.getItemMeta();
			im.setDisplayName("Velocity");
			itemwithinfo.setItemMeta(im);
			return itemwithinfo;
		case ERUPTION:
			itemwithinfo = new ItemStack(Material.EGG, 1);
			im = itemwithinfo.getItemMeta();
			im.setDisplayName("Eruption");
			itemwithinfo.setItemMeta(im);
			return itemwithinfo;
		case NONE:
			itemwithinfo = new ItemStack(Material.THIN_GLASS, 1);
			im = itemwithinfo.getItemMeta();
			im.setDisplayName("None");
			itemwithinfo.setItemMeta(im);
			return itemwithinfo;
		default:
			return null;
		}
	}

	// power functions
	private ItemStack speed() {
		ItemStack i = new ItemStack(Material.POTION, 1);
		Potion p = new Potion(PotionType.SPEED);
		p.splash();
		p.setLevel(2);
		p.apply(i);
		return i;
	}

	private ItemStack slowdown() {
		ItemStack i = new ItemStack(Material.POTION, 2);
		Potion po = new Potion(PotionType.SLOWNESS);
		po.splash();
		po.apply(i);
		PotionMeta pm = (PotionMeta) i.getItemMeta();
		PotionEffect pe = new PotionEffect(PotionEffectType.SLOW, 300, 2);
		pm.addCustomEffect(pe, true);
		i.setItemMeta(pm);
		return i;
	}

	private ItemStack blindness() {
		ItemStack i = new ItemStack(Material.POTION, 2);
		Potion po = new Potion(1);
		po.splash();
		po.apply(i);
		PotionMeta pm = (PotionMeta) i.getItemMeta();
		PotionEffect pe = new PotionEffect(PotionEffectType.BLINDNESS, 160, 1);
		pm.addCustomEffect(pe, true);
		pm.setDisplayName("Blindness");
		i.setItemMeta(pm);
		return i;
	}

	private ItemStack spontaneousCombustion() {
		ItemStack i = new ItemStack(Material.POTION, 1);
		Potion po = new Potion(PotionType.POISON);
		po.splash();
		po.apply(i);
		PotionMeta pm = (PotionMeta) i.getItemMeta();
		PotionEffect pe = new PotionEffect(PotionEffectType.POISON, 200, 3);
		pm.addCustomEffect(pe, true);
		pm.setDisplayName("Spontaneous Combustion");
		i.setItemMeta(pm);
		return i;
	}

	private ItemStack sniperRifle() {
		ItemStack i = new ItemStack(Material.BOW);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("Sniper Rifle");
		i.setItemMeta(im);
		return i;
	}

	private ItemStack sniperAmmo() {
		ItemStack i = new ItemStack(Material.ARROW);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("Snow Ammo");
		i.setItemMeta(im);
		return i;
	}

	private ItemStack smite() {
		ItemStack i = new ItemStack(Material.BLAZE_ROD);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("Lightning Rod");
		i.setItemMeta(im);
		return i;
	}
	
	private ItemStack eruption() {
		ItemStack i = new ItemStack(Material.EGG);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("Eruption");
		i.setItemMeta(im);
		return i;
	}

	private void addToInventory(ItemStack i) {
		if (getPlayer().getInventory().contains(i.getType())) {
			getPlayer().getInventory().remove(i.getType());
		}
		getPlayer().getInventory().addItem(i);
	}
}
