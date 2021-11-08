package de.Puishi.TitanJump.perks;

import de.Puishi.TitanJump.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 03.07.2020 / 09:34                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Perk_Manager {

    public static HashMap<String, String> perk = new HashMap<>();

    public void setPerk(Player player) {
        switch (perk.get(player.getUniqueId().toString())) {

            case "§8» §aJump-Perk":
                player.getInventory().setItem(8, new ItemBuilder(Material.BLAZE_ROD).setName("§8» §aJump-Perk").toItemStack());
                break;

            case "§8» §eRettungs-Perk":
                player.getInventory().setItem(8, new ItemBuilder(Material.EMERALD).setName("§8» §eRettungs-Perk").toItemStack());
                break;

            case "§8» §fFreeze-Perk":
                player.getInventory().setItem(8, new ItemBuilder(Material.NETHER_STAR).setName("§8» §fFreeze-Perk").toItemStack());
                break;

            default:
                break;

        }
    }


}
