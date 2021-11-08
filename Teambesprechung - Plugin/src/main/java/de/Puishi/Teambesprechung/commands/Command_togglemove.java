package de.Puishi.Teambesprechung.commands;

import de.Puishi.Teambesprechung.Teambesprechung;
import de.Puishi.Teambesprechung.listener.ToggleMoveListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 12.05.2020 / 19:11                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Command_togglemove implements CommandExecutor {

    public static ArrayList<String> togglemovelist = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if(p.hasPermission("tb.togglemove")){
            if(args.length == 0){
                if(togglemovelist.contains(p.getName())){
                    togglemovelist.remove(p.getName());
                    p.sendMessage(Teambesprechung.PREFIX + "Du kannst dich nun wieder bewegen!");
                }else {
                    togglemovelist.add(p.getName());
                    p.sendMessage(Teambesprechung.PREFIX + "Du kannst dich nun nicht mehr bewegen!");
                 }

            }
        }else {
            p.sendMessage(Teambesprechung.PREFIX + "§cKeine Rechte!");
        }

        return false;
    }
}
