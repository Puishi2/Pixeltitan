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

public class TPMLG implements Listener{

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (MLGWars.getInstance().getGameStates() == GameStates.INGAME) {

            ItemStack BOAT = new ItemStack(Material.BOAT);
            ItemMeta btMeta = BOAT.getItemMeta();
            btMeta.setDisplayName("§8» §6BoatMLG");
            BOAT.setItemMeta(btMeta);

            ItemStack TP = new ItemStack(Material.ENDER_PEARL);
            ItemMeta tpMeta = TP.getItemMeta();
            tpMeta.setDisplayName("§8» §6TPMLG");
            TP.setItemMeta(tpMeta);

            if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GRASS) {
                p.teleport(LocationManager.getLocation("Pos5"));
                p.getInventory().clear();
                p.getInventory().setItem(0, BOAT);
                p.sendTitle("§6+10", "§cCoins");

                Coins.coins.put(p, Coins.coins.get(p) + 10);
                LevelManager.level.put(p, 5);
            }
            if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.DIRT) {
                p.teleport(LocationManager.getLocation("Pos4"));
                p.getInventory().setItem(0, TP);
                Coins.coins.put(p, Coins.coins.get(p) - 2);
            }
        }
    }
}

