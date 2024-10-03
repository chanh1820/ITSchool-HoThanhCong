package com.nmc.itschool.controller;

import com.nmc.itschool.dto.LessonCollectionParentDTO;
import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.service.LessonCollectionParentService;
import com.nmc.itschool.service.LessonService;
import com.nmc.itschool.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/lesson")
public class LessonController {
    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    @Autowired
    LessonCollectionParentService lessonCollectionParentService;
    @Autowired
    LessonService lessonService;
    @GetMapping("/add")
    public String saveLessonPage(Model model) {
        log.info("start saveLessonPage");

        List<LessonCollectionParentDTO> lessonCollectionParentDTOS = lessonCollectionParentService.getAll();
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(lessonCollectionParentDTOS));
        model.addAttribute("lessonCollectionParentDTOS", lessonCollectionParentDTOS);

        log.info("end saveLessonPage");

        return "lesson_save";  // Trả về tệp home.html trong thư mục templates
    }
    @PostMapping("/api/save")
    public String saveLesson(
            @RequestParam("lessonCode") String lessonCode,
            @RequestParam("lessonName") String lessonName,
            @RequestParam("description") String description,
            @RequestParam("collectionPrefix") String collectionPrefix,
            @RequestParam("collectionParentPrefix") String collectionParentPrefix,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("pdfFile") MultipartFile pdfFile,
            Model model) {

        // Validate file types
        if (!isImageValid(imageFile) || !isPdfValid(pdfFile)) {
            model.addAttribute("error", "Invalid image or PDF file");
            return "add";
        }

        // Save the files and get their URLs
        String imageUrl = saveFile(imageFile);
        String pdfUrl = saveFile(pdfFile);

        // Create and save the LessonDTO
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setLessonCode(lessonCode);
        lessonDTO.setLessonName(lessonName);
        lessonDTO.setDescription(description);
        lessonDTO.setCollectionPrefix(collectionPrefix);
        lessonDTO.setCollectionParentPrefix(collectionParentPrefix);
        lessonDTO.setImageUrl(imageUrl);
        lessonDTO.setPdfUrl(pdfUrl);

        lessonService.save(lessonDTO);
        // Here you would save lessonDTO to your database
        model.addAttribute("message", "Lesson saved successfully");

        return "redirect:/lesson/adda";
    }

    private boolean isImageValid(MultipartFile imageFile) {
        String contentType = imageFile.getContentType();
        return contentType != null && (contentType.equals("image/png") || contentType.equals("image/jpeg"));
    }

    private boolean isPdfValid(MultipartFile pdfFile) {
        String contentType = pdfFile.getContentType();
        return contentType != null && contentType.equals("application/pdf");
    }

    private String saveFile(MultipartFile file) {
        String fileName = "";
        try {
            // Get the original filename
            fileName = file.getOriginalFilename();

            // Define the path to save the file
            Path filePath = Paths.get(UPLOAD_DIR, fileName);

            // Save the file locally
            Files.write(filePath, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }
}