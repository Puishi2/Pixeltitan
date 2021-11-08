package de.vacebuild.api.utils;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ItemManager {

    private HashMap<String, String> extra = new HashMap<>();
    private ItemStack itemStack;
    private ItemMeta meta;
    private List<String> lore = new ArrayList<>();

    public ItemManager(Material mat, int id, int amount) {
        itemStack = new ItemStack(mat, amount, (short) id);
        meta = itemStack.getItemMeta();
    }

    public ItemManager(ItemStack item) {
        this.itemStack = item;
        this.meta = item.getItemMeta();
    }

    public ItemManager(Material material) {
        itemStack = new ItemStack(material, 1, (short) 0);
        meta = itemStack.getItemMeta();
    }

    public ItemManager(Material material, int id) {
        itemStack = new ItemStack(material, 1, (short) id);
        meta = itemStack.getItemMeta();
    }

    public ItemManager setExtra(String extra) {
        this.extra.put(meta.getDisplayName(), extra);

        return this;
    }

    public ItemManager setAmount(int value) {
        itemStack.setAmount(value);

        return this;
    }

    public ItemManager setDurability(short durability) {
        itemStack.setDurability(durability);

        return this;
    }

    public ItemManager hideFlags() {
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);

        return this;
    }

    public ItemManager setData(int data) {
        itemStack.setDurability((short) data);

        return this;
    }

    public ItemManager addLoreLine(String line) {
        lore.add(line);

        return this;
    }

    public ItemManager addLoreArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            Collections.addAll(lore, array[i]);
        }

        return this;
    }

    public ItemManager addLoreAll(List<String> list) {
        lore.addAll(list);

        return this;
    }

    public ItemManager setDisplayName(String name) {
        meta.setDisplayName(name);

        return this;
    }

    public ItemManager setSkullOwner(String name) {
        ((SkullMeta) meta).setOwner(name);

        return this;
    }

    public ItemManager setArmorColor(Color color) {
        ((LeatherArmorMeta) meta).setColor(color);

        return this;
    }

    public ItemManager setBannerColor(DyeColor color) {
        ((BannerMeta) meta).setBaseColor(color);

        return this;
    }

    public ItemManager addPattern(DyeColor color, PatternType pattern) {
        ((BannerMeta) meta).addPattern(new Pattern(color, pattern));

        return this;
    }

    public ItemManager setUnbreakable(boolean value) {
        meta.spigot().setUnbreakable(value);

        return this;
    }

    public ItemManager addEnchantment(Enchantment enchantment, int lvl) {
        meta.addEnchant(enchantment, lvl, true);

        return this;
    }

    public ItemManager addItemFlag(ItemFlag flag) {
        meta.addItemFlags(flag);

        return this;
    }

    public HashMap<String, String> getExtra() {
        return extra;
    }

    public ItemStack build() {
        if (!lore.isEmpty()) {
            meta.setLore(lore);
        }

        itemStack.setItemMeta(meta);

        return itemStack;
    }

}
