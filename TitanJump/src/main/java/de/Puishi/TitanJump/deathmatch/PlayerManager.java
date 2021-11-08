package de.Puishi.TitanJump.deathmatch;

import de.Puishi.TitanJump.utils.Data;
import de.Puishi.TitanJump.utils.LocationManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 09.07.2020 / 12:02                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class PlayerManager {

    public static HashMap<Player, Integer> lives = new HashMap<>();

    public static ArrayList<String> playerList = new ArrayList<>();
    public static ArrayList<String> specList = new ArrayList<>();

    public void setSpectator(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage(Data.getPrefix() + "Du bist nun ein Spectator!");
        playerList.remove(player.getUniqueId().toString());
        specList.add(player.getUniqueId().toString());
        player.teleport(new LocationManager().getLocation("Spec"));
    }

    public static HashMap<Player, Integer> getLives() {
        return lives;
    }
}
