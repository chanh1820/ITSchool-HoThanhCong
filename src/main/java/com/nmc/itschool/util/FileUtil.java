package com.nmc.itschool.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileUtil {
//    @Value("${myinfo.client-name}")
//    private static String clientName;
    @Value("${app.image.upload-dir}")
    private String uploadDir;

    public static boolean isImageValid(MultipartFile imageFile) {
        String contentType = imageFile.getContentType();
        return contentType != null && (contentType.equals("image/png") || contentType.equals("image/jpeg"));
    }

    public static boolean isPdfValid(MultipartFile pdfFile) {
        String contentType = pdfFile.getContentType();
        return contentType != null && contentType.equals("application/pdf");
    }

    public  String saveFile(MultipartFile file) {



        String fileName = "";
        try {
            // Get the original filename
            fileName = file.getOriginalFilename();

            // Define the path to save the file
            Path filePath = Paths.get(getPathUploadFile(), fileName);

            // Save the file locally
            Files.write(filePath, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public String getPathUploadFile(){
        String osName = System.getProperty("os.name").toLowerCase();

        // Check if the OS is Windows or Linux/Unix
        if (osName.contains("win")) {
//            log.info("System OS is {}, FILE_PATH is {}", osName, "/uploads/");
            return System.getProperty("user.dir").replace("\\", "/") + "/uploads";
//            return "/uploads/";
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("mac")) {
//            log.info("System OS is {}, FILE_PATH is {}", osName, ("/home/web/" + clientName));
            return uploadDir;
        } else {
            log.info("Unknown operating system: {}", osName);
            return uploadDir;
        }
    }

    public String getPathResourceFile(){
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return "/uploads/";
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("mac")) {
            return uploadDir;
        } else {
            log.info("Unknown operating system: {}", osName);
            return uploadDir;
        }
    }
}
