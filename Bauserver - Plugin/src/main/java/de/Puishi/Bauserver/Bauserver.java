package de.Puishi.Bauserver;

import de.Puishi.Bauserver.commands.Day_Command;
import de.Puishi.Bauserver.commands.Gamemode_Command;
import de.Puishi.Bauserver.commands.Night_Command;
import de.Puishi.Bauserver.commands.setSpec_Command;
import de.Puishi.Bauserver.listener.Events;
import de.Puishi.Bauserver.listener.JoinQuitListener;
import de.Puishi.Bauserver.scoreboard.PlayerScoreboard;
import de.Puishi.Bauserver.utils.ActionBar;
import de.Puishi.Bauserver.wm.WM_Command;
import de.Puishi.Bauserver.worldmanager.CreateWorld;
import de.Puishi.Bauserver.worldmanager.TeleportWorld;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 07.06.2020 / 12:21                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Bauserver extends JavaPlugin {

    public static Bauserver instance;

    @Override
    public void onEnable() {
        instance = this;
        register();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
            @Override
            public void run() {
                ActionBar.updateActionbar();
            }
        }, 0L, 20L);

        new PlayerScoreboard().update();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public void register() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinQuitListener(), this);
        pluginManager.registerEvents(new Events(), this);

        getCommand("setspec").setExecutor(new setSpec_Command());
        getCommand("gm").setExecutor(new Gamemode_Command());
        getCommand("day").setExecutor(new Day_Command());
        getCommand("night").setExecutor(new Night_Command());
        getCommand("createworld").setExecutor(new CreateWorld());
        getCommand("world").setExecutor(new TeleportWorld());
        getCommand("wm").setExecutor(new WM_Command());
    }

    public static Bauserver getInstance() {
        return instance;
    }
}
