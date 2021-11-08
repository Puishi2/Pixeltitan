package de.vacebuild.api.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;

import de.vacebuild.api.main.VaceAPI;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class SkullAPI {

    private VaceAPI plugin;
    private HashMap<String, ItemMeta> headsByUrl = new HashMap<>();
    private HashMap<String, ItemMeta> headsByUuid = new HashMap<>();
    private Class<?> skullMetaClass;
    private int mcVersion;

    public SkullAPI(VaceAPI plugin) {
        this.plugin = plugin;
    }

    public ItemStack getSkull(String name, String url) {
        if (!headsByUrl.containsKey(url)) {
            ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();

            itemMeta.setDisplayName(name);

            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
            profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

            Field profileField = null;

            try {
                profileField = itemMeta.getClass().getDeclaredField("profile");
            } catch (NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
            }

            profileField.setAccessible(true);

            try {
                profileField.set(itemMeta, profile);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }

            itemStack.setItemMeta(itemMeta);

            headsByUrl.put(url, itemMeta);

            return itemStack;
        } else {
            ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            ItemMeta itemMeta = headsByUrl.get(url);

            itemMeta.setDisplayName(name);
            itemStack.setItemMeta(itemMeta);

            return itemStack;
        }
    }

    public ItemStack loadSkull(String uuid, String name) {
        if (!headsByUuid.containsKey(uuid)) {
            String version = plugin.getServer().getClass().getPackage().getName().split("\\.")[3];
            mcVersion = Integer.parseInt(version.replaceAll("[^0-9]", ""));

            try {
                skullMetaClass = Class.forName("org.bukkit.craftbukkit." + version + ".inventory.CraftMetaSkull");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();

            itemMeta.setDisplayName(name);

            boolean success = false;

            try {
                Field profileField = skullMetaClass.getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(itemMeta, GameProfileBuilder.fetch(UUID.fromString(uuid)));

                success = true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            itemStack.setItemMeta(itemMeta);

            if (success) {
                headsByUuid.put(uuid, itemMeta);
            }

            return itemStack;
        } else {
            ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            ItemMeta itemMeta = headsByUuid.get(uuid);

            itemMeta.setDisplayName(name);
            itemStack.setItemMeta(itemMeta);

            return itemStack;
        }
    }

}
