package co.networkery.uvbeenzaned.SnowBrawl;

public enum Powers {
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

}