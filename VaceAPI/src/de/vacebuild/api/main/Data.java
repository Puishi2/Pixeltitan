package de.vacebuild.api.main;

import org.bukkit.entity.Player;

public class Data {

    private VaceAPI plugin;
    private String prefix = "§8» §3Vorbauen §8┃ §7";
    private String noPerms = prefix + "§7Dieser §bBefehl §7wurde nicht gefunden§8.";
    private String noPlayer = prefix + "§7Du musst ein §bSpieler §7sein§8.";

    public Data(VaceAPI plugin) {
        this.plugin = plugin;
    }

    public String getPrefix() {
        return prefix;
    }

    public void noPerms(Player player) {
        player.sendMessage(this.noPerms);
    }

    public void noPlayer() {
        plugin.getServer().getConsoleSender().sendMessage(this.noPlayer);
    }

}
