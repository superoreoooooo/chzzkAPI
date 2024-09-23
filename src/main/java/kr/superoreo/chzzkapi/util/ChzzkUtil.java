package kr.superoreo.chzzkapi.util;

import kr.superoreo.chzzkapi.ChzzkAPI;
import org.bukkit.entity.Player;
import xyz.r2turntrue.chzzk4j.types.channel.ChzzkChannel;

import java.io.IOException;
import java.util.HashMap;

public class ChzzkUtil {
    public static ChzzkChannel getChannelByID(String CHANNEL_ID) throws IOException {
        return ChzzkAPI.chzzk.getChannel(CHANNEL_ID);
    }
}
