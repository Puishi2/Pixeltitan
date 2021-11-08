package de.Puishi.SkyWars.listener;

import de.Puishi.SkyWars.GameState.GameStates;
import de.Puishi.SkyWars.Main;
import de.Puishi.SkyWars.countdown.LobbyCountdown;
import de.Puishi.SkyWars.database.KitDatabaseManager;
import de.Puishi.SkyWars.kits.KitManager;
import de.Puishi.SkyWars.scoreboard.ScoreboardManager;
import de.Puishi.SkyWars.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 24.05.2020 / 20:16                                               *
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

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {

        final Player player = event.getPlayer();

        if(KitDatabaseManager.getKit(player.getUniqueId().toString(), "KIT").equals("Titan")) {
            KitManager.kit.put(player.getUniqueId().toString(), "§8» §6Titan-Kit");
        } else if (KitDatabaseManager.getKit(player.getUniqueId().toString(), "KIT").equals("Spotter")) {
            KitManager.kit.put(player.getUniqueId().toString(), "§8» §eSpotter-Kit");
        } else if (KitDatabaseManager.getKit(player.getUniqueId().toString(), "KIT").equals("Pixel")) {
            KitManager.kit.put(player.getUniqueId().toString(), "§8» §aPixel-Kit");
        } else if (KitDatabaseManager.getKit(player.getUniqueId().toString(), "KIT").equals("Tp")) {
            KitManager.kit.put(player.getUniqueId().toString(), "§8» §2TP-Kit");
        } else if (KitDatabaseManager.getKit(player.getUniqueId().toString(), "KIT").equals("Assasine")) {
            KitManager.kit.put(player.getUniqueId().toString(), "§8» §bAssasine-Kit");
        } else if (KitDatabaseManager.getKit(player.getUniqueId().toString(), "KIT").equals("Build")) {
            KitManager.kit.put(player.getUniqueId().toString(), "§8» §dBuild-Kit");
        } else if (KitDatabaseManager.getKit(player.getUniqueId().toString(), "KIT").equals("Mlg")) {
            KitManager.kit.put(player.getUniqueId().toString(), "§8» §9MLG-Kit");
        } else if (KitDatabaseManager.getKit(player.getUniqueId().toString(), "KIT").equals("Titan")) {
            KitManager.kit.put(player.getUniqueId().toString(), "§8» §6Titan-Kit");
        }

        if(Main.getInstance().getGameStates() == GameStates.INGAME || Main.getInstance().getGameStates() == GameStates.ENDING) {
            SpecUtils.spectatorlist.add(player.getName());
            Main.getInstance().getSpecUtils().setSpectator(player);
            new ScoreboardManager().setIngameScoreboard(player);
            event.setJoinMessage(null);
            return;
        }


        event.setJoinMessage(Data.getPrefix() + "Der Spieler " + event.getPlayer().getDisplayName() + " §7hat das Spiel betreten!");

        new WaitingInventory().setInventory(player);

        if(Main.getInstance().getGameStates() == GameStates.LOBBY) {
            if (Bukkit.getOnlinePlayers().size() == Main.getInstance().getMinplayers()) {
                new LobbyCountdown().start(player);
            } else {
                new LobbyCountdown().stop();
            }
        }

        SpecUtils.playerlist.add(player.getName());

        new ScoreboardManager().setScoreboard(player);

        try {
            player.teleport(Main.getInstance().getMapManager().loadLocation(Main.getInstance().getMap() + "." + 10));
        } catch (Exception e) {
            player.sendMessage("§cEs konnte kein Lobby-Spawn für die Map " + Main.getInstance().getMap() + " gefunden werden!");
        }

        player.setGameMode(GameMode.SURVIVAL);
        player.setHealthScale(20);
        player.setHealth(20);
        player.setFoodLevel(20);

    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent event){
        final Player player = event.getPlayer();
        event.setQuitMessage(Data.getPrefix() + "Der Spieler " + player.getName() + " §7hat das Spiel verlassen!");
        SpecUtils.playerlist.remove(player.getName());
        SpecUtils.spectatorlist.remove(player.getName());

        if(Main.getInstance().getGameStates() == GameStates.LOBBY) {
            if (SpecUtils.playerlist.size() < Main.getInstance().getMinplayers()) {
                Main.getInstance().getLobbyCountdown().stop();
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.setLevel(0);
                    all.setExp(0.0F);
                }
                Bukkit.broadcastMessage(Data.PREFIX + "§cEs sind nicht genügend Spieler online!");
                Main.getInstance().getLobbyCountdown().setRunning(false);
                Main.getInstance().getLobbyCountdown().setSeconds(30);
            }
        }

        if(Main.getInstance().getGameStates() == GameStates.INGAME) {
            if (SpecUtils.playerlist.size() != 1) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.sendMessage(Data.PREFIX + "§7Es leben noch §2" + SpecUtils.playerlist.size() + " §7Spieler.");
                    all.hidePlayer(player);
                }
            } else {
                if (SpecUtils.playerlist.size() == 1) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.setGameMode(GameMode.SURVIVAL);
                        all.getInventory().clear();
                        all.setFoodLevel(20);
                        all.setHealth(20);
                        all.getInventory().setArmorContents(null);
                        all.showPlayer(player);
                        all.teleport(Main.getInstance().getMapManager().loadLocation(Main.getInstance().getMap() + "." + 10));
                        ActionBar.setTitle(all, "§aDer Spieler §2" + SpecUtils.playerlist.get(0), "§ahat das Spiel gewonnen!", 1, 20 * 5, 1);
                        Main.getInstance().setGameStates(GameStates.ENDING);
                        System.out.println(Main.getInstance().getGameStates());
                        player.getInventory().clear();
                        Main.getInstance().getEndingCountdown().start(all);
                    }
                }
            }
        }
    }
}
