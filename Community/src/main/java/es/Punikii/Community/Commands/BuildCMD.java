package es.Punikii.Community.Commands;

import es.Punikii.Community.Community;
import es.Punikii.Community.Listeners.OnJoin;
import es.Punikii.Community.Manager.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCMD implements CommandExecutor {

    private Community plugin;

    public BuildCMD(Community Community) {
        // TODO Auto-generated constructor stub
        this.plugin = Community;
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
                        p.sendMessage(Community.prefix+"§7Du bist nun nicht mehr im §eBuild modus");
                        p.setGameMode(GameMode.SURVIVAL);
                        p.playSound(p.getLocation(), Sound.FIREWORK_BLAST, 2, 3);
                        p.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setName("§8» §eNavigator").toItemStack());
                        p.getInventory().setItem(4, new ItemBuilder(Material.BLAZE_ROD).setName("§8» §dSpieler Verstecken").toItemStack());
                        p.getInventory().setItem(8, new ItemBuilder(Material.MAGMA_CREAM).setName("§8» §cVerlassen").toItemStack());
                    } else {
                        OnJoin.build.add(p);
                        p.sendMessage(Community.prefix+"§7Du bist nun im §eBuild modus");
                        p.getInventory().clear();
                        p.playSound(p.getLocation(), Sound.FIREWORK_BLAST, 2, 3);
                        p.setGameMode(GameMode.CREATIVE);
                    }
                } else {
                    p.sendMessage(Community.prefix+"§7Nutze: §e/build");
                }

            } else {
                p.sendMessage(Community.prefix+"§cDazu hast du keine Berechtigung!");
            }
        }
        return true;
    }

}