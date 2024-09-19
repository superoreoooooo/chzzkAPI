package kr.superoreo.chzzkapi.listener;

import org.bukkit.Bukkit;
import xyz.r2turntrue.chzzk4j.chat.ChatEventListener;
import xyz.r2turntrue.chzzk4j.chat.ChzzkChat;
import xyz.r2turntrue.chzzk4j.chat.DonationMessage;

public class ChzzkListener implements ChatEventListener {
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
            Bukkit.broadcastMessage("[Donation] 익명: " + msg.getContent() + ": " + msg.getContent() + " [" + msg.getPayAmount() + "원]");
            return;
        }
        Bukkit.broadcastMessage("[Donation] " + msg.getProfile().getNickname() + ": " + msg.getContent() + " [" + msg.getPayAmount() + "원]");
    }
}
