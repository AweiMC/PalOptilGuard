package com.AweiMC.PalOptilGuard.SteamCmd.PalWorld;

import java.io.File;

public class PalServerCheck {
    public static boolean isFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }
}
