package de.Puishi.TitanJump.deathmatch;

import de.Puishi.TitanJump.countdown.EndingCountdown;
import de.Puishi.TitanJump.utils.ActionBar;
import de.Puishi.TitanJump.utils.Data;
import de.Puishi.TitanJump.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 09.07.2020 / 14:03                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.getDrops().clear();
        event.setDroppedExp(0);
        event.setDeathMessage(null);

        if (event.getEntity() instanceof Player) {
            if (event.getEntity().getKiller() != null) {
                if (event.getEntity().getKiller() instanceof Player) {
                    final Player player = event.getEntity();
                    final Player killer = event.getEntity().getKiller();

                    if (!PlayerManager.lives.containsKey(player)) {
                        PlayerManager.lives.put(player, 3);
                    }

                    Bukkit.broadcastMessage(Data.getPrefix() + "Der Spieler §b" + player.getName() + " §7wurde von §b" + killer.getName() + " §7getötet!");

                    if (PlayerManager.getLives().get(player) > 1) {
                        player.getInventory().setContents(player.getInventory().getContents());
                        player.getInventory().setArmorContents(player.getInventory().getArmorContents());
                        PlayerManager.getLives().put(player, PlayerManager.getLives().get(player) - 1);
                        player.sendMessage(Data.getPrefix() + "Du hast noch §b" + PlayerManager.getLives().get(player) + " §7Leben!");
                    } else {
                        Bukkit.broadcastMessage(Data.getPrefix() + "§cDer Spieler §b" + player.getName() + " §cist ausgeschieden!");
                        PlayerManager.playerList.remove(player.getName());
                        new PlayerManager().setSpectator(player);
                    }
                    player.spigot().respawn();
                }
            } else {
                Player entity = event.getEntity();
                Bukkit.broadcastMessage(Data.getPrefix() + "Der Spieler §b" + entity.getName() + " §7ist gestorben!");

                if (PlayerManager.getLives().get(entity) == 0) {
                    entity.getInventory().setContents(entity.getInventory().getContents());
                    entity.getInventory().setArmorContents(entity.getInventory().getArmorContents());
                    PlayerManager.getLives().put(entity, PlayerManager.getLives().get(entity) - 1);
                    entity.sendMessage(Data.getPrefix() + "Du hast noch §b" + new PlayerManager().getLives() + " §7Leben!");
                } else {
                    Bukkit.broadcastMessage(Data.getPrefix() + "§cDer Spieler §b" + entity.getName() + " §cist ausgeschieden!");
                    PlayerManager.playerList.remove(entity.getName());
                }
                entity.spigot().respawn();

            }
        }

        if (PlayerManager.playerList.size() == 1) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.teleport(new LocationManager().getLocation("Waiting-Lobby"));
                ActionBar.setTitle(all, "§aDer Spieler §b" + PlayerManager.playerList.get(0), "§ahat das Spiel gewonnen!", 20, 80, 20);
                all.setFoodLevel(20);
                all.setHealth(20);
                all.getInventory().clear();
                all.getInventory().setArmorContents(null);
                new EndingCountdown().startStartCountdown(event.getEntity());
            }
        }

    }

}
