package de.Puishi.Lobby.listener;

import de.Puishi.Lobby.Main;
import de.Puishi.Lobby.utils.*;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.api.player.PlayerExecutorBridge;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;

import static org.bukkit.Material.FEATHER;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 20.05.2020 / 00:20                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class PlayerInventoryClick implements Listener {

    public static ArrayList<Player> isFlying = new ArrayList<>();

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getInventory() != null){
            if(e.getInventory().getName().equalsIgnoreCase("§8» §6Navigator")){
                if(e.getCurrentItem().getType() == Material.BED){
                    p.closeInventory();
                    playEffect(p, new LocationAPI().getLocation("BedWars"), "BedWars");
                    p.playSound(p.getLocation(), Sound.SPLASH, 0.5F, 15.0F);
                } else if(e.getCurrentItem().getType() == Material.GRASS){
                    p.closeInventory();
                    playEffect(p, new LocationAPI().getLocation("SkyWars"), "SkyWars");
                    p.getWorld().playEffect(p.getLocation(), Effect.POTION_BREAK, 6);
                    p.playSound(p.getLocation(), Sound.SPLASH, 0.5F, 15.0F);
                } else if(e.getCurrentItem().getType() == Material.DIAMOND_BOOTS){
                    p.closeInventory();
                    playEffect(p, new LocationAPI().getLocation("TitanJump"), "TitanJump");
                    p.getWorld().playEffect(p.getLocation(), Effect.POTION_BREAK, 6);
                    p.playSound(p.getLocation(), Sound.SPLASH, 0.5F, 15.0F);
                }else if(e.getCurrentItem().getType() == Material.IRON_CHESTPLATE){
                    p.closeInventory();
                    playEffect(p, new LocationAPI().getLocation("Survival"), "Survival");
                    p.getWorld().playEffect(p.getLocation(), Effect.POTION_BREAK, 6);
                    p.playSound(p.getLocation(), Sound.SPLASH, 0.5F, 15.0F);
                } else if(e.getCurrentItem().getType() == Material.MAGMA_CREAM){
                    p.closeInventory();
                    playEffect(p, new LocationAPI().getLocation("Spawn"), "Spawn");
                    p.getWorld().playEffect(p.getLocation(), Effect.POTION_BREAK, 6);
                    p.playSound(p.getLocation(), Sound.SPLASH, 0.5F, 15.0F);
                } else if(e.getCurrentItem().getType() == Material.SKULL_ITEM){
                    p.closeInventory();
                    playEffect(p, new LocationAPI().getLocation("Community"), "Community");
                    p.getWorld().playEffect(p.getLocation(), Effect.POTION_BREAK, 6);
                    p.playSound(p.getLocation(), Sound.SPLASH, 0.5F, 15.0F);
                } else if(e.getCurrentItem().getType() == Material.STICK){
                    p.closeInventory();
                    playEffect(p, new LocationAPI().getLocation("TTT"), "TTT");
                    p.getWorld().playEffect(p.getLocation(), Effect.POTION_BREAK, 6);
                    p.playSound(p.getLocation(), Sound.SPLASH, 0.5F, 15.0F);
                }
            } else if(e.getInventory().getName().equalsIgnoreCase("§8» §eLobby-Switcher")){
                if(e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eLobby-1")) {
                        PlayerExecutorBridge.INSTANCE.sendPlayer(CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()), "Lobby-1");
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eLobby-2")) {
                        PlayerExecutorBridge.INSTANCE.sendPlayer(CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()), "Lobby-2");
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eLobby-3")) {
                        PlayerExecutorBridge.INSTANCE.sendPlayer(CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()), "Lobby-3");
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eLobby-4")) {
                        PlayerExecutorBridge.INSTANCE.sendPlayer(CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()), "Lobby-4");
                    }
                }
            } else if(e.getInventory().getName().equalsIgnoreCase("§8» §5Gadgets")){
                if(e.getCurrentItem().getType() == Material.LEATHER_BOOTS){
                    Inventory inv = Bukkit.createInventory(null, 54, "§8» §cBoots");
                    p.openInventory(inv);

                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            p.playSound(p.getLocation(), Sound.NOTE_BASS, 5, 5);
                            inv.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(45, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(53, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                        }
                    }.runTaskLater(Main.getInstance(), 10);

                }
            }
        }
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private static void playSound(Player p) {
        p.playSound(p.getLocation(), Sound.WOOD_CLICK, 40, 40);
    }

    public static void playEffect(Player p, Location loc, String warp) {

        ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) getRandomNumberInRange(0, 15));
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(" ");
        i.setItemMeta(m);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() { @Override public void run() { p.getInventory().setItem(0, i);playSound(p);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() { @Override public void run() { p.getInventory().setItem(1, i);playSound(p);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() { @Override public void run() { p.getInventory().setItem(2, i);playSound(p);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() { @Override public void run() { p.getInventory().setItem(3, i);playSound(p);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() { @Override public void run() { p.getInventory().setItem(4, i);playSound(p);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() { @Override public void run() { p.getInventory().setItem(5, i);playSound(p);
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() { @Override public void run() { p.getInventory().setItem(6, i);playSound(p);
                                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() { @Override public void run() {p.getInventory().setItem(7, i);playSound(p);
                                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() { @Override public void run() { p.getInventory().setItem(8, i);playSound(p);
                                        }
                                        }, 2);
                                    }
                                    }, 2);
                                }
                                }, 2);
                            }
                            }, 2);
                        }
                        }, 2);
                    }
                    }, 2);
                }
                }, 2);
            }
            }, 2);
        }
        }, 2);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {

                Location playerLoc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), -90);

                p.setVelocity(new Vector(40, 10, 40));
                Vector dir = playerLoc.getDirection();
                p.setVelocity(dir.multiply(1.6));

                Particle particle = new Particle(EnumParticle.MOB_APPEARANCE, p.getLocation(), true, 0.1f, 0.1f, 0.1f, 1, 1);
                particle.sendPlayer(p);

                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        p.teleport(loc);

                        for (int i = 0; i < 9; i++) {
                            p.getInventory().setItem(i, null);
                        }

                        Main.getInstance().getLobbyInventory().setInventory(p);


                        p.setHealth(20);

                        Actionbar.setTitle(p, "§8§l« §6" + warp + " §8§l»", "", 1, 20*2, 1);

                        Firework firework = p.getWorld().spawn(p.getLocation(), Firework.class);
                        FireworkEffect effect = FireworkEffect.builder()
                                .withColor(Color.ORANGE)
                                .flicker(true)
                                .trail(true)
                                .withFade(Color.ORANGE)
                                .with(FireworkEffect.Type.STAR)
                                .build();

                        FireworkMeta meta = firework.getFireworkMeta();
                        meta.addEffect(effect);
                        meta.setPower(1);

                        firework.setFireworkMeta(meta);

                    }
                }, 30);
            }
        }, 20);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() != null) {
            if (event.getCurrentItem().getType() == Material.FEATHER) {
                if (!isFlying.contains(player)) {
                    isFlying.add(player);
                    player.setAllowFlight(true);
                    player.sendMessage(Data.PRERIX + "Du kannst nun fliegen!");
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 10, 10);
                } else {
                    isFlying.remove(player);
                    player.setAllowFlight(false);
                    player.sendMessage(Data.PRERIX + "Du kannst nun nicht mehr fliegen!");
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 10, 10);
                }
            }
        }
    }
}
