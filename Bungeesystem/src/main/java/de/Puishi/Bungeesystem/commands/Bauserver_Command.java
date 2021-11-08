package de.Puishi.Bungeesystem.commands;

import de.Puishi.Bungeesystem.Bungeesystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.lang.reflect.Proxy;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 10.06.2020 / 22:15                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Bauserver_Command extends Command {


    public Bauserver_Command(String bauserver) {
        super("bauserver");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if(commandSender instanceof ProxiedPlayer){
            ProxiedPlayer proxiedPlayer = (ProxiedPlayer) commandSender;
            if(proxiedPlayer.hasPermission("pixeltitan.bauserver")){
                if(args.length == 0){
                    proxiedPlayer.connect(ProxyServer.getInstance().getServerInfo("Build-1"));
                    proxiedPlayer.sendMessage(new TextComponent(Bungeesystem.getInstance().getPrefix() + "Du wurdest auf den Bauserver verbunden!"));
                }
            } else {
                proxiedPlayer.sendMessage(new TextComponent(Bungeesystem.getInstance().getPrefix() + "§cDu hast keine Rechte für diesen Command!"));
            }
        }
    }
}
