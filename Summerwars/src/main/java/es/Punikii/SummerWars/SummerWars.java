package es.Punikii.SummerWars;

import java.io.File;

import es.Punikii.SummerWars.Commands.SetSpawnCMD;
import es.Punikii.SummerWars.Events.Respawn;
import es.Punikii.SummerWars.Events.Water;
import es.Punikii.SummerWars.Events.onJoin;
import es.Punikii.SummerWars.Events.onQuit;
import es.Punikii.SummerWars.Handlers.WeaponHandler;
import es.Punikii.SummerWars.Listeners.Listeners;
import es.Punikii.SummerWars.Listeners.PlayerInteract;
import es.Punikii.SummerWars.Managers.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SummerWars extends JavaPlugin{

    private static SummerWars instance;

    public static String prefix = "§8» §6SummerWars §8┃ §7";

    private ScoreboardManager scoreBoardManager;

    @Override
    public void onEnable() {
        instance = this;
        this.scoreBoardManager = scoreBoardManager;
        register();
    }

    public void register(){

        /**
         * Register the listeners
         */
        Bukkit.getPluginManager().registerEvents(new Water(), this);
        getServer().getPluginManager().registerEvents(new onJoin(this), this);
        getServer().getPluginManager().registerEvents(new Respawn(this), this);
        getServer().getPluginManager().registerEvents(new WeaponHandler(this), this);
        getServer().getPluginManager().registerEvents(new Listeners(this), this);
        getServer().getPluginManager().registerEvents(new onQuit(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        this.getServer().getPluginManager().registerEvents(new Respawn(this), this);

        /**
         * Register the commands
         */
        getServer().getPluginCommand("setspawn").setExecutor(new SetSpawnCMD(this));
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static SummerWars getInstance() {
        return instance;
    }

    public ScoreboardManager getScoreBoardManager() {
        return scoreBoardManager;
    }
}
