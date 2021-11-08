package de.Puishi.BedWars;

import de.Puishi.BedWars.commands.BedWars_CMD;
import de.Puishi.BedWars.gamestate.GameState;
import de.Puishi.BedWars.listener.Events;
import de.Puishi.BedWars.listener.JoinQuitListener;
import de.Puishi.BedWars.scoreboard.PlayerScoreboard;
import de.Puishi.BedWars.listener.Teamauswahl;
import de.Puishi.BedWars.utils.Data;
import de.Puishi.BedWars.utils.LocationAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 14.05.2020 / 20:54                                               *
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

    private GameState gameState;
    private LocationAPI locationAPI;
    public String map;

    @Override
    public void onEnable() {
        instance = this;

        this.gameState = GameState.LOBBY;
        this.locationAPI = new LocationAPI(this);

        register();

        getRandomMap();

        new PlayerScoreboard().update();

        Bukkit.getConsoleSender().sendMessage(Data.PREFIX + ".d8888. d888888b  .d8b.  d8888b. d888888b d888888b d8b   db  d888b  ");
        Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "88'  YP `~~88~~' d8' `8b 88  `8D `~~88~~'   `88'   888o  88 88' Y8b ");
        Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "`8bo.      88    88ooo88 88oobY'    88       88    88V8o 88 88      ");
        Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "  `Y8b.    88    88~~~88 88`8b      88       88    88 V8o88 88  ooo");
        Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "db   8D    88    88   88 88 `88.    88      .88.   88  V888 88. ~8~ ");
        Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "`8888Y'    YP    YP   YP 88   YD    YP    Y888888P VP   V8P  Y888P ");
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public void register(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinQuitListener(this), this);
        pm.registerEvents(new Teamauswahl(), this);
        pm.registerEvents(new Events(), this);

        getCommand("setup").setExecutor(new BedWars_CMD());
    }

    private void getRandomMap() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(getInstance(), new Runnable() {
            @Override
            public void run() {
                ArrayList<String> maps = (ArrayList<String>) locationAPI.getCfg().getStringList("Maps");
                int rdm = (int) (Math.random() * maps.size());
                if(maps.isEmpty()) {
                    Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "§cBitte richte eine Map ein...");
                    return;
                }
                map = maps.get(rdm);
                Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "§7Es wurde die Map §2" + map + " §7ausgewählt!");
            }
        }, 1L);
    }



    public static Main getInstance() {
        return instance;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public LocationAPI getLocationAPI() {
        return locationAPI;
    }

    public void setLocationAPI(LocationAPI locationAPI) {
        this.locationAPI = locationAPI;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
