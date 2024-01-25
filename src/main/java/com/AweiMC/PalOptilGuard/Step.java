package com.AweiMC.PalOptilGuard;

import com.AweiMC.PalOptilGuard.Init.*;

import com.AweiMC.PalOptilGuard.SteamCmd.PalWorld.InsPalWorldServer;
import com.AweiMC.PalOptilGuard.SteamCmd.NetworkPinger;
import com.AweiMC.PalOptilGuard.SteamCmd.PalWorld.PalServerCheck;
import com.AweiMC.PalOptilGuard.SteamCmd.SteamCmdRunner;
import com.AweiMC.PalOptilGuard.SteamCmd.WriteToTxtFile;
import com.AweiMC.PalOptilGuard.util.Download.DownLoader;
import com.AweiMC.PalOptilGuard.config.OptilConfig;
import com.AweiMC.PalOptilGuard.util.I18nManager;
import com.AweiMC.PalOptilGuard.util.Zip.Unzipper;

import java.io.File;
import java.io.IOException;
import java.net.ProxySelector;
import java.util.concurrent.CompletableFuture;


public class Step {
    public static final String stmC = "steamcmd";
    private static final String parInsPath = Main.Name+File.separator+stmC+"palworldserver";
    private static final String zipPathZip = Main.Name + File.separator + "steamcmd.zip";

    private static final String stmPath = Main.Name + File.separator + stmC;


    public static void Init() {
        getNetworkTest();
        setProxy();
        checkPalServer();
        StepManager.setStep(StepEnum.Init); // 步骤-初始化
        downloadAndUnzip();
        runnerSteamCmd();
        insServer();
        insComplete();
    }
    private static void checkPalServer() {
        String palPath = Main.Name + File.separator + Step.stmC + File.separator + "palworldserver" + File.separator + getPalServer();
        if (PalServerCheck.isFileExists(palPath)) {
            Main.LOGGER.info(palPath + I18nManager.getMessage("init.file.ok"));
            System.exit(0);
        } else {
            Main.LOGGER.info(palPath + I18nManager.getMessage("init.file.un"));
        }
    }
    private static void getNetworkTest() {
        Main.LOGGER.info(I18nManager.getMessage("message.network.test"));
        String websites = "steamcdn-a.akamaihd.net";
        if(!NetworkPinger.isHostReachable(websites)) {
            Main.LOGGER.info(I18nManager.getMessage("message.network.error"));
            System.exit(-1);
        }
        Main.LOGGER.info(I18nManager.getMessage("message.network.ok"));
    }
    private static void setProxy() {
        var proxy = new OptilConfig();
        if (proxy.getProxyBool()) {
            Main.LOGGER.info(I18nManager.getMessage("message.proxy.on"));
            System.setProperty("http.proxyHost", proxy.getProxyHost());
            System.setProperty("http.proxyPort", String.valueOf(proxy.getProxyPort()));
        } else {
            Main.LOGGER.info(I18nManager.getMessage("message.proxy.system"));
            ProxySelector.setDefault(null);
        }
    }


    private static void downloadAndUnzip() {
        try {
            if(OSDetector.PalOS == OSEnum.WINDOWS) {
                download();
                unzip();
            } else {
                SteamCmdRunner.dowStmCmd("sudo apt install steamcmd");
            }
        } catch (Exception e) {
            Main.LOGGER.error(e.getMessage());
            exitWithError();
        }
    }

    private static void download() throws Exception {
        var config = new OptilConfig();
        var dw = new DownLoader();
        StepManager.setStep(StepEnum.DOWNLOAD);// 步骤-下载
        File zipFile = new File(zipPathZip);
        if (!zipFile.exists()) {
            dw.downloader(config.getSteamCmdUrl(),stmPath+".zip");
        }
    }

    private static void unzip() {
        StepManager.setStep(StepEnum.UNZIP);
        try {
            createSteamCmdFolder();
            Unzipper.unzip(zipPathZip, stmPath);
            //Main.LOGGER.info("ZipPath: "+ zipPathZip + " | ZipOut: " + stmPath);
            Main.LOGGER.info(I18nManager.getMessage("message.zip.unzip.ok"));
        } catch (IOException e) {
            Main.LOGGER.error(e.getMessage());
            exitWithError();
        }
    }

    private static void runnerSteamCmd() {
        String steamCmdPath = Main.Name + File.separator + stmC + File.separator + getSteamCmd();
        createSteamCmdFolder();

        CompletableFuture<Void> asyncTask = CompletableFuture.runAsync(() -> {
            try {
                SteamCmdRunner.runSteamCmd(steamCmdPath);
            } catch (IOException e) {
                exitWithError();
                throw new RuntimeException(e);
            }
        });

        // 等待异步任务完成
        asyncTask.join();
    }


    private static void insServer() {
        String steamCmdPath = Main.Name + File.separator + stmC + File.separator + getSteamCmd();
        StepManager.setStep(StepEnum.CONTINUE);
        InsPalWorldServer.runIns(steamCmdPath);
    }
    private static void insComplete() {
        getPalWorldServer();
        String filePath = "PathInfo.txt";
        var par = System.getProperty("user.dir")+parInsPath;
        WriteToTxtFile.writeToTxtFile(I18nManager.getMessage("message.ins.pal.path.txt") + par,filePath);
        Main.LOGGER.info(I18nManager.getMessage("main.1"));
        Main.LOGGER.info(I18nManager.getMessage("message.ins.complete"));
        Main.LOGGER.info(I18nManager.getMessage("message.ins.pal.path") + parInsPath);
        Main.LOGGER.info(I18nManager.getMessage("message.ins.pal.a"));
        Main.LOGGER.info(I18nManager.getMessage("message.ins.pal.b"));
        Main.LOGGER.info(I18nManager.getMessage("message.ins.pal.c"));
        Main.LOGGER.info(I18nManager.getMessage("message.ins.pal.d"));
        Main.LOGGER.info(I18nManager.getMessage("message.ins.pal.e"));
        Main.LOGGER.info(I18nManager.getMessage("main.1"));
    }
    private static void getPalWorldServer() {
        String palPath = Main.Name + File.separator + Step.stmC + File.separator + "palworldserver" + File.separator + getPalServer();
        if (PalServerCheck.isFileExists(palPath)) {
            System.exit(0);
        } else {
            Main.LOGGER.info(palPath + I18nManager.getMessage("message.pal.ins.error.c"));
            System.exit(-1);
        }
    }

    private static void createSteamCmdFolder() {
        File steamCmdFolder = new File(Main.Name + File.separator + stmC);
        if (!steamCmdFolder.exists()) {
            // 如果文件夹不存在，创建文件夹
            if (steamCmdFolder.mkdirs()) {
                Main.LOGGER.info(I18nManager.getMessage("message.zip.create.folder.ok"));
            } else {
                Main.LOGGER.error(I18nManager.getMessage("message.zip.create.folder.fail"));
                // 这里可以添加其他处理逻辑，比如抛出异常或者退出程序
                System.exit(1);
            }
        }
    }
    private static void exitWithError() {
        // 这里可以添加其他需要执行的清理或日志记录操作
        System.exit(1);
    }
    private static String getSteamCmd() {
        if(OSDetector.PalOS == OSEnum.WINDOWS) {
            return "steamcmd.exe";
        } else if (OSDetector.PalOS == OSEnum.Linux) {
            return "steamcmd.sh";
        } else {
            System.exit(0);
            return null;
        }
    }
    private static String getPalServer() {
        if(OSDetector.PalOS == OSEnum.WINDOWS) {
            return "PalServer.exe";
        } else if (OSDetector.PalOS == OSEnum.Linux) {
            return "PalServer.sh";
        } else {
            System.exit(0);
            return null;
        }
    }



}
