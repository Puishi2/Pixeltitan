package es.Punikii.Community.Commands;

import es.Punikii.Community.Community;
import es.Punikii.Community.Manager.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetScreenCMD implements CommandExecutor {

    private LocationManager locationManager = new LocationManager();

    private Community plugin;

    public SetScreenCMD(Community Community) {
        // TODO Auto-generated constructor stub
        this.plugin = Community;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        // TODO Auto-generated method stub
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(p.hasPermission("pixel.hadmin")) {
                if(args.length == 0) {

                    LocationManager.setLocation("Screen", p.getLocation());
                    LocationManager.saveLocations();

                    p.sendMessage(Community.prefix + "§7Du hast den §eSpawn fuer die ScreenBox §7gesetzt!");
                } else {
                    p.sendMessage(Community.prefix + "§7Nutze: §e/setscreen");
                }
            } else {
                p.sendMessage(Community.prefix + "§cDazu hast du keine Berechtigung!");
            }
        }
        return true;
    }

}