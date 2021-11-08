package de.vacebuild.trial.utils;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EditWorldEvent extends Event {

    public EditWorldEvent() {
    }

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
