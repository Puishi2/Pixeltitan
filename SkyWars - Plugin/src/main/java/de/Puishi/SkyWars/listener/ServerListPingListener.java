package de.Puishi.SkyWars.listener;

import de.Puishi.SkyWars.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

/**
 * Created by Luca on 09.06.2020.
 * Project: PixelTitan
 * © Copyright by JavaArray | Luca K.
 */

public class ServerListPingListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        event.setMaxPlayers(Main.getInstance().getMaxplayers());
        event.setMotd(Main.getInstance().getMap());
    }

}
