package de.Puishi.Lobby.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 19.05.2020 / 23:12                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Base64 {

    private static Class<?> skullMetaClass;
    private static Class<?> tileEntityClass;
    private static Class<?> blockPositionClass;
    private static int mcVersion;

    static
    {
        String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        mcVersion = Integer.parseInt(version.replaceAll("[^0-9]", ""));
        try
        {
            skullMetaClass = Class.forName("org.bukkit.craftbukkit." + version + ".inventory.CraftMetaSkull");
            tileEntityClass = Class.forName("net.minecraft.server." + version + ".TileEntitySkull");
            if (mcVersion > 174) {
                blockPositionClass = Class.forName("net.minecraft.server." + version + ".BlockPosition");
            } else {
                blockPositionClass = null;
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static ItemStack getSkull(String skinURL)
    {
        return getSkull(skinURL, 1);
    }

    public static ItemStack getSkull(String skinURL, int amount)
    {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, amount, (short)3);
        SkullMeta meta = (SkullMeta)skull.getItemMeta();
        try
        {
            Field profileField = skullMetaClass.getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, getProfile(skinURL));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        skull.setItemMeta(meta);
        return skull;
    }

    public static boolean setBlock(Location loc, String skinURL)
    {
        return setBlock(loc.getBlock(), skinURL);
    }

    public static boolean setBlock(Block block, String skinURL)
    {
        boolean flag = block.getType() == Material.SKULL;
        if (!flag) {
            block.setType(Material.SKULL);
        }
        try
        {
            Object nmsWorld = block.getWorld().getClass().getMethod("getHandle", new Class[0]).invoke(block.getWorld(), new Object[0]);
            Object tileEntity = null;
            if (mcVersion <= 174)
            {
                Method getTileEntity = nmsWorld.getClass().getMethod("getTileEntity", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
                tileEntity = tileEntityClass.cast(getTileEntity.invoke(nmsWorld, new Object[] { Integer.valueOf(block.getX()), Integer.valueOf(block.getY()), Integer.valueOf(block.getZ()) }));
            }
            else
            {
                Method getTileEntity = nmsWorld.getClass().getMethod("getTileEntity", new Class[] { blockPositionClass });
                tileEntity = tileEntityClass.cast(getTileEntity.invoke(nmsWorld, new Object[] { getBlockPositionFor(block.getX(), block.getY(), block.getZ()) }));
            }
            tileEntityClass.getMethod("setGameProfile", new Class[] { GameProfile.class }).invoke(tileEntity, new Object[] { getProfile(skinURL) });
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return !flag;
    }

    private static GameProfile getProfile(String skinURL)
    {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        String base64encoded = java.util.Base64.getEncoder().encodeToString(new String("{textures:{SKIN:{url:\"" + skinURL + "\"}}}").getBytes());
        Property property = new Property("textures", base64encoded);
        profile.getProperties().put("textures", property);
        return profile;
    }


    private static Object getBlockPositionFor(int x, int y, int z)
    {
        Object blockPosition = null;
        try
        {
            Constructor<?> cons = blockPositionClass.getConstructor(new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
            blockPosition = cons.newInstance(new Object[] { Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z) });
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return blockPosition;
    }


}
