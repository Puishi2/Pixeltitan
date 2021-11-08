package de.Puishi.Lobby;

import de.Puishi.Lobby.commands.Command_Setup;
import de.Puishi.Lobby.database.MySQL;
import de.Puishi.Lobby.listener.*;
import de.Puishi.Lobby.utils.*;
import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 19.05.2020 / 21:05                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Main extends JavaPlugin {

    public static Main instance;

    private LobbyInventory lobbyInventory;
    private LocationAPI locationAPI;
    private VisibleHandler visibleHandler;

    public static EffectManager effectManager;

    @Override
    public void onEnable() {
        instance = this;
        this.lobbyInventory = new LobbyInventory();
        this.locationAPI = new LocationAPI();
        this.visibleHandler = new VisibleHandler();

        effectManager = new EffectManager(EffectLib.instance());

        new MySQL("localhost", "Lobby", "admin", "JulienIstRainbow10");

        register();
        //spawnParticle();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
            @Override
            public void run() {
                Actionbar.updateActionbar();
            }
        }, 0L, 20L);

        new PlayerScoreboard().update();

        Bukkit.getConsoleSender().sendMessage(Data.PRERIX + ".d8888. d888888b  .d8b.  d8888b. d888888b d888888b d8b   db  d888b  ");
        Bukkit.getConsoleSender().sendMessage(Data.PRERIX + "88'  YP `~~88~~' d8' `8b 88  `8D `~~88~~'   `88'   888o  88 88' Y8b ");
        Bukkit.getConsoleSender().sendMessage(Data.PRERIX + "`8bo.      88    88ooo88 88oobY'    88       88    88V8o 88 88      ");
        Bukkit.getConsoleSender().sendMessage(Data.PRERIX + "  `Y8b.    88    88~~~88 88`8b      88       88    88 V8o88 88  ooo");
        Bukkit.getConsoleSender().sendMessage(Data.PRERIX + "db   8D    88    88   88 88 `88.    88      .88.   88  V888 88. ~8~ ");
        Bukkit.getConsoleSender().sendMessage(Data.PRERIX + "`8888Y'    YP    YP   YP 88   YD    YP    Y888888P VP   V8P  Y888P ");
    }

    @Override
    public void onDisable() {
        instance = null;
        effectManager.dispose();
    }

    public void register(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new ItemSwitch(), this);
        pm.registerEvents(new PlayerInteractListener(), this);
        pm.registerEvents(new Events(), this);
        pm.registerEvents(new PlayerInventoryClick(), this);
        pm.registerEvents(new PlayerScoreboard(), this);
        pm.registerEvents(new Enterhaken(), this);

        getCommand("setup").setExecutor(new Command_Setup());
    }

    /*private void spawnParticle() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            int effect = 1;

            @Override
            public void run() {
                if (this.effect == 4) {
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.spigot().playEffect(locationAPI.getBlockLocation("Lottery"), Effect.FLAME, 1, 1, 0.0F, 0.0F, 0.0F, 0.1F, 32, 8);
                    }
                    this.effect = 1;
                }
                this.effect++;
            }
        }, 10L, 10L);
    }*/

    public static Main getInstance() {
        return instance;
    }

    public LobbyInventory getLobbyInventory() {
        return lobbyInventory;
    }

    public LocationAPI getLocationAPI() {
        return locationAPI;
    }

    public static EffectManager getEffectManager() {
        return effectManager;
    }

    public VisibleHandler getVisibleHandler() {
        return visibleHandler;
    }
}
