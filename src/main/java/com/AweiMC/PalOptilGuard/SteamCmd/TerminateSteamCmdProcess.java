package com.AweiMC.PalOptilGuard.SteamCmd;

import com.AweiMC.PalOptilGuard.Init.OSDetector;
import com.AweiMC.PalOptilGuard.Init.OSEnum;
import com.AweiMC.PalOptilGuard.Main;
import com.AweiMC.PalOptilGuard.util.I18nManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TerminateSteamCmdProcess {

    public static void terminateSteamCmdProcess() {
        ProcessBuilder processBuilder;

        if (OSDetector.PalOS == OSEnum.WINDOWS) {
            // Windows
            processBuilder = new ProcessBuilder("taskkill", "/F", "/IM", "steamcmd.exe");
        } else {
            // Linux
            processBuilder = new ProcessBuilder("pkill", "steamcmd");
        }

        try {
            Process process = processBuilder.start();
            // 等待进程终止，设置一个超时时间
            boolean processTerminated = process.waitFor(5, TimeUnit.SECONDS);

            if (!processTerminated) {
                // 如果在超时时间内没有终止，强制终止
                process.destroyForcibly().waitFor();
            }

            int exitCode = process.exitValue();
            //Main.LOGGER.info(I18nManager.getMessage("message.cmd.kil") + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
