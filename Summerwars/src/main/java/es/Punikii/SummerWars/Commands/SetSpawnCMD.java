package es.Punikii.SummerWars.Commands;

import es.Punikii.SummerWars.Managers.LocationManager;
import es.Punikii.SummerWars.SummerWars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCMD implements CommandExecutor {

    private SummerWars plugin;

    public SetSpawnCMD(SummerWars main) {
        // TODO Auto-generated constructor stub
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        // TODO Auto-generated method stub
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(p.hasPermission("pixel.admin")) {
                if(args.length == 0) {

                    LocationManager.setLocation("Spawn", p.getLocation());

                    LocationManager.saveLocations();

                    p.sendMessage("§7Du hast den §eSpawn §7gesetzt!");
                } else {
                    p.sendMessage("§7Nutze: §e/set");
                }
            } else {
                p.sendMessage("§cDazu hast du keine Berechtigung!");
            }
        }
        return true;
    }

}