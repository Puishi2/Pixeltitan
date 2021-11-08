package de.Puishi.Bungeesystem.commands;

import java.util.HashMap;
import java.util.Iterator;

import de.Puishi.Bungeesystem.Bungeesystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Command;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 11.06.2020 / 12:18                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class CC_Command extends Command {

    public static HashMap<Server, String> muted = new HashMap();

    public CC_Command() {
        super("cc");
    }

    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer) sender;
        if (sender.hasPermission("pixeltitan.cc")) {
            if (args.length != 0) {
                sender.sendMessage(Bungeesystem.getInstance().getPrefix() + "Bitte benutze §e/cc!");
            } else {
                ProxiedPlayer all;
                Iterator var6;
                for(int i = 0; i < 200; ++i) {
                    var6 = p.getServer().getInfo().getPlayers().iterator();

                    while(var6.hasNext()) {
                        all = (ProxiedPlayer)var6.next();
                        all.sendMessage(" ");
                    }
                }

                var6 = p.getServer().getInfo().getPlayers().iterator();

                while(var6.hasNext()) {
                    all = (ProxiedPlayer)var6.next();
                    all.sendMessage(Bungeesystem.getInstance().getPrefix() + "§7Der Chat wurde von §e" + p.getName() + " §7geleert.");
                }
            }
        } else {
            sender.sendMessage(Bungeesystem.getInstance().getPrefix() + Bungeesystem.noperm);
        }

    }

}
