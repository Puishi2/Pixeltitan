package de.vacebuild.api.sql;

import de.vacebuild.api.main.VaceAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SQLFile {

    private VaceAPI plugin;
    private File file;
    private FileConfiguration cfg;
    public String host;
    public String port;
    public String username;
    public String database;
    public String password;

    public SQLFile(VaceAPI plugin) {
        this.plugin = plugin;
        this.file = new File("plugins//PixelTitan", "mysql.yml");
        this.cfg = YamlConfiguration.loadConfiguration(this.file);
    }

    public void setFile() {
        if (!this.file.exists()) {
            this.cfg.options().copyDefaults(true);

            this.cfg.set("host", "localhost");
            this.cfg.set("port", "3306");
            this.cfg.set("username", "root");
            this.cfg.set("database", "Bauserver");
            this.cfg.set("password", "risomugi");

            try {
                this.cfg.save(this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readFile() {
        if (this.file.exists()) {
            this.host = this.cfg.getString("host");
            this.port = this.cfg.getString("port");
            this.username = this.cfg.getString("username");
            this.database = this.cfg.getString("database");
            this.password = this.cfg.getString("password");
        }
    }

}
