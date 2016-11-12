package com.zdh.alphathink.imh.util;

import android.net.wifi.WifiInfo;

/**
 * Created by Panda on 2016/10/17.
 */

public class GetIpAddress {
    public static String getIpAddress(WifiInfo wifiInfo) {
        String result;
        int ip = wifiInfo.getIpAddress();

        result = String.format("%d.%d.%d.%d", (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff),
                (ip >> 24 & 0xff));

        return result;
    }
}
