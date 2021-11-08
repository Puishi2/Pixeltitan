package es.Punikii.SummerWars.Weapons;

import es.Punikii.SummerWars.SummerWars;
import es.Punikii.SummerWars.Weapon;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

public class Bombe extends Weapon {

    public Bombe(SummerWars plugin, Material material, long reloadTime, double damage) {
        super(plugin, material, reloadTime, damage);
    }

    @Override
    public void shootEffects(Player player) {
        player.launchProjectile(Snowball.class);
        player.playEffect(player.getLocation(), Effect.WATERDRIP, 12);
        player.playSound(player.getLocation(), Sound.WATER, 5, 5);
    }
}
