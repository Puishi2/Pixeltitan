package es.Punikii.SummerWars.Weapons;

import es.Punikii.SummerWars.SummerWars;
import es.Punikii.SummerWars.Weapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

public class Pistole extends Weapon {

    public Pistole(SummerWars plugin, Material material, long reloadTime, double damage) {
        super(plugin, material, reloadTime, damage);
    }

    @Override
    public void shootEffects(Player player) {
        player.launchProjectile(Snowball.class);
    }

}
