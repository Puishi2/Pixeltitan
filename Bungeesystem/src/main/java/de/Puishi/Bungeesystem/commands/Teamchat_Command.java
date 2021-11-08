package de.Puishi.Bungeesystem.commands;

import de.Puishi.Bungeesystem.Bungeesystem;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 17.06.2020 / 21:23                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Teamchat_Command extends Command {

    public Teamchat_Command(String name) {
        super(name, "", "tc");
    }

    String name;

    @Deprecated
    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if (player.hasPermission("pixeltitan.teamchat")) {
                if (args.length == 0) {
                    List<String> team = new ArrayList<>();

                    for (ProxiedPlayer all : Bungeesystem.getInstance().getProxy().getPlayers()) {
                        if (all.hasPermission("pixeltitan.teamchat")) {
                            Collections.addAll(team, all.getName());
                        }
                    }

                    player.sendMessage(Bungeesystem.teamchatprefix + "§7Aktive Teammitglieder§8:");

                    int len = team.size() -1;
                    String msg = Bungeesystem.teamchatprefix + "§e";

                    for (String string : team) {
                        if (len > 0) {
                            msg = msg + string + "§8, §e";
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
                        if (all.hasPermission("pixeltitan.teamchat")) {
                            all.sendMessage(Bungeesystem.teamchatprefix + "§e" + CloudAPI.getInstance().getOfflinePlayer(player.getName()).getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool()).getColor() + player.getName() + " §8»§7" + message);
                        }
                    }
                }
            } else {
                player.sendMessage(Bungeesystem.noperm);
            }
        }
    }
}
