package de.vacebuild.trial.listener;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.vacebuild.trial.main.Trialsystem;
import de.vacebuild.trial.utils.EditWorldEvent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class FinishListener implements Listener {

    private Trialsystem plugin;

    public FinishListener(Trialsystem plugin) {
        this.plugin = plugin;
    }

    @Deprecated
    @EventHandler
    public void handleClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() != null && event.getClickedInventory().getName().equalsIgnoreCase("§8» §bWelt abgeben")) {
            event.setCancelled(true);

            if (event.getCurrentItem().getType() == Material.STAINED_CLAY) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bFortfahren")) {
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                    if (plugin.getMySQL().hasWorld(player.getUniqueId())) {
                            if (plugin.getMySQL().getStatus(player.getUniqueId()) != 1) {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Deine §bWelt §7wurde erfolgreich zur Bewertung abgegeben§8.");

                                plugin.getMySQL().setStatus(player.getUniqueId(), 1);
                                plugin.getMySQL().finishedWorlds.add(player.getUniqueId().toString());

                                plugin.getScoreboardManager().updateScoreboard(player);
                                plugin.getServer().getPluginManager().callEvent(new EditWorldEvent());

                                ByteArrayDataOutput out = ByteStreams.newDataOutput();

                                out.writeUTF(player.getUniqueId().toString());

                                player.sendPluginMessage(plugin, "FinishWorld", out.toByteArray());
                            } else {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Deine §bWelt §7ist bereits zur Bewertung abgegeben§8.");
                            }
                    } else {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Du hast noch keine §bWelt§8.");
                    }
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bAbbrechen")) {
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                    player.sendMessage(plugin.getData().getPrefix() + "§7Der §bVorgang §7wurde abgebrochen§8.");
                }
            }
        }
    }

}
