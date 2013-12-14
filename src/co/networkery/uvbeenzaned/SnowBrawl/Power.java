package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
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
		case SNIPER:
			sniper();
			break;
		default:
			break;
		}
	}

	//power functions
	private void speed() {
		ItemStack i = new ItemStack(Material.POTION, 2);
		Potion p = new Potion(PotionType.SPEED);
		p.splash();
		p.setLevel(2);
		p.apply(i);
		getPlayer().getInventory().addItem(i);
	}
	
	private void sniper() {
		ItemStack i = new ItemStack(Material.BOW);
	}
}
