package de.Puishi.TitanJump.team;

import org.bukkit.Location;

import java.util.ArrayList;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 02.07.2020 / 08:31                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class TeamManager {

    public static ArrayList<String> rot = new ArrayList<>();
    public static ArrayList<String> blau = new ArrayList<>();
    public static ArrayList<String> gelb = new ArrayList<>();
    public static ArrayList<String> grün = new ArrayList<>();
    public static ArrayList<String> lila = new ArrayList<>();
    public static ArrayList<String> pink = new ArrayList<>();
    public static ArrayList<String> hellblau = new ArrayList<>();
    public static ArrayList<String> hellgrün = new ArrayList<>();

    private Location spawn;
    private String teamprefix;
    private String teamname;

    public TeamManager(String teamname, Location spawn) {
        this.teamname = teamname;
        this.spawn = spawn;

        switch (teamname) {
            case "rot":
                teamprefix = "§c";
                break;
            case "blau":
                teamname = "§9";
                break;
            case "gelb":
                teamname = "§e";
                break;
            case "grün":
                teamname = "§2";
                break;
            case "lila":
                teamname = "§5";
                break;
            case "pink":
                teamname = "§d";
                break;
            case "hellblau":
                teamname = "§b";
                break;
            case "hellgrün":
                teamname = "§a";
                break;
        }

    }

    public String getTeamname() {
        return teamname;
    }

    public Location getSpawn() {
        return spawn;
    }

    public String getTeamprefix() {
        return teamprefix;
    }
}
