package de.pentamuria.countdown;

import org.bukkit.plugin.Plugin;

import java.util.Date;

/**
 * Created by Rafel/oconner13
 * Project: Pentamuria
 * Date: 13/12/2022
 * Time: 21:08
 */

public class CountdownManager {

    private Plugin plugin;
    private Date startDate;

    public CountdownManager(Plugin plugin, Date startDate) {
        this.plugin = plugin;
        this.startDate = startDate;
    }

    public void start() {

    }

}
