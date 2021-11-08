package de.Puishi.SkyWars.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 24.05.2020 / 20:38                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class ActionBar {

    public static void setActionBar(Player p, String msg) {
        PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
        IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(chat, (byte) 2);
        connection.sendPacket(packetPlayOutChat);
    }

    public static void setTitle(Player p, String title, String subtitle, int fadein, int stay, int fadeout) {
        PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
        IChatBaseComponent Ititle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
        IChatBaseComponent Isub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
        PacketPlayOutTitle titletime = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, Ititle, fadein, stay, fadeout);
        PacketPlayOutTitle subtitletime = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, Isub);
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, Ititle);
        PacketPlayOutTitle subPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, Isub);
        connection.sendPacket(titletime);
        connection.sendPacket(subtitletime);
        connection.sendPacket(titlePacket);
        connection.sendPacket(subPacket);
    }

}
