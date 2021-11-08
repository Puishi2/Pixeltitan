package de.Puishi.BedWars.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 17.05.2020 / 15:24                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Teammanager {

    public static ArrayList<Player> teamblau = new ArrayList<>();
    public static ArrayList<Player> teamrot = new ArrayList<>();

    public static String getTeam(Player p) {
        if(teamrot.contains(p)) {
            return "§cRot";
        } else if(teamblau.contains(p)){
            return "§3Blau";
        }
        return "§cKein Team";
    }

}
