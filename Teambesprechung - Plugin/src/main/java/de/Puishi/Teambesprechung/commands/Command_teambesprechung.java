package de.Puishi.Teambesprechung.commands;

import de.Puishi.Teambesprechung.Teambesprechung;
import de.Puishi.Teambesprechung.listener.PlayerInventoryClick;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 12.05.2020 / 19:49                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Command_teambesprechung implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(p.hasPermission("tb.help")){
                if(args.length == 0){
                    p.sendMessage(Teambesprechung.PREFIX + " ");
                    p.sendMessage(Teambesprechung.PREFIX + "Benutze /togglemove");
                    p.sendMessage(Teambesprechung.PREFIX + "Benutze /togglechat");
                    p.sendMessage(Teambesprechung.PREFIX + " ");
                }
            }else {
                p.sendMessage(Teambesprechung.PREFIX + "§cKeine Rechte!");
            }
        }
        return false;
    }
}
