package de.Puishi.Bungeesystem.commands;

import de.Puishi.Bungeesystem.Bungeesystem;
import de.dytanic.cloudnet.api.CloudAPI;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 17.06.2020 / 15:41                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class JoinMe_Command extends Command {

    public JoinMe_Command(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer proxiedPlayer = (ProxiedPlayer) commandSender;

            TextComponent textComponent = new TextComponent();

            if(proxiedPlayer.hasPermission("pixeltitan.joinme")) {
                if(strings.length == 0) {
                    ProxyServer.getInstance().broadcast("§8§m-------------------------------");
                    ProxyServer.getInstance().broadcast(" ");
                    ProxyServer.getInstance().broadcast("§8» §aJoinMe §8┃ "+ CloudAPI.getInstance().getOfflinePlayer(proxiedPlayer.getName()).getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool()).getColor() + proxiedPlayer.getName() + " §7spielt auf §3" + proxiedPlayer.getServer().getInfo().getName());
                    textComponent.setText("§8» §aJoinMe §8┃ §b§lKlicke hier, um nachzujoinen!");
                    textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gehezu " + proxiedPlayer.getName()));
                    ProxyServer.getInstance().broadcast(textComponent);
                    ProxyServer.getInstance().broadcast(" ");
                    ProxyServer.getInstance().broadcast("§8§m-------------------------------");
                }
            }

        }
    }
}
