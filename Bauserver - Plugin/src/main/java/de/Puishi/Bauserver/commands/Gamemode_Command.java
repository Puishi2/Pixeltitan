package de.Puishi.Bauserver.commands;

import de.Puishi.Bauserver.utils.ActionBar;
import de.Puishi.Bauserver.utils.Data;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 10.06.2020 / 21:42                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Gamemode_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(player.hasPermission("bauserver.gm")) {
                if(args.length == 0) {
                    player.sendMessage(Data.getPREFIX() + "Benutze /gm <1,2,3,0>!");
                } else if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("1")) {
                        player.setGameMode(GameMode.CREATIVE);
                        ActionBar.setTitle(player, "§3Gamemode", "§7§lCreative", 10, 40, 10);
                    } else if(args[0].equalsIgnoreCase("2")) {
                        player.setGameMode(GameMode.ADVENTURE);
                        ActionBar.setTitle(player, "§3Gamemode", "§7§lAdventure", 10, 40, 10);
                    } else if(args[0].equalsIgnoreCase("3")) {
                        player.setGameMode(GameMode.SPECTATOR);
                        ActionBar.setTitle(player, "§3Gamemode", "§7§lSpectator", 10, 40, 10);
                    } else if(args[0].equalsIgnoreCase("0")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        ActionBar.setTitle(player, "§3Gamemode", "§7§lSurvival", 10, 40, 10);
                    }
                }
            } else {
                player.sendMessage(Data.getPREFIX() + "§cDazu hast du keine Rechte!");
            }
        }
        return false;
    }
}
