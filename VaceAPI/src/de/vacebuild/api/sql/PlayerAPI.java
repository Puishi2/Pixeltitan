package de.vacebuild.api.sql;

import de.vacebuild.api.main.VaceAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class PlayerAPI {

    private VaceAPI plugin;
    private HashMap<UUID, PlayerObject> objectsByUuid = new HashMap<>();
    private HashMap<String, PlayerObject> objectsByName = new HashMap<>();
    private HashMap<String, PlayerObject> objectsByNameLower = new HashMap<>();

    public PlayerAPI(VaceAPI plugin) {
        this.plugin = plugin;
    }

    public void create(String name, UUID uuid, String ip) {
        plugin.getSql().update("INSERT INTO PlayerInfo (Name, UUID, IP, OnlineTime, Staff) VALUES ('" + name + "', '" + uuid + "', " +
                "'" + ip + "', '0', 'false')");

        PlayerObject playerObject = new PlayerObject();

        playerObject.setName(name);
        playerObject.setUuid(uuid.toString());
        playerObject.setIp(ip);
        playerObject.setOnlineTime(0);
        playerObject.setStaff(false);

        this.objectsByUuid.put(uuid, playerObject);
        this.objectsByName.put(name, playerObject);
        this.objectsByNameLower.put(name.toLowerCase(), playerObject);
    }

    public void load(UUID uuid) {
        plugin.getSql().query("SELECT * FROM PlayerInfo WHERE UUID='" + uuid + "'", resultSet -> {
            try {
                if (resultSet.next()) {
                    PlayerObject playerObject = new PlayerObject();

                    playerObject.setName(resultSet.getString("Name"));
                    playerObject.setUuid(uuid.toString());
                    playerObject.setIp(resultSet.getString("IP"));
                    playerObject.setOnlineTime(resultSet.getLong("OnlineTime"));
                    playerObject.setStaff(resultSet.getBoolean("Staff"));

                    this.objectsByUuid.put(uuid, playerObject);
                    this.objectsByName.put(playerObject.getName(), playerObject);
                    this.objectsByNameLower.put(playerObject.getName().toLowerCase(), playerObject);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void loadSync(UUID uuid) {
        ResultSet resultSet = plugin.getSql().syncQuery("SELECT * FROM PlayerInfo WHERE UUID='" + uuid + "'");

        try {
            if (resultSet.next()) {
                PlayerObject playerObject = new PlayerObject();

                playerObject.setName(resultSet.getString("Name"));
                playerObject.setUuid(uuid.toString());
                playerObject.setIp(resultSet.getString("IP"));
                playerObject.setOnlineTime(resultSet.getLong("OnlineTime"));
                playerObject.setStaff(resultSet.getBoolean("Staff"));

                this.objectsByUuid.put(uuid, playerObject);
                this.objectsByName.put(playerObject.getName(), playerObject);
                this.objectsByNameLower.put(playerObject.getName().toLowerCase(), playerObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void load(String name) {
        plugin.getSql().query("SELECT * FROM PlayerInfo WHERE Name='" + name + "'", resultSet -> {
            try {
                if (resultSet.next()) {
                    PlayerObject playerObject = new PlayerObject();

                    playerObject.setName(resultSet.getString("Name"));
                    playerObject.setUuid(resultSet.getString("UUID"));
                    playerObject.setIp(resultSet.getString("IP"));
                    playerObject.setOnlineTime(resultSet.getLong("OnlineTime"));
                    playerObject.setStaff(resultSet.getBoolean("Staff"));

                    this.objectsByUuid.put(UUID.fromString(playerObject.getUuid()), playerObject);
                    this.objectsByName.put(name, playerObject);
                    this.objectsByNameLower.put(name.toLowerCase(), playerObject);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void loadSync(String name) {
        ResultSet resultSet = plugin.getSql().syncQuery("SELECT * FROM PlayerInfo WHERE Name='" + name + "'");

        try {
            if (resultSet.next()) {
                PlayerObject playerObject = new PlayerObject();

                playerObject.setName(resultSet.getString("Name"));
                playerObject.setUuid(resultSet.getString("UUID"));
                playerObject.setIp(resultSet.getString("IP"));
                playerObject.setOnlineTime(resultSet.getLong("OnlineTime"));
                playerObject.setStaff(resultSet.getBoolean("Staff"));

                this.objectsByUuid.put(UUID.fromString(playerObject.getUuid()), playerObject);
                this.objectsByName.put(name, playerObject);
                this.objectsByNameLower.put(name.toLowerCase(), playerObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isPlayerExists(UUID uuid) {
        if (this.objectsByUuid.containsKey(uuid)) {
            return true;
        } else {
            ResultSet resultSet = plugin.getSql().syncQuery("SELECT * FROM PlayerInfo WHERE UUID='" + uuid + "'");

            try {
                if (resultSet.next()) {
                    load(uuid);
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                return false;
            }
        }
    }

    public boolean isPlayerExists(String name) {
        if (this.objectsByNameLower.containsKey(name.toLowerCase())) {
            return true;
        } else {
            ResultSet resultSet = plugin.getSql().syncQuery("SELECT * FROM PlayerInfo WHERE Name='" + name + "'");

            try {
                if (resultSet.next()) {
                    load(name);
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                return false;
            }
        }
    }

    public UUID getUUID(String name) {
        if (!this.objectsByNameLower.containsKey(name.toLowerCase())) {
            loadSync(name);
        }

        PlayerObject playerObject = getPlayerObjectLower(name.toLowerCase());

        return UUID.fromString(playerObject.getUuid());
    }

    public String getName(UUID uuid) {
        if (!this.objectsByUuid.containsKey(uuid)) {
            loadSync(uuid);
        }

        PlayerObject playerObject = getPlayerObject(uuid);

        return playerObject.getName();
    }

    public void setName(UUID uuid, String name) {
        if (!this.objectsByUuid.containsKey(uuid)) {
            loadSync(uuid);
        }

        String old = getPlayerObject(uuid).getName();

        plugin.getSql().update("UPDATE PlayerInfo SET Name='" + name + "' WHERE UUID='" + uuid + "'");

        this.objectsByName.remove(old);
        this.objectsByNameLower.remove(old.toLowerCase());

        PlayerObject playerObject = getPlayerObject(uuid);

        playerObject.setName(name);

        this.objectsByUuid.put(uuid, playerObject);
        this.objectsByName.put(name, playerObject);
        this.objectsByNameLower.put(name.toLowerCase(), playerObject);
    }

    public String getIP(UUID uuid) {
        if (!this.objectsByUuid.containsKey(uuid)) {
            loadSync(uuid);
        }

        PlayerObject playerObject = getPlayerObject(uuid);

        return playerObject.getIp();
    }

    public void setIP(UUID uuid, String value) {
        if (!this.objectsByUuid.containsKey(uuid)) {
            loadSync(uuid);
        }

        plugin.getSql().update("UPDATE PlayerInfo SET IP='" + value + "' WHERE UUID='" + uuid + "'");

        PlayerObject playerObject = getPlayerObject(uuid);

        playerObject.setIp(value);

        this.objectsByUuid.put(uuid, playerObject);
        this.objectsByName.put(playerObject.getName(), playerObject);
        this.objectsByNameLower.put(playerObject.getName().toLowerCase(), playerObject);
    }

    public long getOnlineTime(UUID uuid) {
        if (!this.objectsByUuid.containsKey(uuid)) {
            loadSync(uuid);
        }

        PlayerObject playerObject = getPlayerObject(uuid);

        return playerObject.getOnlineTime();
    }

    public void setOnlineTime(UUID uuid, long value) {
        if (!this.objectsByUuid.containsKey(uuid)) {
            loadSync(uuid);
        }

        plugin.getSql().update("UPDATE PlayerInfo SET OnlineTime='" + value + "' WHERE UUID='" + uuid + "'");

        PlayerObject playerObject = getPlayerObject(uuid);

        playerObject.setOnlineTime(value);

        this.objectsByUuid.put(uuid, playerObject);
        this.objectsByName.put(playerObject.getName(), playerObject);
        this.objectsByNameLower.put(playerObject.getName().toLowerCase(), playerObject);
    }

    public String getOnlinetime(long onlineTime) {
        long difference = onlineTime;

        int seconds = 0;
        int minutes = 0;
        int hours = 0;
        int days = 0;
        int weeks = 0;
        int months = 0;
        int years = 0;

        while (difference > 1000) {
            difference -= 1000;
            seconds++;
        }

        while (seconds > 60) {
            seconds -= 60;
            minutes++;
        }

        while (minutes > 60) {
            minutes -= 60;
            hours++;
        }

        while (hours > 24) {
            hours -= 24;
            days++;
        }

        if (days >= 30) {
            while (days > 30) {
                days -= 30;
                months++;
            }

            while (days > 7) {
                days -= 7;
                weeks++;
            }
        } else {
            while (days > 7) {
                days -= 7;
                weeks++;
            }
        }

        while (months > 12) {
            months -= 12;
            years++;
        }

        String message = "§b" + years + "y " + months + "§a§bm " + weeks + "w " + days + "d " + hours + "h " + minutes + "m";
        message = message.replace("§b0y ", "").replace("0§a§bm ", "").replace("0w ", "").replace("0d ", "").replace("0h ", "");

        return message;
    }

    public boolean isStaff(UUID uuid) {
        if (!this.objectsByUuid.containsKey(uuid)) {
            loadSync(uuid);
        }

        PlayerObject playerObject = getPlayerObject(uuid);

        return playerObject.isStaff();
    }

    public void setStaff(UUID uuid, boolean value) {
        if (!this.objectsByUuid.containsKey(uuid)) {
            loadSync(uuid);
        }

        plugin.getSql().update("UPDATE PlayerInfo SET Staff='" + value + "' WHERE UUID='" + uuid + "'");

        PlayerObject playerObject = getPlayerObject(uuid);

        playerObject.setStaff(value);

        this.objectsByUuid.put(uuid, playerObject);
        this.objectsByName.put(playerObject.getName(), playerObject);
        this.objectsByNameLower.put(playerObject.getName().toLowerCase(), playerObject);
    }

    private PlayerObject getPlayerObject(UUID uuid) {
        return objectsByUuid.get(uuid);
    }

    private PlayerObject getPlayerObject(String name) {
        return objectsByName.get(name);
    }

    private PlayerObject getPlayerObjectLower(String name) {
        return objectsByNameLower.get(name);
    }

}
