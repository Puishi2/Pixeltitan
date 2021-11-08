package de.Puishi.SkyWars.commands;

import de.Puishi.SkyWars.GameState.GameStates;
import de.Puishi.SkyWars.Main;
import de.Puishi.SkyWars.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.BatchUpdateException;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 28.05.2020 / 04:46                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Command_Forcemap implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("skywars.forcemap")) {
                if (args.length == 1) {
                    if (Main.getInstance().getGameStates() == GameStates.LOBBY) {
                        if (Main.getInstance().getMapManager().getMaps().contains(args[0])) {
                            Main.getInstance().setMap(args[0]);
                            player.sendMessage(Data.PREFIX + "§7Du hast die Map auf §2" + Main.getInstance().getMap() + " §7gesetzt!");
                        } else {
                            player.sendMessage(Data.PREFIX + "§cDiese Map gibt es nicht!");
                        }
                    } else {
                        player.sendMessage(Data.PREFIX + "§cDas Spiel läuft schon!");
                    }
                } else {
                    player.sendMessage(Data.PREFIX + "§cBitte nutze: /forcemap <Map>");
                }
            } else {
                player.sendMessage(Data.PREFIX + "§cDu hast keine Rechte!");
            }
        }
        return false;
    }
}


