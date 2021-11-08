package de.Puishi.Lobby.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 08.07.2020 / 17:27                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class LobbyInventory {

    public void setInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setItem(1, new ItemBuilder(Material.CLAY_BALL).setName("§8» §3Navigator").toItemStack());
        player.getInventory().setItem(7, new ItemBuilder(Material.FISHING_ROD).setName("§8» §3Enterhaken").setUnbrak().toItemStack());
    }

}
