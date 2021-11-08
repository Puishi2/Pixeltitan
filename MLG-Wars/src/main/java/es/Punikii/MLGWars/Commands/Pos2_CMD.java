package es.Punikii.MLGWars.Commands;

import es.Punikii.MLGWars.Locations.LocationManager;
import es.Punikii.MLGWars.MLGWars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pos2_CMD implements CommandExecutor {

    private MLGWars plugin;

    public Pos2_CMD(MLGWars MLGWars) {
        // TODO Auto-generated constructor stub
        this.plugin = MLGWars;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        // TODO Auto-generated method stub
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(p.hasPermission("community.admin")) {
                if(args.length == 0) {

                    LocationManager.setLocation("Pos2", p.getLocation());

                    LocationManager.saveLocations();

                    p.sendMessage(MLGWars.prefix + "§7Du hast den §czweiten Spawn §7gesetzt!");
                } else {
                    p.sendMessage(MLGWars.prefix + "§7Nutze: §c/setpos2");
                }
            } else {
                p.sendMessage(MLGWars.prefix + "§cDazu hast du keine Berechtigung!");
            }
        }
        return true;
    }

}