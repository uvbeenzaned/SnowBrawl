package co.networkery.uvbeenzaned.SnowBrawl;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class StaticUpgradeData {

	private static List<String> burnsaveuses = new ArrayList<String>();
	
	public static boolean hasUsedRoundBurnSave(Player p) {
		return burnsaveuses.contains(p.getName());
	}
	
	public static void useBurnSave(Player p) {
		burnsaveuses.add(p.getName());
	}
	
	public static void clearBurnSaveUses() {
		burnsaveuses.clear();
	}
	
}
