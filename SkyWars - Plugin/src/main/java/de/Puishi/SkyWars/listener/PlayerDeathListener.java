package de.Puishi.SkyWars.listener;

import de.Puishi.SkyWars.GameState.GameStates;
import de.Puishi.SkyWars.Main;
import de.Puishi.SkyWars.database.StatsManager;
import de.Puishi.SkyWars.utils.ActionBar;
import de.Puishi.SkyWars.utils.Data;
import de.Puishi.SkyWars.utils.SpecUtils;
import de.javaarray.pixeltitan.coinsapi.api.CoinsAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 28.05.2020 / 04:03                                               *
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

        final Player player = (Player) event.getEntity();
        final Player killer = (Player) event.getEntity().getKiller();

        if(killer == null) {
            event.setDeathMessage(Data.PREFIX + "Der Spieler " + player.getName() + "§7 ist gestorben.");

            SpecUtils.playerlist.remove(player.getName());
           // StatsManager.addStats(player.getUniqueId().toString(), 1, "DEATHS");

            if(SpecUtils.playerlist.size() != 1) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.sendMessage(Data.PREFIX + "§7Es leben noch §2" + SpecUtils.playerlist.size() + " §7Spieler.");
                    all.hidePlayer(player);
                }
            } else {
                if(SpecUtils.playerlist.size() == 1) {
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        all.teleport(Main.getInstance().getMapManager().loadLocation(Main.getInstance().getMap() + "." + 10));
                        all.setGameMode(GameMode.SURVIVAL);
                        all.getInventory().clear();
                        all.setFoodLevel(20);
                        all.setHealth(20);
                        all.getInventory().setArmorContents(null);
                        player.showPlayer(all);
                        ActionBar.setTitle(all, "§aDer Spieler §2" + SpecUtils.playerlist.get(0), "§ahat das Spiel gewonnen!", 1, 20*5, 1);
                        Main.getInstance().setGameStates(GameStates.ENDING);
                        System.out.println(Main.getInstance().getGameStates());
                        player.getInventory().clear();
                        CoinsAPI.addCoins(SpecUtils.playerlist.get(0), 100);
                        //StatsManager.addStats(SpecUtils.playerlist.get(0), 1, "WINS");
                    }
                    Main.getInstance().getEndingCountdown().start(player);
                    player.teleport(Main.getInstance().getMapManager().loadLocation(Main.getInstance().getMap() + "." + 10));
                }
            }

            Main.getInstance().getSpecUtils().setSpectator(player);

            Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {

                @Override
                public void run() {
                    player.spigot().respawn();
                    SpecUtils.playerlist.remove(player.getName());

                    if(SpecUtils.playerlist.size() == 1 || SpecUtils.playerlist.size() == 0) {
                        player.teleport(Main.getInstance().getMapManager().loadLocation(Main.getInstance().getMap() + "." + 9));
                    }
                }
            }, 3);

            Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {

                @Override
                public void run() {
                    player.teleport(Main.getInstance().getMapManager().loadLocation(Main.getInstance().getMap() + "." + 9));
                }
            }, 3);

            killer.sendMessage( Data.PREFIX + "Du hast den Spieler " + player.getName() + " §7getötet.");
            killer.sendMessage(Data.PREFIX + "Du hast §210 Coins erhalten!");
            CoinsAPI.addCoins(killer.getUniqueId().toString(), 10);
            //StatsManager.addStats(killer.getUniqueId().toString(), 1, "KILLS");

        }

        event.setDeathMessage(Data.PREFIX + "Der Spieler " + player.getName() + " §7wurde von " + killer.getName() + " §7getötet.");

        SpecUtils.playerlist.remove(player.getName());

        if(SpecUtils.playerlist.size() != 1) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendMessage(Data.PREFIX + "§7Es leben noch §2" + SpecUtils.playerlist.size() + " §7Spieler.");
                all.hidePlayer(player);
            }
        }

        Main.getInstance().getSpecUtils().setSpectator(player);

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {

            @Override
            public void run() {
                player.spigot().respawn();
            }
        }, 3);

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {

            @Override
            public void run() {
                player.teleport(Main.getInstance().getMapManager().loadLocation(Main.getInstance().getMap() + "." + 9));
            }
        }, 3);

        killer.sendMessage( Data.PREFIX + "Du hast den Spieler " + player.getName() + " §7getötet.");
        killer.sendMessage(Data.PREFIX + "Du hast §210 Coins erhalten!");
        CoinsAPI.addCoins(killer.getUniqueId().toString(), 10);
        //StatsManager.addStats(killer.getUniqueId().toString(), 1, "KILLS");

        if(SpecUtils.playerlist.size() == 1) {
            for(Player all : Bukkit.getOnlinePlayers()) {
                all.teleport(Main.getInstance().getMapManager().loadLocation(Main.getInstance().getMap() + "." + 10));
                all.setGameMode(GameMode.SURVIVAL);
                all.getInventory().clear();
                all.setFoodLevel(20);
                all.setHealth(20);
                all.getInventory().setArmorContents(null);
                player.showPlayer(all);
                ActionBar.setTitle(all, "§aDer Spieler §2" + SpecUtils.playerlist.get(0), "§ahat das Spiel gewonnen!", 1, 20*5, 1);
                Main.getInstance().setGameStates(GameStates.ENDING);
                System.out.println(Main.getInstance().getGameStates());
                player.getInventory().clear();
                CoinsAPI.addCoins(SpecUtils.playerlist.get(0), 100);
                //StatsManager.addStats(SpecUtils.playerlist.get(0), 1, "WINS");
            }
            Main.getInstance().getEndingCountdown().start(player);
            player.teleport(Main.getInstance().getMapManager().loadLocation(Main.getInstance().getMap() + "." + 10));
        }
    }
}
