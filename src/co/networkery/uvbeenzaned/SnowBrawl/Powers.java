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

public enum Powers implements IAddonDefs {
    SPEED("Speed"), SLOWDOWN("Slowdown"), BLINDNESS("Blindness"), SPONTANEOUS_COMBUSTION("Spontaneous Combustion"), INSTA_RELOAD("Insta-reload"), SNIPER("Sniper"), SMITE("Smite"), VELOCITY("Velocity"), ERUPTION("Eruption"), ABSORPTION("Absorption"), SWAPPER("Swapper"), NONE("None");

    private final String name;

    private Powers(String s) {
        name = s;
    }

    public static List<String> toStringList() {
        List<String> pwl = new ArrayList<String>();
        for (Powers pw : values()) {
            pwl.add(pw.toString().toLowerCase());
        }
        return pwl;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equalsIgnoreCase(otherName);
    }

    public String toString() {
        return name;
    }

}