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

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StaticData {

    private static List<String> burnsaveuses = new ArrayList<String>();
    private static List<String> absorptionallowed = new ArrayList<String>();

    public static boolean hasUsedRoundBurnSave(Player p) {
        return burnsaveuses.contains(p.getName());
    }

    public static void useBurnSave(Player p) {
        burnsaveuses.add(p.getName());
    }

    public static void clearBurnSaveUses() {
        burnsaveuses.clear();
    }

    public static void allowAbsorption(Player p) {
        if (!absorptionallowed.contains(p.getName()))
            absorptionallowed.add(p.getName());
    }

    public static void disallowAbsorption(Player p) {
        if (absorptionallowed.contains(p.getName()))
            absorptionallowed.remove(p.getName());
    }

    public static boolean canUseAbsorption(Player p) {
        return absorptionallowed.contains(p.getName());
    }

    public static void clearAbsorptionList() {
        absorptionallowed.clear();
    }

}
