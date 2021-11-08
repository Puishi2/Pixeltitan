package de.Puishi.Lottery.database;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *
 *    Erstellt: 22.05.2020 / 23:44                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class MySQL {

    private static String HOST;
    private static String DATABASE;
    private static String USER;
    private static String PASSWORD;

    private static Connection connection;

    public MySQL(String host, String database, String user, String password) {
        this.HOST = host;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;

        connect();
        createTables();
    }

    public static void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
            Bukkit.getConsoleSender().sendMessage("§a[MySQL] Die Verbindung war Erfolgreich!");
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§c[MySQL] Die Verbindung ist Fehlgeschlagen! Fehler:" + e.getMessage());
        }
    }

    public void close() {
        try {
            if(connection != null) {
                connection.close();
                Bukkit.getConsoleSender().sendMessage("[MySQL] Die Verbindung zur MySQL wurde Erfolgreich beendet!");
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("[MySQL] Fehler beim beenden der Verbindung zur MySQL! Fehler: " + e.getMessage());
        }
    }

    public static void update(String qry) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;

        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }

    public static boolean exists(Player player) {
        try {
            PreparedStatement State = connection.prepareStatement("SELECT * FROM lobby WHERE uuid='" + player.getUniqueId() + "';");
            ResultSet Result = State.executeQuery();
            boolean Contains = Result.next();
            State.close();
            Result.close();
            return Contains;
        } catch (SQLException exception) {
            Bukkit.getConsoleSender().sendMessage("" + exception.getErrorCode());
            Bukkit.getConsoleSender().sendMessage("" + exception.getMessage());
            return true;
        }
    }

    private void createTables() {
        update("CREATE TABLE IF NOT EXISTS lobby (UUID varchar(64), tickets int(100));");
    }

    public static int getLotteryTickets(Player player) {
        try {
            PreparedStatement State = connection.prepareStatement("SELECT * FROM lobby WHERE uuid='" + player.getUniqueId() + "';");
            ResultSet Result = State.executeQuery();
            Result.next();
            int stats1 = Result.getInt("tickets");
            Result.close();
            State.close();
            return stats1;
        } catch (SQLException exception) {
            Bukkit.getConsoleSender().sendMessage("" + exception.getErrorCode());
            Bukkit.getConsoleSender().sendMessage("" + exception.getMessage());
            return 0;
        }
    }

    public static void addTickets(Player player, int size) {
        int newtickets = getLotteryTickets(player) + size;
        update("UPDATE lobby SET tickets='" + newtickets + "' WHERE uuid='" + player.getUniqueId() + "'");
    }

    public static void removeTickets(Player player, int size) {
        int newtickets = getLotteryTickets(player) - size;
        update("UPDATE lobby SET tickets='" + newtickets + "' WHERE uuid='" + player.getUniqueId() + "'");
    }

    public static void setTickets(Player player, int size) {
        update("UPDATE lobby SET tickets='" + size + "' WHERE uuid='" + player.getUniqueId() + "'");
    }

    public static void createPlayer(Player player) {
        if (!exists(player)) {
            try {
                PreparedStatement State = connection.prepareStatement("INSERT INTO lobby VALUES ('" + player.getUniqueId() + "', 0);");
                State.execute();
                State.close();
            } catch (SQLException exception) {
                Bukkit.getConsoleSender().sendMessage("" + exception.getErrorCode());
                Bukkit.getConsoleSender().sendMessage("" + exception.getMessage());
            }
        }
    }
}

