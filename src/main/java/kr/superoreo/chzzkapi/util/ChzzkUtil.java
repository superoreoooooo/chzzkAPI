package kr.superoreo.chzzkapi.util;

import kr.superoreo.chzzkapi.ChzzkAPI;
import xyz.r2turntrue.chzzk4j.types.channel.ChzzkChannel;

import java.io.IOException;

public class ChzzkUtil {
    public static ChzzkChannel getChannelByID(String CHANNEL_ID) throws IOException {
        return ChzzkAPI.chzzk.getChannel(CHANNEL_ID);
    }
}
