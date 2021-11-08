package de.Puishi.TitanJump.perks.functions;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.gamestate.GameState;
import de.Puishi.TitanJump.listener.MoveListener;
import de.Puishi.TitanJump.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 04.07.2020 / 15:56                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Rettungs_Perk implements Listener {

    private int over = 3;

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getItem() != null) {
            if(event.getItem().getItemMeta().getDisplayName() != null) {
                if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eRettungs-Perk")
                    && event.getItem().getType() == Material.EMERALD) {
                    if(TitanJump.getInstance().getGameState() == GameState.INGAME) {
                        if(over >= 0) {
                            player.teleport(new MoveListener().locationHashMap.get(player.getUniqueId().toString()).clone().add(0.5, 3, 0.5));
                            player.sendMessage(Data.getPrefix() + "Du hast das Perk eingelöst!");
                            over--;
                            player.spigot().playEffect(player.getLocation(), Effect.FLAME, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 15, 10);

                            if(over == 0) {
                                player.getInventory().remove(Material.EMERALD);
                            }
                        }
                    }
                }
            }
        }
    }
}
