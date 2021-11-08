package de.Puishi.Teambesprechung.commands;

import de.Puishi.Teambesprechung.Teambesprechung;
import de.Puishi.Teambesprechung.utils.LocationAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 18.05.2020 / 21:15                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Command_set implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(p.hasPermission("tb.set")){
                if(args.length == 0){
                    p.sendMessage(Teambesprechung.PREFIX + "Benutze §c/set <Name>§7.");
                } else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("CuzImJulien")){
                        new LocationAPI(Teambesprechung.getInstance(), p.getLocation(), "CuzImJulien").saveLocation();
                        p.sendMessage(Teambesprechung.PREFIX + "Du hast die Location für §cCuzImJulien §7gesetzt!");
                    } else if(args[0].equalsIgnoreCase(target.getName())){
                        new LocationAPI(Teambesprechung.getInstance(), p.getLocation(), target.getName());
                        p.sendMessage(Teambesprechung.PREFIX + "Du hast die Location für §c" + target.getName() + " §7gesetzt!");
                    }
                }
            }
        }
        return false;
    }
}
