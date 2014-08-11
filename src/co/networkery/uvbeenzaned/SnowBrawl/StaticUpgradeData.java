package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

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
