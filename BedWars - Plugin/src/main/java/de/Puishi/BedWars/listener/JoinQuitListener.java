package de.Puishi.BedWars.listener;

import de.Puishi.BedWars.Main;
import de.Puishi.BedWars.countdown.PlayerCountdown;
import de.Puishi.BedWars.gamestate.GameState;
import de.Puishi.BedWars.scoreboard.PlayerScoreboard;
import de.Puishi.BedWars.utils.Data;
import de.Puishi.BedWars.utils.WaitingInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 14.05.2020 / 20:58                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class JoinQuitListener implements Listener {

    private final Main plugin;

    public JoinQuitListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent e){
        Player player = e.getPlayer();
        e.setJoinMessage(null);
        Main.getInstance().setGameState(GameState.LOBBY);

        if(Bukkit.getOnlinePlayers().size() == 1){
            new PlayerCountdown().start(player);
        }

        try {
            player.teleport(Main.getInstance().getLocationAPI().loadLocation(Main.getInstance().getMap() + "." + 3));
        } catch (Exception exception) {
            player.sendMessage("§cEs konnte kein Lobby-Spawn für die Map " + Main.getInstance().getMap() + " gefunden werden!");
        }

        new WaitingInventory(e.getPlayer()).setInventory();
        //new PlayerScoreboard().set(e.getPlayer());

        player.setFoodLevel(20);
        player.setHealth(20);
    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent event){
        final Player player = event.getPlayer();
        event.setQuitMessage(Data.getPREFIX() + "Der Spieler §2" + player.getName() + " §7hat das Spiel verlassen!");
    }
}
