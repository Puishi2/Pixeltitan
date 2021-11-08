package de.vacebuild.trial.utils;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import de.dytanic.cloudnet.lib.player.permission.PermissionGroup;
import de.vacebuild.api.main.VaceAPI;
import de.vacebuild.trial.main.Trialsystem;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.UUID;

public class ScoreboardManager {

    private Trialsystem plugin;
    public HashMap<Player, Scoreboard> boards = new HashMap<>();

    public ScoreboardManager(Trialsystem plugin) {
        this.plugin = plugin;
    }

    public void createScoreboard(Player player) {
        Scoreboard scoreboard = plugin.getServer().getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("aaa", "bbb");
        String server = CloudServer.getInstance().getServerProcessMeta().getServiceId().getServerId();

        Team admin = scoreboard.registerNewTeam("a");
        Team builder = scoreboard.registerNewTeam("c");
        Team dev = scoreboard.registerNewTeam("d");
        Team team = scoreboard.registerNewTeam("e");
        Team bewerber = scoreboard.registerNewTeam("k");

        admin.setPrefix("§4Leitung §8┃ §7");
        builder.setPrefix("§3Builder §8┃ §7");
        dev.setPrefix("§bDev §8┃ §b");
        team.setPrefix("§cTeam §8┃ §7");
        bewerber.setPrefix("§aApply §8┃ §7");

        for (Player all : plugin.getServer().getOnlinePlayers()) {
            if (!getTeam(all, scoreboard).hasEntry(all.getName())) {
                getTeam(all, scoreboard).addEntry(all.getName());
            }
        }

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§8» §3Vorbauen");
        objective.getScore("§8§m----------------").setScore(15);

        objective.getScore("§1").setScore(14);

        objective.getScore("§8× §7Online§8:").setScore(13);

        Team online = scoreboard.registerNewTeam("online");
        online.setPrefix(" §8» §b");
        online.setSuffix("§b" + CloudAPI.getInstance().getServerInfo(server).getOnlineCount());
        online.addEntry("§a");
        objective.getScore("§a").setScore(12);

        objective.getScore("§2").setScore(11);

        objective.getScore("§8× §7Besitzer§8:").setScore(10);
        Team owner = scoreboard.registerNewTeam("owner");
        owner.setPrefix(" §8» §b");
        owner.setSuffix(getWorldOwner(player));
        owner.addEntry("§b");
        objective.getScore("§b").setScore(9);

        objective.getScore("§3").setScore(8);

        objective.getScore("§8× §7Thema§8:").setScore(7);
        Team topic = scoreboard.registerNewTeam("topic");
        topic.setPrefix(" §8» §b");
        topic.setSuffix(getTopic(player));
        topic.addEntry("§c");
        objective.getScore("§c").setScore(6);

        objective.getScore("§4").setScore(5);

        objective.getScore("§8× §7Status§8:").setScore(4);
        Team status = scoreboard.registerNewTeam("status");
        status.setPrefix(" §8» §b");
        status.setSuffix(getWorldStatus(player));
        status.addEntry("§d");
        objective.getScore("§d").setScore(3);

        objective.getScore("§5").setScore(2);

        objective.getScore("§a§8§m----------------").setScore(1);

        boards.put(player, scoreboard);
        player.setScoreboard(scoreboard);
    }

    public void updateScoreboard(Player player) {
        Scoreboard scoreboard = boards.get(player);
        String server = CloudServer.getInstance().getServerProcessMeta().getServiceId().getServerId();

        for (Player all : plugin.getServer().getOnlinePlayers()) {
            if (!getTeam(all, scoreboard).hasEntry(all.getName())) {
                getTeam(all, scoreboard).addEntry(all.getName());
            }
        }

        scoreboard.getTeam("online").setSuffix("§b" + CloudAPI.getInstance().getServerInfo(server).getOnlineCount());
        scoreboard.getTeam("owner").setSuffix(getWorldOwner(player));
        scoreboard.getTeam("status").setSuffix(getWorldStatus(player));

        boards.put(player, scoreboard);
        player.setScoreboard(scoreboard);
    }

    private String getWorldOwner(Player player) {
        if (player.getWorld().getName().contains("Worlds//") && VaceAPI.getApi().getPlayerAPI()
                .isPlayerExists(UUID.fromString(player.getWorld().getName().replace("Worlds//", "")))) {
            UUID uuid = UUID.fromString(player.getWorld().getName().replace("Worlds//", ""));

            return VaceAPI.getApi().getPlayerAPI().getName(uuid);
        } else {
            return "§cNicht gefunden";
        }
    }

    private String getTopic(Player player) {
        if (player.getWorld().getName().contains("Worlds//") && VaceAPI.getApi().getPlayerAPI()
                .isPlayerExists(UUID.fromString(player.getWorld().getName().replace("Worlds//", "")))) {
            UUID uuid = UUID.fromString(player.getWorld().getName().replace("Worlds//", ""));

            System.out.println(uuid);

            if (plugin.getMySQL().isPlayerExists(uuid)) {
                return plugin.getMySQL().getTopic(uuid);
            } else {
                return "§cNicht gefunden";
            }
        } else {
            return "§cNicht gefunden";
        }
    }

    private String getWorldStatus(Player player) {
        if (player.getWorld().getName().contains("Worlds//") && VaceAPI.getApi().getPlayerAPI()
                .isPlayerExists(UUID.fromString(player.getWorld().getName().replace("Worlds//", "")))) {
            UUID uuid = UUID.fromString(player.getWorld().getName().replace("Worlds//", ""));

            if (!isFinished(uuid)) {
                if (plugin.getMySQL().getStatus(uuid) == 0) {
                    return "§6In Bau";
                } else if (plugin.getMySQL().getStatus(uuid) == 1) {
                    return "§eIn Bewertung";
                } else if (plugin.getMySQL().getStatus(uuid) == 2) {
                    return "§aAngenommen";
                } else {
                    return "§cAbgelehnt";
                }
            } else {
                return "§aAngenommen";
            }
        } else {
            return "§cNicht gefunden";
        }
    }

    public boolean isFinished(UUID uuid) {
        String group = CloudServer.getInstance().getServerProcessMeta().getServiceId().getGroup();

        if (CloudAPI.getInstance().getOnlinePlayer(uuid) != null) {
            CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(uuid);
            PermissionGroup permissionGroup = cloudPlayer.getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool());

            return !permissionGroup.getName().equalsIgnoreCase(group);
        } else {
            OfflinePlayer offlinePlayer = CloudAPI.getInstance().getOfflinePlayer(uuid);
            PermissionGroup permissionGroup = offlinePlayer.getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool());

            return !permissionGroup.getName().equalsIgnoreCase(group);
        }
    }

    private Team getTeam(Player player, Scoreboard scoreboard) {
        if (player.hasPermission("pb.admin")) {
            return scoreboard.getTeam("a");
        } else if(player.hasPermission("pb.builder")) {
            return scoreboard.getTeam("c");
        } else if(player.hasPermission("pb.dev")) {
            return scoreboard.getTeam("d");
        } else if(player.hasPermission("pb.team")) {
            return scoreboard.getTeam("e");
        } else {
            return scoreboard.getTeam("k");
        }
    }

}
