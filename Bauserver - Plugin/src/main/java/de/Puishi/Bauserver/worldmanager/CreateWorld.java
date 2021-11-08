package de.Puishi.Bauserver.worldmanager;

import de.Puishi.Bauserver.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 19.06.2020 / 10:31                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class CreateWorld implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if(args.length == 1) {
                String worldname = args[0];
                player.sendMessage(Data.getPREFIX() + "Erstelle Welt...");
                WorldCreator worldCreator = new WorldCreator(worldname);
                worldCreator.generator(new CleanWorldGenerator());
                worldCreator.createWorld();
                //Bukkit.createWorld(WorldCreator.name(worldname).generator(new CleanWorldGenerator()));
                //Bukkit.createWorld(WorldCreator.name(worldname).type(WorldType.FLAT).generateStructures(false).environment(World.Environment.NORMAL));
                player.sendMessage(Data.getPREFIX() + "Die Welt §3" + worldname + " §7wurde erstellt!");
                player.sendMessage(Data.getPREFIX() + "Benutze §3/world tp " + worldname + " §7um zu der Welt zu kommen!");
            } else {
                player.sendMessage(Data.getPREFIX() + "Benutze §3/createworld <Name>§7!");
            }

        }
        return false;
    }
}
