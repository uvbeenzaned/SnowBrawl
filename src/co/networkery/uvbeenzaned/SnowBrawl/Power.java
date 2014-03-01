package co.networkery.uvbeenzaned.SnowBrawl;

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
	private Player player;

	public Power(Powers p, Player pl) {
		setPower(p);
		setPlayer(pl);
	}

	public Powers getPower() {
		return power;
	}

	public void setPower(Powers power) {
		this.power = power;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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
			// no function required
			break;
		default:
			return 0;
		}
		return 0;
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
		addToInventory(i);
		return i;
	}
	
	private ItemStack sniperAmmo() {
		ItemStack i = new ItemStack(Material.ARROW);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("Ammo");
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
	
	private void addToInventory(ItemStack i) {
		if (getPlayer().getInventory().contains(i.getType())) {
			getPlayer().getInventory().remove(i.getType());
		}
		getPlayer().getInventory().addItem(i);
	}
}
