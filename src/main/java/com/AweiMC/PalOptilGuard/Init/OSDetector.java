package com.AweiMC.PalOptilGuard.Init;


import com.AweiMC.PalOptilGuard.Main;
import com.AweiMC.PalOptilGuard.util.I18nManager;


public class OSDetector {
    public static Enum<OSEnum> PalOS;

    public static void init() {
        String os = getOperatingSystem();
        Main.LOGGER.info(I18nManager.getMessage("message.os.is") + os);
    }

    public static String getOperatingSystem() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            PalOS = OSEnum.WINDOWS;
            return "Windows";
        } else if (os.contains("nix") || os.contains("nux") ) {
            PalOS = OSEnum.Linux;
            return "Unix/Linux";
        } else if(os.contains("mac")){
            PalOS = OSEnum.MAC;
            return "MAC";
        } else {
            return "Other";
        }
    }

}


