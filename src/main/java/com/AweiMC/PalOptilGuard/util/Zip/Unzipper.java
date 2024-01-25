package com.AweiMC.PalOptilGuard.util.Zip;

import com.AweiMC.PalOptilGuard.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzipper {

    public static void unzip(String zipFilePath, String unzipFolderPath) throws IOException {
        Path destDir = Paths.get(unzipFolderPath);
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();

            while (entry != null) {
                Path filePath = destDir.resolve(entry.getName());

                if (!entry.isDirectory()) {
                    // 如果是文件，创建父文件夹
                    Files.createDirectories(filePath.getParent());

                    // 写入文件内容
                    try (OutputStream os = Files.newOutputStream(filePath)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zipIn.read(buffer)) != -1) {
                            os.write(buffer, 0, bytesRead);
                        }
                    }
                } else {
                    // 如果是文件夹，创建文件夹
                    Files.createDirectories(filePath);
                }

                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        } catch (IOException e) {
            Main.LOGGER.error(e.getMessage());
            System.exit(1);
        }
    }
}
