package de.vacebuild.trial.main;

import org.bukkit.entity.Player;

public class Data {

    private Trialsystem plugin;
    private String prefix = "§8» §3Vorbauen §8┃ §7";
    private String noPerms = prefix + "§cDazu hast du keine Rechte!";
    private String noPlayer = prefix + "§cDu musst ein §bSpieler §7sein§8.";

    public Data(Trialsystem plugin) {
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
