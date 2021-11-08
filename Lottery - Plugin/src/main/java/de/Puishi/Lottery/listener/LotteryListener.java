package de.Puishi.Lobby.lotterie;

import de.Puishi.Lobby.Main;
import de.Puishi.Lobby.database.MySQL;
import de.Puishi.Lobby.utils.Data;
import de.javaarray.pixeltitan.coinsapi.api.CoinsAPI;
import de.slikey.effectlib.effect.VortexEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Random;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 20.05.2020 / 22:53                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class LotteryListener implements Listener {

    public static HashMap<Player, Location> chestLoc = new HashMap<>();

    @EventHandler
    public void onInteractEntity(PlayerInteractAtEntityEvent event) {

        Player player = event.getPlayer();

        if (event.getRightClicked() instanceof ArmorStand) {
            event.setCancelled(true);
            //if (event.getRightClicked().getCustomName().equalsIgnoreCase("§8* §6Lottery §8*")) {
            if (event.getRightClicked().getName().equalsIgnoreCase("§8* §6Lottery §8*")) {
                chestLoc.put(player, event.getRightClicked().getLocation().add(0, 1, 0));
                new LotteryInventory().openLotteryInventory(player, Bukkit.createInventory(null, InventoryType.CHEST, "§8» §6Lottery"));
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
            }
        }
    }


    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getInventory() != null) {
            if (event.getInventory().getName().equalsIgnoreCase("§8» §6Lottery")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getType() == Material.GOLD_INGOT) {
                    if (CoinsAPI.getCoins(player.getUniqueId().toString()) >= 100) {
                        MySQL.addTickets(player, 1);
                        CoinsAPI.removeCoins(player.getUniqueId().toString(), 100);
                        player.sendMessage(Data.PRERIX + "§7Du hast dir ein Lottery-Ticket gekauft!");
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                        player.closeInventory();
                    } else {
                        player.sendMessage(Data.PRERIX + "§cDu hast zu wenig Coins.");
                        player.closeInventory();
                    }
                } else if (event.getCurrentItem().getType() == Material.PAPER) {
                    if (chestLoc.containsKey(player)) {
                        if (MySQL.getLotteryTickets(player) != 0) {
                            MySQL.removeTickets(player, 1);
                            player.sendMessage(Data.PRERIX + "Du hast ein Lottery-Ticket eingelöst!");
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                            player.closeInventory();

                            Random random = new Random();
                            int coins = random.nextInt(200);

                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                                ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(chestLoc.get(player).add(0, 0, 0), EntityType.ARMOR_STAND);
                                armorStand.setCanPickupItems(false);
                                armorStand.setVisible(false);
                                armorStand.setCustomName("§6§l" + coins);
                                armorStand.setCustomNameVisible(true);
                                armorStand.setHelmet(new ItemStack(Material.GOLD_BLOCK));

                                VortexEffect vortexEffect = new VortexEffect(Main.effectManager);
                                vortexEffect.particle = ParticleEffect.FLAME;
                                vortexEffect.setDynamicOrigin(new DynamicLocation(chestLoc.get(player)));
                                vortexEffect.setDynamicTarget(new DynamicLocation(chestLoc.get(player).add(0, 2, 0)));
                                vortexEffect.offset = new Vector(0, 1, 0);
                                vortexEffect.autoOrient = true;
                                vortexEffect.pitchOffset = -20;
                                vortexEffect.relativeOffset = new Vector(0, 1, 0);
                                vortexEffect.targetOffset = new Vector(0, 1, 0);
                                vortexEffect.iterations = 20 * 2;
                                vortexEffect.helixes = 1;
                                vortexEffect.start();

                                player.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 1, 1);
                                player.getWorld().playEffect(player.getLocation(), Effect.EXPLOSION_LARGE, 1);

                                armorStand.setVelocity(new Vector(0, 1, 0));

                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {

                                    chestLoc.get(player).getWorld().getNearbyEntities(chestLoc.get(player), 5, 5, 5).forEach(nearby -> {
                                        if (nearby instanceof ArmorStand) {
                                            if (((ArmorStand) nearby).getHelmet().getType() == Material.GOLD_BLOCK)
                                                nearby.remove();
                                                chestLoc.remove(player);
                                        }
                                    });

                                }, 20 * 2);

                            }, 5);

                            CoinsAPI.addCoins(player.getUniqueId().toString(), coins);

                            player.sendMessage(Data.PRERIX + "Du hast §a" + coins + " Coins §7bekommen.");
                            player.sendMessage(Data.PRERIX + "Du hast momentan §a" + CoinsAPI.getCoins(player.getUniqueId().toString()) + " §aCoins§7.");
                        } else {
                            player.sendMessage(Data.PRERIX + "§cDu hast nicht genügend Tickets.");
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            player.closeInventory();
                        }

                    } else {
                        player.sendMessage(Data.PRERIX + "§cBitte warte einen Moment...");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                        player.closeInventory();
                    }
                }
            }
        }
    }
}