package de.pentamuria.countdown;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rafel/oconner13
 * Project: Pentamuria
 * Date: 13/12/2022
 * Time: 21:08
 */

public class CountdownManager {

    private Plugin plugin;
    private Date startDate;
    private boolean isActiveHourCount;
    private List<Integer> startedMinCountdowns;
    private List<Integer> broadcastedMinuteds;
    private BukkitTask secsCountdown;

    public CountdownManager(Plugin plugin, Date startDate) {
        this.plugin = plugin;
        this.startDate = startDate;
        this.isActiveHourCount = false;
        startedMinCountdowns = new ArrayList<>();
        broadcastedMinuteds = new ArrayList<>();
    }

    public void start() {
        // Blocks the countdown if the start is too close or in the past

        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.runTaskTimer(plugin, task -> {
            Date now = new Date();
            long startMillis = startDate.getTime() - now.getTime();

            if(now.getDate() == startDate.getDate()
                    && now.getMonth() == startDate.getMonth()
                    && now.getYear() == startDate.getYear()) {
                // Today

                int allMin = (int) startMillis/1000/60;
                int mintoNextHour = allMin-((allMin/60)*60);

                if (mintoNextHour <= 3 && !isActiveHourCount && allMin >= 4)  {
                    // Start Countdown to Broadcast.
                    isActiveHourCount = true;
                    sendHourBroadcast();
                } else if(mintoNextHour <= -1) {
                    isActiveHourCount = false;
                }



                if(allMin <= 45+2 && allMin >= 45) {
                    startMinCountdown(45);
                } else if(allMin <= 30+2 && allMin >= 30) {
                    startMinCountdown(30);
                } else if(allMin <= 15+2 && allMin >= 15) {
                    startMinCountdown(15);
                } else if(allMin <= 10+2 && allMin >= 10) {
                    startMinCountdown(10);
                } else if(allMin <= 5+2 && allMin >= 5) {
                    startMinCountdown(5);
                } else if(allMin <= 3+2 && allMin >= 3) {
                    startMinCountdown(3);
                } else if(allMin <= 2+2 && allMin >= 2) {
                    startMinCountdown(2);
                } else if(allMin <= 1+2 && allMin >= 1) {
                    startMinCountdown(1);
                    if(secsCountdown == null) {
                        startSecsCountdown();
                        task.cancel();
                    }
                }
            }
        }, 20L * 10L /*<-- the initial delay */, 20L * 20L /*<-- the interval */);
    }

    private void sendHourBroadcast() {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.runTaskTimer(plugin, task -> {
            Date now = new Date();
            int startSecs = (int) (startDate.getTime() - now.getTime())/1000;
            startSecs = startSecs-((startSecs/60/60)*60*60);

            int hours = (int) ((startDate.getTime() - now.getTime())/1000/60/60);

            // If now the Time
            if (startSecs <= 0) {
                if(hours == 1) {
                    Bukkit.broadcastMessage("§7[§ePentamuria§7] §bPentamuria beginnt in einer Stunde!");
                } else
                    Bukkit.broadcastMessage("§7[§ePentamuria§7] §bPentamuria beginnt in " + hours + " Stunden!");
                task.cancel();
            }
        }, 0L, 4L);
    }

    private void startMinCountdown(int min) {
        if(startedMinCountdowns.contains(min))
            return;
        startedMinCountdowns.add(min);

        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.runTaskTimer(plugin, task -> {
            Date now = new Date();
            int startSecs = (int) (startDate.getTime() - now.getTime())/1000;

            if (startSecs <= min*60) {

                if(min == 1) {
                    Bukkit.broadcastMessage("§7[§ePentamuria§7] §bPentamuria beginnt in einer Minute!");
                } else
                    Bukkit.broadcastMessage("§7[§ePentamuria§7] §bPentamuria beginnt in " + min + " Minuten!");
                task.cancel();
            }
        }, 0L, 20L);

    }

    private void startSecsCountdown() {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        secsCountdown = scheduler.runTaskTimer(plugin,() -> {
            Date now = new Date();
            int startSecs = (int) (startDate.getTime() - now.getTime())/1000;
            if(!broadcastedMinuteds.contains(startSecs)) {
                broadcastedMinuteds.add(startSecs);


                String message = "§7[§ePentamuria§7] §bPentamuria beginnt in " + startSecs + " Sekunden!";
                switch (startSecs) {
                    case 45:
                        Bukkit.broadcastMessage(message);
                        break;
                    case 30:
                        Bukkit.broadcastMessage(message);
                        break;
                    case 15:
                        Bukkit.broadcastMessage(message);
                        break;
                    case 10:
                        Bukkit.broadcastMessage(message);
                        break;
                    case 5:
                        Bukkit.broadcastMessage(message);
                        break;
                    case 4:
                        Bukkit.broadcastMessage(message);
                        break;
                    case 3:
                        Bukkit.broadcastMessage(message);
                        break;
                    case 2:
                        Bukkit.broadcastMessage(message);
                        break;
                    case 1:
                        Bukkit.broadcastMessage("§7[§ePentamuria§7] §bPentamuria beginnt in einer Sekunden!");
                        break;
                    case 0:
                        Bukkit.broadcastMessage("§7[§ePentamuria§7] §bPentamuria beginnt!!!");
                        pentamuriaStart();
                        break;

                }
            }
        }, 0L, 20L);
    }

    private void pentamuriaStart() {

    }

}
