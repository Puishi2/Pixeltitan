package de.Puishi.TitanJump;

import de.Puishi.TitanJump.commands.Setup_Command;
import de.Puishi.TitanJump.commands.Start_Command;
import de.Puishi.TitanJump.countdown.LobbyCountdown;
import de.Puishi.TitanJump.database.MySQL;
import de.Puishi.TitanJump.deathmatch.PlayerDeathListener;
import de.Puishi.TitanJump.deathmatch.PlayerRespawnListener;
import de.Puishi.TitanJump.gamestate.GameState;
import de.Puishi.TitanJump.listener.*;
import de.Puishi.TitanJump.perks.Perks_Listener;
import de.Puishi.TitanJump.perks.functions.Freeze_Perk;
import de.Puishi.TitanJump.perks.functions.Jumpboost_Perk;
import de.Puishi.TitanJump.perks.functions.Rettungs_Perk;
import de.Puishi.TitanJump.scoreboard.PlayerScoreboard;
import de.Puishi.TitanJump.shopping.Anvil_Listener;
import de.Puishi.TitanJump.shopping.Shop_Listener;
import de.Puishi.TitanJump.team.Teamauswahl_Listener;
import de.Puishi.TitanJump.utils.Data;
import de.Puishi.TitanJump.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 14.06.2020 / 19:34                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class TitanJump extends JavaPlugin {

    public static TitanJump instance;

    public String map;

    private GameState gameState;
    private Data data;
    private LobbyCountdown lobbyCountdown;

    public int minplayers = 1;

    @Override
    public void onEnable() {
        instance = this;
        register();

        this.gameState = GameState.LOBBY;
        this.data = data;
        this.lobbyCountdown = lobbyCountdown;
        new PlayerScoreboard().updatePerk();

        //new PlayerScoreboard().updateCoins();
        //new PlayerScoreboard().updateMap();

        new MySQL();

        Bukkit.getConsoleSender().sendMessage(Data.getPrefix() + ".d8888. d888888b  .d8b.  d8888b. d888888b d888888b d8b   db  d888b  ");
        Bukkit.getConsoleSender().sendMessage(Data.getPrefix() + "88'  YP `~~88~~' d8' `8b 88  `8D `~~88~~'   `88'   888o  88 88' Y8b ");
        Bukkit.getConsoleSender().sendMessage(Data.getPrefix() + "`8bo.      88    88ooo88 88oobY'    88       88    88V8o 88 88      ");
        Bukkit.getConsoleSender().sendMessage(Data.getPrefix() + "  `Y8b.    88    88~~~88 88`8b      88       88    88 V8o88 88  ooo");
        Bukkit.getConsoleSender().sendMessage(Data.getPrefix() + "db   8D    88    88   88 88 `88.    88      .88.   88  V888 88. ~8~ ");
        Bukkit.getConsoleSender().sendMessage(Data.getPrefix() + "`8888Y'    YP    YP   YP 88   YD    YP    Y888888P VP   V8P  Y888P ");


    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public void register() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new MoveListener(), this);
        pluginManager.registerEvents(new Events(), this);
        pluginManager.registerEvents(new Perks_Listener(), this);
        pluginManager.registerEvents(new Leave_Listener(), this);
        pluginManager.registerEvents(new Teamauswahl_Listener(), this);
        pluginManager.registerEvents(new Jumpboost_Perk(), this);
        pluginManager.registerEvents(new Rettungs_Perk(), this);
        pluginManager.registerEvents(new Freeze_Perk(), this);
        pluginManager.registerEvents(new Gameexplanation_Listener(), this);
        pluginManager.registerEvents(new Shop_Listener(), this);
        pluginManager.registerEvents(new Anvil_Listener(), this);
        pluginManager.registerEvents(new PlayerDeathListener(), this);
        pluginManager.registerEvents(new PlayerRespawnListener(), this);

        getCommand("setup").setExecutor(new Setup_Command());
        getCommand("start").setExecutor(new Start_Command());

    }

    public static TitanJump getInstance() {
        return instance;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }


    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public Data getData() {
        return data;
    }

    public int getMinplayers() {
        return minplayers;
    }

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }

    public void setLobbyCountdown(LobbyCountdown lobbyCountdown) {
        this.lobbyCountdown = lobbyCountdown;
    }
}
