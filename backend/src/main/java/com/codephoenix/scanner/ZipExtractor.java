package com.codephoenix.scanner;

import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
public class ZipExtractor {

    /**
     * Safely extracts a zip file to the destination directory.
     * Prevents Zip Slip vulnerability by checking that the extracted file path
     * is within the target destination directory.
     */
    public static void extract(File zipFile, File destDir) throws IOException {
        String destDirPath = destDir.getCanonicalPath();

        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry = zipIn.getNextEntry();
            
            while (entry != null) {
                File targetFile = new File(destDir, entry.getName());
                String targetFilePath = targetFile.getCanonicalPath();

                if (!targetFilePath.startsWith(destDirPath + File.separator) && !targetFilePath.equals(destDirPath)) {
                    throw new IOException("Entry is outside of the target dir: " + entry.getName());
                }

                if (entry.isDirectory()) {
                    if (!targetFile.exists()) {
                        targetFile.mkdirs();
                    }
                } else {
                    // Create parent directories if they don't exist
                    File parent = targetFile.getParentFile();
                    if (parent != null && !parent.exists()) {
                        parent.mkdirs();
                    }

                    // Write file
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile))) {
                        byte[] bytesIn = new byte[4096];
                        int read = 0;
                        while ((read = zipIn.read(bytesIn)) != -1) {
                            bos.write(bytesIn, 0, read);
                        }
                    }
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
        log.info("ZIP file extracted successfully to {}", destDirPath);
    }
}
