package kr.superoreo.chzzkapi;

import kr.superoreo.chzzkapi.command.Command;
import kr.superoreo.chzzkapi.listener.BukkitListener;
import kr.superoreo.chzzkapi.listener.ChzzkListener;
import kr.superoreo.chzzkapi.util.ChzzkMgr;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.r2turntrue.chzzk4j.Chzzk;
import xyz.r2turntrue.chzzk4j.ChzzkBuilder;
import xyz.r2turntrue.chzzk4j.chat.ChzzkChat;

import java.io.IOException;

public final class ChzzkAPI extends JavaPlugin {

    public static Chzzk chzzk;
    //public static String CHANNEL_ID;
    //private static ChzzkChat chat;
    public static ChzzkMgr mgr;

    @Override
    public void onEnable() {
        chzzk = new ChzzkBuilder().build();
        mgr = ChzzkMgr.getInstance();

        getCommand("chzzk").setExecutor(new Command());
        Bukkit.getPluginManager().registerEvents(new BukkitListener(), this);
        /**
        try {
            chat = chzzk.chat(CHANNEL_ID).withChatListener(new ChzzkListener()).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        chat.connectBlocking(); */
    }

    @Override
    public void onDisable() {
        //chat.closeBlocking();
    }
}
