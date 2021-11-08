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

public class SlimeMLG implements Listener{

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (MLGWars.getInstance().getGameStates() == GameStates.INGAME) {

            ItemStack TP = new ItemStack(Material.ENDER_PEARL);
            ItemMeta tpMeta = TP.getItemMeta();
            tpMeta.setDisplayName("§8» §6TPMLG");
            TP.setItemMeta(tpMeta);

            ItemStack Slime = new ItemStack(Material.SLIME_BLOCK);
            ItemMeta slimeMeta = Slime.getItemMeta();
            slimeMeta.setDisplayName("§8» §aSlimeMLG");
            Slime.setItemMeta(slimeMeta);

            if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SLIME_BLOCK) {
                p.teleport(LocationManager.getLocation("Pos4"));
                p.getInventory().clear();
                p.getInventory().setItem(0, TP);
                p.sendTitle("§6+10", "§cCoins");

                Coins.coins.put(p, Coins.coins.get(p) + 10);
                LevelManager.level.put(p, 4);
            }
            if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.CLAY) {
                p.teleport(LocationManager.getLocation("Pos3"));
                p.getInventory().setItem(0, Slime);
                Coins.coins.put(p, Coins.coins.get(p) - 2);
            }
        }
    }
}
