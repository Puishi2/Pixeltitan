package de.Puishi.TitanJump.utils;

import java.util.ArrayList;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 14.06.2020 / 19:38                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Data {

    public static String prefix = "§8» §bTitanJump §8┃ §7";

    public Integer minPlayers = 2;
    public Integer maxPlayers = 8;

    public static String getPrefix() {
        return prefix;
    }

    public static ArrayList<String> playerlist = new ArrayList<>();

    public static ArrayList<String> speclist = new ArrayList<>();

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public Integer getMinPlayers() {
        return minPlayers;
    }
}
