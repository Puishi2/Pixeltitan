package de.vacebuild.api.utils;

import de.vacebuild.api.main.VaceAPI;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class LocationAPI {

    private VaceAPI plugin;
    private static LocationAPI locationAPI;
    private HashMap<String, Location> locations = new HashMap<>();

    public LocationAPI(VaceAPI plugin) {
        this.plugin = plugin;
        locationAPI = this;
    }

    public static LocationAPI getLocationAPI() {
        return locationAPI;
    }

    public File getFile() {
        return new File("plugins//VaceBuild//spawns.yml");
    }

    public YamlConfiguration getFileConfiguration() {
        return YamlConfiguration.loadConfiguration(this.getFile());
    }

    public boolean existsLocation(String path) {
        YamlConfiguration locs = this.getFileConfiguration();

        if (locs.getString(path) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void addLocation(Location loc, String path) {
        YamlConfiguration locs = this.getFileConfiguration();

        locs.set(path + ".World", loc.getWorld().getName());
        locs.set(path + ".X", loc.getX());
        locs.set(path + ".Y", loc.getY());
        locs.set(path + ".Z", loc.getZ());
        locs.set(path + ".Yaw", loc.getYaw());
        locs.set(path + ".Pitch", loc.getPitch());

        try {
            locs.save(this.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBlockLocation(Location loc, String path) {
        YamlConfiguration locs = this.getFileConfiguration();

        locs.set(path + ".World", loc.getWorld().getName());
        locs.set(path + ".X", loc.getBlock());
        locs.set(path + ".Y", loc.getBlockY());
        locs.set(path + ".Z", loc.getBlockZ());

        try {
            locs.save(this.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getLocation(String path) {
        YamlConfiguration locs = this.getFileConfiguration();

        if (this.locations.containsKey(path)) {
            return locations.get(path);
        } else {
            World w = plugin.getServer().getWorld(locs.getString(path + ".World"));
            double x = locs.getDouble(path + ".X");
            double y = locs.getDouble(path + ".Y");
            double z = locs.getDouble(path + ".Z");
            float yaw = (float) locs.getDouble(path + ".Yaw");
            float pitch = (float) locs.getDouble(path + ".Pitch");

            locations.put(path, new Location(w, x, y, z, yaw, pitch));
            return new Location(w, x, y, z, yaw, pitch);
        }
    }

    public Location getBlockLocation(String path) {
        YamlConfiguration locs = this.getFileConfiguration();

        if (this.locations.containsKey(path)) {
            return locations.get(path);
        } else {
            World w = plugin.getServer().getWorld(locs.getString(path + ".World"));
            double x = locs.getDouble(path + ".X");
            double y = locs.getDouble(path + ".Y");
            double z = locs.getDouble(path + ".Z");

            locations.put(path, new Location(w, x, y, z));
            return new Location(w, x, y, z);
        }
    }

}
