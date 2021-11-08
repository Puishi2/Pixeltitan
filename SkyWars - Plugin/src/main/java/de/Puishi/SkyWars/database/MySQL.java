package de.Puishi.SkyWars.database;

import java.sql.*;
import java.util.UUID;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 23.06.2020 / 15:46                                               *
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
    private static String PORT;
    public static Connection con;

    public MySQL() {
        HOST = "localhost";
        DATABASE = "skywars";
        USER = "admin";
        PASSWORD = "JulienIstRainbow10";
        PORT = "3306";
        connect();
        //update("CREATE TABLE IF NOT EXISTS stats(UUID varchar(64), KILLS int, DEATHS int, WINS int, PLAYED int);");
        update("CREATE TABLE IF NOT EXISTS kits(UUID varchar(64), KIT varchar(64));");
    }

    public static void connect() {
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
            System.out.println("MySQL - Erfolgreich hergestellt");
        } catch (SQLException e) {
            System.out.println("MySQL - Fehler | Fehler: " + e.getMessage());
        }
    }

    public static void close() {
        try {
            if (con != null) {
                con.close();
                System.out.println("[MySQL] Die Verbindung zur MySQL wurde Erfolgreich beendet!");
            }
        } catch (SQLException e) {
            System.out.println("[MySQL] Fehler beim beenden der Verbindung zur MySQL! Fehler: " + e.getMessage());
        }
    }

    public static void update(String qry) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
    }

    public static ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }

    /*public static int getRank(String uuid) {
        int rank = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con
                    .prepareStatement("SELECT * FROM stats ORDER BY WINS DESC");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                rank++;
                String uuid2 = result.getString("UUID");
                if (uuid2.equalsIgnoreCase(uuid)) {
                    return rank;

                }
            }
            result.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rank;
    }

    public static UUID getRank(int id) {
        int i = 0;
        ResultSet rs = query("SELECT * FROM stats ORDER BY WINS DESC LIMIT " + id);
        try {
            while (rs.next()) {
                i++;
                if (i == id) {
                    return UUID.fromString(rs.getString("UUID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


     */
    public void reconnect() {
        if (con == null) {
            connect();
        }

    }



}
