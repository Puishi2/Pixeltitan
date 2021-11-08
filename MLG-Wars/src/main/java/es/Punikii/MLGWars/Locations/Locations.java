package es.Punikii.MLGWars.Locations;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Locations {

    public static void createNavigator() {

        File ordner = new File("plugins//MLGWars");
        File file = new File("plugins//MLGWars//Locations.yml");

        if(!ordner.exists()) {

            ordner.mkdirs();

        }

        if(!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean getNavigator() {

        File file = new File("plugins//MLGWars//Locations.yml");

        if(file.exists()) {

            return true;

        } else {

            return false;

        }

    }

    public static void setSpawn(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static Location getSpawn(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
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
    public static void createPos1() {

        File ordner = new File("plugins//MLGWars");
        File file = new File("plugins//MLGWars//Locations.yml");

        if(!ordner.exists()) {

            ordner.mkdirs();

        }

        if(!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean getPos2() {

        File file = new File("plugins//MLGWars//Locations.yml");

        if(file.exists()) {

            return true;

        } else {

            return false;

        }

    }

    public static void setPos1(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set("Pos1.X", loc.getX());
        cfg.set("Pos1.Y", loc.getY());
        cfg.set("Pos1.Z", loc.getZ());
        cfg.set("Pos1.World", loc.getWorld().getName());
        cfg.set("Pos1.Yaw", loc.getYaw());
        cfg.set("Pos1.Pitch", loc.getPitch());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static Location getPos1(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Pos1.X"));
        loc.setY(cfg.getDouble("Pos1.Y"));
        loc.setZ(cfg.getDouble("Pos1.Z"));
        loc.setYaw((float)cfg.getDouble("Pos1.Yaw"));
        loc.setPitch((float)cfg.getDouble("Pos1.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Pos1.World")));

        return loc;

    }
    public static void setPos2(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set("Pos2.X", loc.getX());
        cfg.set("Pos2.Y", loc.getY());
        cfg.set("Pos2.Z", loc.getZ());
        cfg.set("Pos2.World", loc.getWorld().getName());
        cfg.set("Pos2.Yaw", loc.getYaw());
        cfg.set("Pos2.Pitch", loc.getPitch());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static Location getPos2(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Pos2.X"));
        loc.setY(cfg.getDouble("Pos2.Y"));
        loc.setZ(cfg.getDouble("Pos2.Z"));
        loc.setYaw((float)cfg.getDouble("Pos2.Yaw"));
        loc.setPitch((float)cfg.getDouble("Pos2.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Pos2.World")));

        return loc;

    }
    public static void setPos3(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set("Pos3.X", loc.getX());
        cfg.set("Pos3.Y", loc.getY());
        cfg.set("Pos3.Z", loc.getZ());
        cfg.set("Pos3.World", loc.getWorld().getName());
        cfg.set("Pos3.Yaw", loc.getYaw());
        cfg.set("Pos3.Pitch", loc.getPitch());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static Location getPos3(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Pos3.X"));
        loc.setY(cfg.getDouble("Pos3.Y"));
        loc.setZ(cfg.getDouble("Pos3.Z"));
        loc.setYaw((float)cfg.getDouble("Pos3.Yaw"));
        loc.setPitch((float)cfg.getDouble("Pos3.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Pos3.World")));

        return loc;

    }
    public static void setPos4(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set("Pos4.X", loc.getX());
        cfg.set("Pos4.Y", loc.getY());
        cfg.set("Pos4.Z", loc.getZ());
        cfg.set("Pos4.World", loc.getWorld().getName());
        cfg.set("Pos4.Yaw", loc.getYaw());
        cfg.set("Pos4.Pitch", loc.getPitch());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static Location getPos4(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Pos4.X"));
        loc.setY(cfg.getDouble("Pos4.Y"));
        loc.setZ(cfg.getDouble("Pos4.Z"));
        loc.setYaw((float)cfg.getDouble("Pos4.Yaw"));
        loc.setPitch((float)cfg.getDouble("Pos4.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Pos4.World")));

        return loc;

    }
    public static Location getShop(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Shop.X"));
        loc.setY(cfg.getDouble("Shop.Y"));
        loc.setZ(cfg.getDouble("Shop.Z"));
        loc.setYaw((float)cfg.getDouble("Shop.Yaw"));
        loc.setPitch((float)cfg.getDouble("Shop.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Shop.World")));

        return loc;

    }
    public static Location getPos5(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Pos5.X"));
        loc.setY(cfg.getDouble("Pos5.Y"));
        loc.setZ(cfg.getDouble("Pos5.Z"));
        loc.setYaw((float)cfg.getDouble("Pos5.Yaw"));
        loc.setPitch((float)cfg.getDouble("Pos5.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Pos5.World")));

        return loc;

    }
    public static Location getpvp1(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Pvp1.X"));
        loc.setY(cfg.getDouble("Pvp1.Y"));
        loc.setZ(cfg.getDouble("Pvp1.Z"));
        loc.setYaw((float)cfg.getDouble("Pvp1.Yaw"));
        loc.setPitch((float)cfg.getDouble("Pvp1.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Pvp1.World")));

        return loc;

    }
    public static Location getpvp2(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Pvp2.X"));
        loc.setY(cfg.getDouble("Pvp2.Y"));
        loc.setZ(cfg.getDouble("Pvp2.Z"));
        loc.setYaw((float)cfg.getDouble("Pvp2.Yaw"));
        loc.setPitch((float)cfg.getDouble("Pvp2.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Pvp2.World")));

        return loc;

    }
    public static Location getpvp3(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Pvp3.X"));
        loc.setY(cfg.getDouble("Pvp3.Y"));
        loc.setZ(cfg.getDouble("Pvp3.Z"));
        loc.setYaw((float)cfg.getDouble("Pvp3.Yaw"));
        loc.setPitch((float)cfg.getDouble("Pvp3.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Pvp3.World")));

        return loc;

    }
    public static Location getpvp4(Player p) {

        File file = new File("plugins//MLGWars//Locations.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Pvp4.X"));
        loc.setY(cfg.getDouble("Pvp4.Y"));
        loc.setZ(cfg.getDouble("Pvp4.Z"));
        loc.setYaw((float)cfg.getDouble("Pvp4.Yaw"));
        loc.setPitch((float)cfg.getDouble("Pvp4.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Pvp4.World")));

        return loc;

    }
}
