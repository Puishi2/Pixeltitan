package de.vacebuild.api.sql;

import de.vacebuild.api.main.VaceAPI;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class SQL {

    private VaceAPI plugin;
    private Connection connection;
    private ExecutorService executor;

    public SQL(VaceAPI plugin) {
        this.plugin = plugin;

        this.executor = Executors.newCachedThreadPool();

        this.plugin.getSqlFile().setFile();
        this.plugin.getSqlFile().readFile();
        connect();
    }

    private void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + plugin.getSqlFile().host + ":" + plugin.getSqlFile().port +
                        "/" + plugin.getSqlFile().database + "?autoReconnect=true", plugin.getSqlFile().username, plugin.getSqlFile().password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void query(String query, Consumer<ResultSet> consumer) {
        executor.execute(() -> {
            ResultSet result = syncQuery(query);
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> consumer.accept(result));
        });
    }

    public ResultSet syncQuery(String query) {
        try {
            return query(this.connection.prepareStatement(query));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(String query) {
        executor.execute(() -> queryUpdate(query));
    }

    public void prepare(String query) {
        PreparedStatement ps;

        try {
            ps =  getConnection().prepareStatement(query);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void queryUpdate(String query) {
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            queryUpdate(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ResultSet query(PreparedStatement ps) {
        try {
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void queryUpdate(PreparedStatement ps) {
        try {
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isConnected() {
        if (connection != null) {
            return true;
        } else {
            return false;
        }
    }

    private Connection getConnection() {
        return connection;
    }

}
