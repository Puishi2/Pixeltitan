package de.Puishi.TitanJump.points;

import org.bukkit.entity.Player;

import java.util.HashMap;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 18.07.2020 / 10:11                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class PointsAPI {

    public static HashMap<Player, Integer> points = new HashMap<Player, Integer>();

    public static int getPoints(Player player) {
        int integer;
        if (points.containsKey(player)) {
            integer = points.get(player);
        } else {
            integer = 0;
        }
        return integer;
    }


    public static void setPoints(Player player, int amount) {
        if (points.containsKey(player)) {
            int a = getPoints(player);
            points.remove(player);
            points.put(player, amount + a);
        } else {
            points.put(player, amount);
        }
    }

    public static void removePoints(Player player, int amount) {

        if (points.containsKey(player)) {
            int integer = getPoints(player) - amount;
            points.remove(player);
            points.put(player, integer);
        }
    }

}
