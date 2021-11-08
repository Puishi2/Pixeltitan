package de.vacebuild.trial.listener;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.permission.PermissionGroup;
import de.vacebuild.trial.main.Trialsystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncChatListener implements Listener {

    private Trialsystem plugin;

    public AsyncChatListener(Trialsystem plugin) {
        this.plugin = plugin;
    }

    @Deprecated
    @EventHandler
    public void handleChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage().replace("%", "%%");

        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());
        PermissionGroup group = cloudPlayer.getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool());

        if (player.hasPermission("pb.admin")) {
            message = message.replace("&", "§");
        }

        event.setFormat(group.getPrefix() + player.getName() + " §8» §7" + message);
    }

}
