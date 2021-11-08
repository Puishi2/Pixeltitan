package de.Puishi.Bauserver.commands;

import de.Puishi.Bauserver.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 10.06.2020 / 10:27                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class setSpec_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(player.hasPermission("bauserver.setspec")) {
                if (args.length == 0) {
                    player.sendMessage(Data.getPREFIX() + "Benutze /setspec <Player>!");
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.setGameMode(GameMode.SPECTATOR);
                    target.sendMessage(Data.getPREFIX() + "Du bist nun im Spectator-Modus!");
                    player.sendMessage(Data.getPREFIX() + "Du hast den Spieler in den Spectator-Modus gesetzt!");

                }
            } else {
                player.sendMessage(Data.getPREFIX() + "§cDazu hast du keine Rechte!");
            }
        }

        return false;
    }
}
