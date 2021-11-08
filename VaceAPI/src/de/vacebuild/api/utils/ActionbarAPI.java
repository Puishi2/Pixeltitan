package de.vacebuild.api.utils;

import de.vacebuild.api.main.VaceAPI;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionbarAPI {

    private VaceAPI plugin;

    public ActionbarAPI(VaceAPI plugin) {
        this.plugin = plugin;
    }

    public void sendActionBar(Player player, String mesage) {
        String actionBar = mesage.replace("_", " ");
        String string = ChatColor.translateAlternateColorCodes('&', actionBar);

        IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + string + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(iChatBaseComponent, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet<?>) packet);
    }

    private class PacketPlayOutChat {
        public PacketPlayOutChat(IChatBaseComponent iChatBaseComponent, byte b) {
        }
    }
}
