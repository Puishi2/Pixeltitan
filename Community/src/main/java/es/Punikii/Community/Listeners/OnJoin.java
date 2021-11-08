package es.Punikii.Community.Listeners;

import java.util.ArrayList;

import es.Punikii.Community.Community;
import es.Punikii.Community.Manager.ItemBuilder;
import es.Punikii.Community.Manager.LocationManager;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.ScoreboardManager;

public class OnJoin implements Listener {

    private LocationManager locationManager = new LocationManager();

    public static ArrayList<Player> build = new ArrayList<Player>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        LocationManager lm = new LocationManager();
        //Community.getInstance().getScoreBoardManager().createScoreboard(p);

        e.setJoinMessage("");

        ItemStack Navigator = new ItemStack(Material.COMPASS);
        ItemMeta NavMeta = Navigator.getItemMeta();
        NavMeta.setDisplayName("§8» §eNavigator");
        Navigator.setItemMeta(NavMeta);

        ItemStack Verstecken = new ItemStack(Material.BLAZE_ROD);
        ItemMeta VersteckMeta = Verstecken.getItemMeta();
        VersteckMeta.setDisplayName("§8» §dSpieler Verstecken");
        Verstecken.setItemMeta(VersteckMeta);

        ItemStack Leave = new ItemStack(Material.MAGMA_CREAM);
        ItemMeta LeaveMeta = Leave.getItemMeta();
        LeaveMeta.setDisplayName("§8» §cVerlassen");
        Leave.setItemMeta(LeaveMeta);

        try {
            p.teleport(locationManager.getLocation("Spawn"));
        } catch (Exception ex) {
            p.sendMessage(Community.prefix + "Bitte Setzt den Spawn§8!");
        }

        p.setHealth(20);
        p.setFoodLevel(20);
        p.setGameMode(GameMode.SURVIVAL);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        p.getInventory().setItem(0, Navigator);
        p.getInventory().setItem(4, Verstecken);
        p.getInventory().setItem(8, Leave);

        if (p.hasPermission("pixel.hadmin")) {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(170, 0, 0)).setName("§8» §7Rang §8┃ §4HeadAdmin").toItemStack());
        } else if (p.hasPermission("pixel.admin")) {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(170, 0, 0)).setName("§8» §7Rang §8┃ §4Admin").toItemStack());
        } else if (p.hasPermission("pixel.cadmin")) {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(170, 0, 0)).setName("§8» §7Rang §8┃ §4CAdmin").toItemStack());
        } else if (p.hasPermission("pixel.srdev")) {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(85, 255, 255)).setName("§8» §7Rang §8┃ §bSrDeveloper").toItemStack());
        } else if (p.hasPermission("pixel.dev")) {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(85, 255, 255)).setName("§8» §7Rang §8┃ §bDeveloper").toItemStack());
        } else if (p.hasPermission("pixel.content")) {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(85, 255, 255)).setName("§8» §7Rang §8┃ §bContent").toItemStack());
        } else if (p.hasPermission("pixel.srmod")) {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(255, 85, 85)).setName("§8» §7Rang §8┃ §cSrModerator").toItemStack());
        } else if (p.hasPermission("pixel.mod")) {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(255, 85, 85)).setName("§8» §7Rang §8┃ §cModerator").toItemStack());
        } else if (p.hasPermission("pixel.sup")) {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(255, 85, 85)).setName("§8» §7Rang §8┃ §9Supporter").toItemStack());
        } else if (p.hasPermission("pixel.srbuilder")) {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(0, 170, 170)).setName("§8» §7Rang §8┃ §3SrBuilder").toItemStack());
        } else if (p.hasPermission("pixel.builder")) {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(0, 170, 170)).setName("§8» §7Rang §8┃ §3Builder").toItemStack());
        } else if (p.hasPermission("pixel.youtube")) {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(170, 0, 170)).setName("§8» §7Rang §8┃ §5YouTuber").toItemStack());
        } else if (p.hasPermission("pixel.pixel")) {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(85, 255, 85)).setName("§8» §7Rang §8┃ §aPixel").toItemStack());
        } else if (p.hasPermission("pixel.premium")) {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(255, 170, 0)).setName("§8» §7Rang §8┃ §6Premium").toItemStack());
        } else {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setUnbrak().setLeatherArmorColor(Color.fromRGB(170, 170, 170)).setName("§8» §7Rang §8┃ §7Spieler").toItemStack());
        }
    }
}