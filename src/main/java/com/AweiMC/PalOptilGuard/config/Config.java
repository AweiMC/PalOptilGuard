package com.AweiMC.PalOptilGuard.config;



import com.AweiMC.PalOptilGuard.Main;
import com.AweiMC.PalOptilGuard.util.I18nManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.toml.TomlFactory;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Config {
    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);


    public static void init() throws IOException {
        File configFile = new File(Main.Name+ File.separator + "lang"  + File.separator + "Config.toml");
        if (!configFile.exists()) {
            // 如果文件不存在，生成默认配置并写入文件
            generateDefaultConfig(configFile);
        }
    }
    private static void generateDefaultConfig(File configFile) throws IOException {
        // 确保目录存在
        File parentDir = configFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();  // 创建多层目录
        }

        // 创建默认配置对象
        OptilConfig defaultConfig = new OptilConfig();
        defaultConfig.setLanguage("zh_CN");
        defaultConfig.setTimeOut(42);

        // 使用 Jackson DataFormat TOML 将默认配置写入文件
        ObjectMapper objectMapper = new TomlMapper(new TomlFactory());
        objectMapper.writeValue(configFile, defaultConfig);

        LOGGER.info(I18nManager.getMessage("message.config.gen"));
    }



}
