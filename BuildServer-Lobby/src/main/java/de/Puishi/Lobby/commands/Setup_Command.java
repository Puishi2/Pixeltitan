package de.Puishi.Lobby.commands;

import de.Puishi.Lobby.utils.Data;
import de.Puishi.Lobby.utils.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 08.07.2020 / 17:29                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Setup_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(args.length == 1) {
                if(player.hasPermission("lobby.setup")) {
                    if(args[0].equalsIgnoreCase("spawn")) {
                        new LocationManager().saveLocation(player, "Spawn");
                        player.sendMessage(Data.getPrefix() + "Location gesetzt!");
                    } else if(args[0].equalsIgnoreCase("amorstands")) {
                        new LocationManager().saveLocation(player, "amorstands");
                        player.sendMessage(Data.getPrefix() + "Location gesetzt!");
                    }
                } else {
                    player.sendMessage(Data.getPrefix() + Data.getNoperm());
                }
            } else {
                player.sendMessage(Data.getPrefix() + "Benutze §3/setup <Spawn, Amorstands>§7!");
            }
        }
        return false;
    }
}
