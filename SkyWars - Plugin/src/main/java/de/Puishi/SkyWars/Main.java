package de.Puishi.SkyWars;

import de.Puishi.SkyWars.GameState.GameStates;
import de.Puishi.SkyWars.chestloot.ChestLoot;
import de.Puishi.SkyWars.commands.Command_Forcemap;
import de.Puishi.SkyWars.commands.Command_Setup;
import de.Puishi.SkyWars.commands.Command_Start;
import de.Puishi.SkyWars.countdown.EndingCountdown;
import de.Puishi.SkyWars.countdown.LobbyCountdown;
import de.Puishi.SkyWars.countdown.RefillCountdown;
import de.Puishi.SkyWars.database.MySQL;
import de.Puishi.SkyWars.kits.KitsListener;
import de.Puishi.SkyWars.kits.function.SpotterKit;
import de.Puishi.SkyWars.listener.*;
import de.Puishi.SkyWars.map.MapManager;
import de.Puishi.SkyWars.scoreboard.ScoreboardManager;
import de.Puishi.SkyWars.utils.Data;
import de.Puishi.SkyWars.utils.SpecUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 24.05.2020 / 20:05                                               *
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

    private GameStates gameStates;
    public static Main instance;

    private MapManager mapManager;
    private SpecUtils specUtils;
    private LobbyCountdown lobbyCountdown;
    private EndingCountdown endingCountdown;
    private RefillCountdown refillCountdown;

    public String map;

    private final int minplayers = 2;
    private final int maxplayers = 8;

    @Override
    public void onEnable() {
        instance = this;

        new MySQL();

        this.mapManager = new MapManager(this);
        this.specUtils = new SpecUtils();
        this.lobbyCountdown = new LobbyCountdown();
        this.endingCountdown = new EndingCountdown();
        this.refillCountdown = new RefillCountdown();
        register();
        getRandomMap();
        this.gameStates = GameStates.LOBBY;
        Bukkit.getConsoleSender().sendMessage(Data.PREFIX + ".d8888. d888888b  .d8b.  d8888b. d888888b d888888b d8b   db  d888b  ");
        Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "88'  YP `~~88~~' d8' `8b 88  `8D `~~88~~'   `88'   888o  88 88' Y8b ");
        Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "`8bo.      88    88ooo88 88oobY'    88       88    88V8o 88 88      ");
        Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "  `Y8b.    88    88~~~88 88`8b      88       88    88 V8o88 88  ooo");
        Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "db   8D    88    88   88 88 `88.    88      .88.   88  V888 88. ~8~ ");
        Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "`8888Y'    YP    YP   YP 88   YD    YP    Y888888P VP   V8P  Y888P ");

        new ScoreboardManager().update();
        new ScoreboardManager().updateKit();
        //new ScoreboardManager().updatePlayer();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void getRandomMap() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(getInstance(), new Runnable() {
            @Override
            public void run() {
                ArrayList<String> maps = (ArrayList<String>) mapManager.getCfg().getStringList("Maps");
                int rdm = (int) (Math.random() * maps.size());
                if (maps.isEmpty()) {
                    Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "§cBitte richte eine Map ein...");
                    return;
                }
                map = maps.get(rdm);
                Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "§7Es wurde die Map §2" + map + " §7ausgewählt!");
            }
        }, 1L);
    }

    public void register() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinQuitListener(), this);
        pm.registerEvents(new KitsListener(), this);
        pm.registerEvents(new Events(), this);
        pm.registerEvents(new PlayerDeathListener(), this);
        pm.registerEvents(new SpotterKit(), this);
        pm.registerEvents(new LeaveListener(), this);
        pm.registerEvents(new ChestLoot(), this);
        pm.registerEvents(new ServerListPingListener(), this);

        getCommand("setup").setExecutor(new Command_Setup());
        getCommand("start").setExecutor(new Command_Start());
        getCommand("forcemap").setExecutor(new Command_Forcemap());
    }

    public static Main getInstance() {
        return instance;
    }

    public GameStates getGameStates() {
        return gameStates;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public void setGameStates(GameStates gameStates) {
        this.gameStates = gameStates;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }

    public SpecUtils getSpecUtils() {
        return specUtils;
    }

    public EndingCountdown getEndingCountdown() {
        return endingCountdown;
    }

    public int getMaxplayers() {
        return maxplayers;
    }

    public int getMinplayers() {
        return minplayers;
    }

    public RefillCountdown getRefillCountdown() {
        return refillCountdown;
    }
}
