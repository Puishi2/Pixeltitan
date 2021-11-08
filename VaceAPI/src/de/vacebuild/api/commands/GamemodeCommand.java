package de.vacebuild.api.commands;

import de.vacebuild.api.main.VaceAPI;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    private VaceAPI plugin;

    public GamemodeCommand(VaceAPI plugin) {
        this.plugin = plugin;
    }

    @Deprecated
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.hasPermission("pixel.use")) {
                switch (args.length) {
                    case 1:
                        switch (args[0]) {
                            case "0":
                                player.setGameMode(GameMode.SURVIVAL);
                                player.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt im §bÜberlebensmodus§8.");
                                plugin.getTitleAPI().sendTitle(player, 5, 30, 5, "§8» §bGamemode", "§7Überleben");
                                break;

                            case "1":
                                player.setGameMode(GameMode.CREATIVE);
                                player.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt im §bKreativmodus§8.");
                                plugin.getTitleAPI().sendTitle(player, 5, 30, 5, "§8» §bGamemode", "§7Kreativ");
                                break;

                            case "2":
                                player.setGameMode(GameMode.ADVENTURE);
                                player.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt im §bAbenteuermodus§8.");
                                plugin.getTitleAPI().sendTitle(player, 5, 30, 5, "§8» §bGamemode", "§7Abenteuer");
                                break;

                            case "3":
                                player.setGameMode(GameMode.SPECTATOR);
                                player.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt im §bZuschauermodus§8.");
                                plugin.getTitleAPI().sendTitle(player, 5, 30, 5, "§8» §bGamemode", "§7Zuschauer");
                                break;

                            default:
                                player.sendMessage(plugin.getData().getPrefix() + "§7Verwende §b/gamemode <0 §8- §b1 §8- §b2 §8- §b3>");
                                break;
                        }
                        break;

                    case 2:
                        if (player.hasPermission("vacebuild.admin")) {
                            Player target = plugin.getServer().getPlayerExact(args[1]);

                            try {
                                switch (args[0]) {
                                    case "0":
                                        target.setGameMode(GameMode.SURVIVAL);
                                        target.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt im §bÜberlebensmodus§8.");
                                        player.sendMessage(plugin.getData().getPrefix() + "§b" + target.getName() + " §7ist jetzt im §bÜberlebensmodus§8.");
                                        plugin.getTitleAPI().sendTitle(target, 5, 30, 5, "§8» §bGamemode", "§7Überleben");
                                        break;

                                    case "1":
                                        target.setGameMode(GameMode.CREATIVE);
                                        target.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt im §bKreativmodus§8.");
                                        player.sendMessage(plugin.getData().getPrefix() + "§b" + target.getName() + " §7ist jetzt im §bKreativmodus§8.");
                                        plugin.getTitleAPI().sendTitle(target, 5, 30, 5, "§8» §bGamemode", "§7Kreativ");
                                        break;

                                    case "2":
                                        target.setGameMode(GameMode.ADVENTURE);
                                        target.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt im §bAbenteuermodus§8.");
                                        player.sendMessage(plugin.getData().getPrefix() + "§b" + target.getName() + " §7ist jetzt im §bAbenteuermodus§8.");
                                        plugin.getTitleAPI().sendTitle(target, 5, 30, 5, "§8» §bGamemode", "§7Abenteuer");
                                        break;

                                    case "3":
                                        target.setGameMode(GameMode.SPECTATOR);
                                        target.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt im §bZuschauermodus§8.");
                                        player.sendMessage(plugin.getData().getPrefix() + "§b" + target.getName() + " §7ist jetzt im §bZuschauermodus§8.");
                                        plugin.getTitleAPI().sendTitle(target, 5, 30, 5, "§8» §bGamemode", "§7Zuschauer");
                                        break;

                                    default:
                                        player.sendMessage(plugin.getData().getPrefix() + "§7Verwende §b/gamemode <0 §8- §b1 §8- §b2 §8- §b3> <Spieler>");
                                        break;
                                }
                            } catch (NullPointerException e) {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7ist nicht online§8.");
                            }
                            break;
                        } else {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Dafür hast du keine §bRechte§8.");
                        }

                    default:
                        player.sendMessage(plugin.getData().getPrefix() + "§7Verwende §b/gamemode <0 §8- §b1 §8- §b2 §8- §b3>");
                        break;
                }
            } else {
                plugin.getData().noPerms(player);
            }
        } else {
            plugin.getData().noPlayer();
        }

        return false;
    }


}
