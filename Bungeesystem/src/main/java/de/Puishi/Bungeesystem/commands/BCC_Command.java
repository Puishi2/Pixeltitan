package de.Puishi.Bungeesystem.commands;

import de.Puishi.Bungeesystem.Bungeesystem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 11.06.2020 / 12:17                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class BCC_Command extends Command {

    public BCC_Command(String name) {
        super(name);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("pixeltitan.bc")) {
            if (sender instanceof ProxiedPlayer) {

                ProxiedPlayer p = (ProxiedPlayer) sender;
                if (args.length > 0) {
                    String message = "";
                    for (int i = 0; i < args.length; i++) {
                        message = message + args[i] + " ";
                    }

                    message = ChatColor.translateAlternateColorCodes('&', message);

                    ProxyServer.getInstance().broadcast("§8§l----------§c§lAchtung§8§l----------");
                    ProxyServer.getInstance().broadcast(" ");
                    ProxyServer.getInstance().broadcast(Bungeesystem.getInstance().getPrefix() + "§6§l" + message);
                    ProxyServer.getInstance().broadcast(" ");
                    ProxyServer.getInstance().broadcast("§8§l----------§c§lAchtung§8§l----------");


                } else {
                    p.sendMessage(Bungeesystem.getInstance().getPrefix()+ "Benutze§8: §e/bc <Nachricht>");
                }
            } else {
                sender.sendMessage(Bungeesystem.getInstance().getPrefix() + Bungeesystem.noperm);
            }
        }

    }

}
