package es.Punikii.Community.Commands;

import es.Punikii.Community.Community;
import es.Punikii.Community.Manager.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCMD implements CommandExecutor {

    private LocationManager locationManager = new LocationManager();

    private Community plugin;

    public SetSpawnCMD(Community main) {
        // TODO Auto-generated constructor stub
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(p.hasPermission("pixel.hadmin")) {
                if(args.length == 0) {

                    LocationManager.setLocation("Spawn", p.getLocation());
                    LocationManager.saveLocations();

                    p.sendMessage(Community.prefix + "§7Du hast den §eSpawn §7gesetzt!");
                } else {
                    p.sendMessage(Community.prefix + "§7Nutze: §e/setspawn");
                }
            } else {
                p.sendMessage(Community.prefix + "§cDazu hast du keine Berechtigung!");
            }
        }
        return true;
    }

}