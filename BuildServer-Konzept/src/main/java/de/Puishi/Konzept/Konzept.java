package de.Puishi.Konzept;

import de.Puishi.Konzept.commands.Setup_Command;
import de.Puishi.Konzept.listener.JoinLIstener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 16.07.2020 / 17:18                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Konzept extends JavaPlugin {

    public static Konzept instance;

    @Override
    public void onEnable() {
        instance = this;
        register();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public void register() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinLIstener(), this);

        getCommand("setup").setExecutor(new Setup_Command());
    }

    public static Konzept getInstance() {
        return instance;
    }
}
