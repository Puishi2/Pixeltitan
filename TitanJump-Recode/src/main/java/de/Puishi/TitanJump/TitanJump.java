package de.Puishi.TitanJump;

import de.Puishi.TitanJump.gamestate.GameState;
import de.Puishi.TitanJump.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 17.07.2020 / 20:20                                               *
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

    private GameState gameState;
    private LocationManager locationManager;


    @Override
    public void onEnable() {
        instance = this;
        register();
        gameState = GameState.LOBBY;;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public void register() {
        PluginManager pluginManager = Bukkit.getPluginManager();

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

    public LocationManager getLocationManager() {
        return locationManager;
    }
}
