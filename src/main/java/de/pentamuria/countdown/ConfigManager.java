package de.pentamuria.countdown;

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

    public void setStartDate(Date startDate) {
        config.addDefault("startTime", startDate);
        config.options().copyDefaults(true);
        plugin.saveConfig();

        plugin.getServer().getConsoleSender().sendMessage(
                "[CD] The StartTime has been updated now the Server must be restart!");
    }

    public Date getStartDate() {
        return startDate;
    }

}
