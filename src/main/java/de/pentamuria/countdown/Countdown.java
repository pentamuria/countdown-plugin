package de.pentamuria.countdown;

import de.pentamuria.countdown.commands.SetStartLocationCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;
import java.util.Objects;

public final class Countdown extends JavaPlugin {

    private Date startDate = null;
    private CountdownManager countdownManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {

        configManager = new ConfigManager(this);
        startDate = configManager.getStartDate();
        // TODO Print StartTime to Console


        if(startDate.getTime()- new Date().getTime() <= 2*60*1000) {
            getServer().getConsoleSender().sendMessage(
                    "[CD] The countdown has expired or is about to expire, the plugin wants to disable.");

            getPluginLoader().disablePlugin(this);
            return;
        }

        countdownManager = new CountdownManager(this, startDate);

        getServer().getConsoleSender().sendMessage("[CD] Plugin was enable!");

        countdownManager.start();

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("[CD] Plugin was disable!");
    }

}
