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
			speed();
			break;
		case SLOWDOWN:
			slowdown();
			break;
		case BLINDNESS:
			blindness();
			break;
		case SPONTANEOUS_COMBUSTION:
			spontaneousCombustion();
			break;
		case INSTA_RELOAD:
			// no function required
			break;
		case SNIPER:
			sniper();
			break;
		default:
			break;
		}
	}

	// power functions
	private void speed() {
		ItemStack i = new ItemStack(Material.POTION, 1);
		Potion p = new Potion(PotionType.SPEED);
		p.splash();
		p.setLevel(2);
		p.apply(i);
		if (getPlayer().getInventory().contains(i.getType())) {
			getPlayer().getInventory().remove(i.getType());
		}
		getPlayer().getInventory().addItem(i);
	}

	private void slowdown() {
		ItemStack i = new ItemStack(Material.POTION, 2);
		Potion po = new Potion(PotionType.SLOWNESS);
		po.splash();
		po.apply(i);
		PotionMeta pm = (PotionMeta) i.getItemMeta();
		PotionEffect pe = new PotionEffect(PotionEffectType.SLOW, 300, 2);
		pm.addCustomEffect(pe, true);
		i.setItemMeta(pm);
		if (getPlayer().getInventory().contains(i.getType())) {
			getPlayer().getInventory().remove(i.getType());
		}
		getPlayer().getInventory().addItem(i);
	}
	
	private void blindness() {
		ItemStack i = new ItemStack(Material.POTION, 0);
		Potion po = new Potion(1);
		po.splash();
		po.apply(i);
		PotionMeta pm = (PotionMeta) i.getItemMeta();
		PotionEffect pe = new PotionEffect(PotionEffectType.BLINDNESS, 100, 1);
		pm.addCustomEffect(pe, true);
		pm.setDisplayName("Blindness");
		i.setItemMeta(pm);
		if (getPlayer().getInventory().contains(i.getType())) {
			getPlayer().getInventory().remove(i.getType());
		}
		getPlayer().getInventory().addItem(i);
	}
	
	private void spontaneousCombustion() {
		ItemStack i = new ItemStack(Material.POTION, 1);
		Potion po = new Potion(PotionType.POISON);
		po.splash();
		po.apply(i);
		PotionMeta pm = (PotionMeta) i.getItemMeta();
		PotionEffect pe = new PotionEffect(PotionEffectType.POISON, 200, 3);
		pm.addCustomEffect(pe, true);
		pm.setDisplayName("Spontaneous Combustion");
		i.setItemMeta(pm);
		if (getPlayer().getInventory().contains(i.getType())) {
			getPlayer().getInventory().remove(i.getType());
		}
		getPlayer().getInventory().addItem(i);
	}

	private void sniper() {
		ItemStack i = new ItemStack(Material.BOW);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("Sniper Rifle");
		i.setItemMeta(im);
		if (getPlayer().getInventory().contains(i.getType())) {
			getPlayer().getInventory().remove(i.getType());
		}
		getPlayer().getInventory().addItem(i);
		ItemStack i1 = new ItemStack(Material.ARROW);
		ItemMeta im1 = i1.getItemMeta();
		im1.setDisplayName("Ammo");
		i1.setItemMeta(im1);
		if (getPlayer().getInventory().contains(i1.getType())) {
			getPlayer().getInventory().remove(i1.getType());
		}
		getPlayer().getInventory().addItem(i1);
	}
}
