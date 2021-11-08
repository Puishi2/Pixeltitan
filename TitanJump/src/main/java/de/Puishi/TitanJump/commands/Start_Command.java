package de.Puishi.TitanJump.commands;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.countdown.LobbyCountdown;
import de.Puishi.TitanJump.gamestate.GameState;
import de.Puishi.TitanJump.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 04.07.2020 / 16:45                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Start_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length == 0) {
                if (player.hasPermission("tj.start")) {
                    if (TitanJump.getInstance().getGameState() == GameState.LOBBY) {
                        if (Bukkit.getOnlinePlayers().size() >= TitanJump.getInstance().getMinplayers()) {
                            if (new LobbyCountdown().getCountdown() >= 10) {
                                player.sendMessage(Data.getPrefix() + "Du hast das Spiel gestartet!");
                                new LobbyCountdown().setCountdown(5);
                                player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                            } else {
                                player.sendMessage(Data.getPrefix() + "§cDas Spiel kann nicht mehr gestartet werden!");
                            }
                        } else {
                            player.sendMessage(Data.getPrefix() + "§cEs sind zu wenige Spieler online!");
                        }
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDas Spiel hat bereits begonnen!");
                    }
                } else {
                    player.sendMessage(Data.getPrefix() + "§cDazu hast du keine Rechte!");
                }
            } else {
                player.sendMessage(Data.getPrefix() + "§cBenutze: /start");
            }

        }
        return false;
    }
}
