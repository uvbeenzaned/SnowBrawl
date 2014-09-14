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

import java.util.ArrayList;
import java.util.List;

public enum Upgrades implements IAddonDefs {
    SOFT_FALL_BOOTS("Soft Fall Boots"), HIGHER_ERUPTION_DENSITY("Higher Eruption Density"), /*SNIPER_RIFLE_SILENCER("Sniper Rifle Silencer"),*/ /*EXTENDED_SNIPER_MAGAZINE("Extended Sniper Magazine"),*/ BINOCULARS("Binoculars"), POWER_RELOAD_TIME_REDUCTION("Power Reload Time Reduction"), BURN_SAVE("Burn Save");

    private final String name;

    private Upgrades(String s) {
        name = s;
    }

    public static List<String> toStringList() {
        List<String> upl = new ArrayList<String>();
        for (Upgrades u : values()) {
            upl.add(u.toString().toLowerCase());
        }
        return upl;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equalsIgnoreCase(otherName);
    }

    public String toString() {
        return name;
    }
}
