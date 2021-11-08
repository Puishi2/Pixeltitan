package es.Punikii.MLGWars;

import es.Punikii.MLGWars.BlockPlace.BlockPlace;
import es.Punikii.MLGWars.Commands.*;
import es.Punikii.MLGWars.Listeners.*;
import es.Punikii.MLGWars.Locations.ScoreboardManager;
import es.Punikii.MLGWars.MLGs.*;
import es.Punikii.MLGWars.countdown.EndCountdown;
import es.Punikii.MLGWars.countdown.LobbyCountdown;
import es.Punikii.MLGWars.countdown.ShopCountdown;
import es.Punikii.MLGWars.gamestate.GameStates;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Map;

public class MLGWars extends JavaPlugin {

    public static MLGWars instance;
    public static String prefix = "§8» §cMLGWars §8┃ §7";

    private GameStates gameStates;
    private LobbyCountdown lobbyCountdown;
    private ShopCountdown shopCountdown;
    private EndCountdown endCountdown;

    public static ArrayList<String> playlist = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        this.lobbyCountdown = new LobbyCountdown();
        this.shopCountdown = new ShopCountdown();
        this.gameStates = GameStates.LOBBY;
        ScoreboardManager.updater();
        register();
    }

    public void register() {
        /**
         * Register the listeners
         */
        Bukkit.getPluginManager().registerEvents(new OnJoin(this), this);
        Bukkit.getPluginManager().registerEvents(new OnQuit(this), this);
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginManager().registerEvents(new BlockMLG(), this);
        Bukkit.getPluginManager().registerEvents(new CobwebMLG(), this);
        Bukkit.getPluginManager().registerEvents(new SlimeMLG(), this);
        Bukkit.getPluginManager().registerEvents(new WaterMLG(), this);
        Bukkit.getPluginManager().registerEvents(new BoatMLG(), this);
        Bukkit.getPluginManager().registerEvents(new TPMLG(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteracts(), this);
        Bukkit.getPluginManager().registerEvents(new Shop(), this);
        Bukkit.getPluginManager().registerEvents(new ScoreboardManager(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlace(), this);

        /**
         * Register the commands
         */
        getServer().getPluginCommand("setspawn").setExecutor(new Spawn_CMD(this));
        getServer().getPluginCommand("setpos1").setExecutor(new Pos1_CMD(this));
        getServer().getPluginCommand("setpos2").setExecutor(new Pos2_CMD(this));
        getServer().getPluginCommand("setpos3").setExecutor(new Pos3_CMD(this));
        getServer().getPluginCommand("setpos4").setExecutor(new Pos4_CMD(this));
        getServer().getPluginCommand("setpos5").setExecutor(new Pos5_CMD(this));
        getServer().getPluginCommand("setpos6").setExecutor(new Pos6_CMD(this));
        getServer().getPluginCommand("build").setExecutor(new Build_CMD(this));
        getServer().getPluginCommand("setshop").setExecutor(new ShopCMD(this));
        getServer().getPluginCommand("pvp1").setExecutor(new Pvp1_CMD(this));
        getServer().getPluginCommand("pvp2").setExecutor(new Pvp2_CMD(this));
        getServer().getPluginCommand("pvp3").setExecutor(new Pvp3_CMD(this));
        getServer().getPluginCommand("pvp4").setExecutor(new Pvp4_CMD(this));
    }

    @Override
    public void onDisable() {
        instance = null;
        playlist.clear();
    }

    public static MLGWars getInstance() {
        return instance;
    }

    public GameStates getGameStates() {
        return gameStates;
    }

    public void setGameStates(GameStates gameStates) {
        this.gameStates = gameStates;
    }

    public static ArrayList<String> getPlaylist() {
        return playlist;
    }

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }
    public ShopCountdown getShopCountdown() {
        return shopCountdown;
    }
    public EndCountdown getEndCountdown() {
        return endCountdown;
    }
}
