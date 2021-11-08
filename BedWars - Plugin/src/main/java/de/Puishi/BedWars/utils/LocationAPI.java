package de.Puishi.BedWars.utils;

import de.Puishi.BedWars.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *
 *    Erstellt: 28.05.2020 / 03:33                                               *
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

    private final Main plugin;

    public ArrayList<String> maps = new ArrayList<>();

    private final File file;

    private final FileConfiguration cfg;

    public LocationAPI(Main plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "locations.yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);
    }

    public void load() {
        if (!this.file.exists()) {
            this.cfg.options().copyDefaults(true);
            this.cfg.set("Maps", null);
            save();
        } else {
            try {
                this.cfg.load(this.file);
            } catch (IOException | InvalidConfigurationException e) {
                Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "§cFehler beim laden der locations.yml");
            }
        }
    }

    public void save() {
        try {
            this.cfg.save(this.file);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "§cFehler beim speichern den locations.yml");
        }
    }

    public void createMap(String map) {
        maps = (ArrayList<String>) this.cfg.getStringList("Maps");
        String[] parts = map.split("\\.");
        String s = parts[0];
        if (!maps.contains(s))
            maps.add(s);
        this.cfg.set("Maps", maps);

        save();
    }

    public void deleteMap(String map) {
        maps = (ArrayList<String>) this.cfg.getStringList("Maps");
        String[] parts = map.split("\\.");
        String s = parts[0];
        if (maps.contains(s))
            maps.remove(s);
        this.cfg.set("Maps", maps);

        save();
    }

    public void saveLocation(Location location, String map) {
        this.cfg.set(map + ".X", location.getX());
        this.cfg.set(map + ".Y", location.getY());
        this.cfg.set(map + ".Z", location.getZ());
        this.cfg.set(map + ".World", location.getWorld().getName());

        maps = (ArrayList<String>) this.cfg.getStringList("Maps");
        String[] parts = map.split("\\.");
        String s = parts[0];
        if (!maps.contains(s))
            maps.add(s);
        this.cfg.set("Maps", maps);

        save();
    }

    public Location loadLocation(String name) {
        double x = this.cfg.getDouble(name + ".X");
        if (this.cfg.getDouble(name + ".X") == 0.0D)
            return null;
        double y = this.cfg.getDouble(name + ".Y");
        double z = this.cfg.getDouble(name + ".Z");
        double yaw = this.cfg.getDouble(name + ".Yaw");
        double pitch = this.cfg.getDouble(name + ".Pitch");
        World world = this.plugin.getServer().getWorld(this.cfg.getString(name + ".World"));
        Location location = new Location(world, x, y, z);
        location.setPitch((float) pitch);
        location.setYaw((float) yaw);
        return location;
    }

    public void saveBlockLocation(Location loc, String name) {
        this.cfg.set(name + ".X", loc.getX());
        this.cfg.set(name + ".Y", loc.getY());
        this.cfg.set(name + ".Z", loc.getZ());
        this.cfg.set(name + ".World", loc.getWorld().getName());

        maps = (ArrayList<String>) this.cfg.getStringList("Maps");
        String[] parts = name.split("\\.");
        String s = parts[0];
        if (!maps.contains(s))
            maps.add(s);
        this.cfg.set("Maps", maps);

        save();
    }

    public Location loadBlockLocation(String name) {
        double x = this.cfg.getDouble(name + ".X");
        if (this.cfg.getDouble(name + ".X") == 0.0D)
            return null;
        double y = this.cfg.getDouble(name + ".Y");
        double z = this.cfg.getDouble(name + ".Z");
        World world = this.plugin.getServer().getWorld(this.cfg.getString(name + ".World"));
        Location location = new Location(world, x, y, z);
        return location;
    }

    public ArrayList<String> getMaps() {
        return maps;
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getCfg() {
        return cfg;
    }

}
