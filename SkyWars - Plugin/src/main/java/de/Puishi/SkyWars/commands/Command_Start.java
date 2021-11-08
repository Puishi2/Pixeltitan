package de.Puishi.SkyWars.commands;

import de.Puishi.SkyWars.GameState.GameStates;
import de.Puishi.SkyWars.Main;
import de.Puishi.SkyWars.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 28.05.2020 / 04:37                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Command_Start implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(player.hasPermission("skywars.start")) {
                if (args.length == 0) {
                    if(Main.getInstance().getGameStates() == GameStates.LOBBY) {
                        if(Main.getInstance().getLobbyCountdown().isRunning) {
                            if(Main.getInstance().getLobbyCountdown().seconds < 10) {
                                Main.getInstance().getLobbyCountdown().setSeconds(10);
                                Bukkit.broadcastMessage(Data.PREFIX + "Der Countdown wurde verkürzt.");
                            } else {
                                player.sendMessage(Data.PREFIX + "§cDu kannst das Spiel nicht mehr starten!");
                            }
                        }
                    } else {
                        player.sendMessage(Data.PREFIX + "§cDas Spiel läuft schon!");
                    }
                } else {
                 player.sendMessage(Data.PREFIX + "§cBitte nutze: /start");
                }
            } else {
                player.sendMessage(Data.PREFIX + "§cDu hast keine Rechte!");
            }
        }
        return false;
    }
}

