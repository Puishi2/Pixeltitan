package es.Punikii.MLGWars.MLGs;

import es.Punikii.MLGWars.Coins.Coins;
import es.Punikii.MLGWars.Level.LevelManager;
import es.Punikii.MLGWars.Locations.LocationManager;
import es.Punikii.MLGWars.MLGWars;
import es.Punikii.MLGWars.gamestate.GameStates;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BoatMLG implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (MLGWars.getInstance().getGameStates() == GameStates.INGAME) {

            ItemStack BOAT = new ItemStack(Material.BOAT);
            ItemMeta btMeta = BOAT.getItemMeta();
            btMeta.setDisplayName("§8» §6BoatMLG");
            BOAT.setItemMeta(btMeta);

            ItemStack Block = new ItemStack(Material.SANDSTONE);
            ItemMeta blockMeta = Block.getItemMeta();
            blockMeta.setDisplayName("§8» §6BlockMLG");
            Block.setItemMeta(blockMeta);


            if(p.isInsideVehicle()) {
                if(p.getVehicle() instanceof Boat) {
                    p.teleport(LocationManager.getLocation("Pos6"));
                    p.getInventory().clear();
                    p.getInventory().setItem(0, Block);
                    p.sendTitle("§6+10", "§cCoins");

                    Coins.coins.put(p, Coins.coins.get(p) + 10);
                    LevelManager.level.put(p, 6);
                }
            }
            if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.STONE) {
                p.teleport(LocationManager.getLocation("Pos5"));
                p.getInventory().setItem(0, BOAT);
                Coins.coins.put(p, Coins.coins.get(p) - 2);
            }
        }
    }
}