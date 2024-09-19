package kr.superoreo.chzzkapi.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class BukkitListener implements org.bukkit.event.Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage("ㅎㅇ");
    }
}
