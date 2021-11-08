package de.Puishi.TitanJump.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 17.07.2020 / 20:24                                               *
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
        player.getInventory().setItem(4, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/d5c6dc2bbf51c36cfc7714585a6a5683ef2b14d47d8ff714654a893f5da622")).setName("§8» §bPerks").toItemStack());
        player.getInventory().setItem(0, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/6fb290a13df88267ea5f5fcf796b6157ff64ccee5cd39d469724591babeed1f6")).setName("§8» §bTeamauswahl").toItemStack());
        player.getInventory().setItem(5, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/98daa1e3ed94ff3e33e1d4c6e43f024c47d78a57ba4d38e75e7c9264106")).setName("§8» §bMapvoting").toItemStack());
        player.getInventory().setItem(3, new ItemBuilder(Material.BOOK).setName("§8» §bSpielerklärung").toItemStack());
        player.getInventory().setItem(8, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/19bf3292e126a105b54eba713aa1b152d541a1d8938829c56364d178ed22bf")).setName("§8» §cVerlassen").toItemStack());

    }

}
