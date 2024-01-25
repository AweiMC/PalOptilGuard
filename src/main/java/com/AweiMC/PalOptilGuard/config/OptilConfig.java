package com.AweiMC.PalOptilGuard.config;

public class OptilConfig {
    private String language = "zh_CN";
    private int timeOut = 5000;
    private final String steamCmdUrl = "https://steamcdn-a.akamaihd.net/client/installer/steamcmd.zip";
    private final String proxyHost = "localhost";
    private final int proxyPort = 8080;
    private final boolean Proxy = false;
    public boolean getProxyBool() {
        return Proxy;
    }
    public String getProxyHost() {
        return proxyHost;
    }
    public int getProxyPort() {
        return proxyPort;
    }

    public String getSteamCmdUrl() {
        return steamCmdUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }
}
