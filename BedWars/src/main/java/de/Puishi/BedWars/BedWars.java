package de.Puishi.BedWars;

import de.Puishi.BedWars.commands.Command_Setup_Alt;
import de.Puishi.BedWars.gamestate.GameState;
import de.Puishi.BedWars.utils.LocationAPI;
import de.Puishi.BedWars.utils.LocationAPI_Alt;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 02.06.2020 / 23:39                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class BedWars extends JavaPlugin {

    public static BedWars instance;

    private GameState gameState;
    private LocationAPI locationAPI;
    public String map;
    private LocationAPI_Alt locationAPI_alt;

    @Override
    public void onEnable() {
        instance = this;
        register();

        this.gameState = GameState.LOBBY;
        this.locationAPI = new LocationAPI(this);
        this.locationAPI_alt = new LocationAPI_Alt();
    }

    @Override
    public void onDisable() {

    }

    public void register(){
        PluginManager pm = Bukkit.getPluginManager();

        getCommand("setup").setExecutor(new Command_Setup_Alt());
    }

    public static BedWars getInstance() {
        return instance;
    }

    public LocationAPI getLocationAPI() {
        return locationAPI;
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

    public LocationAPI_Alt getLocationAPI_alt() {
        return locationAPI_alt;
    }

    public void setLocationAPI(LocationAPI locationAPI) {
        this.locationAPI = locationAPI;
    }
}
