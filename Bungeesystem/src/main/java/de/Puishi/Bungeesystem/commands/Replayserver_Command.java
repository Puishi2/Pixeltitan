package de.Puishi.Bungeesystem.commands;

import de.Puishi.Bungeesystem.Bungeesystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 11.06.2020 / 11:45                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Replayserver_Command extends Command {


    public Replayserver_Command(String replayserver) {
        super("replayserver");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if(commandSender instanceof ProxiedPlayer){
            ProxiedPlayer proxiedPlayer = (ProxiedPlayer) commandSender;
            if(proxiedPlayer.hasPermission("pixeltitan.replayserver")){
                if(args.length == 0){
                    proxiedPlayer.connect(ProxyServer.getInstance().getServerInfo("Replay-1"));
                    proxiedPlayer.sendMessage(new TextComponent(Bungeesystem.getInstance().getPrefix() + "Du wurdest auf den Replayserver verbunden!"));
                }
            } else {
                proxiedPlayer.sendMessage(new TextComponent(Bungeesystem.getInstance().getPrefix() + Bungeesystem.noperm));
            }
        }
    }
}
