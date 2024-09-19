package kr.superoreo.chzzkapi.util;

import kr.superoreo.chzzkapi.ChzzkAPI;
import kr.superoreo.chzzkapi.listener.ChzzkListener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.r2turntrue.chzzk4j.chat.ChzzkChat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChzzkMgr {
    public static ChzzkMgr instance;

    private ChzzkMgr() {
        chatOverwatchList = new ArrayList<>();
    }

    public static ChzzkMgr getInstance() {
        if (instance == null) {
            instance = new ChzzkMgr();
        }

        return instance;
    }

    private List<ChzzkChat> chatOverwatchList;

    public List<ChzzkChat> getChatOverwatchList() {
        return chatOverwatchList;
    }

    public boolean addChatOverWatch(String CHANNEL_ID) {
        ChzzkChat chat;
        boolean isAdded = false;
        try {
            chat = JavaPlugin.getPlugin(ChzzkAPI.class).chzzk.chat(CHANNEL_ID).withChatListener(new ChzzkListener()).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (chat != null) {
            chatOverwatchList.add(chat);
            chat.connectBlocking();
            isAdded = true;
        }
        return isAdded;
    }

    public boolean removeChatOverWatch(String CHANNEL_ID) {
        boolean isRemoved = false;
        for (ChzzkChat chat : chatOverwatchList) {
            if (chat.getChannelId().equals(CHANNEL_ID)) {
                chat.closeBlocking();
                chatOverwatchList.remove(chat);
                isRemoved = true;
            }
        }
        return isRemoved;
    }
}
