package de.Puishi.BedWars.commands;

import de.Puishi.BedWars.BedWars;
import de.Puishi.BedWars.utils.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 02.06.2020 / 23:47                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Command_Setup implements CommandExecutor {

    private int spawnID;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(player.hasPermission("bedwars.setup")) {
                if (args.length == 2) {
                    try {
                        spawnID = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        player.sendMessage(Data.PREFIX + "§cBitte gebe eine Zahl an! (1-9)");
                    }

                    BedWars.getInstance().getLocationAPI().saveLocation(player.getLocation(), args[0] + "." + spawnID);
                    player.sendMessage(Data.PREFIX + "§7Du hast den Spawn §3" + spawnID + " §7für die Map §3" + args[0] + " §7erfolgreich gesetzt!");

                } else {
                    player.sendMessage(Data.PREFIX + "§cBitte nutze: /setup <Map> <SpawnID> (1-9)");
                }
            } else {
                player.sendMessage(Data.PREFIX + "§cDu hast keine Rechte!");
            }
        }
        return false;
    }
}
