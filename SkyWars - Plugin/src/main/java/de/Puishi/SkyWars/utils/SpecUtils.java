package de.Puishi.SkyWars.utils;

import de.Puishi.SkyWars.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 28.05.2020 / 03:58                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class SpecUtils {

    public static ArrayList<String> playerlist = new ArrayList<>();
    public static ArrayList<String> spectatorlist = new ArrayList<>();

    public void setSpectator(Player player) {

        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage(Data.PREFIX + "§7Du bist nun Spectator!");
        playerlist.remove(player.getName());
        spectatorlist.add(player.getName());

    }

}
