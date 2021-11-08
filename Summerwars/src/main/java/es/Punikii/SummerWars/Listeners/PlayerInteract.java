package es.Punikii.SummerWars.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener{

    @EventHandler
    public void onInteract(PlayerInteractEvent e){

        Player p = e.getPlayer();


        try{

            if((e.getAction() == Action.RIGHT_CLICK_AIR) || e.getAction() == Action.RIGHT_CLICK_BLOCK  ){

                if(p.getItemInHand().getItemMeta().getDisplayName().equals("§8» §cLeave")){
                    p.kickPlayer("§6§lSummerWars §8» §cDu hast das Spiel verlassen§8.");
                }
            }

        }catch(Exception e1){

        }
    }
}