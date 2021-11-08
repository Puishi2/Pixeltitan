package de.Puishi.TitanJump.shopping;

import de.Puishi.TitanJump.utils.Base64;
import de.Puishi.TitanJump.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 04.07.2020 / 16:17                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Shopping_Inventory {

    public void setInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setItem(2, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/69cb3d19db529a215cf62697591cc13fb8c786a8f27b7528c32ac2986b9670c4")).setName("§8» §bShop").toItemStack());
        player.getInventory().setItem(6, new ItemBuilder(Material.ANVIL).setName("§8» §bAmboss").toItemStack());
    }

}
