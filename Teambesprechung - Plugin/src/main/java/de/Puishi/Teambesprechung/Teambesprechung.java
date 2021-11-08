package de.Puishi.Teambesprechung;

import de.Puishi.Teambesprechung.commands.Command_Next;
import de.Puishi.Teambesprechung.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 12.05.2020 / 15:51                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Teambesprechung extends JavaPlugin {

    public static Teambesprechung instance;

    public static String PREFIX = "§8» §cTeambesprechung §8┃ §7";

    @Override
    public void onEnable() {
        register();
        instance = this;
        Bukkit.getConsoleSender().sendMessage(Teambesprechung.PREFIX + ".d8888. d888888b  .d8b.  d8888b. d888888b d888888b d8b   db  d888b  ");
        Bukkit.getConsoleSender().sendMessage(Teambesprechung.PREFIX + "88'  YP `~~88~~' d8' `8b 88  `8D `~~88~~'   `88'   888o  88 88' Y8b ");
        Bukkit.getConsoleSender().sendMessage(Teambesprechung.PREFIX + "`8bo.      88    88ooo88 88oobY'    88       88    88V8o 88 88      ");
        Bukkit.getConsoleSender().sendMessage(Teambesprechung.PREFIX + "  `Y8b.    88    88~~~88 88`8b      88       88    88 V8o88 88  ooo");
        Bukkit.getConsoleSender().sendMessage(Teambesprechung.PREFIX + "db   8D    88    88   88 88 `88.    88      .88.   88  V888 88. ~8~ ");
        Bukkit.getConsoleSender().sendMessage(Teambesprechung.PREFIX + "`8888Y'    YP    YP   YP 88   YD    YP    Y888888P VP   V8P  Y888P ");

    }

    @Override
    public void onDisable() {

    }

    public void register(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new InteractEvent(this), this);
        pm.registerEvents(new Events(), this);
        pm.registerEvents(new ToggleMoveListener(), this);
        pm.registerEvents(new ToggleChatListener(), this);
        pm.registerEvents(new PlayerInventoryClick(), this);
        pm.registerEvents(new Scoreboard(), this);
        pm.registerEvents(new Scoreboard_SneakEvent(), this);



        //getCommand("togglemove").setExecutor(new Command_togglemove());
        //getCommand("togglechat").setExecutor(new Command_togglechat());
        //getCommand("tb").setExecutor(new Command_teambesprechung());
        getCommand("next").setExecutor(new Command_Next());
    }

    public static Teambesprechung getInstance() {
        return instance;
    }

    public static String getPREFIX() {
        return PREFIX;
    }
}

/*
Permissions:

tb.item
tb.help
tb.togglechat
tb.togglemove
tb.chat
tb.next
tb.set

 */
