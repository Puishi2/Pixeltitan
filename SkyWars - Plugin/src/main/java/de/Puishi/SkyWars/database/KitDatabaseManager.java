package de.Puishi.SkyWars.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 23.06.2020 / 16:01                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class KitDatabaseManager {

    public static boolean playerExists(String uuid) {
        try {
            ResultSet rs = MySQL.query("SELECT * FROM kits WHERE UUID= '" + uuid + "'");
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
                    MySQL.update("INSERT INTO kits(UUID, KIT) VALUES ('" + uuid
                            + "', 'Titan');");
                }
            };
            thread.setPriority(1);
            thread.start();

        }
    }

    public static String getKit(String uuid, String what) {
        String kit = "";
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.query("SELECT * FROM kits WHERE UUID= '" + uuid + "'");
                if ((rs.next()) && (rs.getString(what)) == null) {
                }
               kit = rs.getString(what);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getKit(uuid, what);
        }
        return kit;
    }

    public static void setKit(final String uuid, final String value, final String what) {
        if (playerExists(uuid)) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    MySQL.update("UPDATE kits SET "+what+"= '" + value + "' WHERE UUID= '" + uuid + "';");
                }

            };
            thread.setPriority(1);
            thread.start();

        } else {
            createPlayer(uuid);
            setKit(uuid, value, what);
        }
    }
}
