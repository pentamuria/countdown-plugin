package de.pentamuria.countdown;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.Date;

/**
 * Created by Rafel/oconner13
 * Project: Pentamuria
 * Date: 13/12/2022
 * Time: 21:20
 */

public class ConfigManager {

    private Plugin plugin;
    private FileConfiguration config;
    private Date startDate;

    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();


        if(config.getString("startTime") == null) {
            config.addDefault("startTime", new Date());
            config.options().copyDefaults(true);
            plugin.saveConfig();
        }

        startDate = (Date) config.get("startTime");
    }

    public void setStartLocation(Location location) {
        config.set("startLocation", location);
        plugin.saveConfig();
    }

    public Location getStartLocation() {
        return (Location) config.get("startLocation");
    }

    public Date getStartDate() {
        return startDate;
    }

}
