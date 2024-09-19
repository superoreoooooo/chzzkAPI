package kr.superoreo.chzzkapi.listener;

import kr.superoreo.chzzkapi.util.ChzzkUtil;
import org.bukkit.Bukkit;
import xyz.r2turntrue.chzzk4j.chat.ChatEventListener;
import xyz.r2turntrue.chzzk4j.chat.ChzzkChat;
import xyz.r2turntrue.chzzk4j.chat.DonationMessage;
import xyz.r2turntrue.chzzk4j.types.channel.ChzzkChannel;

import java.io.IOException;

public class ChzzkListener implements ChatEventListener {
    private String CHANNEL_ID;
    private ChzzkChannel channel;

    public ChzzkListener(String CHANNEL_ID) throws IOException {
        this.CHANNEL_ID = CHANNEL_ID;
        this.channel = ChzzkUtil.getChannelByID(CHANNEL_ID);
    }

    @Override
    public void onConnect(ChzzkChat chat, boolean isReconnecting) {
        Bukkit.broadcastMessage("Connected to chat!");

        if (!isReconnecting) {
            chat.requestRecentChat(50);
        }
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onDonationChat(DonationMessage msg) {

        //ChatEventListener.super.onDonationChat(msg);
        if (msg.getProfile() == null) {
            Bukkit.broadcastMessage("(" + channel.getChannelName() + ") [Donation] 익명: " + msg.getContent() + " [" + msg.getPayAmount() + "원]");
            return;
        }
        Bukkit.broadcastMessage("(" + channel.getChannelName() + ") [Donation] " + msg.getProfile().getNickname() + ": " + msg.getContent() + " [" + msg.getPayAmount() + "원]");
    }
}
