package com.AweiMC.PalOptilGuard.SteamCmd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToTxtFile {
    public static void writeToTxtFile(String information, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // 将信息写入文件
            writer.write(information);

            System.out.println("Information written to " + filePath + " successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
