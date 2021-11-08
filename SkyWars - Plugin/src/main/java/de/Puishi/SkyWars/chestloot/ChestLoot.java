package de.Puishi.SkyWars.chestloot;

import de.Puishi.SkyWars.GameState.GameStates;
import de.Puishi.SkyWars.Main;
import de.Puishi.SkyWars.utils.Data;
import de.Puishi.SkyWars.utils.ItemBuilder;
import de.Puishi.SkyWars.utils.SpecUtils;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 28.05.2020 / 04:53                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class ChestLoot implements Listener {

    private final ArrayList<ItemStack> loot = new ArrayList<>();
    public static final ArrayList<Location> chestloc = new ArrayList<>();

    @EventHandler
    public void onChestOpen(PlayerInteractEvent e) {
            Player player = e.getPlayer();

            try {
                if(Main.getInstance().getGameStates() == GameStates.LOBBY || Main.getInstance().getGameStates() == GameStates.ENDING) {
                    return;
                }

                if (e.getClickedBlock().getType() == Material.CHEST || e.getClickedBlock().getType() == Material.TRAPPED_CHEST &&  e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    e.setCancelled(true);

                    if (!SpecUtils.spectatorlist.contains(player.getName())) {

                        this.registerLoot();

                        e.getClickedBlock().setType(Material.AIR);
                        player.spigot().playEffect(e.getClickedBlock().getLocation(), Effect.FLAME, 1, 1, 0.0F, 0.0F, 0.0F, 0.1F, 32, 8);

                        int i = 0;

                        while (i < Data.rndInt(15, 22)) {
                            e.getClickedBlock().getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), this.loot.get(rndInt(0, this.loot.size() - 1)));
                            i++;
                        }

                        /*Inventory inv = Bukkit.createInventory(null, 27, "§8» §2Truhe");
                        int i = 0;

                        while (i < Data.rndInt(15, 22)) {
                            inv.setItem(Data.rndInt(0, inv.getSize() - 1), this.loot.get(Data.rndInt(0, this.loot.size() - 1)));
                            ++i;
                        }*/

                        chestloc.add(e.getClickedBlock().getLocation());

                        // player.openInventory(inv);

                    }
                }
            } catch (Exception exception) {

            }
    }

    public void registerLoot() {
        this.loot.add(new ItemBuilder(Material.STONE_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.WOOD_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.LEATHER_CHESTPLATE).toItemStack());
        this.loot.add(new ItemBuilder(Material.LEATHER_HELMET).toItemStack());
        this.loot.add(new ItemBuilder(Material.LEATHER_LEGGINGS).toItemStack());
        this.loot.add(new ItemBuilder(Material.LEATHER_BOOTS).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_HELMET).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_BOOTS).addEnchant(Enchantment.PROTECTION_FALL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.DIAMOND_BOOTS).toItemStack());
        this.loot.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_BOOTS).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_LEGGINGS).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_HELMET).toItemStack());
        this.loot.add(new ItemBuilder(Material.TNT).setAmount(rndInt(4, 7)).toItemStack());
        this.loot.add(new ItemBuilder(Material.FLINT_AND_STEEL).toItemStack());
        this.loot.add(new ItemBuilder(Material.FISHING_ROD).toItemStack());
        this.loot.add(new ItemBuilder(Material.COOKED_BEEF).setAmount(rndInt(5, 9)).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 2).setName("Puishi's OP-Schwert").toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.LEATHER_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.LEATHER_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.LEATHER_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.LEATHER_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_CHESTPLATE).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_HELMET).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_LEGGINGS).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.DIAMOND_BOOTS).toItemStack());
        this.loot.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_BOOTS).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_HELMET).addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.TNT).setAmount(rndInt(4, 9)).toItemStack());
        this.loot.add(new ItemBuilder(Material.FLINT_AND_STEEL).toItemStack());
        this.loot.add(new ItemBuilder(Material.FISHING_ROD).toItemStack());
        this.loot.add(new ItemBuilder(Material.COOKED_BEEF).setAmount(rndInt(3, 5)).toItemStack());
        this.loot.add(new ItemBuilder(Material.COOKED_CHICKEN).setAmount(rndInt(3, 5)).toItemStack());
        this.loot.add(new ItemBuilder(Material.CAKE).toItemStack());
        this.loot.add(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.CHAINMAIL_HELMET).toItemStack());
        this.loot.add(new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.ARROW).setAmount(rndInt(2, 4)).toItemStack());
        this.loot.add(new ItemBuilder(Material.FLINT).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_INGOT).setAmount(rndInt(2, 4)).toItemStack());
        this.loot.add(new ItemBuilder(Material.DIAMOND).setAmount(rndInt(1, 7)).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_INGOT).setAmount(rndInt(3, 6)).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_CHESTPLATE).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_FIRE, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_LEGGINGS).addEnchant(Enchantment.PROTECTION_PROJECTILE, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_BOOTS).toItemStack());
        this.loot.add(new ItemBuilder(Material.DIRT).setAmount(rndInt(34, 64)).toItemStack());
        this.loot.add(new ItemBuilder(Material.DIAMOND_BOOTS).toItemStack());
        this.loot.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_LEGGINGS).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_HELMET).toItemStack());
        this.loot.add(new ItemBuilder(Material.COOKED_BEEF).setAmount(rndInt(1, 3)).toItemStack());
        this.loot.add(new ItemBuilder(Material.DIRT).setAmount(rndInt(34, 64)).toItemStack());
        this.loot.add(new ItemBuilder(Material.COOKED_CHICKEN).setAmount(rndInt(1, 3)).toItemStack());
        this.loot.add(new ItemBuilder(Material.CAKE).toItemStack());
        this.loot.add(new ItemBuilder(Material.CHAINMAIL_HELMET).addEnchant(Enchantment.PROTECTION_FIRE, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.BOW).toItemStack());
        this.loot.add(new ItemBuilder(Material.ARROW).setAmount(rndInt(1, 3)).toItemStack());
        this.loot.add(new ItemBuilder(Material.FLINT).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_INGOT).setAmount(rndInt(1, 6)).toItemStack());
        this.loot.add(new ItemBuilder(Material.DIAMOND).setAmount(rndInt(1, 3)).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_INGOT).setAmount(rndInt(1, 5)).toItemStack());
        this.loot.add(new ItemBuilder(Material.COBBLESTONE).setAmount(rndInt(1, 64)).toItemStack());
        this.loot.add(new ItemBuilder(Material.APPLE).setAmount(rndInt(3, 5)).toItemStack());
        this.loot.add(new ItemBuilder(Material.BREAD).setAmount(rndInt(4, 6)).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLDEN_APPLE).setAmount(rndInt(1, 2)).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_AXE).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_AXE).toItemStack());
        this.loot.add(new ItemBuilder(Material.LOG).setAmount(rndInt(1, 3)).toItemStack());
        this.loot.add(new ItemBuilder(Material.DIAMOND_AXE).toItemStack());
        this.loot.add(new ItemBuilder(Material.STONE).setAmount(rndInt(55, 64)).toItemStack());
        this.loot.add(new ItemBuilder(Material.DIRT).setAmount(rndInt(34, 64)).toItemStack());
        this.loot.add(new ItemBuilder(Material.LOG).setAmount(rndInt(1, 3)).toItemStack());
        this.loot.add(new ItemBuilder(Material.WOOD).setAmount(rndInt(1, 64)).toItemStack());
        this.loot.add(new ItemBuilder(Material.BRICK).setAmount(rndInt(1, 64)).toItemStack());
        this.loot.add(new ItemBuilder(Material.LOG).setAmount(rndInt(1, 10)).toItemStack());
        this.loot.add(new ItemBuilder(Material.STICK).setAmount(rndInt(2, 7)).toItemStack());
        this.loot.add(new ItemBuilder(Material.EXP_BOTTLE).setAmount(rndInt(4, 12)).toItemStack());
        this.loot.add(new ItemBuilder(Material.APPLE).setAmount(rndInt(1, 3)).toItemStack());
        this.loot.add(new ItemBuilder(Material.BREAD).setAmount(rndInt(1, 3)).toItemStack());
        this.loot.add(new ItemBuilder(Material.COBBLESTONE).setAmount(rndInt(1, 64)).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLDEN_APPLE).setAmount(rndInt(1, 2)).toItemStack());
        this.loot.add(new ItemBuilder(Material.GOLD_AXE).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.IRON_AXE).addEnchant(Enchantment.KNOCKBACK, 1).toItemStack());
        this.loot.add(new ItemBuilder(Material.DIAMOND_AXE).toItemStack());
        this.loot.add(new ItemBuilder(Material.STONE).setAmount(rndInt(1, 64)).toItemStack());
        this.loot.add(new ItemBuilder(Material.BRICK).setAmount(rndInt(1, 64)).toItemStack());
        this.loot.add(new ItemBuilder(Material.LOG).setAmount(rndInt(1, 3)).toItemStack());
        this.loot.add(new ItemBuilder(Material.STICK).setAmount(rndInt(1, 6)).toItemStack());
        this.loot.add(new ItemBuilder(Material.ENCHANTMENT_TABLE).toItemStack());
        this.loot.add(new ItemBuilder(Material.ENDER_PEARL).toItemStack());
        this.loot.add(new ItemBuilder(Material.CARROT).setAmount(rndInt(5, 10)).toItemStack());
        this.loot.add(new ItemBuilder(Material.MELON).setAmount(rndInt(4, 10)).toItemStack());
        this.loot.add(new ItemBuilder(Material.COBBLESTONE).setAmount(rndInt(1, 64)).toItemStack());
        this.loot.add(new ItemBuilder(Material.WEB).setAmount(rndInt(2, 4)).toItemStack());
        this.loot.add(new ItemBuilder(Material.BAKED_POTATO).setAmount(rndInt(3, 7)).toItemStack());
        this.loot.add(new ItemBuilder(Material.POTATO).setAmount(rndInt(7, 15)).toItemStack());
        this.loot.add(new ItemBuilder(Material.COOKED_FISH).setAmount(rndInt(7, 14)).toItemStack());
        this.loot.add(new ItemBuilder(Material.WOOD_BUTTON).setAmount(rndInt(1, 3)).toItemStack());
        this.loot.add(new ItemBuilder(Material.WATER_BUCKET).toItemStack());
        this.loot.add(new ItemBuilder(Material.LAVA_BUCKET).toItemStack());
        this.loot.add(new ItemBuilder(Material.SNOW_BALL).setAmount(rndInt(1, 5)).toItemStack());
        this.loot.add(new ItemBuilder(Material.EGG).setAmount(rndInt(1, 5)).toItemStack());
        this.loot.add(new ItemBuilder(Material.ENDER_PEARL).toItemStack());
        this.loot.add(new ItemBuilder(Material.COBBLESTONE).setAmount(rndInt(1, 64)).toItemStack());
        this.loot.add(new ItemBuilder(Material.POTATO).setAmount(rndInt(1, 8)).toItemStack());
        this.loot.add(new ItemBuilder(Material.COOKED_FISH).setAmount(rndInt(1, 7)).toItemStack());
        this.loot.add(new ItemBuilder(Material.WOOD_BUTTON).setAmount(rndInt(1, 3)).toItemStack());
        this.loot.add(new ItemBuilder(Material.WATER_BUCKET).toItemStack());
        this.loot.add(new ItemBuilder(Material.LAVA_BUCKET).toItemStack());
        this.loot.add(new ItemBuilder(Material.SNOW_BALL).setAmount(rndInt(1, 8)).toItemStack());
        this.loot.add(new ItemBuilder(Material.EGG).setAmount(rndInt(1, 7)).toItemStack());
    }

    public static int rndInt(int min, int max) {
        Random r = new Random();
        int i = r.nextInt(max - min + 1) + min;
        return i;
    }

}
