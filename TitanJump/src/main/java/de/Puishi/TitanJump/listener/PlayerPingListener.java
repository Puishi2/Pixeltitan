package de.Puishi.TitanJump.listener;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.gamestate.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 26.06.2020 / 09:07                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class PlayerPingListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        event.setMaxPlayers(TitanJump.getInstance().getData().getMaxPlayers().intValue());
        if(TitanJump.getInstance().getGameState() == GameState.LOBBY) {
            event.setMotd("§aLobby-Phase");
        } else if(TitanJump.getInstance().getGameState() == GameState.INGAME) {
            event.setMotd("§6Ingame-Phase");
        } else if(TitanJump.getInstance().getGameState() == GameState.ENDING) {
            event.setMotd("§cEnding-Phase");
        }
    }

}
