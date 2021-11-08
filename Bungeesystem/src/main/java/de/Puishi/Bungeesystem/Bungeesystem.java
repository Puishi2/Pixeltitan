package de.Puishi.Bungeesystem;

import de.Puishi.Bungeesystem.commands.*;
import de.Puishi.Bungeesystem.listener.TabComplete_Listener;
import net.md_5.bungee.api.plugin.Plugin;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 10.06.2020 / 22:12                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Bungeesystem extends Plugin {

    public static Bungeesystem instance;
    public static String prefix = "§8» §9Proxy §8┃ §7";
    public static String teamchatprefix = "§8» §cTeamchat §8┃ §7";
    public static String adminchatprefix = "§8» §4Adminchat §8┃ §7";
    public static String noperm = "§8» §9Proxy §8┃ §cDu hast keine Rechte für diesen Command!";

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Funktioniert");
        getProxy().getConsole().sendMessage(prefix + "Plugin wurde gestartet!");
        register();
    }

    @Override
    public void onDisable() {
        instance = null;
        getProxy().getConsole().sendMessage(prefix + "Plugin wurde gestoppt!");
    }

    public void register() {
        getProxy().getPluginManager().registerCommand(this, new Bauserver_Command("bauserver"));
        getProxy().getPluginManager().registerCommand(this, new Replayserver_Command("replayserver"));
        //getProxy().getPluginManager().registerCommand(this, new Team_Command("team"));
        getProxy().getPluginManager().registerCommand(this, new PingCommand("ping"));
        getProxy().getPluginManager().registerCommand(this, new BCC_Command("bc"));
        getProxy().getPluginManager().registerCommand(this, new CC_Command());
        getProxy().getPluginManager().registerCommand(this, new Find_Command("find"));
        getProxy().getPluginManager().registerCommand(this, new Jump_Command());
        getProxy().getPluginManager().registerCommand(this, new Bewerben_Command("bewerben"));
        getProxy().getPluginManager().registerCommand(this, new Gay_Command("gay"));
        getProxy().getPluginManager().registerCommand(this, new ZuWild_Command("zuwild"));
        getProxy().getPluginManager().registerCommand(this, new JoinMe_Command("joinme"));
        getProxy().getPluginManager().registerCommand(this, new Teamchat_Command("teamchat"));
        getProxy().getPluginManager().registerCommand(this, new List_Command("list"));
        getProxy().getPluginManager().registerCommand(this, new Serverinfo_Command("serverinfo"));
        getProxy().getPluginManager().registerCommand(this, new Adminchat_Command("adminchat"));
        getProxy().getPluginManager().registerCommand(this, new gehezu_Command_Joinme("gehezu"));
        getProxy().getPluginManager().registerCommand(this, new Whereami_Command("whereami"));
        getProxy().getPluginManager().registerCommand(this, new IchBinCool_Command("ichbincool"));

        getProxy().getPluginManager().registerListener(this, new TabComplete_Listener());
    }

    public static Bungeesystem getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

    /**
     * Permissions
     *
     * pixeltitan.bauserver
     * pixeltitan.replayserver
     * pixeltitan.jump
     * pixeltitan.bc
     * pixeltitan.cc
     * pixeltitan.find
     * pixeltitan.teamchat
     * pixeltitan.serverinfo
     * pixeltitan.adminchat
     * pixeltitan.joinme
     *
     */
}
