package de.Puishi.Teambesprechung.utils;

import de.Puishi.Teambesprechung.Teambesprechung;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 18.05.2020 / 21:13                                               *
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

    private Teambesprechung plugin;
    private Location loc;
    private String name;

    File file = new File("plugins//BedWars//locations.yml");
    FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

    public void set(){
        if(!file.exists()) {
            file.mkdir();
        }
    }

    public LocationAPI(Teambesprechung plugin, Location loc, String name){
        this.plugin = plugin;
        this.loc = loc;
        this.name = name;
    }

    public LocationAPI(Teambesprechung plugin, String name){
        this(plugin, null, name);
    }

    public void saveLocation(){
        fileConfiguration.set(name + ".world", loc.getWorld().getName());
        fileConfiguration.set(name + ".x", loc.getX());
        fileConfiguration.set(name + ".y", loc.getY());
        fileConfiguration.set(name + ".z", loc.getZ());
        fileConfiguration.set(name + ".yaw", loc.getYaw());
        fileConfiguration.set(name + ".pitch", loc.getPitch());
    }

    public Location loadLocation(){
        World world = Bukkit.getWorld(fileConfiguration.getString(name + ".world"));
        double x = fileConfiguration.getDouble(name + ".x"),
                y = fileConfiguration.getDouble(name + ".y"),
                z = fileConfiguration.getDouble(name + ".z");
        float yaw = (float) fileConfiguration.getDouble(name + ".yaw"),
                pitch = (float) fileConfiguration.getDouble(name + ".pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

}
