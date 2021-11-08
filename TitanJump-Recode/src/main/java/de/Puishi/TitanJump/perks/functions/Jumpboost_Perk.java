package de.Puishi.TitanJump.perks.functions;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.gamestate.GameState;
import de.Puishi.TitanJump.utils.Data;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 04.07.2020 / 15:46                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Jumpboost_Perk implements Listener {

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(event.getItem() != null) {
            if(event.getItem().getItemMeta().getDisplayName() != null) {
                if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aJumpBoost-Perk")
                        && event.getItem().getType() == Material.BLAZE_ROD) {
                    if(TitanJump.getInstance().getGameState() == GameState.INGAME) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 400, 2));
                        player.sendMessage(Data.getPrefix() + "Du hast das Perk eingelöst!");
                        player.getInventory().remove(Material.BLAZE_ROD);
                        player.spigot().playEffect(player.getLocation(), Effect.FLAME, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 15, 10);
                    }
                }
            }
        }

    }

}
