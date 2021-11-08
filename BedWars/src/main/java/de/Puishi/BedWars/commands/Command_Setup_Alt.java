package de.Puishi.BedWars.commands;

import de.Puishi.BedWars.BedWars;
import de.Puishi.BedWars.utils.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 03.06.2020 / 10:57                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Command_Setup_Alt implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(player.hasPermission("bedwars.setup")){
                if(args.length == 0){
                    player.sendMessage(" ");
                    player.sendMessage(Data.PREFIX + "/setup Blau");
                    player.sendMessage(Data.PREFIX + "/setup Rot");
                    player.sendMessage(Data.PREFIX + "/setup Bronze1");
                    player.sendMessage(Data.PREFIX + "/setup Bronze2");
                    player.sendMessage(Data.PREFIX + "/setup Silber1");
                    player.sendMessage(Data.PREFIX + "/setup Silber2");
                    player.sendMessage(Data.PREFIX + "/setup Gold");
                    player.sendMessage(Data.PREFIX + "/setup BedBlau");
                    player.sendMessage(Data.PREFIX + "/setup BedRot");
                    player.sendMessage(Data.PREFIX + "/setup Wartelobby");
                    player.sendMessage(" ");
                } else if(args[0].equalsIgnoreCase("blau")){
                    BedWars.getInstance().getLocationAPI_alt().saveLocation(player, "Blau");
                } else if(args[0].equalsIgnoreCase("rot")){
                    BedWars.getInstance().getLocationAPI_alt().saveLocation(player, "Rot");
                } else if(args[0].equalsIgnoreCase("bronze1")){
                    BedWars.getInstance().getLocationAPI_alt().saveLocation(player, "Bronze1");
                } else if(args[0].equalsIgnoreCase("bronze2")){
                    BedWars.getInstance().getLocationAPI_alt().saveLocation(player, "Bronze2");
                } else if(args[0].equalsIgnoreCase("silber1")){
                    BedWars.getInstance().getLocationAPI_alt().saveLocation(player, "Silber1");
                } else if(args[0].equalsIgnoreCase("silber2")){
                    BedWars.getInstance().getLocationAPI_alt().saveLocation(player, "Silber2");
                } else if(args[0].equalsIgnoreCase("gold")){
                    BedWars.getInstance().getLocationAPI_alt().saveLocation(player, "Gold");
                } else if(args[0].equalsIgnoreCase("BedBlau")){
                    BedWars.getInstance().getLocationAPI_alt().saveBlockLocation(player.getLocation(), "BedBlau");
                } else if(args[0].equalsIgnoreCase("BedRot")){
                    BedWars.getInstance().getLocationAPI_alt().saveBlockLocation(player.getLocation(), "BedRot");
                } else if(args[0].equalsIgnoreCase("Wartelobby")){
                    BedWars.getInstance().getLocationAPI_alt().saveLocation(player, "Wartelobby");
                }
            }
        }
        return false;
    }
}
