package de.Puishi.Konzept.commands;

import de.Puishi.Konzept.utils.Data;
import de.Puishi.Konzept.utils.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 16.07.2020 / 17:22                                               *
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
        Player player = (Player) commandSender;
        if(commandSender.hasPermission("Konzept.setup")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("spawn")) {
                    new LocationManager().saveLocation(player, "Spawn");
                    player.sendMessage(Data.getPrefix() + "Du hast die Location gesetzt!");
                }
            }
        } else {
            player.sendMessage(Data.getNoperm());
        }

        return false;
    }
}
