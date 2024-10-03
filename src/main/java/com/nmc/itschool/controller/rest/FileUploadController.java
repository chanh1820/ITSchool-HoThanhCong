package com.nmc.itschool.controller.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {
    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    @PostMapping("/upload")
    public String handleImageUpload(@RequestParam("imageFile") MultipartFile file,
                                    RedirectAttributes redirectAttributes) {
        try {
            // Get the original filename
            String fileName = file.getOriginalFilename();

            // Define the path to save the file
            Path filePath = Paths.get(UPLOAD_DIR, fileName);

            // Save the file locally
            Files.write(filePath, file.getBytes());

            redirectAttributes.addFlashAttribute("message", "Image uploaded successfully: " + fileName);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Image upload failed!");
            e.printStackTrace();
        }

        return "redirect:/";
    }
}
