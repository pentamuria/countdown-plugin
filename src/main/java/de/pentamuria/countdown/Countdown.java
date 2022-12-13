package de.pentamuria.countdown;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;

public final class Countdown extends JavaPlugin {

    private Date startDate = null;
    private CountdownManager countdownManager;

    @Override
    public void onEnable() {

        ConfigManager configManager = new ConfigManager(this);
        startDate = configManager.getStartDate();

        countdownManager = new CountdownManager(this, startDate);

        getServer().getConsoleSender().sendMessage("[CD] Plugin was loaded!");

        // Start the Countdown in 10 Seconds
        getServer().getScheduler().runTaskLaterAsynchronously(
                this, () -> countdownManager.start(), 20L*10L);


    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("[CD] Plugin was unloaded!");
    }
}
