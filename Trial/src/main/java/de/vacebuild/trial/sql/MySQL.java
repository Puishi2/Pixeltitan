package de.vacebuild.trial.sql;

import de.vacebuild.api.main.VaceAPI;
import de.vacebuild.trial.main.Trialsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MySQL {

    private Trialsystem plugin;
    private HashMap<UUID, MySQLObject> objects = new HashMap<>();
    public ArrayList<String> finishedWorlds = new ArrayList<>();

    public MySQL(Trialsystem plugin) {
        this.plugin = plugin;
    }

    public void create(UUID uuid) {
        VaceAPI.getApi().getSql().update("INSERT INTO Trial (UUID, HasWorld, Status, Reason, Topic) VALUES " +
                "('" + uuid + "', 'false', '0', '', '')");

        MySQLObject mySQLObject = new MySQLObject();

        mySQLObject.setUuid(uuid.toString());
        mySQLObject.setHasWorld(false);
        mySQLObject.setStatus(0);
        mySQLObject.setReason("");
        mySQLObject.setTopic("");

        this.objects.put(uuid, mySQLObject);
    }

    public void load(UUID uuid) {
        ResultSet resultSet = VaceAPI.getApi().getSql().syncQuery("SELECT * FROM Trial WHERE UUID='" + uuid + "'");

        try {
            if (resultSet.next()) {
                MySQLObject mySQLObject = new MySQLObject();

                mySQLObject.setUuid(resultSet.getString("UUID"));
                mySQLObject.setHasWorld(resultSet.getBoolean("HasWorld"));
                mySQLObject.setStatus(resultSet.getInt("Status"));
                mySQLObject.setReason(resultSet.getString("Reason"));
                mySQLObject.setTopic(resultSet.getString("Topic"));

                this.objects.put(uuid, mySQLObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadWorlds() {
        VaceAPI.getApi().getSql().query("SELECT * FROM Trial WHERE Status='1'", resultSet -> {
            try {
                while (resultSet.next()) {
                    this.finishedWorlds.add(resultSet.getString("UUID"));

                    MySQLObject mySQLObject = new MySQLObject();

                    mySQLObject.setUuid(resultSet.getString("UUID"));
                    mySQLObject.setHasWorld(resultSet.getBoolean("HasWorld"));
                    mySQLObject.setStatus(resultSet.getInt("Status"));
                    mySQLObject.setReason(resultSet.getString("Reason"));
                    mySQLObject.setTopic(resultSet.getString("Topic"));

                    this.objects.put(UUID.fromString(resultSet.getString("UUID")), mySQLObject);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public boolean isPlayerExists(UUID uuid) {
        if (this.objects.containsKey(uuid)) {
            return true;
        } else {
            ResultSet resultSet = VaceAPI.getApi().getSql().syncQuery("SELECT * FROM Trial WHERE UUID='" + uuid + "'");

            try {
                return resultSet.next();
            } catch (SQLException e) {
                return false;
            }
        }
    }

    public int getStatus(UUID uuid) {
        if (!this.objects.containsKey(uuid)) {
            load(uuid);
        }

        MySQLObject mySQLObject = getMySQLObject(uuid);

        return mySQLObject.getStatus();
    }

    public void setStatus(UUID uuid, int value) {
        if (!this.objects.containsKey(uuid)) {
            load(uuid);
        }

        VaceAPI.getApi().getSql().update("UPDATE Trial SET Status='" + value + "' WHERE UUID='" + uuid + "'");

        MySQLObject mySQLObject = getMySQLObject(uuid);

        mySQLObject.setStatus(value);

        this.objects.put(uuid, mySQLObject);
    }

    public boolean hasWorld(UUID uuid) {
        if (!this.objects.containsKey(uuid)) {
            load(uuid);
        }

        MySQLObject mySQLObject = getMySQLObject(uuid);

        return mySQLObject.isHasWorld();
    }

    public void setHasWorld(UUID uuid, boolean value) {
        if (!this.objects.containsKey(uuid)) {
            load(uuid);
        }

        VaceAPI.getApi().getSql().update("UPDATE Trial SET HasWorld='" + value + "' WHERE UUID='" + uuid + "'");

        MySQLObject mySQLObject = getMySQLObject(uuid);

        mySQLObject.setHasWorld(value);

        this.objects.put(uuid, mySQLObject);
    }

    public String getReason(UUID uuid) {
        if (!this.objects.containsKey(uuid)) {
            load(uuid);
        }

        MySQLObject mySQLObject = getMySQLObject(uuid);

        return mySQLObject.getReason();
    }

    public void setReason(UUID uuid, String value) {
        if (!this.objects.containsKey(uuid)) {
            load(uuid);
        }

        VaceAPI.getApi().getSql().update("UPDATE Trial SET Reason='" + value + "' WHERE UUID='" + uuid + "'");

        MySQLObject mySQLObject = getMySQLObject(uuid);

        mySQLObject.setReason(value);

        this.objects.put(uuid, mySQLObject);
    }

    public String getTopic(UUID uuid) {
        if (!this.objects.containsKey(uuid)) {
            load(uuid);
        }

        MySQLObject mySQLObject = getMySQLObject(uuid);

        return mySQLObject.getTopic();
    }

    public void setTopic(UUID uuid, String value) {
        if (!this.objects.containsKey(uuid)) {
            load(uuid);
        }

        VaceAPI.getApi().getSql().update("UPDATE Trial SET Topic='" + value + "' WHERE UUID='" + uuid + "'");

        MySQLObject mySQLObject = getMySQLObject(uuid);

        mySQLObject.setTopic(value);

        this.objects.put(uuid, mySQLObject);
    }

    private MySQLObject getMySQLObject(UUID uuid) {
        return this.objects.get(uuid);
    }

}
