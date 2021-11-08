package de.Puishi.Bauserver.worldmanager;

import de.Puishi.Bauserver.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 19.06.2020 / 10:36                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class TeleportWorld implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if(args.length == 2) {
                String world = args[1];
                World w = Bukkit.getWorld(world);

                if(w != null) {
                    player.teleport(w.getSpawnLocation());
                    player.sendMessage(Data.getPREFIX() + "Du bist nun in der Welt: §3" + w.getName() + "§7.");
                } else {
                    player.sendMessage(Data.getPREFIX() + "§cDiese Welt existriert nicht!");
                }

            } else {
                player.sendMessage(Data.getPREFIX() + "Benutze /world tp <Name>");
            }

        }
        return false;
    }
}
