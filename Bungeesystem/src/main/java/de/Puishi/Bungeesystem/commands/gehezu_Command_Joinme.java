package de.Puishi.Bungeesystem.commands;

import de.Puishi.Bungeesystem.Bungeesystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 19.06.2020 / 17:29                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class gehezu_Command_Joinme extends Command {


    public gehezu_Command_Joinme(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) sender;
            if(args.length == 1) {
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                ServerInfo server = target.getServer().getInfo();
                if(target.getServer().getInfo() != player.getServer().getInfo()) {
                    player.connect(server);
                    player.sendMessage(Bungeesystem.getInstance().getPrefix() + "Du bist zu §e" + target.getName() + " §7gesprungen");
                } else {
                    player.sendMessage(Bungeesystem.getInstance().getPrefix() + "§cDu bist schon auf diesen Server!");
                }
            } else {
                player.sendMessage(Bungeesystem.getInstance().getPrefix() + "Bitte benutze §e/gehezu <Name>§7!");
            }

    }

}
