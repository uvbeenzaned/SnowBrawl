package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;
import java.util.List;

public enum Powers implements IAddonDefs {
	SPEED("Speed"), SLOWDOWN("Slowdown"), BLINDNESS("Blindness"), SPONTANEOUS_COMBUSTION("Spontaneous Combustion"), INSTA_RELOAD("Insta-reload"), SNIPER("Sniper"), SMITE("Smite"), VELOCITY("Velocity"), ERUPTION("Eruption"), NONE("None");

	private final String name;

	private Powers(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equalsIgnoreCase(otherName);
	}

	public String toString() {
		return name;
	}

	public static List<String> toStringList() {
		List<String> pwl = new ArrayList<String>();
		for (Powers pw : values()) {
			pwl.add(pw.toString().toLowerCase());
		}
		return pwl;
	}

}