package com.AweiMC.PalOptilGuard.SteamCmd.PalWorld;

import com.AweiMC.PalOptilGuard.Main;
import com.AweiMC.PalOptilGuard.SteamCmd.TerminateSteamCmdProcess;
import com.AweiMC.PalOptilGuard.util.I18nManager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class InsPalWorldServer {
    public static void runIns(String stmPath) {
        ProcessBuilder processBuilder = getProcessBuilder(stmPath);
        try {
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,  StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                Main.LOGGER.info("[PalWorld Server Install] {}",line);
            }

            // 读取进程的错误输出
            InputStream errorStream = process.getErrorStream();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                Main.LOGGER.error("[PalWorld Server Install Error] {}",errorLine);
            }

            // 等待进程执行完成
            int exitCode = process.waitFor();
            handleProcessOk(process);//假如运行完成之后
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static ProcessBuilder getProcessBuilder(String stmPath) {
        String PalServer = "palworldserver";
        List<String> command = new ArrayList<>();
        command.add(stmPath);
        command.add("+login");
        command.add("anonymous");
        command.add("+force_install_dir");
        command.add(PalServer);
        command.add("+app_update");
        command.add("2394010");
        command.add("validate");
        command.add("+exit");
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new File(Main.Name + File.separator + "steamcmd"));
        return processBuilder;
    }

    private static void handleProcessOk(Process process) {
        Main.LOGGER.info(I18nManager.getMessage("message.cmd.ins.run.ok"));
        TerminateSteamCmdProcess.terminateSteamCmdProcess();
        process.destroyForcibly();
    }
}

