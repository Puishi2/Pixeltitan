package de.Puishi.Lobby.commands;

import de.Puishi.Lobby.Main;
import de.Puishi.Lobby.utils.Data;
import de.Puishi.Lobby.utils.LocationAPI;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 19.05.2020 / 21:18                                               *
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

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(p.hasPermission("pixel.setup")){
                if(args.length == 0){
                    p.sendMessage(" ");
                    p.sendMessage(Data.PRERIX + "Benutze §6/setup <Spawn>§7!");
                    p.sendMessage(Data.PRERIX + "Benutze §6/setup <TitanJump>§7!");
                    p.sendMessage(Data.PRERIX + "Benutze §6/setup <BedWars>§7!");
                    p.sendMessage(Data.PRERIX + "Benutze §6/setup <SkyWars>§7!");
                    p.sendMessage(Data.PRERIX + "Benutze §6/setup <Survival>§7!");
                    p.sendMessage(Data.PRERIX + "Benutze §6/setup <Community>§7!");
                    p.sendMessage(Data.PRERIX + "Benutze §6/setup <TTT>§7!");
                    p.sendMessage(" ");
                } else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("spawn")){
                        new LocationAPI().saveLocation(p, "Spawn");
                        p.sendMessage(Data.PRERIX + "Du hast den Spawn gesetzt!");
                        p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 1);
                    } else if(args[0].equalsIgnoreCase("titanjump")){
                        new LocationAPI().saveLocation(p, "TitanJump");
                        p.sendMessage(Data.PRERIX + "Du hast den Spawn für TitanJump gesetzt!");
                        p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 1);
                    } else if(args[0].equalsIgnoreCase("bedwars")){
                        new LocationAPI().saveLocation(p, "BedWars");
                        p.sendMessage(Data.PRERIX + "Du hast den Spawn für BedWars gesetzt!");
                        p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 1);
                    } else if(args[0].equalsIgnoreCase("skywars")){
                        new LocationAPI().saveLocation(p, "SkyWars");
                        p.sendMessage(Data.PRERIX + "Du hast den Spawn für SkyWars gesetzt!");
                        p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 1);
                    } else if(args[0].equalsIgnoreCase("survival")){
                        new LocationAPI().saveLocation(p, "Survival");
                        p.sendMessage(Data.PRERIX + "Du hast den Spawn für Survival gesetzt!");
                        p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 1);
                    } else if(args[0].equalsIgnoreCase("community")){
                        new LocationAPI().saveLocation(p, "Community");
                        p.sendMessage(Data.PRERIX + "Du hast den Spawn für Community gesetzt!");
                        p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 1);
                    } else if(args[0].equalsIgnoreCase("ttt")){
                        new LocationAPI().saveLocation(p, "TTT");
                        p.sendMessage(Data.PRERIX + "Du hast den Spawn für TTT gesetzt!");
                        p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 1);
                    }
                }
            }
        }
        return false;
    }
}
