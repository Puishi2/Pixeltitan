package de.Puishi.Lobby.utils;

import com.google.common.reflect.Reflection;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import java.util.List;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 19.05.2020 / 23:16                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class SkullBuilder {

    private static final Base64 base64 = new Base64();
    private String id;

    private SkullBuilder(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static ItemStack getCustomSkull(String url, String displayname, int amount, List<String> lore) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), (String)null);
        PropertyMap propertyMap = profile.getProperties();
        if (propertyMap == null) {
            throw new IllegalStateException("Profile doesn't contain a property map");
        } else {
            byte[] encodedData = base64.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
            propertyMap.put("textures", new Property("textures", new String(encodedData)));
            ItemStack head = new ItemStack(Material.SKULL_ITEM, amount, (short)3);
            ItemMeta headMeta = head.getItemMeta();
            headMeta.setDisplayName(displayname);
            headMeta.setLore(lore);
            Class<?> headMetaClass = headMeta.getClass();
            Reflections.getField(headMetaClass, "profile", GameProfile.class).set(headMeta, profile);
            head.setItemMeta(headMeta);
            return head;
        }
    }

}
