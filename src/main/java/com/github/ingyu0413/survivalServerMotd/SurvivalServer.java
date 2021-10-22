package com.github.ingyu0413.survivalServerMotd;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalServer extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    private long getWorldTime() {
        long gametime = Bukkit.getServer().getWorld("world").getTime();
        return (gametime + 6000) % 24000;
    }

    private int getHour() {
        return (int) getWorldTime() / 1000;
    }

    private String getWorldTimeString() {
        long gametime = getWorldTime();
        int hour = (int) gametime / 1000;
        int minute = (int) (gametime % 1000 / 1000.0 * 60);
        int second = (int) (gametime % 1000 / 1000.0 * 3600 % 60);
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    @EventHandler
    public void onPaperServerListPing(PaperServerListPingEvent e) {
        ChatColor titlecolor = null;
        int hour = getHour();
        if (hour < 5 || hour >= 19) {
            titlecolor = ChatColor.DARK_BLUE; //night
        } else if (hour < 7) {
            titlecolor = ChatColor.BLUE; //sunrise
        } else if (hour < 18) {
            titlecolor = ChatColor.AQUA; //day
        } else {
            titlecolor = ChatColor.GOLD; //sunset
        }
        e.setMotd(titlecolor + "" + ChatColor.BOLD + "마인크래프트 야생서버" + ChatColor.RESET + " " + ChatColor.DARK_GRAY + "time: " + getWorldTimeString());
        e.getPlayerSample().clear();
    }
}