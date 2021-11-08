package es.Punikii.MLGWars.Listeners;

import es.Punikii.MLGWars.Coins.Coins;
import es.Punikii.MLGWars.MLGWars;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class Shop implements Listener {

    private final String GUI_NAME = "§8» §cShop";

    public static HashMap<Player, ItemStack[]> inv = new HashMap<>();

    public void openGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 2, GUI_NAME);
        inventory.setItem(1, new ItemBuilder(Material.GOLD_SWORD).setName("§cSchwerter").toItemStack());
        inventory.setItem(2, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setName("§cRüstung").toItemStack());
        inventory.setItem(3, new ItemBuilder(Material.COOKED_BEEF).setName("§cEssen").toItemStack());
        inventory.setItem(4, new ItemBuilder(Material.BOW).setName("§cBögen").toItemStack());
        inventory.setItem(5, new ItemBuilder(Material.POTION).setName("§cTränke").toItemStack());
        inventory.setItem(6, new ItemBuilder(Material.WEB).setName("§cExtras").toItemStack());
        inventory.setItem(7, new ItemBuilder(Material.EXP_BOTTLE).setName("§cXP").toItemStack());


        player.openInventory(inventory);
    }

    @EventHandler
    public void handleNavigatorOpener(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        try {
            if(event.getClickedBlock().getType() == Material.GOLD_BLOCK) {
                if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
                    openGUI(event.getPlayer());
                }
            }
        } catch (Exception e) {}
    }

    @EventHandler
    public void handleNavigatorGUIClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory().getTitle().equals(GUI_NAME)) {
            event.setCancelled(true);
            switch (event.getCurrentItem().getType()) {
                case GOLD_SWORD:
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    event.getInventory().setItem(9, new ItemBuilder(Material.WOOD_SWORD).setName("§cHolz-Schwert §8» 2 Coins").toItemStack());
                    event.getInventory().setItem(10, new ItemBuilder(Material.STONE_SWORD).setName("§cStein-Schwert §8» 5 Coins").toItemStack());
                    event.getInventory().setItem(11, new ItemBuilder(Material.IRON_SWORD).setName("§cEisen-Schwert §8» 8 Coins").toItemStack());
                    event.getInventory().setItem(12, new ItemBuilder(Material.DIAMOND_SWORD).setName("§cDiamant-Schwert §8» 10 Coins").toItemStack());
                    event.getInventory().setItem(13, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(14, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(15, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(16, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(17, new ItemBuilder(Material.AIR).toItemStack());
                    player.updateInventory();
                    break;

                case CHAINMAIL_CHESTPLATE:
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    event.getInventory().setItem(9, new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("§cLeder-Rüstung §8» 3 Coins").toItemStack());
                    event.getInventory().setItem(10, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setName("§cChain-Rüstung §8» 5 Coins").toItemStack());
                    event.getInventory().setItem(11, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§cEisen-Rüstung §8» 10 Coins").toItemStack());
                    event.getInventory().setItem(12, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§cDiamant-Rüstung §8» 15 Coins").toItemStack());
                    event.getInventory().setItem(13, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(14, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(15, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(16, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(17, new ItemBuilder(Material.AIR).toItemStack());
                    player.updateInventory();
                    break;

                case COOKED_BEEF:
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    event.getInventory().setItem(9, new ItemBuilder(Material.APPLE).setName("§cApfel §8» 1 Coins").toItemStack());
                    event.getInventory().setItem(10, new ItemBuilder(Material.COOKED_BEEF).setName("§cFleisch §8» 1 Coins").toItemStack());
                    event.getInventory().setItem(11, new ItemBuilder(Material.COOKIE).setName("§cCookie §8» 1 Coins").toItemStack());
                    event.getInventory().setItem(12, new ItemBuilder(Material.CARROT_ITEM).setName("§cKarrote §8» 1 Coins").toItemStack());
                    event.getInventory().setItem(13, new ItemBuilder(Material.GOLDEN_APPLE).setName("§cGoldenen Apfel §8» 3 Coins").toItemStack());
                    event.getInventory().setItem(14, new ItemBuilder(Material.GOLDEN_APPLE, 1, (byte) 1).setName("§cOp Gold Apfel §8» 10 Coins").toItemStack());
                    event.getInventory().setItem(15, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(16, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(17, new ItemBuilder(Material.AIR).toItemStack());
                    player.updateInventory();
                    break;

                case BOW:
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    event.getInventory().setItem(9, new ItemBuilder(Material.BOW).setName("§cBogen").toItemStack());
                    event.getInventory().setItem(10, new ItemBuilder(Material.BOW).setName("§cBogen-").toItemStack());
                    event.getInventory().setItem(11, new ItemBuilder(Material.BOW).setName("§cBogen-").toItemStack());
                    event.getInventory().setItem(12, new ItemBuilder(Material.BOW).setName("§cBogen-").toItemStack());
                    event.getInventory().setItem(13, new ItemBuilder(Material.BOW).setName("§cBogen-").toItemStack());
                    event.getInventory().setItem(14, new ItemBuilder(Material.ARROW).setName("§cPfeil").toItemStack());
                    event.getInventory().setItem(15, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(16, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(17, new ItemBuilder(Material.AIR).toItemStack());
                    player.updateInventory();
                    break;

                case POTION:
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    event.getInventory().setItem(9, new ItemBuilder(Material.POTION).setName("§cHeilungs-Trank §8» 2 Coins").toItemStack());
                    event.getInventory().setItem(10, new ItemBuilder(Material.POTION).setName("§cSpeed-Trank §8» 2 Coins").toItemStack());
                    event.getInventory().setItem(11, new ItemBuilder(Material.POTION).setName("§cGift-Trank §8» 5 Coins").toItemStack());
                    event.getInventory().setItem(12, new ItemBuilder(Material.POTION).setName("§cStärke-Trank §8» 8 Coins").toItemStack());
                    event.getInventory().setItem(13, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(14, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(15, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(16, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(17, new ItemBuilder(Material.AIR).toItemStack());
                    player.updateInventory();
                    break;

                case WEB:
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    event.getInventory().setItem(9, new ItemBuilder(Material.WEB).setName("§cCobweb §8» 2 Coins").toItemStack());
                    event.getInventory().setItem(10, new ItemBuilder(Material.FISHING_ROD).setName("§cAngel §8» 3 Coins").toItemStack());
                    event.getInventory().setItem(11, new ItemBuilder(Material.TNT).setName("§cTNT §8» 4 Coins").toItemStack());
                    event.getInventory().setItem(12, new ItemBuilder(Material.COMPASS).setName("§cSpieler-Finder §8» 2 Coins").toItemStack());
                    event.getInventory().setItem(13, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(14, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(15, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(16, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(17, new ItemBuilder(Material.AIR).toItemStack());
                    player.updateInventory();
                    break;
                case EXP_BOTTLE:
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    event.getInventory().setItem(9, new ItemBuilder(Material.EXP_BOTTLE).setName("§c15XP §8» 7 Coins").toItemStack());
                    event.getInventory().setItem(10, new ItemBuilder(Material.EXP_BOTTLE).setName("§c20XP §8» 10 Coins").toItemStack());
                    event.getInventory().setItem(11, new ItemBuilder(Material.EXP_BOTTLE).setName("§c25XP §8» 13 Coins").toItemStack());
                    event.getInventory().setItem(12, new ItemBuilder(Material.EXP_BOTTLE).setName("§c30XP §8» 15 Coins").toItemStack());
                    event.getInventory().setItem(13, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(14, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(15, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(16, new ItemBuilder(Material.AIR).toItemStack());
                    event.getInventory().setItem(17, new ItemBuilder(Material.AIR).toItemStack());
                    player.updateInventory();
                    break;

                default:

                    break;
            }
        }
    }
    @EventHandler
    public void handleNavigatorGUIClick2(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();

        ItemStack WS = new ItemStack(Material.WOOD_SWORD);
        ItemMeta wsMeta = WS.getItemMeta();
        wsMeta.setDisplayName("§8» §cHolz-Schwert");
        WS.setItemMeta(wsMeta);

        if (event.getClickedInventory().getTitle().equals(GUI_NAME)) {
            event.setCancelled(true);

            if (!inv.containsKey(player)) inv.put(player, new ItemStack[]{});

            if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cHolz-Schwert §8» 2 Coins")) {
                if (Coins.coins.get(player) >= 2) {
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    event.getInventory().setItem(9, new ItemBuilder(Material.WOOD_SWORD).setName("§8» §aGekauft").toItemStack());
                    Coins.coins.put(player, Coins.coins.get(player) - 2);
                    player.getInventory().setItem(0, new ItemBuilder(Material.WOOD_SWORD).setName("§cHolz-Schwert").toItemStack());
                } else
                    player.sendMessage(MLGWars.prefix + "Du hast nicht genügend §cCoins");
                }
                if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cStein-Schwert §8» 5 Coins")) {
                    if (Coins.coins.get(player) >= 5) {
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                        event.getInventory().setItem(10, new ItemBuilder(Material.STONE_SWORD).setName("§8» §aGekauft").toItemStack());
                        Coins.coins.put(player, Coins.coins.get(player) - 5);
                        player.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD).setName("§cStein-Schwert").toItemStack());
                    } else
                        player.sendMessage(MLGWars.prefix + "Du hast nicht genügend §cCoins");
                }
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cEisen-Schwert §8» 8 Coins")) {
                        if (Coins.coins.get(player) >= 8) {
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            event.getInventory().setItem(11, new ItemBuilder(Material.IRON_SWORD).setName("§8» §aGekauft").toItemStack());
                            Coins.coins.put(player, Coins.coins.get(player) - 8);
                            player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setName("§cEisen-Schwert").toItemStack());
                        } else
                            player.sendMessage(MLGWars.prefix + "Du hast nicht genügend §cCoins");
                    }
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cDiamant-Schwert §8» 10 Coins")) {
                        if (Coins.coins.get(player) >= 10) {
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            event.getInventory().setItem(12, new ItemBuilder(Material.DIAMOND_SWORD).setName("§8» §aGekauft").toItemStack());
                            Coins.coins.put(player, Coins.coins.get(player) - 10);
                            player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setName("§cDiamant-Schwert").toItemStack());
                        } else
                            player.sendMessage(MLGWars.prefix + "Du hast nicht genügend §cCoins");
                    }
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cLeder-Rüstung §8» 3 Coins")) {
                        ItemStack leggins = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
                        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET, 1);
                        if (Coins.coins.get(player) >= 3) {
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            event.getInventory().setItem(9, new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("§8» §aGekauft").toItemStack());
                            Coins.coins.put(player, Coins.coins.get(player) - 3);
                            player.getInventory().setHelmet(helmet);
                            player.getInventory().setChestplate(chest);
                            player.getInventory().setLeggings(leggins);
                            player.getInventory().setBoots(boots);
                        } else
                            player.sendMessage(MLGWars.prefix + "Du hast nicht genügend §cCoins");
                    }
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cChain-Rüstung §8» 5 Coins")) {
                        ItemStack leggins = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
                        ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
                        ItemStack chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
                        ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET, 1);
                        if (Coins.coins.get(player) >= 5) {
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            event.getInventory().setItem(10, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setName("§8» §aGekauft").toItemStack());
                            Coins.coins.put(player, Coins.coins.get(player) - 5);
                            player.getInventory().setHelmet(helmet);
                            player.getInventory().setChestplate(chest);
                            player.getInventory().setLeggings(leggins);
                            player.getInventory().setBoots(boots);
                        } else
                            player.sendMessage(MLGWars.prefix + "Du hast nicht genügend §cCoins");
                    }

                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cEisen-Rüstung §8» 10 Coins")) {
                        ItemStack leggins = new ItemStack(Material.IRON_LEGGINGS, 1);
                        ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);
                        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE, 1);
                        ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
                        if (Coins.coins.get(player) >= 10) {
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            event.getInventory().setItem(11, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§8» §aGekauft").toItemStack());
                            Coins.coins.put(player, Coins.coins.get(player) - 10);
                            player.getInventory().setHelmet(helmet);
                            player.getInventory().setChestplate(chest);
                            player.getInventory().setLeggings(leggins);
                            player.getInventory().setBoots(boots);
                        } else
                            player.sendMessage(MLGWars.prefix + "Du hast nicht genügend §cCoins");
                    }
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cDiamant-Rüstung §8» 15 Coins")) {
                        ItemStack leggins = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
                        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
                        ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
                        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
                        if (Coins.coins.get(player) >= 15) {
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            event.getInventory().setItem(12, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§8» §aGekauft").toItemStack());
                            Coins.coins.put(player, Coins.coins.get(player) - 15);
                            player.getInventory().setHelmet(helmet);
                            player.getInventory().setChestplate(chest);
                            player.getInventory().setLeggings(leggins);
                            player.getInventory().setBoots(boots);
                        } else
                            player.sendMessage(MLGWars.prefix + "Du hast nicht genügend §cCoins");
                    }
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cApfel §8» 1 Coins")) {
                        if (Coins.coins.get(player) >= 1) {
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            event.getInventory().setItem(9, new ItemBuilder(Material.APPLE).setName("§8» §aGekauft").toItemStack());
                            Coins.coins.put(player, Coins.coins.get(player) - 1);
                            player.getInventory().setItem(2, new ItemBuilder(Material.APPLE).setName("§cApfel").toItemStack());
                        } else
                            player.sendMessage(MLGWars.prefix + "Du hast nicht genügend §cCoins");
                    }
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cFleisch §8» 1 Coins")) {
                        if (Coins.coins.get(player) >= 1) {
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            event.getInventory().setItem(10, new ItemBuilder(Material.COOKED_BEEF).setName("§8» §aGekauft").toItemStack());
                            Coins.coins.put(player, Coins.coins.get(player) - 1);
                            player.getInventory().setItem(3, new ItemBuilder(Material.COOKED_BEEF).setName("§cFleisch").toItemStack());
                        } else
                            player.sendMessage(MLGWars.prefix + "Du hast nicht genügend §cCoins");
                    }
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cKarrote §8» 1 Coins")) {
                        if (Coins.coins.get(player) >= 1) {
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            event.getInventory().setItem(10, new ItemBuilder(Material.CARROT_ITEM).setName("§8» §aGekauft").toItemStack());
                            Coins.coins.put(player, Coins.coins.get(player) - 1);
                            if (player.getInventory().containsAtLeast(new ItemBuilder(Material.COOKED_BEEF).setName("§cFleich").toItemStack(), 1)) {
                                player.getInventory().setItem(4, new ItemBuilder(Material.CARROT_ITEM).setName("§cKarrote").toItemStack());
                            } else
                                player.getInventory().setItem(3, new ItemBuilder(Material.CARROT_ITEM).setName("§cKarrote").toItemStack());
                        } else
                            player.sendMessage(MLGWars.prefix + "Du hast nicht genügend §cCoins");
                    }
                                                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cGoldenen Apfel §8» 3 Coins")) {
                                                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                                                    Coins.coins.put(player, Coins.coins.get(player) - 3);
                                                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cOp Gold Apfel §8» 10 Coins")) {
                                                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                                                        Coins.coins.put(player, Coins.coins.get(player) - 10);
                }
            }
        }
    }

}
