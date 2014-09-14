/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Networkery
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package co.networkery.uvbeenzaned.SnowBrawl;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class Rank {
    public static void checkRank(Player p) {
        Stats s = new Stats(p);
        int score = s.getPoints();
        String rank = "";
        if (score <= -1) {
            rank = "Loser";
        }
        if (score >= 0 && score < 100) {
            rank = "Newbie";
        }
        if (score >= 100 && score < 300) {
            rank = "Beginner";
        }
        if (score >= 300 && score < 700) {
            rank = "Amature";
        }
        if (score >= 700 && score < 2000) {
            rank = "Intermediate";
        }
        if (score >= 2000 && score < 5000) {
            rank = "Master";
        }
        if (score >= 5000 && score < 10000) {
            rank = "Boss";
        }
        if (score >= 10000 && score < 20000) {
            rank = "Butch";
        }
        if (score >= 20000 && score < 50000) {
            rank = "Rex";
        }
        if (score >= 50000 && score < 100000) {
            rank = "Killer";
        }
        if (score >= 100000 && score < 500000) {
            rank = "Alpha";
        }
        if (score >= 500000 && score < 1000000) {
            rank = "Hunter";
        }
        if (score >= 1000000 && score < 5000000) {
            rank = "Double";
        }
        if (score >= 5000000 && score < 10000000) {
            rank = "Winner";
        }
        if (score >= 10000000 && score < 100000000) {
            rank = "Ultimate";
        }
        if (score >= 100000000) {
            rank = "Chosen";
        }
        switch (rank) {
            case "Loser":
                try {
                    p.getInventory().setChestplate(colorArmor(255, 0, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Newbie":
                try {
                    p.getInventory().setChestplate(colorArmor(255, 74, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Beginner":
                try {
                    p.getInventory().setChestplate(colorArmor(255, 119, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Amature":
                try {
                    p.getInventory().setChestplate(colorArmor(255, 195, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Intermediate":
                try {
                    p.getInventory().setChestplate(colorArmor(255, 255, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Master":
                try {
                    p.getInventory().setChestplate(colorArmor(204, 255, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Boss":
                try {
                    p.getInventory().setChestplate(colorArmor(0, 255, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Butch":
                try {
                    p.getInventory().setChestplate(colorArmor(0, 255, 255));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Rex":
                try {
                    p.getInventory().setChestplate(colorArmor(0, 0, 255));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Killer":
                try {
                    p.getInventory().setChestplate(colorArmor(89, 0, 255));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Alpha":
                try {
                    p.getInventory().setChestplate(colorArmor(255, 0, 255));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Hunter":
                try {
                    p.getInventory().setChestplate(colorArmor(255, 0, 78));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Double":
                try {
                    p.getInventory().setChestplate(colorArmor(0, 0, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Winner":
                try {
                    p.getInventory().setChestplate(colorArmor(96, 96, 96));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Ultimate":
                try {
                    p.getInventory().setChestplate(colorArmor(160, 160, 160));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Chosen":
                try {
                    p.getInventory().setChestplate(colorArmor(255, 255, 255));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        if (rank.trim().toLowerCase() != s.getLastRank().trim().toLowerCase()) {
            s.setRank(rank);
            // Chat.sendAllTeamsMsg(p + "'s rank has changed to " +
            // ChatColor.BLUE + rank + ChatColor.RESET + ".");
        }
    }

    public static String getRankName(String p) {
        Stats s = new Stats(p);
        int score = s.getPoints();
        String rank = "";
        if (score <= -1) {
            rank = "Loser";
        }
        if (score >= 0 && score < 100) {
            rank = "{R1}Newbie";
        }
        if (score >= 100 && score < 300) {
            rank = "{R2}Beginner";
        }
        if (score >= 300 && score < 700) {
            rank = "{R3}Amature";
        }
        if (score >= 700 && score < 2000) {
            rank = "{R4}Intermediate";
        }
        if (score >= 2000 && score < 5000) {
            rank = "{R5}Master";
        }
        if (score >= 5000 && score < 10000) {
            rank = "{R6}Boss";
        }
        if (score >= 10000 && score < 20000) {
            rank = "{R7}Butch";
        }
        if (score >= 20000 && score < 50000) {
            rank = "{R8}Rex";
        }
        if (score >= 50000 && score < 100000) {
            rank = "{R9}Killer";
        }
        if (score >= 100000 && score < 500000) {
            rank = "{R10}Alpha";
        }
        if (score >= 500000 && score < 1000000) {
            rank = "{R11}Hunter";
        }
        if (score >= 1000000 && score < 5000000) {
            rank = "{R12}Double";
        }
        if (score >= 5000000 && score < 10000000) {
            rank = "{R13}Winner";
        }
        if (score >= 10000000 && score < 100000000) {
            rank = "{R14}Ultimate";
        }
        if (score >= 100000000) {
            rank = "{R15}Chosen";
        }
        return rank;
    }

    public static String getRankNameFromStringColorCode(String code) {
        switch (code) {
            case "255, 0, 0":
                return "Loser";
            case "255, 74, 0":
                return "{R1}Newbie";
            case "255, 119, 0":
                return "{R2}Beginner";
            case "255, 195, 0":
                return "{R3}Amature";
            case "255, 255, 0":
                return "{R4}Intermediate";
            case "204, 255, 0":
                return "{R5}Master";
            case "0, 255, 0":
                return "{R6}Boss";
            case "0, 255, 255":
                return "{R7}Butch";
            case "0, 0, 255":
                return "{R8}Rex";
            case "89, 0, 255":
                return "{R9}Killer";
            case "255, 0, 255":
                return "{R10}Alpha";
            case "255, 0, 78":
                return "{R11}Hunter";
            case "0, 0, 0":
                return "{R12}Double";
            case "96, 96, 96":
                return "{R13}Winner";
            case "160, 160, 160":
                return "{R14}Ultimate";
            case "255, 255, 255":
                return "{R15}Chosen";
            default:
                return null;
        }
    }

    public static String getRankLoreFromStringColorCode(String code) {
        switch (code) {
            case "255, 0, 0":
                return "-1 or lower pts";
            case "255, 74, 0":
                return "0 - 99 pts";
            case "255, 119, 0":
                return "100 - 299 pts";
            case "255, 195, 0":
                return "300 - 699 pts";
            case "255, 255, 0":
                return "700 - 1999 pts";
            case "204, 255, 0":
                return "2000 - 4999 pts";
            case "0, 255, 0":
                return "5000 - 9999 pts";
            case "0, 255, 255":
                return "10000 - 19999 pts";
            case "0, 0, 255":
                return "20000 - 49999 pts";
            case "89, 0, 255":
                return "50000 - 99999 pts";
            case "255, 0, 255":
                return "100000 - 499999 pts";
            case "255, 0, 78":
                return "500000 - 999999 pts";
            case "0, 0, 0":
                return "1000000 - 4999999 pts";
            case "96, 96, 96":
                return "5000000 - 9999999";
            case "160, 160, 160":
                return "10000000 - 99999999";
            case "255, 255, 255":
                return "100000000 or more pts";
            default:
                return null;
        }
    }

    public static ItemStack colorArmor(int r, int g, int b) {
        ItemStack cp = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta m = (LeatherArmorMeta) cp.getItemMeta();
        m.setColor(Color.fromRGB(r, g, b));
        m.setDisplayName(getRankNameFromStringColorCode(String.valueOf(r) + ", " + String.valueOf(g) + ", " + String.valueOf(b)));
        List<String> lore = new ArrayList<String>();
        lore.add(getRankLoreFromStringColorCode(String.valueOf(r) + ", " + String.valueOf(g) + ", " + String.valueOf(b)));
        m.setLore(lore);
        cp.setItemMeta(m);
        return cp;
    }

    public static ItemStack colorArmor(String colorstring) {
        int r = Integer.valueOf(colorstring.split(",")[0]);
        int g = Integer.valueOf(colorstring.split(",")[1]);
        int b = Integer.valueOf(colorstring.split(",")[2]);
        return colorArmor(r, g, b);
    }
}
