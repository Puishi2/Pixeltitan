package de.vacebuild.trial.commands;

import de.vacebuild.trial.main.Trialsystem;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FinishCommand implements CommandExecutor {

    private Trialsystem plugin;

    public FinishCommand(Trialsystem plugin) {
        this.plugin = plugin;
    }

    @Deprecated
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (plugin.getMySQL().hasWorld(player.getUniqueId())) {
                    if (plugin.getMySQL().getStatus(player.getUniqueId()) != 1) {
                        plugin.getInventoryManager().openConfirmInv(player, "Welt abgeben", Sound.CHEST_OPEN);
                    } else {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Deine §bWelt §7ist bereits zur Bewertung abgegeben§8.");
                    }
            } else {
                player.sendMessage(plugin.getData().getPrefix() + "§7Du hast noch keine §bWelt§8.");
            }
        } else {
            plugin.getData().noPlayer();
        }

        return false;
    }

}
