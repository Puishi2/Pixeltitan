package de.Puishi.Bauserver.wm;

import de.Puishi.Bauserver.Bauserver;
import de.Puishi.Bauserver.utils.ItemBuilder;
import org.apache.commons.io.FileUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 21.06.2020 / 12:04                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class WM_Listener implements Listener {

    private Bauserver plugin;
    private Inventory chooseWorldTypeInv;
    private Inventory chooseTypeInv;
    private Inventory chooseSizeInv;
    private Inventory defaultInv;
    private Inventory filterInv;
    private Inventory confirmInv;
    private Inventory statusInv;
    private Inventory whitelistInv;
    private HashMap<Player, HashMap<String, String>> types = new HashMap<>();
    private HashMap<Player, Integer> currentPage = new HashMap<>();
    private HashMap<Integer, ArrayList<ItemStack>> pages = new HashMap<>();
    private HashMap<Player, Integer> filter = new HashMap<>();
    private HashMap<Player, String> name = new HashMap<>();
    private HashMap<Player, String> from = new HashMap<>();
    private HashMap<Player, String> world = new HashMap<>();
    private boolean isLoaded = false;

    public WM_Listener(Bauserver plugin) {
        this.plugin = plugin;

        this.chooseWorldTypeInv = new InventoryAPI("§8» §bWelttyp", 9 * 3)
                .setItem(VaceAPI.getApi().getSkullAPI().getSkull("§8» §bFlach",
                        "http://textures.minecraft.net/texture/c95d37993e594082678472bf9d86823413c250d4332a2c7d8c52de4976b362"), 11)
                .setItem(VaceAPI.getApi().getSkullAPI().getSkull("§8» §bLeer",
                        "http://textures.minecraft.net/texture/d52efad1be84e0889bb2a581a5e665aa070622260fbf6c1eb8d931a1267a54ba"), 15).build();

        fillInv(this.chooseWorldTypeInv, 9 * 3);

        this.chooseTypeInv = new InventoryAPI("§8» §bMaptyp", 9 * 3)
                .setItem(new ItemBuilder(Material.GRASS).setName("§8» §bLobby").build(), 10)
                .setItem(new ItemBuilder(Material.DIRT).setName("§8» §bWartelobby").build(), 12)
                .setItem(VaceAPI.getApi().getSkullAPI().getSkull("§8» §bMinigame",
                        "http://textures.minecraft.net/texture/4b92cb43333aa621c70eef4ebf299ba412b446fe12e341ccc582f3192189"), 14)
                .setItem(new ItemBuilder(Material.PAPER).setName("§8» §bSonstiges").build(), 16).build();

        fillInv(this.chooseTypeInv, 9 * 3);

        this.chooseSizeInv = new InventoryAPI("§8» §bMapgröße", 9 * 3)
                .setItem(new ItemBuilder(Material.COAL).setName("§8» §b50§8x§b50").build(), 9)
                .setItem(new ItemBuilder(Material.GOLD_INGOT).setName("§8» §b100§8x§b100").build(), 11)
                .setItem(new ItemBuilder(Material.IRON_INGOT).setName("§8» §b150§8x§b150").build(), 13)
                .setItem(new ItemBuilder(Material.EMERALD).setName("§8» §b250§8x§b250").build(), 15)
                .setItem(new ItemBuilder(Material.DIAMOND).setName("§8» §b500§8x§b500").build(), 17).build();

        fillInv(this.chooseSizeInv, 9 * 3);

        this.defaultInv = new InventoryAPI("§8» §b", 9 * 5).build();

        fillInv(this.defaultInv, 9 * 5);

        this.defaultInv.setItem(37, new ItemBuilder(Material.HOPPER).setName("§8» §bFilter").build());
        this.defaultInv.setItem(39, VaceAPI.getApi().getSkullAPI().getSkull("§8» §bZurück",
                "http://textures.minecraft.net/texture/8652e2b936ca8026bd28651d7c9f2819d2e923697734d18dfdb13550f8fdad5f"));
        this.defaultInv.setItem(41, VaceAPI.getApi().getSkullAPI().getSkull("§8» §bWeiter",
                "http://textures.minecraft.net/texture/2a3b8f681daad8bf436cae8da3fe8131f62a162ab81af639c3e0644aa6abac2f"));
        this.defaultInv.setItem(43, new ItemBuilder(Material.DIODE).setName("§8» §bDeine Welt").build());

        this.filterInv = new InventoryAPI("§8» §bFilter", 9 * 3).setItem(new ItemBuilder(Material.BARRIER)
                .setName("§8» §bAlle Maps anzeigen").build(), 9)
                .setItem(new ItemBuilder(Material.WOOL).setName("§8» §bNach Status filtern").build(), 11)
                .setItem(new ItemBuilder(Material.PAPER).setName("§8» §bNach Auftraggeber filtern").build(), 13)
                .setItem(new ItemBuilder(Material.PAPER).setName("§8» §bNach Builder filtern").build(), 15)
                .setItem(new ItemBuilder(Material.GRASS).setName("§8» §bAutomatisch importiert").build(), 17).build();

        fillInv(this.filterInv, 9 * 3);

        this.confirmInv = new InventoryAPI("§8» §bWelt löschen", 9 * 3)
                .setItem(new ItemBuilder(Material.STAINED_CLAY).setName("§8» §bFortfahren").setData(5).build(), 11)
                .setItem(new ItemBuilder(Material.STAINED_CLAY).setName("§8» §bAbbrechen").setData(14).build(), 15).build();

        fillInv(this.confirmInv, 9 * 3);

        this.statusInv = new InventoryAPI("§8» §bStatus", 9 * 3)
                .setItem(new ItemBuilder(Material.WOOL).setName("§8» §bIn Bau").setData(1).build(), 11)
                .setItem(new ItemBuilder(Material.WOOL).setName("§8» §bFertiggestellt").setData(4).build(), 13)
                .setItem(new ItemBuilder(Material.WOOL).setName("§8» §bAbgegeben").setData(5).build(), 15).build();

        fillInv(this.statusInv, 9 * 3);

        this.whitelistInv = new InventoryAPI("§8» §bWhitelist", 9 * 3)
                .setItem(new ItemBuilder(Material.STAINED_CLAY).setName("§8» §bSpieler hinzufügen").setData(5).build(), 11)
                .setItem(new ItemBuilder(Material.PAPER).setName("§8» §bWhitelist anzeigen").build(), 13)
                .setItem(new ItemBuilder(Material.STAINED_CLAY).setName("§8» §bSpieler entfernen").setData(14).build(), 15).build();

        fillInv(this.whitelistInv, 9 * 3);
    }

    @EventHandler
    public void handleInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (player.getItemInHand().getType() == Material.REDSTONE_COMPARATOR &&
                    player.getItemInHand().getItemMeta().hasDisplayName() &&
                    player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWorldmanager")) {
                event.setCancelled(true);

                plugin.getInventoryManager().openWorldManager(player);
            }
        }
    }

    @EventHandler
    public void handleClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() != null) {
            if (event.getClickedInventory().getName().equalsIgnoreCase("§8» §bWorldmanager")) {
                event.setCancelled(true);

                if (!isLoaded) {
                    isLoaded = true;

                    loadPages();
                }

                if (!this.filter.containsKey(player)) {
                    this.filter.put(player, 0);
                }

                this.currentPage.put(player, 0);

                if (event.getCurrentItem().getType() == Material.GRASS &&
                        event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWelt erstellen")) {
                    player.sendMessage(plugin.getData().getPrefix() + "§7Bite wähle einen §bWelttypen §7aus§8.");
                    player.openInventory(this.chooseWorldTypeInv);
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                } else if (event.getCurrentItem().getType() == Material.COMPASS &&
                        event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bTeleportieren")) {
                    open(player, "Teleportieren");
                } else if (event.getCurrentItem().getType() == Material.SKULL_ITEM &&
                        event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWelt importieren")) {
                    AnvilGUI anvilGUI = new AnvilGUI(player, anvilClickEvent -> {
                        if (anvilClickEvent.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                            String name = anvilClickEvent.getName();

                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                            if (new File("Worlds//" + name).exists()) {
                                if (name.length() > 2) {
                                    plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                                        AnvilGUI anvilGUI1 = new AnvilGUI(player, anvilClickEvent1 -> {
                                            if (anvilClickEvent1.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                                                String size = anvilClickEvent1.getName();

                                                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                                                if (VaceAPI.getApi().isOnlyNumbers(size)) {
                                                    plugin.getMySQL().create(name, player.getName(), player.getUniqueId(), false, Integer.valueOf(size));
                                                    plugin.getServer().createWorld(new WorldCreator("Worlds//" + name));

                                                    player.sendMessage(plugin.getData().getPrefix() + "§7Die Welt §b" + name + " §7wird importiert§8.");

                                                    teleport(player, "Worlds//" + name);

                                                    loadPages();
                                                } else {
                                                    player.sendMessage(plugin.getData().getPrefix() + "§7Bitte gib eine §bgültige Zahl §7an§8.");
                                                }
                                            } else {
                                                anvilClickEvent1.setWillClose(false);
                                                anvilClickEvent1.setWillDestroy(false);
                                            }
                                        });

                                        anvilGUI1.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, new ItemManager(Material.PAPER).setDisplayName("Größe").build());
                                        anvilGUI1.open();
                                    }, 3L);
                                } else {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Der §bName §7muss mindestens §b2 §7Zeichen lang sein§8.");
                                }
                            } else {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Diese §bWelt §7existiert nicht§8.");

                                File[] files = new File("Worlds").listFiles(File::isDirectory);

                                if (files != null && files.length > 0) {
                                    int len = files.length - 1;

                                    String msg = plugin.getData().getPrefix() + "§b";

                                    for (File file : files) {
                                        if (len > 0) {
                                            msg = msg + file.getName() + "§8, §b";
                                            len--;
                                        } else {
                                            msg = msg + file.getName();
                                        }
                                    }

                                    player.sendMessage(plugin.getData().getPrefix() + "§7Folgende §bWelten §7stehen zur Verfügung§8:");
                                    player.sendMessage(msg);
                                }
                            }
                        } else {
                            anvilClickEvent.setWillClose(false);
                            anvilClickEvent.setWillDestroy(false);
                        }
                    });

                    anvilGUI.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, new ItemManager(Material.PAPER).setDisplayName("Weltenname").build());
                    anvilGUI.open();
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                } else if (event.getCurrentItem().getType() == Material.SKULL_ITEM &&
                        event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWelt unloaden")) {
                    open(player, "Welt unloaden");
                } else if (event.getCurrentItem().getType() == Material.BARRIER &&
                        event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWelt löschen")) {
                    open(player, "Welt löschen");
                } else if (event.getCurrentItem().getType() == Material.REDSTONE_TORCH_ON &&
                        event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bStatus")) {
                    String worldName = player.getWorld().getName().replace("Worlds//", "");

                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                    if (plugin.getMySQL().isWorldExists(worldName)) {
                        if (plugin.getMySQL().getOwner(worldName).equalsIgnoreCase(player.getName()) ||
                                player.hasPermission("vacebuild.admin") || plugin.getMySQL().getWhitelist(worldName).contains(player.getName())) {
                            Inventory inventory = new InventoryAPI("§8» §bStatus", 9 * 3).build();

                            inventory.setContents(this.statusInv.getContents());

                            if (plugin.getMySQL().getStatus(worldName) == 0) {
                                inventory.setItem(11, new ItemManager(inventory.getItem(11)).addEnchantment(Enchantment.DAMAGE_ALL, 1).hideFlags().build());
                            } else if (plugin.getMySQL().getStatus(worldName) == 1) {
                                inventory.setItem(13, new ItemManager(inventory.getItem(13)).addEnchantment(Enchantment.DAMAGE_ALL, 1).hideFlags().build());
                            } else if (plugin.getMySQL().getStatus(worldName) == 2) {
                                inventory.setItem(15, new ItemManager(inventory.getItem(15)).addEnchantment(Enchantment.DAMAGE_ALL, 1).hideFlags().build());
                            }

                            player.openInventory(inventory);
                        } else {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Du kannst diese §bWelt §7nicht bearbeiten§8.");
                        }
                    } else {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Diese §bWelt §7kann nicht bearbeitet werden§8.");
                    }
                } else if (event.getCurrentItem().getType() == Material.PAPER &&
                        event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWhitelist")) {
                    String worldName = player.getWorld().getName().replace("Worlds//", "");

                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                    if (plugin.getMySQL().isWorldExists(worldName)) {
                        if (plugin.getMySQL().getOwner(worldName).equalsIgnoreCase(player.getName()) ||
                                player.hasPermission("vacebuild.admin") || plugin.getMySQL().getWhitelist(worldName).contains(player.getName())) {
                            player.openInventory(this.whitelistInv);
                        } else {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Du kannst diese §bWelt §7nicht bearbeiten§8.");
                        }
                    } else {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Diese §bWelt §7kann nicht bearbeitet werden§8.");
                    }
                }
            } else if (event.getClickedInventory().getName().equalsIgnoreCase("§8» §bWelttyp")) {
                event.setCancelled(true);

                if (event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().getType() != Material.STAINED_GLASS_PANE) {
                    String type = event.getCurrentItem().getItemMeta().getDisplayName().replace("§8» §b", "");

                    HashMap<String, String> map = new HashMap<>();

                    map.put("worldType", type);

                    this.types.put(player, map);

                    player.sendMessage(plugin.getData().getPrefix() + "§7Bite wähle einen §bMaptypen §7aus§8.");
                    player.openInventory(this.chooseTypeInv);
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                }
            } else if (event.getClickedInventory().getName().equalsIgnoreCase("§8» §bMaptyp")) {
                event.setCancelled(true);

                if (event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().getType() != Material.STAINED_GLASS_PANE) {
                    if (event.getCurrentItem().getType() == Material.PAPER &&
                            event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bSonstiges")) {
                        AnvilGUI anvilGUI = new AnvilGUI(player, anvilClickEvent -> {
                            if (anvilClickEvent.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                                String type = anvilClickEvent.getName();

                                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                                if (type.length() == 2) {
                                    HashMap<String, String> map = this.types.get(player);

                                    map.put("mapType", type);

                                    this.types.put(player, map);

                                    player.sendMessage(plugin.getData().getPrefix() + "§7Bite wähle eine §bMapgröße §7aus§8.");

                                    plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () ->
                                            player.openInventory(this.chooseSizeInv), 3L);
                                } else {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Der §bMaptypen §7muss §b2 §7Zeichen lang sein§8.");
                                }
                            } else {
                                anvilClickEvent.setWillClose(false);
                                anvilClickEvent.setWillDestroy(false);
                            }
                        });

                        anvilGUI.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, new ItemManager(Material.PAPER).setDisplayName("Maptyp").build());
                        anvilGUI.open();
                        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                    } else {
                        String type = event.getCurrentItem().getItemMeta().getDisplayName().replace("§8» §b", "");

                        HashMap<String, String> map = this.types.get(player);

                        map.put("mapType", type);

                        this.types.put(player, map);

                        player.sendMessage(plugin.getData().getPrefix() + "§7Bite wähle eine §bMapgröße §7aus§8.");
                        player.openInventory(this.chooseSizeInv);
                        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                    }
                }
            } else if (event.getClickedInventory().getName().equalsIgnoreCase("§8» §bMapgröße")) {
                event.setCancelled(true);

                if (event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().getType() != Material.STAINED_GLASS_PANE) {
                    String rawSize = event.getCurrentItem().getItemMeta().getDisplayName().replace("§8» §b", "").replace("§8", "").replace("§b", "");
                    Integer size = Integer.valueOf(rawSize.substring(0, rawSize.indexOf("x")));

                    HashMap<String, String> map = this.types.get(player);

                    map.put("size", String.valueOf(size));

                    this.types.put(player, map);

                    player.sendMessage(plugin.getData().getPrefix() + "§7Bite schreibe den Namen des §bAuftraggebers§8.");

                    AnvilGUI anvilGUI = new AnvilGUI(player, anvilClickEvent -> {
                        if (anvilClickEvent.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                            String name = anvilClickEvent.getName();

                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                            if (name.length() > 2 && name.length() <= 16) {
                                HashMap<String, String> map1 = this.types.get(player);

                                map1.put("customer", name);

                                this.types.put(player, map1);

                                player.closeInventory();

                                createMap(player);
                            } else {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Der §bName §7muss zwischen §b3 §7und §b16 §7Zeichen lang sein§8.");
                            }
                        } else {
                            anvilClickEvent.setWillClose(false);
                            anvilClickEvent.setWillDestroy(false);
                        }
                    });

                    anvilGUI.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, new ItemManager(Material.PAPER).setDisplayName("Auftraggeber").build());
                    anvilGUI.open();
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                }
            } else if (event.getClickedInventory().getName().equalsIgnoreCase("§8» §bTeleportieren")) {
                event.setCancelled(true);

                if (event.getCurrentItem().getType() != Material.AIR) {
                    if (check(player, event.getCurrentItem(), "Teleportieren")) {
                        String worldName = event.getCurrentItem().getItemMeta().getDisplayName().replace("§8» §b", "");

                        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                        if (worldName.contains("Auto Import")) {
                            worldName = worldName.replace(" §8(§7Auto Import§8)", "");

                            World world = plugin.getServer().getWorld("AutoImport//" + worldName);

                            if (world != null) {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Du wirst auf die Welt §b" + worldName + " §7teleportiert§8.");
                                player.teleport(world.getSpawnLocation());
                            } else {
                                plugin.getServer().createWorld(new WorldCreator("Worlds//" + worldName));

                                teleport(player, "Worlds//" + worldName);

                                player.sendMessage(plugin.getData().getPrefix() + "§7Du wirst auf die Welt §b" + worldName + " §7teleportiert§8.");
                            }
                        } else {
                            if (plugin.getMySQL().isWorldExists(worldName)) {
                                if (plugin.getMySQL().getUUID(worldName).toString().equalsIgnoreCase(player.getUniqueId().toString()) ||
                                        player.hasPermission("vacebuild.srstaff") ||
                                        plugin.getMySQL().getWhitelist(worldName).contains(player.getName()) ||
                                        plugin.getMySQL().getWhitelist(worldName).contains("*")) {
                                    World world = plugin.getServer().getWorld("Worlds//" + worldName);

                                    if (world != null) {
                                        player.sendMessage(plugin.getData().getPrefix() + "§7Du wirst auf die Welt §b" + worldName + " §7teleportiert§8.");
                                        player.teleport(world.getSpawnLocation());
                                    } else {
                                        plugin.getServer().createWorld(new WorldCreator("Worlds//" + worldName));

                                        teleport(player, "Worlds//" + worldName);

                                        player.sendMessage(plugin.getData().getPrefix() + "§7Du wirst auf die Welt §b" + worldName + " §7teleportiert§8.");
                                    }
                                } else {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Du kannst diese §bWelt §7nicht betreten§8.");
                                }
                            } else {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Diese §bWelt §7existiert nicht§8.");
                            }
                        }
                    }
                }
            } else if (event.getClickedInventory().getName().equalsIgnoreCase("§8» §bFilter")) {
                event.setCancelled(true);

                if (event.getCurrentItem().getType() != Material.AIR) {
                    if (event.getCurrentItem().getType() == Material.BARRIER &&
                            event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bAlle Maps anzeigen")) {
                        this.filter.put(player, 0);

                        open(player, this.from.get(player));
                    } else if (event.getCurrentItem().getType() == Material.WOOL) {
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bNach Status filtern")) {
                            this.filter.put(player, 1);

                            open(player, this.from.get(player));
                        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bNach Status filtern §8(§7In Bau§8)")) {
                            this.filter.put(player, 2);

                            open(player, this.from.get(player));
                        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bNach Status filtern §8(§7Fertiggestellt§8)")) {
                            this.filter.put(player, 3);

                            open(player, this.from.get(player));
                        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bNach Status filtern §8(§7Abgegeben§8)")) {
                            this.filter.put(player, 1);

                            open(player, this.from.get(player));
                        }
                    } else if (event.getCurrentItem().getType() == Material.PAPER &&
                            event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§8» §bNach Auftraggeber filtern")) {
                        AnvilGUI anvilGUI = new AnvilGUI(player, anvilClickEvent -> {
                            if (anvilClickEvent.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                                String name = anvilClickEvent.getName();

                                this.filter.put(player, 4);
                                this.name.put(player, name);

                                plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () ->
                                        open(player, this.from.get(player)), 3L);
                            } else {
                                anvilClickEvent.setWillClose(false);
                                anvilClickEvent.setWillDestroy(false);
                            }
                        });

                        anvilGUI.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, new ItemManager(Material.PAPER).setDisplayName("Auftraggeber").build());
                        anvilGUI.open();
                        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                    } else if (event.getCurrentItem().getType() == Material.PAPER &&
                            event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§8» §bNach Builder filtern")) {
                        AnvilGUI anvilGUI = new AnvilGUI(player, anvilClickEvent -> {
                            if (anvilClickEvent.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                                String name = anvilClickEvent.getName();

                                this.filter.put(player, 5);
                                this.name.put(player, name);

                                plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () ->
                                        open(player, this.from.get(player)), 3L);
                            } else {
                                anvilClickEvent.setWillClose(false);
                                anvilClickEvent.setWillDestroy(false);
                            }
                        });

                        anvilGUI.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, new ItemManager(Material.PAPER).setDisplayName("Builder").build());
                        anvilGUI.open();
                        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                    } else if (event.getCurrentItem().getType() == Material.GRASS &&
                            event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bAutomatisch importiert")) {
                        this.filter.put(player, 6);

                        open(player, this.from.get(player));
                    }
                }
            } else if (event.getClickedInventory().getName().equalsIgnoreCase("§8» §bWelt unloaden")) {
                event.setCancelled(true);

                if (event.getCurrentItem().getType() != Material.AIR) {
                    if (check(player, event.getCurrentItem(), "Welt unloaden")) {
                        String worldName = event.getCurrentItem().getItemMeta().getDisplayName().replace("§8» §b", "");

                        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                        if (plugin.getMySQL().isWorldExists(worldName)) {
                            if (plugin.getMySQL().getUUID(worldName).toString().equalsIgnoreCase(player.getUniqueId().toString()) ||
                                    player.hasPermission("vacebuild.admin")) {
                                player.closeInventory();
                                player.sendMessage(plugin.getData().getPrefix() + "§7Die Welt §b" + worldName + " §7wurde unloaded§8.");

                                World world = plugin.getServer().getWorld("Worlds//" + worldName);

                                if (world != null) {
                                    for (Player all : world.getPlayers()) {
                                        all.sendMessage(plugin.getData().getPrefix() + "§7Deine §baktuelle Welt §7wurde unloaded§8.");
                                        all.teleport(VaceAPI.getApi().getLocationAPI().getLocation("Spawn.Spawn"));
                                    }
                                }

                                plugin.getServer().unloadWorld(world, true);
                                plugin.getMySQL().delete(worldName);

                                loadPages();
                            } else {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Du kannst diese §bWelt §7nicht unloaden§8.");
                            }
                        } else {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Diese §bWelt §7existiert nicht oder du kannst sie nicht unloaden§8.");
                        }
                    }
                }
            } else if (event.getClickedInventory().getName().equalsIgnoreCase("§8» §bWelt löschen")) {
                event.setCancelled(true);

                if (event.getCurrentItem().getType() != Material.AIR) {
                    if (check(player, event.getCurrentItem(), "Welt löschen")) {
                        if (event.getCurrentItem().getType() == Material.STAINED_CLAY) {
                            String worldName = this.world.get(player);

                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bFortfahren")) {
                                if (plugin.getMySQL().isWorldExists(worldName)) {
                                    if (plugin.getMySQL().getUUID(worldName).toString().equalsIgnoreCase(player.getUniqueId().toString()) ||
                                            player.hasPermission("vacebuild.admin")) {
                                        player.closeInventory();

                                        try {
                                            World world = plugin.getServer().getWorld("Worlds//" + worldName);

                                            if (world != null) {
                                                for (Player all : world.getPlayers()) {
                                                    all.sendMessage(plugin.getData().getPrefix() + "§7Deine §baktuelle Welt §7wurde gelöscht§8.");
                                                    all.teleport(VaceAPI.getApi().getLocationAPI().getLocation("Spawn.Spawn"));
                                                }
                                            }

                                            plugin.getServer().unloadWorld(world, true);
                                            plugin.getMySQL().delete(worldName);

                                            loadPages();
                                            FileUtils.moveDirectory(new File("Worlds//" + worldName), new File("WorldBackups//" + worldName));

                                            player.sendMessage(plugin.getData().getPrefix() + "§7Die Welt §b" + worldName + " §7wurde gelöscht§8.");
                                        } catch (IOException e) {
                                            player.sendMessage(plugin.getData().getPrefix() + "§7Es ist ein §bFehler §7aufgetreten");
                                        }
                                    } else {
                                        player.sendMessage(plugin.getData().getPrefix() + "§7Du kannst diese §bWelt §7nicht löschen§8.");
                                    }
                                } else {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Diese §bWelt §7existiert nicht oder du kannst sie nicht löschen§8.");
                                }
                            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bAbbrechen")) {
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                                player.sendMessage(plugin.getData().getPrefix() + "§7Der §bVorgang §7wurde abgebrochen§8.");
                            }
                        } else {
                            String worldName = event.getCurrentItem().getItemMeta().getDisplayName().replace("§8» §b", "");

                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                            if (plugin.getMySQL().isWorldExists(worldName)) {
                                if (plugin.getMySQL().getUUID(worldName).toString().equalsIgnoreCase(player.getUniqueId().toString()) ||
                                        player.hasPermission("vacebuild.admin")) {
                                    player.openInventory(this.confirmInv);

                                    this.world.put(player, worldName);
                                } else {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Du kannst diese §bWelt §7nicht löschen§8.");
                                }
                            } else {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Diese §bWelt §7existiert nicht oder du kannst sie nicht löschen§8.");
                            }
                        }
                    }
                }
            } else if (event.getClickedInventory().getName().equalsIgnoreCase("§8» §bStatus")) {
                event.setCancelled(true);

                String worldName = player.getWorld().getName().replace("Worlds//", "");

                if (event.getCurrentItem().getType() == Material.WOOL) {
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                    if (plugin.getMySQL().isWorldExists(worldName)) {
                        if (plugin.getMySQL().getOwner(worldName).equalsIgnoreCase(player.getName()) ||
                                player.hasPermission("vacebuild.admin") || plugin.getMySQL().getWhitelist(worldName).contains(player.getName())) {
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bIn Bau")) {
                                player.closeInventory();

                                if (plugin.getMySQL().getStatus(worldName) == 0) {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bStatus §7ist bereits gesetzt§8.");
                                } else {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Dieser Status wurde auf §8'§bIn Bau§8' §7gesetzt§8.");
                                    plugin.getMySQL().setStatus(worldName, 0);

                                    for (Player all : plugin.getServer().getOnlinePlayers()) {
                                        plugin.getScoreboardManager().updateScoreboard(all);
                                    }

                                    loadPages();
                                }
                            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bFertiggestellt")) {
                                player.closeInventory();

                                if (plugin.getMySQL().getStatus(worldName) == 1) {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bStatus §7ist bereits gesetzt§8.");
                                } else {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Dieser Status wurde auf §8'§bFertiggestellt§8' §7gesetzt§8.");
                                    plugin.getMySQL().setStatus(worldName, 1);

                                    for (Player all : plugin.getServer().getOnlinePlayers()) {
                                        plugin.getScoreboardManager().updateScoreboard(all);
                                    }

                                    loadPages();
                                }
                            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bAbgegeben")) {
                                player.closeInventory();

                                if (plugin.getMySQL().getStatus(worldName) == 2) {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bStatus §7ist bereits gesetzt§8.");
                                } else {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Dieser Status wurde auf §8'§bAbgegeben§8' §7gesetzt§8.");
                                    plugin.getMySQL().setStatus(worldName, 2);

                                    for (Player all : plugin.getServer().getOnlinePlayers()) {
                                        plugin.getScoreboardManager().updateScoreboard(all);
                                    }

                                    loadPages();
                                }
                            }
                        } else {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Du kannst diese §bWelt §7nicht bearbeiten§8.");
                        }
                    } else {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Diese §bWelt §7kann nicht bearbeitet werden§8.");
                    }
                }
            } else if (event.getClickedInventory().getName().equalsIgnoreCase("§8» §bWhitelist")) {
                event.setCancelled(true);

                String worldName = player.getWorld().getName().replace("Worlds//", "");

                if (event.getCurrentItem().getType() == Material.STAINED_CLAY || event.getCurrentItem().getType() == Material.PAPER) {
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                    if (plugin.getMySQL().isWorldExists(worldName)) {
                        if (plugin.getMySQL().getOwner(worldName).equalsIgnoreCase(player.getName()) ||
                                player.hasPermission("vacebuild.admin") || plugin.getMySQL().getWhitelist(worldName).contains(player.getName())) {
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bSpieler hinzufügen")) {
                                AnvilGUI anvilGUI = new AnvilGUI(player, anvilClickEvent -> {
                                    if (anvilClickEvent.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                                        String name = anvilClickEvent.getName();

                                        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                                        if (name.equalsIgnoreCase("*")) {
                                            if (!plugin.getMySQL().getWhitelist(worldName).contains("*")) {
                                                plugin.getMySQL().setWhitelist(worldName, plugin.getMySQL().getRawWhitelist(worldName) + "*;");

                                                player.sendMessage(plugin.getData().getPrefix() + "§bAlle Spieler §7wurden " +
                                                        "zur Whitelist hinzugefügt§8.");
                                            } else {
                                                player.sendMessage(plugin.getData().getPrefix() + "§bAlle Spieler §7sind bereits auf der Whitelist§8.");
                                            }
                                        } else if (name.length() > 2 && name.length() <= 16 && isAlphabetic(name)) {
                                            if (!plugin.getMySQL().getWhitelist(worldName).contains(name)) {
                                                plugin.getMySQL().setWhitelist(worldName, plugin.getMySQL().getRawWhitelist(worldName) + name + ";");

                                                player.sendMessage(plugin.getData().getPrefix() + "§7Der Spieler §b" + name +
                                                        " §7wurde zur Whitelist hinzugefügt§8.");
                                            } else {
                                                player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7ist bereits auf der Whitelist§8.");
                                            }
                                        } else {
                                            player.sendMessage(plugin.getData().getPrefix() + "§7Bitte gib einen §bgültigen " +
                                                    "Spielernamen §7an§8. §7Verwende §8'§b*§8' §7um alle Spieler zu whitelisten§8.");
                                        }
                                    } else {
                                        anvilClickEvent.setWillClose(false);
                                        anvilClickEvent.setWillDestroy(false);
                                    }
                                });

                                anvilGUI.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, new ItemManager(Material.PAPER).setDisplayName("Spieler").build());
                                anvilGUI.open();
                            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bSpieler entfernen")) {
                                AnvilGUI anvilGUI = new AnvilGUI(player, anvilClickEvent -> {
                                    if (anvilClickEvent.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                                        String name = anvilClickEvent.getName();

                                        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                                        if (name.equalsIgnoreCase("*")) {
                                            if (plugin.getMySQL().getWhitelist(worldName).contains("*")) {
                                                plugin.getMySQL().setWhitelist(worldName, plugin.getMySQL().getRawWhitelist(worldName).replace("*;", ""));

                                                player.sendMessage(plugin.getData().getPrefix() + "§bAlle Spieler §7wurden " +
                                                        "von der Whitelist entfernt§8.");
                                            } else {
                                                player.sendMessage(plugin.getData().getPrefix() + "§bAlle Spieler §7sind nicht auf der Whitelist§8.");
                                            }
                                        } else if (name.length() > 2 && name.length() <= 16 && isAlphabetic(name)) {
                                            if (plugin.getMySQL().getWhitelist(worldName).contains(name)) {
                                                plugin.getMySQL().setWhitelist(worldName, plugin.getMySQL().getRawWhitelist(worldName).replace(name + ";", ""));

                                                player.sendMessage(plugin.getData().getPrefix() + "§7Der Spieler §b" + name +
                                                        " §7wurde von der Whitelist entfernt§8.");
                                            } else {
                                                player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7ist nicht auf der Whitelist§8.");
                                            }
                                        } else {
                                            player.sendMessage(plugin.getData().getPrefix() + "§7Bitte gib einen §bgültigen " +
                                                    "Spielernamen §7an§8. §7Verwende §8'§b*§8' §7um alle Spieler von der Whitelist zu entfernen§8.");
                                        }
                                    } else {
                                        anvilClickEvent.setWillClose(false);
                                        anvilClickEvent.setWillDestroy(false);
                                    }
                                });

                                anvilGUI.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, new ItemManager(Material.PAPER).setDisplayName("Spieler").build());
                                anvilGUI.open();
                            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWhitelist anzeigen")) {
                                player.closeInventory();

                                if (plugin.getMySQL().getRawWhitelist(worldName).length() > 0) {
                                    int len = plugin.getMySQL().getWhitelist(worldName).size() - 1;

                                    String msg = plugin.getData().getPrefix() + "§b";

                                    for (String string : plugin.getMySQL().getWhitelist(worldName)) {
                                        if (len > 0) {
                                            msg = msg + string + "§8, §b";
                                            len--;
                                        } else {
                                            msg = msg + string;
                                        }
                                    }

                                    player.sendMessage(plugin.getData().getPrefix() + "§7Folgende Spieler sind auf der §bWhitelist§8:");
                                    player.sendMessage(msg);
                                } else {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Es sind keine Spieler auf der §bWhitelist§8.");
                                }
                            }
                        } else {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Du kannst diese §bWelt §7nicht bearbeiten§8.");
                        }
                    } else {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Diese §bWelt §7kann nicht bearbeitet werden§8.");
                    }
                }
            }
        }
    }

    private boolean check(Player player, ItemStack itemStack, String name) {
        if (itemStack.getType() == Material.SKULL_ITEM) {
            int current = currentPage.get(player);

            if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bZurück")) {
                if (current > 0) {
                    currentPage.put(player, current - 1);

                    open(player, name);
                } else {
                    player.sendMessage(plugin.getData().getPrefix() + "§7Du bist bereits auf der §bersten Seite§8.");
                }

                return false;
            } else if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWeiter")) {
                if (this.pages.get(current + 1) != null) {
                    currentPage.put(player, current + 1);

                    open(player, name);
                } else {
                    player.sendMessage(plugin.getData().getPrefix() + "§7Du bist bereits auf der §bletzten Seite§8.");
                }

                return false;
            }

            return true;
        } else if (itemStack.getType() == Material.HOPPER) {
            openFilter(player);

            this.from.put(player, name);

            return false;
        } else if (itemStack.getType() == Material.DIODE) {
            if (new File("PrivateWorlds//" + player.getUniqueId().toString()).exists()) {
                World world = plugin.getServer().getWorld("PrivateWorlds//" + player.getUniqueId().toString());

                player.sendMessage(plugin.getData().getPrefix() + "§7Du wirst auf deine §bprivate Welt §7teleportiert§8.");

                if (world != null) {
                    player.closeInventory();
                    player.teleport(world.getSpawnLocation());
                } else {
                    plugin.getServer().createWorld(new WorldCreator("PrivateWorlds//" + player.getUniqueId().toString()));

                    teleport(player, "PrivateWorlds//" + player.getUniqueId().toString());
                }
            } else {
                player.sendMessage(plugin.getData().getPrefix() + "§7Es wird eine §bprivate Welt §7erstellt§8.");

                try {
                    FileUtils.copyDirectory(new File("WorldTemplates//Empty"), new File("PrivateWorlds//" + player.getUniqueId().toString()));

                    plugin.getServer().createWorld(new WorldCreator("PrivateWorlds//" + player.getUniqueId().toString()));

                    player.sendMessage(plugin.getData().getPrefix() + "§7Deine §bprivate Welt §7wurde erstellt§8.");

                    teleport(player, "PrivateWorlds//" + player.getUniqueId().toString());
                } catch (IOException e) {
                    player.sendMessage(plugin.getData().getPrefix() + "§7Es ist ein §bFehler §7aufgetreten");
                }
            }

            return false;
        } else if (itemStack.getType() == Material.STAINED_GLASS_PANE) {
            return false;
        } else {
            return true;
        }
    }

    private void createMap(Player player) {
        String worldName = getMapType(this.types.get(player).get("mapType")) + "-" + this.types.get(player).get("customer") + "-" + player.getName();
        String file = getWorldFile(this.types.get(player).get("worldType"));
        int size = Integer.valueOf(this.types.get(player).get("size"));

        if (!plugin.getMySQL().isWorldExists(worldName)) {
            try {
                FileUtils.copyDirectory(new File(file), new File("Worlds//" + worldName));

                plugin.getServer().createWorld(new WorldCreator("Worlds//" + worldName));
                plugin.getMySQL().create(worldName, player.getName(), player.getUniqueId(), false, size);

                player.sendMessage(plugin.getData().getPrefix() + "§7Deine §bWelt §7wurde erfolgreich erstellt §8(§7" + size + "§8x§7" + size + " Blöcke§8)");

                teleport(player, "Worlds//" + worldName);

                loadPages();
            } catch (IOException e) {
                player.sendMessage(plugin.getData().getPrefix() + "§7Es ist ein §bFehler §7aufgetreten§8.");
            }
        } else {
            player.sendMessage(plugin.getData().getPrefix() + "§7Diese §bWelt §7existiert bereits§8.");
        }
    }

    private String getWorldFile(String worldType) {
        switch (worldType) {
            case "Flach":
                return "WorldTemplates//Flat";

            case "Leer":
                return "WorldTemplates//Empty";
        }

        return "WorldTemplates//Empty";
    }

    private String getMapType(String type) {
        switch (type) {
            case "Lobby":
                return "LB";

            case "Wartelobby":
                return "WL";

            case "Minigame":
                return "MG";

            default:
                return type;
        }
    }

    private void fillInv(Inventory inventory, int size) {
        ItemStack glass = new ItemManager(Material.STAINED_GLASS_PANE).setDisplayName("§b").setData(7).build();

        if (size == 9 * 3) {
            for (int i = 0; i <= 8; i++) {
                inventory.setItem(i, glass);
            }

            for (int i = 18; i <= 26; i++) {
                inventory.setItem(i, glass);
            }
        } else if (size == 9 * 4) {
            for (int i = 0; i <= 8; i++) {
                inventory.setItem(i, glass);
            }

            for (int i = 27; i <= 35; i++) {
                inventory.setItem(i, glass);
            }
        } else if (size == 9 * 5) {
            for (int i = 0; i <= 8; i++) {
                inventory.setItem(i, glass);
            }

            for (int i = 36; i <= 44; i++) {
                inventory.setItem(i, glass);
            }
        }
    }

    private void teleport(Player player, String world) {
        plugin.getServer().getScheduler().runTaskLater(plugin, () ->
                player.teleport(plugin.getServer().getWorld(world).getSpawnLocation()), 5L);
    }

    private void open(Player player, String name) {
        int page = currentPage.get(player);

        Inventory inventory = plugin.getServer().createInventory(null, 9 * 5, "§8» §b" + name);

        inventory.setContents(this.defaultInv.getContents());

        int slot = 10;

        for (ItemStack itemStack : this.pages.get(page)) {
            if (this.filter.get(player) > 0) {
                if (isFiltered(player, itemStack.getItemMeta().getDisplayName().replace("§8» §b", ""))) {
                    inventory.setItem(slot, itemStack);

                    slot++;
                    if (slot == 17) {
                        slot = 19;
                    } else if (slot == 18) {
                        slot = 19;
                    } else if (slot == 26) {
                        slot = 28;
                    } else if (slot == 27) {
                        slot = 28;
                    }
                }
            } else {
                inventory.setItem(slot, itemStack);

                slot++;
                if (slot == 17) {
                    slot = 19;
                } else if (slot == 18) {
                    slot = 19;
                } else if (slot == 26) {
                    slot = 28;
                } else if (slot == 27) {
                    slot = 28;
                }
            }
        }

        player.openInventory(inventory);
        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
    }

    private void loadPages() {
        List<String> worlds = new ArrayList<>();

        for (String world : plugin.getMySQL().worlds) {
            Collections.addAll(worlds, world);
        }

        for (World world : plugin.getServer().getWorlds()) {
            if (world.getName().startsWith("AutoImport//")) {
                worlds.add(world.getName());
            }
        }

        this.pages.clear();

        if ((worlds.size() - 21) > 0) {
            int pages = getPages();

            for (int i = 0; i <= pages; i++) {
                ArrayList<ItemStack> items = new ArrayList<>();

                for (int i1 = 0; i1 < worlds.size(); i1++) {
                    int min = 21 * i;
                    int max = min + 21;

                    if (i1 >= min && i1 < max) {
                        String world = worlds.get(i1);

                        if (world.startsWith("AutoImport//")) {
                            items.add(new ItemManager(VaceAPI.getApi().getSkullAPI().getSkull("§8» §b" +
                                            world.replace("AutoImport//", "") + " §8(§7Auto Import§8)",
                                    "http://textures.minecraft.net/texture/c95d37993e594082678472bf9d86823413c250d4332a2c7d8c52de4976b362")).build());
                        } else {
                            items.add(new ItemManager(VaceAPI.getApi().getSkullAPI().loadSkull(plugin.getMySQL().getUUID(world).toString(), "§8» §b" + world))
                                    .addLoreAll(Arrays.asList("§8§m--------------------", "§b",
                                            "§8» §7Besitzer§8: §b" + plugin.getMySQL().getOwner(world),
                                            "§8» §7Größe§8: §b" + plugin.getMySQL().getSize(world) + "§8x§b" + plugin.getMySQL().getSize(world),
                                            "§8» §7Status§8: " + getWorldStatus(world),
                                            "§b", "§8§m--------------------")).build());
                        }
                    }
                }

                this.pages.put(i, items);
            }
        } else {
            ArrayList<ItemStack> items = new ArrayList<>();
            HashMap<Integer, ArrayList<ItemStack>> filtered = new HashMap<>();

            filtered.put(1, new ArrayList<>());
            filtered.put(2, new ArrayList<>());
            filtered.put(3, new ArrayList<>());
            filtered.put(4, new ArrayList<>());
            filtered.put(5, new ArrayList<>());
            filtered.put(6, new ArrayList<>());

            for (String world : worlds) {
                if (world.startsWith("AutoImport//")) {
                    items.add(new ItemManager(VaceAPI.getApi().getSkullAPI().getSkull("§8» §b" +
                                    world.replace("AutoImport//", "") + " §8(§7Auto Import§8)",
                            "http://textures.minecraft.net/texture/c95d37993e594082678472bf9d86823413c250d4332a2c7d8c52de4976b362")).build());

                    filtered.get(6).add(new ItemManager(VaceAPI.getApi().getSkullAPI().getSkull("§8» §b" +
                                    world.replace("AutoImport//", "") + " §8(§7Auto Import§8)",
                            "http://textures.minecraft.net/texture/c95d37993e594082678472bf9d86823413c250d4332a2c7d8c52de4976b362")).build());
                } else {
                    items.add(new ItemManager(VaceAPI.getApi().getSkullAPI().loadSkull(plugin.getMySQL().getUUID(world).toString(), "§8» §b" + world))
                            .addLoreAll(Arrays.asList("§8§m--------------------", "§b",
                                    "§8» §7Besitzer§8: §b" + plugin.getMySQL().getOwner(world),
                                    "§8» §7Größe§8: §b" + plugin.getMySQL().getSize(world) + "§8x§b" + plugin.getMySQL().getSize(world),
                                    "§8» §7Status§8: " + getWorldStatus(world),
                                    "§b", "§8§m--------------------")).build());
                }
            }

            this.pages.put(0, items);
        }
    }

    private boolean isFiltered(Player player, String world) {
        int filter = this.filter.get(player);

        if (plugin.getMySQL().isWorldExists(world)) {
            if (filter == 1) {
                return plugin.getMySQL().getStatus(world) == 0;
            } else if (filter == 2) {
                return plugin.getMySQL().getStatus(world) == 1;
            } else if (filter == 3) {
                return plugin.getMySQL().getStatus(world) == 2;
            } else if (filter == 4) {
                return world.contains("-" + this.name.get(player) + "-");
            } else if (filter == 5) {
                return plugin.getMySQL().getOwner(world).equalsIgnoreCase(this.name.get(player));
            }
        }

        if (filter == 6) {
            return world.contains("Auto Import");
        }

        return false;
    }

    private int getPages() {
        int worlds = plugin.getMySQL().worlds.size();

        for (World world : plugin.getServer().getWorlds()) {
            if (world.getName().startsWith("AutoImport//")) {
                worlds++;
            }
        }

        if (worlds > 21) {
            int pages = 0;
            boolean end = true;

            while (end) {
                if (worlds - 21 >= 0) {
                    worlds -= 21;
                    pages++;
                } else {
                    end = false;
                    break;
                }
            }

            return pages;
        } else {
            return 0;
        }
    }

    private String getWorldStatus(String world) {
        if (plugin.getMySQL().getStatus(world) == 0) {
            return "§6In Bau";
        } else if (plugin.getMySQL().getStatus(world) == 1) {
            return "§eFertiggestellt";
        } else if (plugin.getMySQL().getStatus(world) == 2) {
            return "§aAbgegeben";
        } else {
            return "§cNicht gefunden";
        }
    }

    private void openFilter(Player player) {
        Inventory inventory = new InventoryAPI("§8» §bFilter", 9 * 3).build();

        inventory.setContents(this.filterInv.getContents());

        if (this.filter.get(player) == 0) {
            inventory.setItem(9, new ItemManager(inventory.getItem(9)).addEnchantment(Enchantment.DAMAGE_ALL, 1).hideFlags().build());
        } else if (this.filter.get(player) == 1) {
            inventory.setItem(11, new ItemManager(inventory.getItem(11)).setData(1).setDisplayName("§8» §bNach Status filtern §8(§7In Bau§8)")
                    .addEnchantment(Enchantment.DAMAGE_ALL, 1).hideFlags().build());
        } else if (this.filter.get(player) == 2) {
            inventory.setItem(11, new ItemManager(inventory.getItem(11)).setData(4).setDisplayName("§8» §bNach Status filtern §8(§7Fertiggestellt§8)")
                    .addEnchantment(Enchantment.DAMAGE_ALL, 1).hideFlags().build());
        } else if (this.filter.get(player) == 3) {
            inventory.setItem(11, new ItemManager(inventory.getItem(11)).setData(5).setDisplayName("§8» §bNach Status filtern §8(§7Abgegeben§8)")
                    .addEnchantment(Enchantment.DAMAGE_ALL, 1).hideFlags().build());
        } else if (this.filter.get(player) == 4) {
            inventory.setItem(13, new ItemManager(inventory.getItem(13)).setDisplayName("§8» §bNach Auftraggeber filtern §8(§7" + this.name.get(player) + "§8)")
                    .addEnchantment(Enchantment.DAMAGE_ALL, 1).hideFlags().build());
        } else if (this.filter.get(player) == 5) {
            inventory.setItem(15, new ItemManager(inventory.getItem(15)).setDisplayName("§8» §bNach Builder filtern §8(§7" + this.name.get(player) + "§8)")
                    .addEnchantment(Enchantment.DAMAGE_ALL, 1).hideFlags().build());
        } else if (this.filter.get(player) == 6) {
            inventory.setItem(17, new ItemManager(inventory.getItem(17)).setDisplayName("§8» §bAutomatisch importiert")
                    .addEnchantment(Enchantment.DAMAGE_ALL, 1).hideFlags().build());
        }

        player.openInventory(inventory);
        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
    }

    public boolean isAlphabetic(String string) {
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) != '_' && !Character.isAlphabetic(string.charAt(i))) {
                return false;
            }
        }

        return true;
    }

}
