package es.Punikii.Community.Listeners;

import es.Punikii.Community.Community;
import es.Punikii.Community.Manager.ItemBuilder;
import es.Punikii.Community.Manager.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class Navigator implements Listener{

    private final String GUI_NAME = "§8» §5Navigator";

    private LocationManager locationManager = new LocationManager();

    public void openGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9*3, GUI_NAME);
        inventory.setItem(13, new ItemBuilder(Material.NETHER_STAR).setName("§8» §6Spawn").toItemStack());
        inventory.setItem(5, new ItemBuilder(Material.NOTE_BLOCK).setName("§8» §dBühne").toItemStack());
        inventory.setItem(3, new ItemBuilder(Material.ITEM_FRAME).setName("§8» §aScreenBoxen").toItemStack());
        inventory.setItem(20, new ItemBuilder(Material.BARRIER).setName("§8» §cComing Soon!").toItemStack());
        inventory.setItem(24, new ItemBuilder(Material.MAGMA_CREAM).setName("§8» §aTeamübersicht").toItemStack());
        inventory.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(11, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(12, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(14, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(15, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(22, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(23, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
        if(player.hasPermission("pixel.tb"))
            inventory.setItem(22, new ItemBuilder(Material.GOLD_INGOT).setName("§8» §aTeambesprechung").toItemStack());

        player.openInventory(inventory);
    }

    @EventHandler
    public void handleNavigatorOpener(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getItem().getType() == Material.COMPASS) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
            openGUI(event.getPlayer());
        }
    }

    @EventHandler
    public void handleNavigatorGUIClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if(event.getClickedInventory().getTitle().equals(GUI_NAME)) {
            event.setCancelled(true);
            switch(event.getCurrentItem().getType()) {
                case NETHER_STAR:
                    player.sendMessage(Community.prefix + "Du wurdest zum Spawn teleportiert!");
                    player.teleport(locationManager.getLocation("Spawn"));
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    player.closeInventory();
                    break;

                case NOTE_BLOCK:
                    player.sendMessage(Community.prefix + "Du wurdest zur Bühne teleportiert!");
                    player.teleport(locationManager.getLocation("Buehne"));
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    player.closeInventory();
                    break;

                case ITEM_FRAME:
                    player.sendMessage(Community.prefix + "Du wurdest zur Screenbox teleportiert!");
                    player.teleport(locationManager.getLocation("Screen"));
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    player.closeInventory();
                    break;

                case BARRIER:
                    player.sendMessage(Community.prefix + "Das kommt §cbald! §7:)");
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    player.closeInventory();
                    break;

                case MAGMA_CREAM:
                    player.sendMessage(Community.prefix + "Du wurdest zur Teamübersicht teleportiert!");
                    player.teleport(locationManager.getLocation("Team"));
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    player.closeInventory();
                    break;
                case GOLD_INGOT:
                    player.sendMessage(Community.prefix + "Du wurdest zu der Teambesprechung teleportiert!");
                    player.teleport(locationManager.getLocation("TB"));
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    player.closeInventory();
                    break;

                default:

                    break;
            }
        }
    }

}