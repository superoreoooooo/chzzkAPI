package kr.superoreo.chzzkapi.util;

import kr.superoreo.chzzkapi.ChzzkAPI;
import kr.superoreo.chzzkapi.listener.ChzzkListener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.r2turntrue.chzzk4j.chat.ChzzkChat;

import java.io.IOException;
import java.util.HashMap;

public class ChzzkMgr {
    public static ChzzkMgr instance;

    private ChzzkMgr() {
        chatOverwatchMap = new HashMap<>();
    }

    public static ChzzkMgr getInstance() {
        if (instance == null) {
            instance = new ChzzkMgr();
        }

        return instance;
    }

    public HashMap<String, ChzzkChat> getChatOverwatchMap() {
        return chatOverwatchMap;
    }

    private HashMap<String, ChzzkChat> chatOverwatchMap;

    public boolean addChatOverWatch(String CHANNEL_ID) {
        ChzzkChat chat;
        boolean isAdded = false;
        try {
            chat = ChzzkAPI.chzzk.chat(CHANNEL_ID)
                    .withChatListener(new ChzzkListener(CHANNEL_ID)).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (chat != null) {
            chatOverwatchMap.put(CHANNEL_ID, chat);
            chat.connectBlocking();
            isAdded = true;
        }
        return isAdded;
    }

    public boolean removeChatOverWatch(String CHANNEL_ID) {
        boolean isRemoved = false;
        for (String CNLID : chatOverwatchMap.keySet()) {
            if (CNLID.equals(CHANNEL_ID)) {
                chatOverwatchMap.get(CNLID).closeBlocking();
                chatOverwatchMap.remove(CNLID);
                isRemoved = true;
            }
        }
        return isRemoved;
    }
}
