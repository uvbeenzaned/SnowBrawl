
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

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class SB extends JavaPlugin {

    @SuppressWarnings("unused")
    private Clock c;
    @SuppressWarnings("unused")
    private Arenas as;
    @SuppressWarnings("unused")
    private GameListener gl;
    @SuppressWarnings("unused")
    private ExtrasListener el;
    @SuppressWarnings("unused")
    private PowerListener pl;
    @SuppressWarnings("unused")
    private UpgradeListener ul;
    @SuppressWarnings("unused")
    private Board b;
    @SuppressWarnings("unused")
    private TeleportFixThree tpf;
    @SuppressWarnings("unused")
    private Help h;
    @SuppressWarnings("unused")
    private Store s;
    @SuppressWarnings("unused")
    private Utilities u;
    @SuppressWarnings("unused")
    private PowerCoolDown pcd;
    @SuppressWarnings("unused")
    private ChunkStuckFix csf;
    @SuppressWarnings("unused")
    private MenuListener ml;

    public void onEnable() {
        getCommand("snowbrawl").setExecutor(new SBCommandExecutor());
        Configurations.loadAllConfigurations(this);
        TeamCyan.setName("Cyan");
        TeamCyan.setColor(ChatColor.AQUA);
        TeamLime.setName("Lime");
        TeamLime.setColor(ChatColor.GREEN);
        c = new Clock(this);
        as = new Arenas(this);
        gl = new GameListener(this);
        el = new ExtrasListener(this);
        pl = new PowerListener(this);
        ul = new UpgradeListener(this);
        b = new Board();
        tpf = new TeleportFixThree(this);
        h = new Help(this);
        s = new Store(this);
        u = new Utilities(this);
        pcd = new PowerCoolDown(this);
        csf = new ChunkStuckFix(this);
        ml = new MenuListener(this);
        RankMenu.initialize();
        StoreMenu.initialize();
        if (!Arenas.getNameList().isEmpty()) {
            Round.generateMapLineup();
        }
    }

    public void onDisable() {
        Configurations.saveAllConfigurations();
    }

}
