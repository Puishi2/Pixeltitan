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
 *    Erstellt: 17.06.2020 / 21:28                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class List_Command extends Command {

    public List_Command(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if(commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer proxiedPlayer = (ProxiedPlayer) commandSender;

            if(args.length == 0) {
                proxiedPlayer.sendMessage(new TextComponent(Bungeesystem.getPrefix() + "Momentan sind §e" + ProxyServer.getInstance().getOnlineCount() + " §7Spieler online!"));
            }

        }
    }
}
