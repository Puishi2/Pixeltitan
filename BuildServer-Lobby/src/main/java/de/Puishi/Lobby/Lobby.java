package de.Puishi.Lobby;

import de.Puishi.Lobby.commands.Setup_Command;
import de.Puishi.Lobby.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 08.07.2020 / 17:18                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Lobby extends JavaPlugin {

    public static Lobby instance;

    @Override
    public void onEnable() {
        instance = this;
        register();
    }

    @Override
    public void onDisable() {

    }

    public void register() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinQuitListener(), this);
        pluginManager.registerEvents(new Events(), this);
        pluginManager.registerEvents(new ItemSwitch(), this);
        pluginManager.registerEvents(new Navigator(), this);
        pluginManager.registerEvents(new Enterhaken(), this);

        getCommand("setup").setExecutor(new Setup_Command());
    }

    public static Lobby getInstance() {
        return instance;
    }
}
