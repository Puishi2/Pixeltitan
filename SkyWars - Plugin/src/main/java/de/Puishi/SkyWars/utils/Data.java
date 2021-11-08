package de.Puishi.SkyWars.utils;

import java.util.Random;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 24.05.2020 / 20:06                                               *
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

    public static String PREFIX = "§8» §2SkyWars §8┃ §7";

    public static String getPrefix() {
        return PREFIX;
    }

    public static int rndInt(int min, int max) {
        Random r = new Random();
        int i = r.nextInt(max - min + 1) + min;
        return i;
    }



}
