package kr.superoreo.chzzkapi;

import kr.superoreo.chzzkapi.command.Command;
import kr.superoreo.chzzkapi.listener.BukkitListener;
import kr.superoreo.chzzkapi.listener.ChzzkListener;
import kr.superoreo.chzzkapi.util.ChzzkMgr;
import kr.superoreo.chzzkapi.util.ChzzkUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.r2turntrue.chzzk4j.Chzzk;
import xyz.r2turntrue.chzzk4j.ChzzkBuilder;
import xyz.r2turntrue.chzzk4j.chat.ChzzkChat;

import java.io.IOException;

public final class ChzzkAPI extends JavaPlugin {

    public static Chzzk chzzk;
    public static ChzzkMgr mgr;

    @Override
    public void onEnable() {
        chzzk = new ChzzkBuilder().build();
        mgr = ChzzkMgr.getInstance();

        getCommand("chzzk").setExecutor(new Command());
        Bukkit.getPluginManager().registerEvents(new BukkitListener(), this);
    }

    @Override
    public void onDisable() {
        mgr.clearChatOverwatch();
    }
}
