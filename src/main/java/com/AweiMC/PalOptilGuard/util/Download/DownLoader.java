package com.AweiMC.PalOptilGuard.util.Download;

import com.AweiMC.PalOptilGuard.Main;
import com.AweiMC.PalOptilGuard.util.I18nManager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


import java.io.File;

public class DownLoader {
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public void download(String url, String outputPath) throws Exception {
        File outputFile = new File(outputPath);

        // 如果文件已经存在，先删除
        if (outputFile.exists()) {
            if (outputFile.delete()) {
                Main.LOGGER.info(I18nManager.getMessage("message.download.file.deleted") + outputPath);
            } else {
                Main.LOGGER.error(I18nManager.getMessage("message.download.file.delete.failed") + outputPath);
                return;
            }
        }

        // 创建新文件
        if (outputFile.createNewFile()) {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            try (InputStream in = connection.getInputStream();
                 FileOutputStream out = new FileOutputStream(outputPath)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        } else {
            Main.LOGGER.error(I18nManager.getMessage("message.download.file.create.failed") + outputPath);
        }
    }

    public void downloader(String url, String outputPath) throws Exception {
        setUrl(url);
        Main.LOGGER.info(I18nManager.getMessage("message.download.run") + outputPath);

        download(this.url, outputPath);
    }
}

