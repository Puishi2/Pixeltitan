package de.Puishi.Lobby.lotterie;

import de.Puishi.Lobby.database.MySQL;
import de.Puishi.Lobby.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 20.05.2020 / 22:53                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class LotteryInventory {

    private MySQL mySQL;

    public void openLotteryInventory(Player player, Inventory inventory) {

        ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack();


        for(int i = 0; i < inventory.getSize(); i++) {
            if(inventory.getItem(i) != null)
                inventory.setItem(i, glass);
        }

        inventory.setItem(11, new ItemBuilder(Material.GOLD_INGOT).setName("§8» §6Kaufen").addLoreLine("§8» §c100 Coins").toItemStack());
        inventory.setItem(15, new ItemBuilder(Material.PAPER).setName("§8» §aEinlösen").addLoreLine("§8» §7Du hast §c" + MySQL.getLotteryTickets(player) + " §7Tickets.").toItemStack());

        player.openInventory(inventory);
    }

}
