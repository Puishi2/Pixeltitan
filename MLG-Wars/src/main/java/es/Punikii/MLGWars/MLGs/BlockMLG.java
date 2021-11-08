package es.Punikii.MLGWars.MLGs;

import es.Punikii.MLGWars.Coins.Coins;
import es.Punikii.MLGWars.Locations.LocationManager;
import es.Punikii.MLGWars.MLGWars;
import es.Punikii.MLGWars.countdown.ShopCountdown;
import es.Punikii.MLGWars.gamestate.GameStates;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BlockMLG implements Listener{

    public static ArrayList<Player> Shop = new ArrayList();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (MLGWars.getInstance().getGameStates() == GameStates.INGAME) {

            ItemStack Block = new ItemStack(Material.SANDSTONE);
            ItemMeta blockMeta = Block.getItemMeta();
            blockMeta.setDisplayName("§8» §6BlockMLG");
            Block.setItemMeta(blockMeta);

        if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SANDSTONE) {
            p.teleport(LocationManager.getLocation("Shop"));
            p.getInventory().clear();
            p.sendTitle("§6+10", "§cCoins");
            MLGWars.getInstance().setGameStates(GameStates.SHOP);
            new ShopCountdown().startcountdown(p);
            for(Player all : Bukkit.getOnlinePlayers()) {
                all.teleport(LocationManager.getLocation("Shop"));
                all.setFoodLevel(20);
                all.setHealth(20);
                all.getInventory().clear();
                Coins.coins.put(p, Coins.coins.get(p) + 10);
                all.sendTitle("§cSHOP-PHASE", "§7Klicke den §6Goldenen §7Block um zu §cShopen");
            }
        }
        if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.QUARTZ_BLOCK) {
            p.teleport(LocationManager.getLocation("Pos6"));
            p.getInventory().setItem(0, Block);
            Coins.coins.put(p, Coins.coins.get(p) - 2);
        }
        }
    }
}
