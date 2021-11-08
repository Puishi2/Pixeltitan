package es.Punikii.Community;
import es.Punikii.Community.Commands.*;
import es.Punikii.Community.Listeners.*;
import es.Punikii.Community.Manager.LocationManager;
import es.Punikii.Community.Manager.ScoreBoardManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Community extends JavaPlugin{

    private static Community instance;
    private LocationManager locationManager;

    public static String prefix = "§8» §aCommunity §8┃ §7";

    private ScoreBoardManager scoreBoardManager;

    @Override
    public void onEnable() {
        instance = this;
        ScoreBoardManager.updater();
        ScoreBoardManager.updateCoins();
        this.scoreBoardManager = scoreBoardManager;
        register();
        this.locationManager = locationManager;
    }

    public void register(){

        /**
         * Register the listeners
         */
        getServer().getPluginManager().registerEvents(new OnJoin(), this);
        getServer().getPluginManager().registerEvents(new OnQuit(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new Listeners(this), this);
        getServer().getPluginManager().registerEvents(new Navigator(), this);
        getServer().getPluginManager().registerEvents(new SpielerVerstecken(), this);
        getServer().getPluginManager().registerEvents(new SignEvent(this), this);
        getServer().getPluginManager().registerEvents(new ScoreBoardManager(), this);

        /**
         * Register the commands
         */
        getServer().getPluginCommand("setspawn").setExecutor(new SetSpawnCMD(this));
        getServer().getPluginCommand("setbuehne").setExecutor(new SetBuehneCMD(this));
        getServer().getPluginCommand("setscreen").setExecutor(new SetScreenCMD(this));
        getServer().getPluginCommand("fly").setExecutor(new FlyCMD(this));
        getServer().getPluginCommand("build").setExecutor(new BuildCMD(this));
        getServer().getPluginCommand("setteam").setExecutor(new SetTeamCMD(this));
        getServer().getPluginCommand("seteffect1").setExecutor(new seteffect1CMD(this));
        getServer().getPluginCommand("seteffect2").setExecutor(new seteffect2CMD(this));
        getServer().getPluginCommand("seteffect3").setExecutor(new seteffect3CMD(this));
        getServer().getPluginCommand("seteffect4").setExecutor(new seteffect4CMD(this));
        getServer().getPluginCommand("settb").setExecutor(new SetTBCMD(this));
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Community getInstance() {
        return instance;
    }

    public ScoreBoardManager getScoreBoardManager() {
        return scoreBoardManager;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }
}