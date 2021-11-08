package de.vacebuild.api.main;

import de.vacebuild.api.commands.*;
import de.vacebuild.api.listener.PlayerCommandListener;
import de.vacebuild.api.listener.PlayerJoinListener;
import de.vacebuild.api.sql.PlayerAPI;
import de.vacebuild.api.sql.SQL;
import de.vacebuild.api.sql.SQLFile;
import de.vacebuild.api.utils.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class VaceAPI extends JavaPlugin {

    private static VaceAPI plugin;
    private Data data;

    private SQLFile sqlFile;
    private SQL sql;
    private PlayerAPI playerAPI;

    private ActionbarAPI actionbarAPI;
    private LocationAPI locationAPI;
    private SkullAPI skullAPI;
    private TitleAPI titleAPI;

    public ArrayList<Player> vanishMode = new ArrayList<>();
    public ArrayList<Player> buildMode = new ArrayList<>();

    @Override
    public void onEnable() {
        init();
    }

    private void init() {
        plugin = this;
        this.data = new Data(this);

        this.sqlFile = new SQLFile(this);
        this.sql = new SQL(this);
        this.playerAPI = new PlayerAPI(this);

        this.actionbarAPI = new ActionbarAPI(this);
        this.locationAPI = new LocationAPI(this);
        this.skullAPI = new SkullAPI(this);
        this.titleAPI = new TitleAPI(this);

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandListener(this), this);

        getCommand("gamemode").setExecutor(new GamemodeCommand(this));
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("build").setExecutor(new BuildCommand(this));
        getCommand("clear").setExecutor(new ClearCommand(this));
        getCommand("teleport").setExecutor(new TeleportCommand(this));
    }


    public static VaceAPI getApi() {
        return plugin;
    }

    public Data getData() {
        return data;
    }

    public SQLFile getSqlFile() {
        return sqlFile;
    }

    public SQL getSql() {
        return sql;
    }

    public PlayerAPI getPlayerAPI() {
        return playerAPI;
    }

    public ActionbarAPI getActionbarAPI() {
        return actionbarAPI;
    }

    public LocationAPI getLocationAPI() {
        return locationAPI;
    }

    public SkullAPI getSkullAPI() {
        return skullAPI;
    }

    public TitleAPI getTitleAPI() {
        return titleAPI;
    }

    public boolean isInBuildMode(Player player) {
        return buildMode.contains(player);
    }

    public boolean isInVanishMode(Player player) {
        return vanishMode.contains(player);
    }

    public boolean isAlphabetic(String string) {
        for (int i = 0, n = string.length(); i < n; i++) {
            if (!Character.isAlphabetic(string.charAt(i)) && !Character.isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isOnlyNumbers(String string) {
        for (int i = 0, n = string.length(); i < n; i++) {
            if (string.charAt(i) != '-') {
                if (!Character.isDigit(string.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

}
