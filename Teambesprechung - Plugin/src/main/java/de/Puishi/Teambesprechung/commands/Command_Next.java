package de.Puishi.Teambesprechung.commands;

import de.Puishi.Teambesprechung.Teambesprechung;
import de.Puishi.Teambesprechung.utils.FileBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 14.05.2020 / 15:57                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Command_Next implements CommandExecutor {

    public static FileBuilder fb = new FileBuilder("plugins//Teambesprechung//", "next.yml");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(p.hasPermission("tb.next")){
                if(args.length == 0){
                    p.sendMessage(Teambesprechung.PREFIX + "Benutze /next <Datum>!");
                }else if(args.length == 1){
                    fb.setValue("Datum", args[0]);
                    p.sendMessage(Teambesprechung.PREFIX + "Du hast die Teambesprechung auf §c" + args[0] + " §7gesetzt!");
                }
            }
        }

        return false;
    }
}
