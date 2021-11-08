package es.Punikii.SummerWars;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public abstract class Weapon {

    private SummerWars plugin;
    private Material material;
    private long reloadTime;
    private ArrayList<String> shotWeapon;
    private double damage;

    public Weapon(SummerWars plugin, Material material, long reloadTime, double damage) {
        this.plugin = plugin;
        this.material = material;
        this.reloadTime = reloadTime;
        this.damage = damage;

        shotWeapon = new ArrayList<>();
    }

    public void shoot(Player player) {
        if(!shotWeapon.contains(player.getName())) {
            shotWeapon.add(player.getName());
            shootEffects(player);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    shotWeapon.remove(player.getName());
                }
            }, reloadTime);
        }
    }

    public abstract void shootEffects(Player player);

    public Material getMaterial() {
        return material;
    }

    public double getDamage() {
        return damage;
    }

}