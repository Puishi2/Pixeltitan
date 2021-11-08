package de.Puishi.SkyWars.kits;

import de.Puishi.SkyWars.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.HashMap;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 26.05.2020 / 14:33                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class KitManager {

    public static HashMap<String, String> kit = new HashMap<>();

    public static void setKit(Player player) {

        switch (kit.get(player.getUniqueId().toString())) {

            case "§8» §6Titan-Kit":
                player.getInventory().clear();
                player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setName("§8» §7Eisenschwert").toItemStack());
                player.getInventory().setItem(1, new ItemBuilder(Material.IRON_AXE).setName("§8» §7Eisenaxt").toItemStack());
                player.getInventory().setItem(2, new ItemBuilder(Material.IRON_PICKAXE).setName("§8» §7Eisenspitzhacke").toItemStack());
                player.getInventory().setItem(3, new ItemBuilder(Material.STONE).setAmount(32).setName("§8» §7Blöcke").toItemStack());
                player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS).setName("§8» §7Eisenboots").toItemStack());
                break;

            case "§8» §2TP-Kit":
                player.getInventory().clear();
                player.getInventory().setItem(0, new ItemBuilder(Material.ENDER_PEARL).setAmount(2).setName("§8» §7Enderperle").toItemStack());
                player.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setName("§8» §7Brustplatte").toItemStack());
                break;

            case "§8» §eSpotter-Kit":
                player.getInventory().clear();
                player.getInventory().setItem(0, new ItemBuilder(Material.GLOWSTONE_DUST).setName("§8» §7Spotter").toItemStack());
                player.getInventory().setItem(1, new ItemBuilder(Material.DIAMOND_AXE).setName("§8» §7Diamant-Axt").toItemStack());
                break;

            case "§8» §bAssasine-Kit":
                player.getInventory().clear();
                player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setName("§8» §7Schwert").toItemStack());
                player.getInventory().setItem(1, new ItemBuilder(Material.GOLDEN_APPLE).setName("§8» §7Goldapfel").toItemStack());
                break;

            case "§8» §aPixel-Kit":
                player.getInventory().clear();
                player.getInventory().setItem(0, new ItemBuilder(Material.IRON_AXE).setName("§8» §7Eisenaxt").toItemStack());
                player.getInventory().setItem(1, new ItemBuilder(Material.EXP_BOTTLE).setAmount(32).setName("§8» §7EXP-Flaschen").toItemStack());
                player.getInventory().setItem(2, new ItemBuilder(Material.ENCHANTMENT_TABLE).setName("§8» §7Verzauberungstisch").toItemStack());
                player.getInventory().setChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§8» §7Brustplatte").toItemStack());
                break;

            case "§8» §dBuild-Kit":
                player.getInventory().clear();
                player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§8» §7Spitzhacke").toItemStack());
                player.getInventory().setItem(1, new ItemBuilder(Material.BRICK).setAmount(64).setName("§8» §7Ziegelstein").toItemStack());
                player.getInventory().setItem(2, new ItemBuilder(Material.BRICK).setAmount(64).setName("§8» §7Ziegelstein").toItemStack());
                player.getInventory().setItem(3, new ItemBuilder(Material.BRICK).setAmount(64).setName("§8» §7Ziegelstein").toItemStack());
                player.getInventory().setBoots(new ItemBuilder(Material.GOLD_BOOTS).setName("§8» §8Goldboots").toItemStack());
                break;

            case "§8» §9MLG-Kit":
                player.getInventory().clear();
                player.getInventory().setItem(0, new ItemBuilder(Material.WATER_BUCKET).setName("§8» §7Wassereimer").toItemStack());
                player.getInventory().setItem(1, new ItemBuilder(Material.WEB).setAmount(5).setName("§8» §7Cobweb").toItemStack());
                player.getInventory().setItem(2, new ItemBuilder(Material.SLIME_BLOCK).setAmount(5).setName("§8» §7Schleimblock").toItemStack());
                player.getInventory().setItem(3, new ItemBuilder(Material.TNT).setAmount(8).setName("§8» §7TNT").toItemStack());
                player.getInventory().setItem(4, new ItemBuilder(Material.WOOD).setAmount(32).setName("§8» §7Holz").toItemStack());
                break;

            default:
                break;
        }

    }

    public static void setNoKit(Player player) {
        player.getInventory().clear();
        player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setName("§8» §7Eisenschwert").toItemStack());
        player.getInventory().setItem(1, new ItemBuilder(Material.IRON_AXE).setName("§8» §7Eisenaxt").toItemStack());
        player.getInventory().setItem(2, new ItemBuilder(Material.IRON_PICKAXE).setName("§8» §7Eisenspitzhacke").toItemStack());
        player.getInventory().setItem(3, new ItemBuilder(Material.STONE).setAmount(32).setName("§8» §7Blöcke").toItemStack());
        player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS).setName("§8» §7Eisenboots").toItemStack());
    }

}
