package es.Punikii.Community.Commands;

import es.Punikii.Community.Community;
import es.Punikii.Community.Manager.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class seteffect1CMD implements CommandExecutor {

    private Community plugin;

    private LocationManager locationManager = new LocationManager();

    public seteffect1CMD(Community Community) {
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

                    LocationManager.setLocation("Effect1", p.getLocation());
                    LocationManager.saveLocations();

                    p.sendMessage(Community.prefix + "§7Du hast den §eersten Spawn fuer die Partikel §7gesetzt!");
                } else {
                    p.sendMessage("§7Nutze: §e/seteffect1");
                }
            } else {
                p.sendMessage("§cDazu hast du keine Berechtigung!");
            }
        }
        return true;
    }

}