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

    private String getworldtime() {
        long gametime = Bukkit.getServer().getWorld("world").getTime();
        if (gametime <= 18000) {
            gametime += 6000;
        } else if (gametime > 18000) {
            gametime -= 18000;
        }
        int hour = (int) gametime / 1000;
        int minute = (int) (gametime % 1000 / 1000.0 * 60);
        int second = (int) (gametime % 1000 / 1000.0 * 3600 % 60);
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    @EventHandler
    public void onPaperServerListPing(PaperServerListPingEvent e) {
        e.setMotd(ChatColor.BLUE + "" + ChatColor.BOLD + "24시간 생야생 마크서버" + ChatColor.RESET + " " + ChatColor.DARK_GRAY + getworldtime());
        e.getPlayerSample().clear();
    }
}