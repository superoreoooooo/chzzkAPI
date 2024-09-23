package kr.superoreo.chzzkapi.util;

import kr.superoreo.chzzkapi.ChzzkAPI;
import kr.superoreo.chzzkapi.listener.ChzzkListener;
import org.bukkit.entity.Player;
import xyz.r2turntrue.chzzk4j.Chzzk;
import xyz.r2turntrue.chzzk4j.chat.ChzzkChat;
import xyz.r2turntrue.chzzk4j.types.channel.ChzzkChannel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class ChzzkMgr {
    public static ChzzkMgr instance;

    private ChzzkMgr() {
        chatOverwatchMap = new HashMap<>();
        chzzkPlayers = new HashMap<>();
    }

    public static ChzzkMgr getInstance() {
        if (instance == null) {
            instance = new ChzzkMgr();
        }

        return instance;
    }

    public HashMap<ChzzkChannel, ChzzkChat> getChatOverwatchMap() {
        return chatOverwatchMap;
    }

    public HashMap<ChzzkChannel, Player> getChzzkPlayers() {
        return chzzkPlayers;
    }

    private HashMap<ChzzkChannel, Player> chzzkPlayers;
    private HashMap<ChzzkChannel, ChzzkChat> chatOverwatchMap;

    public boolean removeChzzkPlayer(Player player) {
        for (ChzzkChannel chzzkChannel : chzzkPlayers.keySet()) {
            if (chzzkPlayers.get(chzzkChannel) == player) {
                return chzzkPlayers.remove(chzzkChannel) == player;
            }
        }
        return false;
    }

    public void addChzzkPlayer(ChzzkChannel channel, Player player) {
        getChzzkPlayers().put(channel, player);
    }

    public boolean addChatOverWatch(String CHANNEL_ID) throws IOException {
        ChzzkChat chat;
        boolean isAdded = false;
        for (ChzzkChannel channel : getChatOverwatchMap().keySet()) {
            if (channel.getChannelId().equals(CHANNEL_ID)) {
                return false;
            }
        }
        try {
            chat = ChzzkAPI.chzzk.chat(CHANNEL_ID)
                    .withChatListener(new ChzzkListener(CHANNEL_ID)).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (chat != null) {
            chatOverwatchMap.put(ChzzkUtil.getChannelByID(CHANNEL_ID), chat);
            chat.connectBlocking();
            isAdded = true;
        }
        return isAdded;
    }

    public boolean removeChatOverWatch(String CHANNEL_ID) {
        boolean isRemoved = false;
        for (ChzzkChannel CNL : chatOverwatchMap.keySet()) {
            if (CNL.getChannelId().equals(CHANNEL_ID)) {
                chatOverwatchMap.get(CNL).closeBlocking();
                chatOverwatchMap.remove(CNL);
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    public boolean clearChatOverwatch() {
        Iterator<ChzzkChannel> iterator = chatOverwatchMap.keySet().iterator();
        while (iterator.hasNext()) {
            iterator.remove();
        }
        return chatOverwatchMap.isEmpty();
    }
}
