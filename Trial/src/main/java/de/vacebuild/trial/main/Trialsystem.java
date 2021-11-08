package de.vacebuild.trial.main;

import de.vacebuild.api.main.VaceAPI;
import de.vacebuild.trial.commands.FinishCommand;
import de.vacebuild.trial.commands.SetCommand;
import de.vacebuild.trial.commands.VisitWorldCommand;
import de.vacebuild.trial.listener.*;
import de.vacebuild.trial.sql.MySQL;
import de.vacebuild.trial.utils.InventoryManager;
import de.vacebuild.trial.utils.ScoreboardManager;
import de.vacebuild.trial.utils.TopicManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Trialsystem extends JavaPlugin {

    private static Trialsystem plugin;
    private Data data;

    private MySQL mySQL;

    private InventoryManager inventoryManager;
    private ScoreboardManager scoreboardManager;
    private TopicManager topicManager;

    @Override
    public void onEnable() {
        getServer().getScheduler().runTaskLaterAsynchronously(this, () -> init(), 5L);
    }

    private void init() {
        plugin = this;
        this.data = new Data(this);

        this.mySQL = new MySQL(this);

        this.inventoryManager = new InventoryManager(this);
        this.scoreboardManager = new ScoreboardManager(this);
        this.topicManager = new TopicManager(this);

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new PreLoginListener(this), this);
        getServer().getPluginManager().registerEvents(new AsyncChatListener(this), this);
        getServer().getPluginManager().registerEvents(new ProtectionListener(this), this);
        getServer().getPluginManager().registerEvents(new FinishListener(this), this);
        getServer().getPluginManager().registerEvents(new FinishedWorldsListener(this), this);
        getServer().getPluginManager().registerEvents(new RateWorldListener(this), this);
        getServer().getPluginManager().registerEvents(new WorldmanagerListener(this), this);

        getCommand("set").setExecutor(new SetCommand(this));
        getCommand("finish").setExecutor(new FinishCommand(this));
        getCommand("visitworld").setExecutor(new VisitWorldCommand(this));

        getServer().getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            VaceAPI.getApi().getSql().prepare("CREATE TABLE IF NOT EXISTS Trial (UUID VARCHAR(100), HasWorld VARCHAR(100), " +
                    "Status INT(100), Reason VARCHAR(100), Topic VARCHAR(100))");

            this.mySQL.loadWorlds();
        }, 40L);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "FinishWorld");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "AcceptWorld");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "DenyWorld");
    }

    public static Trialsystem getInstance() {
        return plugin;
    }

    public Data getData() {
        return data;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public TopicManager getTopicManager() {
        return topicManager;
    }

}
