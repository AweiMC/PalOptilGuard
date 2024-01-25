package com.AweiMC.PalOptilGuard.SteamCmd;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkPinger {
    public static boolean isHostReachable(String host) {
        try {
            // 使用 InetAddress 获取指定主机的 InetAddress 实例
            InetAddress inetAddress = InetAddress.getByName(host);

            // 使用 isReachable 方法进行连接测试，5000 毫秒超时
            return inetAddress.isReachable(5000);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
