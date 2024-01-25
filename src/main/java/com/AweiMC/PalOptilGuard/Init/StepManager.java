package com.AweiMC.PalOptilGuard.Init;

import com.AweiMC.PalOptilGuard.Main;
import com.AweiMC.PalOptilGuard.util.I18nManager;

public class StepManager {
    private static Enum<StepEnum> step = null;
    public static void setStep(StepEnum stepEnum) {
        step=stepEnum;
        switch (stepEnum) {
            case Init -> {
                Main.LOGGER.info(I18nManager.getMessage("message.lang"));
            }
            case DOWNLOAD -> {
            }
            case UNZIP -> {
                Main.LOGGER.info(I18nManager.getMessage("message.download.ok"));
                Main.LOGGER.info(I18nManager.getMessage("message.zip.un.ok"));
            }
            case INSTALL -> {
                Main.LOGGER.info(I18nManager.getMessage("message.cmd.ins.run"));
            }
            case CONTINUE -> {
                Main.LOGGER.info(I18nManager.getMessage("message.cmd.ins.run.to"));
            }
            case INSCOMPLETE -> {
                Main.LOGGER.info(I18nManager.getMessage("message.cmd.ins.complete"));
            }

        }
    }
    public Enum<StepEnum> getStep() {
     return step;
    }
}
