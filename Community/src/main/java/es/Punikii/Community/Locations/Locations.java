package es.Punikii.Community.Locations;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Locations {
    public static void createNavigator() {
        File ordner = new File("plugins//Community");
        File file = new File("plugins//Community//Locations.yml");
        if (!ordner.exists()) {
            ordner.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set("Spawn.X", "null");
        cfg.set("Spawn.Y", "null");
        cfg.set("Spawn.Z", "null");
        cfg.set("Spawn.World", "null");
        cfg.set("Spawn.Yaw", "null");
        cfg.set("Spawn.Pitch", "null");

        try {
            cfg.save(file);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public static boolean getNavigator() {
        File file = new File("plugins//Community//Locations.yml");
        return file.exists();
    }

    public static void setSpawn(Player p) {
        File file = new File("plugins//Community//Locations.yml");
        Location loc = p.getLocation();
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set("Spawn.X", loc.getX());
        cfg.set("Spawn.Y", loc.getY());
        cfg.set("Spawn.Z", loc.getZ());
        cfg.set("Spawn.World", loc.getWorld().getName());
        cfg.set("Spawn.Yaw", loc.getYaw());
        cfg.set("Spawn.Pitch", loc.getPitch());

        try {
            cfg.save(file);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    public static Location getSpawn(Player p) {
        File file = new File("plugins//Community//Locations.yml");
        Location loc = p.getLocation();
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        loc.setX(cfg.getDouble("Spawn.X"));
        loc.setY(cfg.getDouble("Spawn.Y"));
        loc.setZ(cfg.getDouble("Spawn.Z"));
        loc.setYaw((float)cfg.getDouble("Spawn.Yaw"));
        loc.setPitch((float)cfg.getDouble("Spawn.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Spawn.World")));
        return loc;
    }

    public static Location getBuehne(Player p) {
        File file = new File("plugins//Community//Locations.yml");
        Location loc = p.getLocation();
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        loc.setX(cfg.getDouble("Buehne.X"));
        loc.setY(cfg.getDouble("Buehne.Y"));
        loc.setZ(cfg.getDouble("Buehne.Z"));
        loc.setYaw((float)cfg.getDouble("Buehne.Yaw"));
        loc.setPitch((float)cfg.getDouble("Buehne.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Buehne.World")));
        return loc;
    }

    public static Location getScreen(Player p) {
        File file = new File("plugins//Community//Locations.yml");
        Location loc = p.getLocation();
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        loc.setX(cfg.getDouble("Screen.X"));
        loc.setY(cfg.getDouble("Screen.Y"));
        loc.setZ(cfg.getDouble("Screen.Z"));
        loc.setYaw((float)cfg.getDouble("Screen.Yaw"));
        loc.setPitch((float)cfg.getDouble("Screen.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Screen.World")));
        return loc;
    }

    public static Location getTeam(Player p) {
        File file = new File("plugins//Community//Locations.yml");
        Location loc = p.getLocation();
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        loc.setX(cfg.getDouble("Team.X"));
        loc.setY(cfg.getDouble("Team.Y"));
        loc.setZ(cfg.getDouble("Team.Z"));
        loc.setYaw((float)cfg.getDouble("Team.Yaw"));
        loc.setPitch((float)cfg.getDouble("Team.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Team.World")));
        return loc;
    }

    public static Location getEffect1(Player p) {
        File file = new File("plugins//Community//Locations.yml");
        Location loc = p.getLocation();
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        loc.setX(cfg.getDouble("Effect1.X"));
        loc.setY(cfg.getDouble("Effect1.Y"));
        loc.setZ(cfg.getDouble("Effect1.Z"));
        loc.setYaw((float)cfg.getDouble("Effect1.Yaw"));
        loc.setPitch((float)cfg.getDouble("Effect1.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Effect1.World")));
        return loc;
    }

    public static Location getEffect2(Player p) {
        File file = new File("plugins//Community//Locations.yml");
        Location loc = p.getLocation();
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        loc.setX(cfg.getDouble("Effect2.X"));
        loc.setY(cfg.getDouble("Effect2.Y"));
        loc.setZ(cfg.getDouble("Effect2.Z"));
        loc.setYaw((float)cfg.getDouble("Effect2.Yaw"));
        loc.setPitch((float)cfg.getDouble("Effect2.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Effect2.World")));
        return loc;
    }
    public static Location getEffect3(Player p) {
        File file = new File("plugins//Community//Locations.yml");
        Location loc = p.getLocation();
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        loc.setX(cfg.getDouble("Effect3.X"));
        loc.setY(cfg.getDouble("Effect3.Y"));
        loc.setZ(cfg.getDouble("Effect3.Z"));
        loc.setYaw((float)cfg.getDouble("Effect3.Yaw"));
        loc.setPitch((float)cfg.getDouble("Effect3.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Effect3.World")));
        return loc;
    }
    public static Location getEffect4(Player p) {
        File file = new File("plugins//Community//Locations.yml");
        Location loc = p.getLocation();
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        loc.setX(cfg.getDouble("Effect4.X"));
        loc.setY(cfg.getDouble("Effect4.Y"));
        loc.setZ(cfg.getDouble("Effect4.Z"));
        loc.setYaw((float)cfg.getDouble("Effect4.Yaw"));
        loc.setPitch((float)cfg.getDouble("Effect4.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Effect4.World")));
        return loc;
    }
    public static Location getTB(Player p) {
        File file = new File("plugins//Community//Locations.yml");
        Location loc = p.getLocation();
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        loc.setX(cfg.getDouble("TB.X"));
        loc.setY(cfg.getDouble("TB.Y"));
        loc.setZ(cfg.getDouble("TB.Z"));
        loc.setYaw((float)cfg.getDouble("TB.Yaw"));
        loc.setPitch((float)cfg.getDouble("TB.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("TB.World")));
        return loc;
    }
}