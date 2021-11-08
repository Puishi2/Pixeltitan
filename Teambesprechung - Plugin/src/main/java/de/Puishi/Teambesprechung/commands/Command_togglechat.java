package de.Puishi.Teambesprechung.commands;

import de.Puishi.Teambesprechung.Teambesprechung;
import de.Puishi.Teambesprechung.listener.ToggleChatListener;
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
 *    Erstellt: 12.05.2020 / 19:28                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Command_togglechat implements CommandExecutor {

    public static ArrayList<String> togglechatlist = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if(p.hasPermission("tb.togglechat")){
            if(args.length == 0){
                if(ToggleChatListener.togglechat == true){
                    Bukkit.broadcastMessage(Teambesprechung.PREFIX + "Der Chat wurde deaktiviert!");
                    ToggleChatListener.togglechat = false;
                }else {
                    ToggleChatListener.togglechat = true;
                    Bukkit.broadcastMessage(Teambesprechung.PREFIX + "Der Chat wurde aktiviert!");
                }
            }
        }else {
            p.sendMessage(Teambesprechung.PREFIX + "§cKeine Rechte!");
        }
        return false;
    }
}
