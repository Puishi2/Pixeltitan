package es.Punikii.MLGWars.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteracts implements Listener{

    @EventHandler
    public void onInteract(PlayerInteractEvent e){

        Player p = e.getPlayer();


        try{

            if((e.getAction() == Action.RIGHT_CLICK_AIR) || e.getAction() == Action.RIGHT_CLICK_BLOCK  ){

                if(p.getItemInHand().getItemMeta().getDisplayName().equals("§8» §cVerlassen")){
                    p.kickPlayer("§c§lMLGWars §8» §cDu hast das Spiel verlassen§8.");
                }
            }

        }catch(Exception e1){

        }
    }
}