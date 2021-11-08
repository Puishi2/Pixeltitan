package es.Punikii.SummerWars.Events;

import java.io.EOFException;

import es.Punikii.SummerWars.Managers.LocationManager;
import es.Punikii.SummerWars.SummerWars;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class onJoin implements Listener {

    public SummerWars plugin;

    public onJoin(SummerWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        LocationManager lm = new LocationManager();
        SummerWars.getInstance().getScoreBoardManager().createScoreboard(p);

        e.setJoinMessage("§7[§a+§7] §7" + e.getPlayer().getName());

        ItemStack Pistole = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta PisMeta = Pistole.getItemMeta();
        PisMeta.setDisplayName("§8» ");
        Pistole.setItemMeta(PisMeta);
        Pistole.setDurability((short) 7);
        try {
            p.teleport(LocationManager.getLocation("Spawn"));
        } catch (Exception ex) {
            p.sendMessage("Bitte Setzt den Spawn§8!");
        }
        ItemStack Kit = new ItemStack(Material.CAKE);
        ItemMeta KitMeta = Kit.getItemMeta();
        KitMeta.setDisplayName("§8» §dStats");
        Kit.setItemMeta(KitMeta);

        ItemStack Leave = new ItemStack(Material.MAGMA_CREAM);
        ItemMeta LeaveMeta = Kit.getItemMeta();
        LeaveMeta.setDisplayName("§8» §cLeave");
        Leave.setItemMeta(LeaveMeta);


        p.setHealth(20D);
        p.setFoodLevel(20);
        p.setGameMode(GameMode.SURVIVAL);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        p.getInventory().setItem(0, Pistole);
        p.getInventory().setItem(1, Pistole);
        p.getInventory().setItem(2, Pistole);
        p.getInventory().setItem(3, Pistole);
        p.getInventory().setItem(4, Kit);
        p.getInventory().setItem(5, Pistole);
        p.getInventory().setItem(6, Pistole);
        p.getInventory().setItem(7, Pistole);
        p.getInventory().setItem(8, Leave);
    }
}