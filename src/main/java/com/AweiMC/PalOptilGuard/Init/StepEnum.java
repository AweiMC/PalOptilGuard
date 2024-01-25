package com.AweiMC.PalOptilGuard.Init;

public enum StepEnum {
    Init,//初始化
    DOWNLOAD,//下载SteamCmd.zip
    UNZIP,//解压SteamCmd.zip - SteamCmd.exe
    INSTALL,//安装SteamCmd.exe
    CONTINUE, //安装PalWorldServer
    INSCOMPLETE//安装完成


}
