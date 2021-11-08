package es.Punikii.Community.Listeners;

import es.Punikii.Community.Community;
import es.Punikii.Community.Manager.ItemBuilder;
import es.Punikii.Community.Manager.LocationManager;
import org.bukkit.*;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class SignEvent implements Listener{

    private Community plugin;

    private LocationManager locationManager = new LocationManager();

    public SignEvent(Community Community) {
        this.plugin = Community;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onSignChange(SignChangeEvent e) {

        if(e.getLine(0).equals("[Community]")) {
            e.setLine(0, "§8┃ §aCommunity §8┃");
        }
    }

    @EventHandler
    public void onPlayerInteractwithSign(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getClickedBlock().getState() instanceof Sign) {
                Sign s = (Sign) e.getClickedBlock().getState();
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);

                if(s.getLine(0).equalsIgnoreCase("§8┃ §aCommunity §8┃")) {

                    String lastLine = s.getLine(3);
                    Inventory inventory = Bukkit.createInventory(null, 9*3, "§8┃ §aEVENT §8┃");
                    inventory.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(9, new ItemBuilder(Material.EXP_BOTTLE).setName("§8» §eXP").toItemStack());
                    inventory.setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(11, new ItemBuilder(Material.SNOW_BALL).setName("§8» §bSchneeBaelle ").toItemStack());
                    inventory.setItem(12, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(13, new ItemBuilder(Material.FIREWORK).setName("§8» §6FeuerWerk").toItemStack());
                    inventory.setItem(14, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(15, new ItemBuilder(Material.FISHING_ROD).setName("§8» §9Angel").toItemStack());
                    inventory.setItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(17, new ItemBuilder(Material.SLIME_BALL).setName("§8» §aStandart Items wieder bekommen!").toItemStack());
                    inventory.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(20, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(22, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(23, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(24, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    p.openInventory(inventory);
                }
            }

        }
    }
    @EventHandler
    public void handleNavigatorGUIClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if(event.getClickedInventory().getTitle().equals("§8┃ §aEVENT §8┃")) {
            event.setCancelled(true);
            switch(event.getCurrentItem().getType()) {
                case FIREWORK:
                    player.sendMessage(Community.prefix + "Du hast FeuerWerk ausgewaehlt!");
                    player.getInventory().clear();
                    player.getInventory().setItem(4, new ItemBuilder(Material.FIREWORK, 999).setName("§8» §6FeuerWerk").toItemStack());
                    player.closeInventory();
                    break;
                case SLIME_BALL:
                    player.sendMessage(Community.prefix + "Du hast jetzt deine Standard Sachen wieder!");
                    player.getInventory().clear();
                    player.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setName("§8» §eNavigator").toItemStack());
                    player.getInventory().setItem(4, new ItemBuilder(Material.BLAZE_ROD).setName("§8» §dSpieler Verstecken").toItemStack());
                    player.getInventory().setItem(8, new ItemBuilder(Material.MAGMA_CREAM).setName("§8» §cVerlassen").toItemStack());
                    player.closeInventory();
                    break;
                case EXP_BOTTLE:
                    player.sendMessage(Community.prefix + "Du hast XP ausgewaehlt!");
                    player.getInventory().clear();
                    player.getInventory().setItem(4, new ItemBuilder(Material.EXP_BOTTLE, 999).setName("§8» §eXP").toItemStack());
                    player.closeInventory();
                    break;
                case SNOW_BALL:
                    player.sendMessage(Community.prefix + "Du hast die SchneeBaelle ausgewaehlt!");
                    player.getInventory().clear();
                    player.getInventory().setItem(4, new ItemBuilder(Material.SNOW_BALL, 999).setName("§8» §bSchneeBaeller").toItemStack());
                    player.closeInventory();
                    break;
                case FISHING_ROD:
                    player.sendMessage(Community.prefix + "Du hast die Angel ausgewaehlt!");
                    player.getInventory().clear();
                    player.getInventory().setItem(4, new ItemBuilder(Material.FISHING_ROD).setName("§8» §9Angel").toItemStack());
                    player.closeInventory();
                    break;

                default:
            }
        }
    }
    @EventHandler
    public void onSignBuehne(SignChangeEvent e) {

        if(e.getLine(0).equals("[Buehne]")) {
            e.setLine(0, "§8┃ §aBühne §8┃");
        }
    }

    @EventHandler
    public void onPlayerInteractwithSign2(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock().getState() instanceof Sign) {
                Sign s = (Sign) e.getClickedBlock().getState();
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);

                if (s.getLine(0).equalsIgnoreCase("§8┃ §aBühne §8┃")) {

                    String lastLine = s.getLine(3);

                    Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§8┃ §aBühne §8┃");
                    inventory.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(10, new ItemBuilder(Material.FIREBALL).setName("§8» §eFeuer").toItemStack());
                    inventory.setItem(11, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(12, new ItemBuilder(Material.BED).setName("§8» §cHerzen").toItemStack());
                    inventory.setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(14, new ItemBuilder(Material.WATER_BUCKET).setName("§8» §bWasser").toItemStack());
                    inventory.setItem(15, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(16, new ItemBuilder(Material.NOTE_BLOCK).setName("§8» §5Noten").toItemStack());
                    inventory.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(20, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(22, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(23, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(24, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack());
                    p.openInventory(inventory);
                }
            }
        }
    }
        @EventHandler
        public void handleNavigatorGUIClick2(InventoryClickEvent event) {
            if(!(event.getWhoClicked() instanceof Player)) return;
            Player player = (Player) event.getWhoClicked();
            if(event.getClickedInventory().getTitle().equals("§8┃ §aBühne §8┃")) {
                event.setCancelled(true);
                switch (event.getCurrentItem().getType()) {
                    case FIREBALL:
                        player.sendMessage(Community.prefix + "Du hast die FeuerPartikel ausgewaehlt!");
                        int scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Community.getInstance(), new Runnable() {
                            int effect = 1;

                            @Override
                            public void run() {
                                if (this.effect == 4) {
                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                        players.spigot().playEffect(locationManager.getLocation("Effect1"), Effect.FLAME, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 32, 15);
                                        players.spigot().playEffect(locationManager.getLocation("Effect2"), Effect.FLAME, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 32, 15);
                                        players.spigot().playEffect(locationManager.getLocation("Effect3"), Effect.FLAME, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 32, 15);
                                        players.spigot().playEffect(locationManager.getLocation("Effect4"), Effect.FLAME, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 32, 15);
                                    }
                                    this.effect = 1;
                                }
                                this.effect++;
                            }
                        }, 10L, 10L);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Community.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                Bukkit.getScheduler().cancelTask(scheduler);
                            }
                        }, 20 * 20L);
                        player.closeInventory();
                        break;
                    case BED:
                        player.sendMessage(Community.prefix + "Du hast die HerzenPartikel ausgewaehlt!");
                        int scheduler1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Community.getInstance(), new Runnable() {
                            int effect = 1;

                            @Override
                            public void run() {
                                if (this.effect == 4) {
                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                        players.spigot().playEffect(locationManager.getLocation("Effect1"), Effect.HEART, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 300, 15);
                                        players.spigot().playEffect(locationManager.getLocation("Effect2"), Effect.HEART, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 300, 15);
                                        players.spigot().playEffect(locationManager.getLocation("Effect3"), Effect.HEART, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 300, 15);
                                        players.spigot().playEffect(locationManager.getLocation("Effect4"), Effect.HEART, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 300, 15);
                                    }
                                    this.effect = 1;
                                }
                                this.effect++;
                            }
                        }, 10L, 10L);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Community.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                Bukkit.getScheduler().cancelTask(scheduler1);
                            }
                        }, 20 * 20L);
                        player.closeInventory();
                        break;
                    case WATER_BUCKET:
                        player.sendMessage(Community.prefix + "Du hast die WasserPartikel ausgewaehlt!");
                        int scheduler2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Community.getInstance(), new Runnable() {
                            int effect = 1;

                            @Override
                            public void run() {
                                if (this.effect == 4) {
                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                        players.spigot().playEffect(locationManager.getLocation("Effect1"), Effect.WATERDRIP, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 300, 15);
                                        players.spigot().playEffect(locationManager.getLocation("Effect2"), Effect.WATERDRIP, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 300, 15);
                                        players.spigot().playEffect(locationManager.getLocation("Effect3"), Effect.WATERDRIP, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 300, 15);
                                        players.spigot().playEffect(locationManager.getLocation("Effect4"), Effect.WATERDRIP, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 300, 15);
                                    }
                                    this.effect = 1;
                                }
                                this.effect++;
                            }
                        }, 10L, 10L);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Community.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                Bukkit.getScheduler().cancelTask(scheduler2);
                            }
                        }, 20 * 20L);
                        player.closeInventory();
                        break;
                    case NOTE_BLOCK:
                        player.sendMessage(Community.prefix + "Du hast die NotenPartikel ausgewaehlt!");
                        int scheduler3 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Community.getInstance(), new Runnable() {
                            int effect = 1;

                            @Override
                            public void run() {
                                if (this.effect == 4) {
                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                        players.spigot().playEffect(locationManager.getLocation("Effect1"), Effect.NOTE, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 300, 15);
                                        players.spigot().playEffect(locationManager.getLocation("Effect2"), Effect.NOTE, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 300, 15);
                                        players.spigot().playEffect(locationManager.getLocation("Effect3"), Effect.NOTE, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 300, 15);
                                        players.spigot().playEffect(locationManager.getLocation("Effect4"), Effect.NOTE, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 300, 15);
                                    }
                                    this.effect = 1;
                                }
                                this.effect++;
                            }
                        }, 10L, 10L);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Community.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                Bukkit.getScheduler().cancelTask(scheduler3);
                            }
                        }, 20 * 20L);
                        player.closeInventory();
                        break;
                }
        }
    }
}