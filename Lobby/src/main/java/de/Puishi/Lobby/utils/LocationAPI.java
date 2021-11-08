package de.Puishi.Lobby.utils;

import de.Puishi.Lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 19.05.2020 / 23:47                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class LocationAPI {


    public static File file = new File("plugins/LobbySystem/locations.yml");
    FileConfiguration cfg  = YamlConfiguration.loadConfiguration(file);

    public void saveLocation(Player player, String locationName) {
        cfg.set("location." + locationName + ".world", player.getLocation().getWorld().getName());
        cfg.set("location." + locationName + ".x", player.getLocation().getX());
        cfg.set("location." + locationName + ".y", player.getLocation().getY());
        cfg.set("location." + locationName + ".z", player.getLocation().getZ());
        cfg.set("location." + locationName + ".yaw", player.getLocation().getYaw());
        cfg.set("location." + locationName + ".pitch", player.getLocation().getPitch());

        try {
            cfg.save(file);
        } catch (Exception e) {

        }
    }

    public void saveBlockLocation(Location loc, String name) {
        this.cfg.set(name + ".X", loc.getX());
        this.cfg.set(name + ".Y", loc.getY());
        this.cfg.set(name + ".Z", loc.getZ());
        this.cfg.set(name + ".World", loc.getWorld().getName());
        try {
            cfg.save(file);
        } catch (Exception e) {

        }
    }

    public Location getBlockLocation(String name) {
        double X = this.cfg.getDouble(name + ".X");
        double Y = this.cfg.getDouble(name + ".Y");
        double Z = this.cfg.getDouble(name + ".Z");
        //World world = Bukkit.getWorld(this.cfg.getString(name + ".World"));
        World world = Main.getInstance().getServer().getWorld(this.cfg.getString(name + ".World"));
        Location loc = new Location(world, X, Y, Z);
        return loc;
    }

    public Location getLocation(String locationName) {
        return new Location(Bukkit.getWorld(cfg.getString("location." + locationName + ".world")),
                cfg.getDouble("location." + locationName + ".x"),
                cfg.getDouble("location." + locationName + ".y"),
                cfg.getDouble("location." + locationName + ".z"),
                (float) cfg.getDouble("location." + locationName + ".yaw"),
                (float) cfg.getDouble("location." + locationName + ".pitch"));
    }

}
