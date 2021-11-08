package de.Puishi.Teambesprechung.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 12.05.2020 / 15:54                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Inventory {

    Player player;

    public Inventory(Player player){
        this.player = player;
    }

    public void setInventory(){
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        if(player.hasPermission("tb.item")){
            player.getInventory().setItem(8, new ItemBuilder(Material.PAPER).setName("§8» §cTeambesprechung").toItemStack());
        }

    }



}
