package de.Puishi.SkyWars.database;

import java.sql.ResultSet;
import java.sql.SQLException;

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

public class StatsManager {

    public static boolean playerExists(String uuid) {
        try {
            ResultSet rs = MySQL.query("SELECT * FROM stats WHERE UUID= '" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(final String uuid) {
        if (!playerExists(uuid)) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    MySQL.update("INSERT INTO stats(UUID, KILLS, DEATHS, WINS, PLAYED) VALUES ('" + uuid
                            + "', '0', '0', '0', '0');");
                }
            };
            thread.setPriority(1);
            thread.start();

        }
    }

    public static void removeStats(final String uuid, final Integer deaths, final String what) {
        if (playerExists(uuid)) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    setStats(uuid, getStats(uuid, what) - deaths, what);
                }

            };
            thread.setPriority(1);
            thread.start();

        } else {
            createPlayer(uuid);
            removeStats(uuid, deaths, what);
        }
    }

    public static Integer getStats(String uuid, String what) {
        Integer i = 0;
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.query("SELECT * FROM stats WHERE UUID= '" + uuid + "'");
                if ((rs.next()) && (Integer.valueOf(rs.getInt(what)) == null)) {
                }
                i = rs.getInt(what);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getStats(uuid, what);
        }
        return i;
    }

    public static void setStats(final String uuid, final Integer value, final String what) {
        if (playerExists(uuid)) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    MySQL.update("UPDATE stats SET "+what+"= '" + value + "' WHERE UUID= '" + uuid + "';");
                }

            };
            thread.setPriority(1);
            thread.start();

        } else {
            createPlayer(uuid);
            setStats(uuid, value, what);
        }
    }

    public static void addStats(final String uuid, final Integer value, final String what) {
        if (playerExists(uuid)) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    setStats(uuid, getStats(uuid, what) + value, what);
                }

            };
            thread.setPriority(1);
            thread.start();

        } else {
            createPlayer(uuid);
            addStats(uuid, value, what);
        }
    }
}
