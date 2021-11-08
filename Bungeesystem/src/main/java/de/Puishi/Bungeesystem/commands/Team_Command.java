package de.Puishi.Bungeesystem.commands;

import de.Puishi.Bungeesystem.Bungeesystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 11.06.2020 / 11:47                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Team_Command extends Command {

    public Team_Command(String team) {
        super("team");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            player.sendMessage(new TextComponent(Bungeesystem.getInstance().getPrefix() + "| §eTeam-Liste §7|"));
            player.sendMessage(new TextComponent(" "));
            player.sendMessage(new TextComponent("§8» §4Head-Admins §8┃ §4Musikalischer§8, §4Angeflux"));
            player.sendMessage(new TextComponent(" "));
            player.sendMessage(new TextComponent("§8» §4Admins §8┃ §4Yanatani§8, §4flux187§8, §4Puishi"));
            player.sendMessage(new TextComponent(" "));
            player.sendMessage(new TextComponent("§8» §4Community-Admin §8┃ §4Whoms88"));
            player.sendMessage(new TextComponent(" "));
            player.sendMessage(new TextComponent("§8» §bSrDeveloper §8┃ §bJavaArray"));
            player.sendMessage(new TextComponent(" "));
            player.sendMessage(new TextComponent("§8» §bDeveloper §8┃ §bPunikii§8, §bLuccboy"));
            player.sendMessage(new TextComponent(" "));
            player.sendMessage(new TextComponent("§8» §bSrContent §8┃ §bHeliumdioxid"));
            player.sendMessage(new TextComponent(" "));
            player.sendMessage(new TextComponent("§8» §bContents §8┃ §bElias_1507"));
            player.sendMessage(new TextComponent(" "));
            player.sendMessage(new TextComponent("§8» §cSrModeratoren §8┃ §cMusikalische§8, §cRav3ntZ"));
            player.sendMessage(new TextComponent(" "));
            player.sendMessage(new TextComponent("§8» §cModeratoren §8┃ §ccalledKathi§8, §cTheSision§8, §cwechseln§8, §cDragon6806"));
            player.sendMessage(new TextComponent(" "));
            player.sendMessage(new TextComponent("§8» §9Supporter §8┃ §9CroxxOP§8, §9Krazy_Zocker§8, §9unsympathisch§8, §9gamingcst§8, §9Ueberlektriker"));
            player.sendMessage(new TextComponent(" "));
            player.sendMessage(new TextComponent("§8» §3SrBuilder §8┃ §3Lhydrac"));
            player.sendMessage(new TextComponent(" "));
            player.sendMessage(new TextComponent("§8» §3Builder §8┃ §3---"));
            player.sendMessage(new TextComponent(" "));
        }
    }


}
