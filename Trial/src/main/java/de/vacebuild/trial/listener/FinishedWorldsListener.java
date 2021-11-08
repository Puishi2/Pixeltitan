package de.vacebuild.trial.listener;

import de.vacebuild.api.main.VaceAPI;
import de.vacebuild.api.utils.InventoryAPI;
import de.vacebuild.api.utils.ItemManager;
import de.vacebuild.trial.main.Trialsystem;
import de.vacebuild.trial.utils.EditWorldEvent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class FinishedWorldsListener implements Listener {

    private Trialsystem plugin;
    private Inventory defaultInv;
    private HashMap<Player, Integer> currentPage = new HashMap<>();
    private HashMap<Integer, ArrayList<ItemStack>> pages = new HashMap<>();
    private boolean isLoaded = false;

    public FinishedWorldsListener(Trialsystem plugin) {
        this.plugin = plugin;

        ItemStack glass = new ItemManager(Material.STAINED_GLASS_PANE).setDisplayName("§b").setData(7).build();

        this.defaultInv = new InventoryAPI("§8» §bFertige Welten", 9 * 5).setItem(glass, 0).setItem(glass, 1).setItem(glass, 2)
                .setItem(glass, 3).setItem(glass, 4).setItem(glass, 5).setItem(glass, 6).setItem(glass, 7).setItem(glass, 8)
                .setItem(glass, 36).setItem(glass, 37).setItem(glass, 38).setItem(glass, 40).setItem(glass, 42).setItem(glass, 43)
                .setItem(glass, 44).setItem(VaceAPI.getApi().getSkullAPI().getSkull("§8» §bZurück",
                        "http://textures.minecraft.net/texture/8652e2b936ca8026bd28651d7c9f2819d2e923697734d18dfdb13550f8fdad5f"), 39)
                .setItem(VaceAPI.getApi().getSkullAPI().getSkull("§8» §bWeiter",
                        "http://textures.minecraft.net/texture/2a3b8f681daad8bf436cae8da3fe8131f62a162ab81af639c3e0644aa6abac2f"), 41).build();
    }

    @EventHandler
    public void handleInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (player.getItemInHand().getType() == Material.COMPASS &&
                    player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bFertige Welten")) {
                if (player.hasPermission("vacebuild.srstaff")) {
                    if (!isLoaded) {
                        isLoaded = true;
                        loadPages();
                    }

                    currentPage.put(player, 0);

                    if (plugin.getMySQL().finishedWorlds.size() > 0) {
                        open(player);
                    } else {
                        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                        player.sendMessage(plugin.getData().getPrefix() + "§7Es gibt keine §bunbearbeiteten Welten§8.");
                    }
                }
            }
        }
    }

    @EventHandler
    public void handleClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() != null && event.getClickedInventory().getName().equalsIgnoreCase("§8» §bFertige Welten")) {
            event.setCancelled(true);

            if (event.getCurrentItem().getType() == Material.SKULL_ITEM) {
                if (check(player, event.getCurrentItem())) {
                    String name = event.getCurrentItem().getItemMeta().getDisplayName().replace("§8» §b", "");

                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                    if (VaceAPI.getApi().getPlayerAPI().isPlayerExists(name)) {
                        UUID uuid = VaceAPI.getApi().getPlayerAPI().getUUID(name);

                        if (plugin.getServer().getWorld("Worlds//" + uuid.toString()) != null) {
                            player.teleport(plugin.getServer().getWorld("Worlds//" + uuid.toString()).getSpawnLocation());
                        } else {
                            plugin.getServer().createWorld(new WorldCreator("Worlds//" + uuid.toString()));

                            plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () ->
                                player.teleport(plugin.getServer().getWorld("Worlds//" + uuid.toString()).getSpawnLocation())
                            , 5L);
                        }

                        player.sendMessage(plugin.getData().getPrefix() + "§7Du wirst auf die Welt von §b" + name + " §7teleportiert§8.");
                    } else {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7war noch nie auf dem Server§8.");
                    }
                }
            }
        }
    }

    @EventHandler
    public void handleWorldFinish(EditWorldEvent event) {
        loadPages();
    }

    private boolean check(Player player, ItemStack itemStack) {
        if (itemStack.getType() == Material.SKULL_ITEM) {
            int current = currentPage.get(player);

            if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bZurück")) {
                if (current > 0) {
                    currentPage.put(player, current - 1);

                    open(player);
                } else {
                    player.sendMessage(plugin.getData().getPrefix() + "§7Du bist bereits auf der §bersten Seite§8.");
                }

                return false;
            } else if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWeiter")) {
                if (this.pages.get(current + 1) != null) {
                    currentPage.put(player, current + 1);

                    open(player);
                } else {
                    player.sendMessage(plugin.getData().getPrefix() + "§7Du bist bereits auf der §bletzten Seite§8.");
                }

                return false;
            }

            return true;
        } else if (itemStack.getType() == Material.STAINED_GLASS_PANE) {
            return false;
        } else {
            return true;
        }
    }

    private void open(Player player) {
        int page = currentPage.get(player);

        Inventory inventory = plugin.getServer().createInventory(null, 9 * 5, "§8» §bFertige Welten");

        inventory.setContents(this.defaultInv.getContents());

        int slot = 10;

        for (ItemStack itemStack : this.pages.get(page)) {
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

        player.openInventory(inventory);
        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
    }

    private void loadPages() {
        List<String> worlds = new ArrayList<>();

        for (String world : plugin.getMySQL().finishedWorlds) {
            Collections.addAll(worlds, world);
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
                        String uuid = worlds.get(i1);

                        items.add(new ItemManager(VaceAPI.getApi().getSkullAPI().loadSkull(uuid, "§8» §b" + VaceAPI.getApi()
                                .getPlayerAPI().getName(UUID.fromString(uuid)))).build());
                    }
                }

                this.pages.put(i, items);
            }
        } else {
            ArrayList<ItemStack> items = new ArrayList<>();

            for (String uuid : worlds) {
                items.add(new ItemManager(VaceAPI.getApi().getSkullAPI().loadSkull(uuid, "§8» §b" + VaceAPI.getApi()
                        .getPlayerAPI().getName(UUID.fromString(uuid)))).build());
            }

            this.pages.put(0, items);
        }
    }

    private int getPages() {
        int plots = plugin.getMySQL().finishedWorlds.size();

        if (plots > 21) {
            int pages = 0;
            boolean end = true;

            while (end) {
                if (plots - 21 >= 0) {
                    plots -= 21;
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

}
