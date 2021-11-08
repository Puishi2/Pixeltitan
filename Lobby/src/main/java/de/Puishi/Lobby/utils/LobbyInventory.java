package de.Puishi.Lobby.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 19.05.2020 / 21:15                                               *
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

    public void setInventory(Player p){
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setName("§8» §6Navigator").toItemStack());
        p.getInventory().setItem(1, new ItemBuilder(Material.BLAZE_ROD).setName("§8» §aAlle Spieler verstecken").toItemStack());
        if(p.hasPermission("pixel.silenthub")){
            p.getInventory().setItem(3, new ItemBuilder(Material.TNT).setName("§8» §cSilent-Hub").toItemStack());
        }
        p.getInventory().setItem(4, new ItemBuilder(Material.FISHING_ROD).setName("§8» §dEnterhaken").setUnbrak().toItemStack());

        if(p.hasPermission("pixel.nick")){
            p.getInventory().setItem(5, new ItemBuilder(Material.NAME_TAG).setName("§8» §bNickTool").toItemStack());
        }

        p.getInventory().setItem(7, new ItemBuilder("§8» §3Profil", p.getName(), 1).buildSkull());
        p.getInventory().setItem(8, new ItemBuilder(Material.NETHER_STAR).setName("§8» §eLobby-Switcher").toItemStack());

        if(p.hasPermission("pixel.fly")){
            p.getInventory().setItem(22, new ItemBuilder(Material.FEATHER).setName("§8» §aFly").toItemStack());
        }

        if(p.hasPermission("pixel.admin")){
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setLeatherArmorColor(Color.fromRGB(170, 0, 0)).setName("§8» §7Rang §8┃ §4Admin").toItemStack());
        } else if(p.hasPermission("pixel.srdev")){
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setLeatherArmorColor(Color.fromRGB(85, 255, 255)).setName("§8» §7Rang §8┃ §bSrDeveloper").toItemStack());
        } else if(p.hasPermission("pixel.dev")){
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setLeatherArmorColor(Color.fromRGB(85, 255, 255)).setName("§8» §7Rang §8┃ §bDeveloper").toItemStack());
        } else if(p.hasPermission("pixel.content")){
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setLeatherArmorColor(Color.fromRGB(85, 255, 255)).setName("§8» §7Rang §8┃ §bContent").toItemStack());
        } else if(p.hasPermission("pixel.srmod")){
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setLeatherArmorColor(Color.fromRGB(255, 85, 85)).setName("§8» §7Rang §8┃ §cSrModerator").toItemStack());
        } else if(p.hasPermission("pixel.mod")){
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setLeatherArmorColor(Color.fromRGB(255, 85, 85)).setName("§8» §7Rang §8┃ §cModerator").toItemStack());
        } else if(p.hasPermission("pixel.jrmod")){
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setLeatherArmorColor(Color.fromRGB(255, 85, 85)).setName("§8» §7Rang §8┃ §cJrModerator").toItemStack());
        } else if(p.hasPermission("pixel.srbuilder")){
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setLeatherArmorColor(Color.fromRGB(0, 170, 170)).setName("§8» §7Rang §8┃ §3SrBuilder").toItemStack());
        } else if(p.hasPermission("pixel.builder")){
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setLeatherArmorColor(Color.fromRGB(0, 170, 170)).setName("§8» §7Rang §8┃ §3Builder").toItemStack());
        } else if(p.hasPermission("pixel.youtube")){
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setLeatherArmorColor(Color.fromRGB(170, 0, 170)).setName("§8» §7Rang §8┃ §5YouTuber").toItemStack());
        } else if(p.hasPermission("pixel.pixel")){
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setLeatherArmorColor(Color.fromRGB(85, 255, 85)).setName("§8» §7Rang §8┃ §aPixel").toItemStack());
        } else if(p.hasPermission("pixel.premium")){
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setLeatherArmorColor(Color.fromRGB(255, 170, 0)).setName("§8» §7Rang §8┃ §6Premium").toItemStack());
        } else {
            p.getInventory().setBoots((new ItemBuilder(Material.LEATHER_BOOTS)).setLeatherArmorColor(Color.fromRGB(170, 170, 170)).setName("§8» §7Rang §8┃ §7Spieler").toItemStack());
        }

    }

}
