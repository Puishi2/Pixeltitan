package es.Punikii.MLGWars.Commands;

import es.Punikii.MLGWars.MLGWars;
import es.Punikii.MLGWars.Listeners.OnJoin;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Build_CMD implements CommandExecutor {

    private MLGWars plugin;

    public Build_CMD(MLGWars MLGWars) {
        // TODO Auto-generated constructor stub
        this.plugin = MLGWars;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        // TODO Auto-generated method stub
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(p.hasPermission("pixel.build")) {

                if(args.length == 0) {
                    if(OnJoin.build.contains(p)) {
                        OnJoin.build.remove(p);
                        p.sendMessage(MLGWars.prefix+"§7Du bist nun nicht mehr im §cBuild modus");
                        p.setGameMode(GameMode.SURVIVAL);
                        p.playSound(p.getLocation(), Sound.FIREWORK_BLAST, 2, 3);
                    } else {
                        OnJoin.build.add(p);
                        p.sendMessage(MLGWars.prefix+"§7Du bist nun im §cBuild modus");
                        p.getInventory().clear();
                        p.playSound(p.getLocation(), Sound.FIREWORK_BLAST, 2, 3);
                        p.setGameMode(GameMode.CREATIVE);
                    }
                } else {
                    p.sendMessage(MLGWars.prefix+"§7Nutze: §c/build");
                }

            } else {
                p.sendMessage(MLGWars.prefix+"§cDazu hast du keine Berechtigung!");
            }
        }
        return true;
    }

}
