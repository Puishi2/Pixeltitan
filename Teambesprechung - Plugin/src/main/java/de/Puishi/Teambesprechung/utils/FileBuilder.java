package de.Puishi.Teambesprechung.utils;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 14.05.2020 / 16:02                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class FileBuilder {

    private File f;
    private YamlConfiguration c;
    private String valuePath;

    public FileBuilder(String FilePath, String FileName){
        this.f  = new File(FilePath, FileName);
        this.c = YamlConfiguration.loadConfiguration(this.f);
    }

    public FileBuilder setValue(String ValuePath, Object Value){
        c.set(ValuePath, Value);
        return this;
    }

    public Object getObject(String ValuePath){
        return c.get(ValuePath);
    }

    public int getInt(String ValuePath){
        return c.getInt(ValuePath);
    }

    public String getString(String ValuePath){
        return c.getString(ValuePath);
    }

    public boolean getBoolean(String ValuePath){
        return c.getBoolean(ValuePath);
    }

    public long getLong(String ValuePath){
        return c.getLong(ValuePath);
    }

    public List<String> getStringList(String ValuePath){
        return c.getStringList(ValuePath);
    }

    public Set<String> getKeys(boolean deep){
        return c.getKeys(deep);
    }

    public ConfigurationSection ConfigurationSection(String Selection){
        return c.getConfigurationSection(Selection);
    }

    public double getDouble(String ValuePath){
        return c.getDouble(String.valueOf(ValuePath));
    }

    public FileBuilder save(){
        try {
            this.c.save(this.f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

}
