package es.Punikii.Community.Commands;

import es.Punikii.Community.Community;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCMD implements CommandExecutor {

    private Community plugin;

    public FlyCMD(Community Community) {
        // TODO Auto-generated constructor stub
        this.plugin = Community;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        // TODO Auto-generated method stub
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(p.hasPermission("community.fly")) {
                if (p.isFlying() ) {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    p.sendMessage(Community.prefix + "Du kannst nicht mehr fliegen!");
                    return true;
                } else {
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.sendMessage(Community.prefix + "Du kannst nun fliegen!");
                    return true;
                }
            } else {
                p.sendMessage(Community.prefix + "§cDazu hast du keine Berechtigung!");
                return true;
            }
        }
        return false;
    }

}