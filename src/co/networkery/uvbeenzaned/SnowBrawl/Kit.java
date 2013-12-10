package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Kit {

	private String name;
	private int itemnumber;
	private short durability;
	private ItemStack item;
	private int amount;
	private String description;

	public Kit(String kitname, String kititem, int kitamount,
			String kitdescription) {
		setName(kitname);
		setItem(Material.valueOf(kititem.split(":")[0]));
		setDurability(Short.parseShort(kititem.split(":")[1]));
		setAmount(kitamount);
		setDescription(kitdescription);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the itemnumber
	 */
	public int getItemnumber() {
		return itemnumber;
	}

	/**
	 * @param itemnumber
	 *            the itemnumber to set
	 */
	public void setItemnumber(int itemnumber) {
		this.itemnumber = itemnumber;
	}

	/**
	 * @return the item
	 */
	public ItemStack getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(Material m) {
		this.item = new ItemStack(m);
		this.item.setAmount(getAmount());
		this.item.setDurability(getDurability());
	}

	/**
	 * @return the durability
	 */
	public short getDurability() {
		return durability;
	}

	/**
	 * @param durability
	 *            the durability to set
	 */
	public void setDurability(short durability) {
		this.durability = durability;
		this.item.setDurability(durability);
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
		this.item.setAmount(amount);
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
