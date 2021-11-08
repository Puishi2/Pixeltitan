package de.Puishi.TitanJump.commands;

import de.Puishi.TitanJump.utils.Data;
import de.Puishi.TitanJump.utils.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 14.06.2020 / 19:40                                               *
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

    private int spawnID;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(player.hasPermission("titanjump.setup")) {
                if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("lobby")) {
                        new LocationManager().saveLocation(player, "Waiting-Lobby");
                        player.sendMessage(Data.getPrefix() + "Location gesetzt!");
                    } else if(args[0].equalsIgnoreCase("1")) {
                        new LocationManager().saveLocation(player, "1");
                        player.sendMessage(Data.getPrefix() + "Location gesetzt!");
                    }else if(args[0].equalsIgnoreCase("2")) {
                        new LocationManager().saveLocation(player, "2");
                        player.sendMessage(Data.getPrefix() + "Location gesetzt!");
                    }else if(args[0].equalsIgnoreCase("3")) {
                        new LocationManager().saveLocation(player, "3");
                        player.sendMessage(Data.getPrefix() + "Location gesetzt!");
                    }else if(args[0].equalsIgnoreCase("4")) {
                        new LocationManager().saveLocation(player, "4");
                        player.sendMessage(Data.getPrefix() + "Location gesetzt!");
                    }else if(args[0].equalsIgnoreCase("5")) {
                        new LocationManager().saveLocation(player, "5");
                        player.sendMessage(Data.getPrefix() + "Location gesetzt!");
                    }else if(args[0].equalsIgnoreCase("6")) {
                        new LocationManager().saveLocation(player, "6");
                        player.sendMessage(Data.getPrefix() + "Location gesetzt!");
                    }else if(args[0].equalsIgnoreCase("7")) {
                        new LocationManager().saveLocation(player, "7");
                        player.sendMessage(Data.getPrefix() + "Location gesetzt!");
                    }else if(args[0].equalsIgnoreCase("8")) {
                        new LocationManager().saveLocation(player, "8");
                        player.sendMessage(Data.getPrefix() + "Location gesetzt!");
                    } else if(args[0].equalsIgnoreCase("shop")) {
                        new LocationManager().saveLocation(player, "shopping");
                        player.sendMessage(Data.getPrefix() + "Location gesetzt!");
                    } else if(args[0].equalsIgnoreCase("deathmatch")) {
                        new LocationManager().saveLocation(player, "deathmatch");
                        player.sendMessage(Data.getPrefix() + "Location gesetzt!");
                    }else if(args[0].equalsIgnoreCase("spec")) {
                        new LocationManager().saveLocation(player, "Spec");
                        player.sendMessage(Data.getPrefix() + "Location gesetzt!");
                    }
                } else {
                    player.sendMessage(Data.getPrefix() + " ");
                    player.sendMessage(Data.getPrefix() + "Benutze §b/setup <lobby, 1-8, shop, deathmatch, spec>§7!");
                    player.sendMessage(Data.getPrefix() + " ");
                }
            } else {
                player.sendMessage(Data.getPrefix() + "§cDu hast keine Rechte!");
            }
        }
        return false;
    }
}
