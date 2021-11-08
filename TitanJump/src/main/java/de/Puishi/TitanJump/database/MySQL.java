package de.Puishi.TitanJump.database;

import java.sql.*;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 03.07.2020 / 09:04                                               *
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
        DATABASE = "titanjump";
        USER = "admin";
        PASSWORD = "JulienIstRainbow10";
        PORT = "3306";
        connect();
        //update("CREATE TABLE IF NOT EXISTS stats(UUID varchar(64), KILLS int, DEATHS int, WINS int, PLAYED int);");
        update("CREATE TABLE IF NOT EXISTS titanjump(UUID varchar(64), PERK varchar(64));");
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

}
