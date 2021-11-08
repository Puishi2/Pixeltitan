package de.Puishi.Lobby.commands;

import de.Puishi.Lobby.Main;
import de.Puishi.Lobby.utils.Base64;
import de.Puishi.Lobby.utils.Data;
import de.Puishi.Lobby.utils.ItemBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 20.05.2020 / 20:00                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Command_Lottery implements CommandExecutor {

    public static ArrayList<String> lotterylist = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("pixel.lottery")) {
                if(args.length == 0) {
                    if(!lotterylist.contains(player.getName())) {
                        lotterylist.add(player.getName());
                        player.getInventory().clear();
                        player.getInventory().setItem(4, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/7e285c5c85599437ac42e27f4921c805aed1dc5774f952a27ea7e8c9abe6e")).setName("§8» §6Lottery").toItemStack());
                    }
                }
            }
        }
        return false;
    }
}
