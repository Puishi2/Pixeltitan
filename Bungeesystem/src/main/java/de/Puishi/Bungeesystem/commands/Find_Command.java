package de.Puishi.Bungeesystem.commands;

import de.Puishi.Bungeesystem.Bungeesystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 11.06.2020 / 12:20                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Find_Command extends Command {


    public Find_Command(String find) {
        super("find");
    }

    @SuppressWarnings("deprecation")
    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if(commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer proxiedPlayer = (ProxiedPlayer) commandSender;

            if(proxiedPlayer.hasPermission("pixeltitan.find")) {

                if (args.length == 0) {
                    proxiedPlayer.sendMessage(new TextComponent(Bungeesystem.getPrefix() + "§7Benutze§8: §e/find <Player>"));
                } else if (args.length == 1) {
                    ProxiedPlayer a = ProxyServer.getInstance().getPlayer(args[0]);
                    ServerInfo info = a.getServer().getInfo();

                    if (a == null || info == null) {
                        proxiedPlayer.sendMessage(new TextComponent(Bungeesystem.getPrefix() + "§cDer Spieler ist nicht online!"));
                    }

                    proxiedPlayer.sendMessage(Bungeesystem.getInstance().getPrefix() + "Der Spieler " + a.getDisplayName() + " §7befindet sich auf folgendem Server: §e" + info.getName());
                }
            } else {
                proxiedPlayer.sendMessage(new TextComponent(Bungeesystem.getPrefix() + Bungeesystem.noperm));
            }
        }
    }
}
