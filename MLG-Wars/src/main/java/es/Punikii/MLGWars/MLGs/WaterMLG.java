package es.Punikii.MLGWars.MLGs;

import es.Punikii.MLGWars.Coins.Coins;
import es.Punikii.MLGWars.Level.LevelManager;
import es.Punikii.MLGWars.Locations.LocationManager;
import es.Punikii.MLGWars.MLGWars;
import es.Punikii.MLGWars.gamestate.GameStates;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WaterMLG implements Listener{

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (MLGWars.getInstance().getGameStates() == GameStates.INGAME) {

        ItemStack Cobweb = new ItemStack(Material.WEB);
        ItemMeta webMeta = Cobweb.getItemMeta();
        webMeta.setDisplayName("§8» §2CobwebMLG");
        Cobweb.setItemMeta(webMeta);

        ItemStack Water = new ItemStack(Material.WATER_BUCKET);
        ItemMeta WatMeta = Water.getItemMeta();
        WatMeta.setDisplayName("§8» §bWaterMLG");
        Water.setItemMeta(WatMeta);

        if (p.getLocation().getBlock().getType() == Material.WATER
                || (p.getLocation().getBlock().getType() == Material.STATIONARY_WATER)) {
            p.teleport(LocationManager.getLocation("Pos2"));
            p.getInventory().clear();
            p.getInventory().setItem(0, Cobweb);
            p.sendTitle("§6+10", "§cCoins");

            Coins.coins.put(p, 10);
            LevelManager.level.put(p, 2);

        }
        if(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
            p.teleport(LocationManager.getLocation("Pos1"));
            p.getInventory().setItem(0, Water);
            Coins.coins.put(p, Coins.coins.get(p) - 2);
        }
        }
    }
}