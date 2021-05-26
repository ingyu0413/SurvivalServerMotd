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

    @EventHandler
    public void onPaperServerListPing(PaperServerListPingEvent e) {
        // e.setNumPlayers(1);
        long gametime = Bukkit.getServer().getWorld("world").getTime() + 6000;
        String time = String.format("%02d:%02d:%02d", gametime / 1000, (int) (gametime % 1000 / 1000.0 * 60), (int) (gametime % 1000 / 1000.0 * 3));
        e.setMotd(ChatColor.BLUE + "" + ChatColor.BOLD + "키뮤의 아틀리에 비공식 마크서버" + ChatColor.RESET + " " + ChatColor.DARK_GRAY + time);
        e.getPlayerSample().clear();
    }
}