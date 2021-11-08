package de.Puishi.BedWars.listener;

import de.Puishi.BedWars.BedWars;
import de.Puishi.BedWars.Countdown.PlayerCountdown;
import de.Puishi.BedWars.gamestate.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 03.06.2020 / 11:04                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage(null);
        BedWars.getInstance().setGameState(GameState.LOBBY);

        if(Bukkit.getOnlinePlayers().size() == 1){
            new PlayerCountdown().start(player);
        }

        player.teleport(BedWars.getInstance().getLocationAPI_alt().getLocation("Wartelobby"));



    }

}
