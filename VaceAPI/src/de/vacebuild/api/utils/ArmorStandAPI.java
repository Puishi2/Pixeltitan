package de.vacebuild.api.utils;

import de.vacebuild.api.main.VaceAPI;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class ArmorStandAPI {

    private Location location;
    private String displayName;

    private boolean basePlate = false;
    private boolean visible = true;
    private boolean nameVisible = true;
    private boolean arms = true;
    private boolean gravity = false;

    private ItemStack helm;
    private ItemStack chest;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack itemInHand;

    public ArmorStandAPI(Location location, String displayName) {
        this.location = location;
        this.displayName = displayName;
    }

    public ArmorStandAPI setBasePlate(boolean basePlate) {
        this.basePlate = basePlate;

        return this;
    }

    public ArmorStandAPI setVisible(boolean visible) {
        this.visible = visible;

        return this;
    }

    public ArmorStandAPI setNameVisible(boolean nameVisible) {
        this.nameVisible = nameVisible;

        return this;
    }

    public ArmorStandAPI setArms(boolean arms) {
        this.arms = arms;

        return this;
    }

    public ArmorStandAPI setHelmet(ItemStack helm) {
        this.helm = helm;

        return this;
    }

    public ArmorStandAPI setHelmet(Color color) {
        this.helm = new ItemManager(Material.LEATHER_HELMET).setArmorColor(color).build();

        return this;
    }

    public ArmorStandAPI setHelmet(String url) {
        this.helm = VaceAPI.getApi().getSkullAPI().getSkull("§b", url);

        return this;
    }

    public ArmorStandAPI setChestplate(ItemStack chest) {
        this.chest = chest;

        return this;
    }

    public ArmorStandAPI setChestplate(Color color) {
        this.chest = new ItemManager(Material.LEATHER_CHESTPLATE).setArmorColor(color).build();

        return this;
    }

    public ArmorStandAPI setLeggings(ItemStack leggings) {
        this.leggings = leggings;

        return this;
    }

    public ArmorStandAPI setLeggings(Color color) {
        this.leggings = new ItemManager(Material.LEATHER_LEGGINGS).setArmorColor(color).build();

        return this;
    }

    public ArmorStandAPI setBoots(ItemStack boots) {
        this.boots = boots;

        return this;
    }

    public ArmorStandAPI setBoots(Color color) {
        this.boots = new ItemManager(Material.LEATHER_BOOTS).setArmorColor(color).build();

        return this;
    }

    public ArmorStandAPI setItemInHand(ItemStack itemInHand) {
        this.itemInHand = itemInHand;

        return this;
    }

    public boolean isGravity() {
        return gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public ArmorStand spawn() {
        ArmorStand armorStand = (ArmorStand) this.location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        armorStand.setCustomName(this.displayName);

        armorStand.setCustomNameVisible(this.nameVisible);
        armorStand.setVisible(this.visible);
        armorStand.setBasePlate(this.basePlate);
        armorStand.setArms(this.arms);
        armorStand.setGravity(this.gravity);

        armorStand.setHelmet(this.helm);
        armorStand.setChestplate(this.chest);
        armorStand.setLeggings(this.leggings);
        armorStand.setBoots(this.boots);
        armorStand.setItemInHand(this.itemInHand);

        return armorStand;
    }

}
