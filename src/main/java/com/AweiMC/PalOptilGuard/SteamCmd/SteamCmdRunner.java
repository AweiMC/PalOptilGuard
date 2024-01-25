package com.AweiMC.PalOptilGuard.SteamCmd;

import com.AweiMC.PalOptilGuard.Main;
import com.AweiMC.PalOptilGuard.util.I18nManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SteamCmdRunner {

    public static void runSteamCmd(String steamCmdPath) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(steamCmdPath);
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                Main.LOGGER.info("[SteamCmd] {}",line);
                if (line.contains("Loading Steam API...OK")) {
                    // 先终止当前进程
                    process.destroyForcibly();
                    TerminateSteamCmdProcess.terminateSteamCmdProcess();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 等待 steamcmd 进程结束
        try {
            int exitCode = process.waitFor();
            if(exitCode == 7) {
                Main.LOGGER.info(I18nManager.getMessage("message.cmd.ins.error") + exitCode);
            }
            Main.LOGGER.info(I18nManager.getMessage("message.cmd.ins.ok") + exitCode);
            process.destroyForcibly();
            TerminateSteamCmdProcess.terminateSteamCmdProcess();
            //if(exitCode !=0) System.exit(-1);
        } catch (InterruptedException e) {
            process.destroyForcibly();
            TerminateSteamCmdProcess.terminateSteamCmdProcess();
            e.printStackTrace();
        }
    }
    public static void dowStmCmd(String linuxCommand) {

        try {
            // 创建进程构建器
            ProcessBuilder processBuilder = new ProcessBuilder();
            // 设置命令和参数
            processBuilder.command(linuxCommand);

            // 启动进程
            Process process = processBuilder.start();

            // 等待进程执行完成
            int exitCode = process.waitFor();

            // 打印命令执行结果
            System.out.println("Command exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
