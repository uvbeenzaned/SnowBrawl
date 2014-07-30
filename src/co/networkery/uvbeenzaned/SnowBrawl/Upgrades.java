package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;
import java.util.List;

public enum Upgrades implements IAddonDefs {
	SOFT_FALL_BOOTS("Soft Fall Boots"), HIGHER_ERUPTION_DENSITY("Higher Eruption Density"), /*SNIPER_RIFLE_SILENCER("Sniper Rifle Silencer"),*/ /*EXTENDED_SNIPER_MAGAZINE("Extended Sniper Magazine"),*/ BINOCULARS("Binoculars"), /*POWER_RELOAD_TIME_REDUCTION("Power Reload Time Reduction"),*/ BURN_SAVE("Burn Save");

	private final String name;

	private Upgrades(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equalsIgnoreCase(otherName);
	}

	public String toString() {
		return name;
	}

	public static List<String> toStringList() {
		List<String> upl = new ArrayList<String>();
		for (Upgrades u : values()) {
			upl.add(u.toString().toLowerCase());
		}
		return upl;
	}
}
