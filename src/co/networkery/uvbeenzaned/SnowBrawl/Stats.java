package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Stats {

    private boolean error = false;
    private String player;

    /**
     * Find stats based on an online player.
     *
     * @param p The player to check against.
     */
    public Stats(Player p) {
        if (!Configurations.getPlayersconfig().contains(p.getName())) {
            Configurations.getPlayersconfig().createSection(p.getName());
        }
        player = p.getName();
    }

    /**
     * Find stats based on a players name. Use getError() to check for an error finding stats.
     *
     * @param p      The players name to check against.
     * @param sender The player who is looking up the stats of another player.
     */
    public Stats(String p, Player sender) {
        if (Configurations.getPlayersconfig().contains(p)) {
            player = p;
        } else {
            error = true;
            if (sender != null)
                Chat.sendPPM("There are no players with the name " + p + " in the records.", sender);
        }
    }

    /**
     * Find stats based on a players name. Use getError() to check for an error finding stats.
     *
     * @param p The players name to check against.
     */
    public Stats(String p) {
        if (Configurations.getPlayersconfig().contains(p)) {
            player = p;
        } else {
            error = true;
        }
    }

    /**
     * Gets stats of everyone who has played.
     *
     * @return A string containing all global stats.
     */
    public static ArrayList<String> getGlobalStats() {
        ArrayList<String> s = new ArrayList<String>();
        int plcount = 0;
        int points = 0;
        int hits = 0;
        float kd = 0;
        int sbthrown = 0;
        String sbrecordplayer = "";
        String sniperrecordplayer = "";
        long snowballdistancerecord = 0;
        long sniperdistancerecord = 0;
        for (String p : Configurations.getPlayersconfig().getKeys(false)) {
            points += Configurations.getPlayersconfig().getConfigurationSection(p).getInt("points");
            hits += Configurations.getPlayersconfig().getConfigurationSection(p).getInt("hits");
            if (Configurations.getPlayersconfig().getConfigurationSection(p).getInt("hits") > 0 && Configurations.getPlayersconfig().getConfigurationSection(p).getInt("losses") > 0) {
                kd += (float) (Configurations.getPlayersconfig().getConfigurationSection(p).getInt("hits") / Configurations.getPlayersconfig().getConfigurationSection(p).getInt("losses"));
                plcount++;
            }
            sbthrown += Configurations.getPlayersconfig().getConfigurationSection(p).getInt("snowballs-thrown");
            if (snowballdistancerecord < Configurations.getPlayersconfig().getConfigurationSection(p).getLong("snowball-record-distance")) {
                snowballdistancerecord = Configurations.getPlayersconfig().getConfigurationSection(p).getLong("snowball-record-distance");
                sbrecordplayer = p;
            }
            if (sniperdistancerecord < Configurations.getPlayersconfig().getConfigurationSection(p).getLong("sniper-record-distance")) {
                sniperdistancerecord = Configurations.getPlayersconfig().getConfigurationSection(p).getLong("sniper-record-distance");
                sniperrecordplayer = p;
            }
        }
        kd = (float) kd / plcount;
        s.add("Global Stats:");
        s.add("    Total Points: " + String.valueOf(points));
        s.add("    Total Hits: " + String.valueOf(hits));
        s.add("    Avg. H/L ratio: " + String.valueOf(kd));
        s.add("    Total Snowballs thrown: " + String.valueOf(sbthrown));
        s.add("    Record Snowball Shot Distance: " + sbrecordplayer + ChatColor.RESET + " - " + String.valueOf(snowballdistancerecord) + " blocks");
        s.add("    Record Sniper Shot Distance: " + sniperrecordplayer + ChatColor.RESET + " - " + String.valueOf(sniperdistancerecord) + " blocks");
        return s;
    }

    /**
     * Get if there was an error while finding stats for this player.
     *
     * @return True if there was an error, false if there wasn't one.
     */
    public boolean getError() {
        return error;
    }

    /**
     * Gets the players total points.
     *
     * @return This players points.
     */
    public int getPoints() {
        return Configurations.getPlayersconfig().getConfigurationSection(player).getInt("points");
    }

    /**
     * Set this players points.
     *
     * @param p What to set the points to.
     */
    public void setPoints(int p) {
        Configurations.getPlayersconfig().getConfigurationSection(player).set("points", p);
        Configurations.savePlayersConfig();
        Board.updatePlayer(Bukkit.getPlayer(player));
    }

    /**
     * Add points to this players existing points.
     *
     * @param p The points to add.
     */
    public void addPoints(int p) {
        setPoints(getPoints() + p);
    }

    /**
     * Take points away from this player.
     *
     * @param p How many points to remove.
     */
    public void removePoints(int p) {
        setPoints(getPoints() - p);
    }

    /**
     * Give the player the default team win points.
     */
    public void giveTeamPoints() {
        int multiply = 0;
        if (TeamCyan.hasPlayer(Bukkit.getPlayer(player)))
            multiply = TeamLime.getPlayers().size();
        if (TeamLime.hasPlayer(Bukkit.getPlayer(player)))
            multiply = TeamCyan.getPlayers().size();
        addPoints(Settings.getTeamPoints() * multiply);
    }

    /**
     * Get how many times a player has been hit in all.
     *
     * @return The amount of hits.
     */
    public int getHits() {
        return Configurations.getPlayersconfig().getConfigurationSection(player).getInt("hits");
    }

    /**
     * Set how many times this player has hit someone else.
     *
     * @param k The amount of hits.
     */
    public void setHits(int k) {
        Configurations.getPlayersconfig().getConfigurationSection(player).set("hits", k);
        Configurations.savePlayersConfig();
    }

    /**
     * Increment the hit count of this player by 1.
     */
    public void incrementHitsCount() {
        Configurations.getPlayersconfig().getConfigurationSection(player).set("hits", getHits() + 1);
        Configurations.savePlayersConfig();
        Board.updatePlayer(Bukkit.getPlayer(player));
    }

    /**
     * Get how many times this player has lost to another player.
     *
     * @return The loss count.
     */
    public int getLosses() {
        return Configurations.getPlayersconfig().getConfigurationSection(player).getInt("losses");
    }

    /**
     * Set how many times this player has lost to another player.
     *
     * @param d The loss count to set.
     */
    public void setLosses(int d) {
        Configurations.getPlayersconfig().getConfigurationSection(player).set("losses", d);
        Configurations.savePlayersConfig();
    }

    /**
     * Increment the loss count by 1.
     */
    public void incrementLossCount() {
        Configurations.getPlayersconfig().getConfigurationSection(player).set("losses", getLosses() + 1);
        Configurations.savePlayersConfig();
    }

    /**
     * Get this players hit to lose ratio.
     *
     * @return The hits to losses ratio.
     */
    public float getKDRatio() {
        return (float) getHits() / getLosses();
    }

    /**
     * Get how many snowballs this player has thrown.
     *
     * @return The amount of snowballs thrown.
     */
    public int getSnowballsThrown() {
        return Configurations.getPlayersconfig().getConfigurationSection(player).getInt("snowballs-thrown");
    }

    /**
     * Set how many snowballs this player has thrown.
     *
     * @param a The amount to set.
     */
    public void setSnowballsThrown(int a) {
        Configurations.getPlayersconfig().getConfigurationSection(player).set("snowballs-thrown", a);
        Board.updatePlayer(Bukkit.getPlayer(player));
    }

    /**
     * How many snowballs to add to this players snowballs thrown count.
     *
     * @param a Snowballs to add.
     */
    public void addSnowballsThrown(int a) {
        setSnowballsThrown(getSnowballsThrown() + a);
    }

    /**
     * Remove snowballs from this players thrown count.
     *
     * @param a The amount to remove.
     */
    public void removeSnowballsThrown(int a) {
        setSnowballsThrown(getSnowballsThrown() - a);
    }

    /**
     * Check to see if a new distance record was made.
     *
     * @param method   The method used to make this distance record. (options: Snowball, Sniper Rifle)
     * @param distance The distance in blocks the shot was.
     */
    public void checkShotDistanceRecord(String method, long distance) {
        if (method == "Snowball") {
            if (Configurations.getPlayersconfig().getConfigurationSection(player).getLong("snowball-record-distance") < distance) {
                Configurations.getPlayersconfig().getConfigurationSection(player).set("snowball-record-distance", distance);
            }
        }
        if (method == "Sniper Rifle") {
            if (Configurations.getPlayersconfig().getConfigurationSection(player).getLong("sniper-record-distance") < distance) {
                Configurations.getPlayersconfig().getConfigurationSection(player).set("sniper-record-distance", distance);
            }
        }
    }

    /**
     * Get this players shot distance record.
     *
     * @param method The method to lookup. (options: Snowball, Sniper Rifle)
     * @return The distance of the record.
     */
    public long getShotDistanceRecord(String method) {
        if (method == "Snowball") {
            return Configurations.getPlayersconfig().getConfigurationSection(player).getLong("snowball-record-distance");
        }
        if (method == "Sniper Rifle") {
            return Configurations.getPlayersconfig().getConfigurationSection(player).getLong("sniper-record-distance");
        }
        return 0;
    }

    /**
     * Get the last rank that this player was.
     *
     * @return The rank name of this player.
     */
    public String getLastRank() {
        if (Configurations.getPlayersconfig().getConfigurationSection(player).getString("last-rank") != null) {
            return Configurations.getPlayersconfig().getConfigurationSection(player).getString("last-rank");
        } else {
            return "N/A";
        }
    }

    /**
     * Set the last rank of this player.
     *
     * @param r The name of the rank to use.
     */
    public void setRank(String r) {
        Configurations.getPlayersconfig().getConfigurationSection(player).set("last-rank", r);
    }

    /**
     * Check to see if this player is using a specific power.
     *
     * @param p The power to check against.
     * @return True if the player is using this power, false if not.
     */
    public boolean usingPower(Powers p) {
        if (Configurations.getPlayersconfig().getConfigurationSection(player).getString("power") != null) {
            return getPower().getType().equals(p);
        }
        return false;
    }

    /**
     * Check to see if this player has purchased a specific power.
     *
     * @param p The power to check against.
     * @return True if the player owns this power, false if not.
     */
    public boolean ownsPower(Powers p) {
        if (getPurchasedPowers().contains(p.toString())) {
            return true;
        }
        return false;
    }

    /**
     * Check to see if this player has purchased a specific set of powers.
     *
     * @param powers The powers to check against.
     * @return True if the player owns these powers, false if not.
     */
    public boolean ownsPowers(List<Powers> powers) {
        boolean result = false;
        for (Powers pws : powers) {
            if (!ownsPower(pws)) {
                result = false;
                break;
            } else {
                result = ownsPower(pws);
            }
        }
        return result;
    }

    /**
     * Get all the powers this player has purchased.
     *
     * @return The purchased powers.
     */
    public List<String> getPurchasedPowers() {
        if (Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("purchased-powers") != null) {
            return Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("purchased-powers");
        }
        return new ArrayList<String>();
    }

    /**
     * Add a power to this players purchased powers.
     *
     * @param p The power to add.
     */
    public void addPower(Powers p) {
        List<String> ppws = getPurchasedPowers();
        if (Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("purchased-powers") != null) {
            if (!getPurchasedPowers().contains(p.toString())) {
                ppws.add(p.toString());
                Configurations.getPlayersconfig().getConfigurationSection(player).set("purchased-powers", ppws);
                Configurations.savePlayersConfig();
            }
        } else {
            Configurations.getPlayersconfig().getConfigurationSection(player).createSection("purchased-powers");
            ppws.add(p.toString());
            Configurations.getPlayersconfig().getConfigurationSection(player).set("purchased-powers", ppws);
            Configurations.savePlayersConfig();
        }
    }

    /**
     * Get the power this player currently is using.
     *
     * @return The power being used.
     */
    public Power getPower() {
        if (Configurations.getPlayersconfig().getConfigurationSection(player).getString("power") != null) {
            for (Powers pw : Powers.values()) {
                if (pw.equalsName(Configurations.getPlayersconfig().getConfigurationSection(player).getString("power"))) {
                    return new Power(pw, Bukkit.getPlayer(player));
                }
            }
        }
        return new Power(Powers.NONE, Bukkit.getPlayer(player));
    }

    /**
     * Set power the player will be using.
     *
     * @param p The power to set.
     */
    public void setPower(Powers p) {
        Configurations.getPlayersconfig().getConfigurationSection(player).set("power", p.toString());
        Configurations.savePlayersConfig();
    }

    /**
     * Check to see if this player is using a specific upgrade.
     *
     * @param u The upgrade to check against.
     * @return True if the upgrade is being used, false if not.
     */
    public boolean usingUpgrade(Upgrades u) {
        if (getEnabledUpgrades().contains(u.toString())) {
            return true;
        }
        return false;
    }

    /**
     * Check to see if this player has purchased a specific upgrade.
     *
     * @param u The upgrade to check against.
     * @return True if the upgrade has been purchased, false if not.
     */
    public boolean ownsUpgrade(Upgrades u) {
        if (getPurchasedUpgrades().contains(u.toString())) {
            return true;
        }
        return false;
    }

    /**
     * Get this players purchased upgrades.
     *
     * @return The purchased upgrades.
     */
    public List<String> getPurchasedUpgrades() {
        if (Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("purchased-upgrades") != null && !Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("purchased-upgrades").isEmpty()) {
            return Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("purchased-upgrades");
        }
        return new ArrayList<String>();
    }

    /**
     * The upgrade to add to this players purchased upgrades.
     *
     * @param u The upgrade to check against.
     */
    public void addUpgrade(Upgrades u) {
        List<String> pupgds = getPurchasedUpgrades();
        if (Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("purchased-upgrades") != null) {
            if (!getPurchasedUpgrades().contains(u.toString())) {
                pupgds.add(u.toString());
                Configurations.getPlayersconfig().getConfigurationSection(player).set("purchased-upgrades", pupgds);
                Configurations.savePlayersConfig();
            }
        } else {
            Configurations.getPlayersconfig().getConfigurationSection(player).createSection("purchased-upgrades");
            pupgds.add(u.toString());
            Configurations.getPlayersconfig().getConfigurationSection(player).set("purchased-upgrades", pupgds);
            Configurations.savePlayersConfig();
        }
    }

    /**
     * Get all the upgrades that this player has enabled.
     *
     * @return The enabled upgrades.
     */
    public List<String> getEnabledUpgrades() {
        if (Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("enabled-upgrades") != null && !Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("enabled-upgrades").isEmpty()) {
            return Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("enabled-upgrades");
        }
        return new ArrayList<String>();
    }

    /**
     * Enable an upgrade for this player.
     *
     * @param u The upgrade to enable.
     */
    public void enableUpgrade(Upgrades u) {
        if (getPurchasedUpgrades().contains(u.toString()) || !Store.isEnabled()) {
            List<String> enabledupgrades = Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("enabled-upgrades");
            Upgrade upgs = new Upgrade(u, Bukkit.getPlayer(player));
            if (!upgs.getPowerConflicts().contains(getPower().getType())) {
                enabledupgrades.add(u.toString());
                Configurations.getPlayersconfig().getConfigurationSection(player).set("enabled-upgrades", enabledupgrades);
                Configurations.savePlayersConfig();
            } else {
                Chat.sendPM("Cannot enable the upgrade " + u.toString() + " because it conflicts with the power " + getPower().getName(), Bukkit.getPlayer(player));
            }
        }
    }

    /**
     * Disable an upgrade for this player.
     *
     * @param u The upgrade to disable.
     */
    public void disableUpgrade(Upgrades u) {
        List<String> enabledupgrades = Configurations.getPlayersconfig().getConfigurationSection(player).getStringList("enabled-upgrades");
        enabledupgrades.remove(u.toString());
        Configurations.getPlayersconfig().getConfigurationSection(player).set("enabled-upgrades", enabledupgrades);
        Configurations.savePlayersConfig();
    }

    /**
     * Get the arenas that this player has created/assisted in.
     *
     * @return A list of arenas.
     */
    public ArrayList<String> getArenasList() {
        ArrayList<String> arenas = new ArrayList<String>();
        for (String n : Arenas.getNameList()) {
            Arena a = Arena.getInstanceFromConfig(n);
            if (a.getAuthors().contains(player)) {
                arenas.add(a.getName());
            }
        }
        if (!arenas.isEmpty()) {
            return arenas;
        }
        return new ArrayList<String>();
    }

    /**
     * Get all the stats of this player.
     *
     * @return The full stats of this player.
     */
    public ArrayList<String> getAllStats() {
        ArrayList<String> s = new ArrayList<String>();
        String purchasedpowers = "";
        String enabledupgrades = "";
        String purchasedupgrades = "";
        s.add("Stats for " + player + ":");
        if (Bukkit.getOfflinePlayer(player).isOnline()) {
            if (TeamCyan.hasPlayer(player)) {
                s.add("    Team: CYAN");
            }
            if (TeamLime.hasPlayer(player)) {
                s.add("    Team: LIME");
            }
            if (!TeamCyan.hasPlayer(player) && !TeamLime.hasPlayer(player))
                s.add("    Team: N/A");
        } else {
            s.add("    Team: N/A (player offline)");
        }
        for (String pw : getPurchasedPowers()) {
            purchasedpowers += pw + ", ";
        }
        for (String u : getEnabledUpgrades()) {
            enabledupgrades += u + ", ";
        }
        for (String u : getPurchasedUpgrades()) {
            purchasedupgrades += u + ", ";
        }
        s.add("    Rank: " + getLastRank());
        s.add("    Points: " + String.valueOf(getPoints()));
        s.add("    Hits: " + String.valueOf(getHits()));
        s.add("    Losses: " + String.valueOf(getLosses()));
        s.add("    H/L ratio: " + String.valueOf(getKDRatio()));
        s.add("    Power: " + getPower().getName());
        s.add("    Purchased powers: " + purchasedpowers);
        s.add("    Enabled upgrades: " + enabledupgrades);
        s.add("    Purchased upgrades: " + purchasedupgrades);
        s.add("    Snowballs thrown: " + String.valueOf(getSnowballsThrown()));
        s.add("    Snowball distance record: " + String.valueOf(getShotDistanceRecord("Snowball")) + " blocks");
        s.add("    Sniper distance record: " + String.valueOf(getShotDistanceRecord("Sniper Rifle")) + " blocks");
        s.add("    Arenas created/assisted: " + String.valueOf(getArenasList().size()));
        return s;
    }

}
