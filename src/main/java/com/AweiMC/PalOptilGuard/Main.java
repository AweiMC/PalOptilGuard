package com.AweiMC.PalOptilGuard;

import com.AweiMC.PalOptilGuard.Init.OSDetector;
import com.AweiMC.PalOptilGuard.Init.OSEnum;
import com.AweiMC.PalOptilGuard.SteamCmd.PalWorld.PalServerCheck;
import com.AweiMC.PalOptilGuard.SteamCmd.PathValidator;
import com.AweiMC.PalOptilGuard.config.Config;
import com.AweiMC.PalOptilGuard.config.OptilConfig;
import com.AweiMC.PalOptilGuard.util.I18nManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


public class Main {

    public static String Name = "PalOptilGuard";
    public static final Logger LOGGER = LoggerFactory.getLogger(Name);
    public static void main(String[] args) throws Exception {
       Main.init();
    }
    public static void init() throws Exception {
        var config = new OptilConfig();
        I18nManager.setLanguage(config.getLanguage());
        initInfo();
        OSDetector.init();
        if(OSDetector.PalOS == OSEnum.MAC) {
            LOGGER.info(I18nManager.getMessage("message.incompatible.mac"));
            System.exit(-1);
        }
        String path = System.getProperty("user.dir");
        LOGGER.info(I18nManager.getMessage("main.path") + path);
        if (!PathValidator.isEnglishPath(path)) {
            Main.LOGGER.error(I18nManager.getMessage( "main.error.path"));
            System.exit(-1);
        }

        Config.init();
        Step.Init();
    }
    private static void initInfo() {
        LOGGER.info(I18nManager.getMessage("message.init.ok"));
        LOGGER.info(I18nManager.getMessage("main.1"));
        LOGGER.info(I18nManager.getMessage("main.2"));
        LOGGER.info(I18nManager.getMessage("main.3"));
        LOGGER.info(I18nManager.getMessage("main.4"));
        LOGGER.info(I18nManager.getMessage("main.5"));
        LOGGER.info(I18nManager.getMessage("main.6"));
        LOGGER.info(I18nManager.getMessage("main.7"));
        LOGGER.info(I18nManager.getMessage("main.8"));
        LOGGER.info(I18nManager.getMessage("main.9"));
        LOGGER.info(I18nManager.getMessage("main.10"));
        LOGGER.info(I18nManager.getMessage("main.1"));
    }
}