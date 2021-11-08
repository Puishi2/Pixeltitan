package de.Puishi.Bungeesystem.commands;

import de.Puishi.Bungeesystem.Bungeesystem;
import de.dytanic.cloudnet.api.CloudAPI;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 19.06.2020 / 17:20                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Adminchat_Command extends Command {


    public Adminchat_Command(String name) {
        super(name, "", "ac");
    }

    @Deprecated
    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if (player.hasPermission("pixeltitan.adminchat")) {
                if (args.length == 0) {
                    List<String> team = new ArrayList<>();

                    for (ProxiedPlayer all : Bungeesystem.getInstance().getProxy().getPlayers()) {
                        if (all.hasPermission("pixeltitan.adminchat")) {
                            Collections.addAll(team, all.getName());
                        }
                    }

                    player.sendMessage(Bungeesystem.adminchatprefix + "§7Aktive Administratoren§8:");

                    int len = team.size() -1;
                    String msg = Bungeesystem.adminchatprefix + "§4";

                    for (String string : team) {
                        if (len > 0) {
                            msg = msg + string + "§8, §4";
                            len--;
                        } else {
                            msg = msg + string;
                        }
                    }

                    player.sendMessage(msg);
                } else {
                    String message = "";
                    int count = 0;

                    while (count < args.length) {
                        message = message + " " + args[count];
                        count++;
                    }

                    for (ProxiedPlayer all : Bungeesystem.getInstance().getProxy().getPlayers()) {
                        if (all.hasPermission("pixeltitan.adminchat")) {
                            all.sendMessage(Bungeesystem.adminchatprefix + "§e" + CloudAPI.getInstance().getOfflinePlayer(player.getName()).getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool()).getColor() + player.getName() + " §8»§7" + message);
                        }
                    }
                }
            } else {
                player.sendMessage(Bungeesystem.noperm);
            }
        }
    }
}
