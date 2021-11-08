package de.Puishi.Bungeesystem.commands;

import de.Puishi.Bungeesystem.Bungeesystem;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.bukkit.command.CommandSender;

import javax.xml.crypto.Data;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 25.06.2020 / 09:22                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class IchBinCool_Command extends Command {

    public IchBinCool_Command(String name) {
        super(name);
    }

    @Override
    public void execute(net.md_5.bungee.api.CommandSender commandSender, String[] args) {
        if(commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer proxiedPlayer = (ProxiedPlayer) commandSender;

            if(args.length == 0) {
                proxiedPlayer.sendMessage(new TextComponent(Bungeesystem.getPrefix() + "Du bist echt cool!"));
                proxiedPlayer.sendMessage(new TextComponent(Bungeesystem.getPrefix() + "Aber der Server ist viel cooler!"));
            }

        }
    }
}
