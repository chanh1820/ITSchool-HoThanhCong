package com.nmc.itschool.controller;

import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.SubjectCollectionParentDTO;
import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.service.SubjectCollectionParentService;
import com.nmc.itschool.service.LessonService;
import com.nmc.itschool.util.FileUtil;
import com.nmc.itschool.util.ObjectMapperUtil;
import com.nmc.itschool.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/lesson")
public class LessonController {
//    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    @Autowired
    SubjectCollectionParentService subjectCollectionParentService;
    @Autowired
    LessonService lessonService;
    @GetMapping("/create")
    public String saveLessonPage(Model model) {
        log.info("start saveLessonPage");

        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS = subjectCollectionParentService.getAll();
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(subjectCollectionParentDTOS));
//        model.addAttribute("pathFile", FileUtil.getPathResourceFile());
        model.addAttribute("subjectCollectionParentDTOS", subjectCollectionParentDTOS);

        log.info("end saveLessonPage");

        return "lesson/lesson_save";
    }

    @GetMapping("/detail/{slug}")
//    @RequestMapping(value = "/detail/{slug}", method = RequestMethod.GET)
    public String detailLesson(Model model, @PathVariable String slug) throws UnsupportedEncodingException {
        log.info("start detailLesson");

        LessonDTO lessonDTO = lessonService.findBySlug(slug);
        if(lessonDTO != null){
//            model.addAttribute("pathFile", FileUtil.getPathResourceFile());
            model.addAttribute("lessonDTO", lessonDTO);
        }else {
            throw new AppException(MessageEnum.ERR_LESSON_NOT_FOUND);
        }
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(lessonDTO));
        log.info("end detailLesson");

        return "lesson/lesson_detail";  // Trả về tệp home.html trong thư mục templates
    }

    @PostMapping("/api/create")
    public String saveLesson(
            @RequestParam("lessonName") String lessonName,
            @RequestParam("description") String description,
            @RequestParam("collectionPrefix") String collectionPrefix,
            @RequestParam("collectionParentPrefix") String collectionParentPrefix,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("pdfFile") MultipartFile pdfFile,
            @RequestParam("videoId") String videoId,
            Model model) {

//        // Validate file types
//        if (!FileUtil.isImageValid(imageFile) || !FileUtil.isPdfValid(pdfFile)) {
//            model.addAttribute("error", "Invalid image or PDF file");
//            return "add";
//        }
        FileUtil fileUtil = new FileUtil();
        // Save the files and get their URLs
        String imageUrl = fileUtil.saveFile(imageFile);
        String pdfUrl = fileUtil.saveFile(pdfFile);

        // Create and save the LessonDTO
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setLessonCode(null);
        lessonDTO.setLessonName(lessonName);
        lessonDTO.setDescription(description);
        lessonDTO.setSlug(StringUtil.convertToSlug(lessonName)+"-"+ UUID.randomUUID().toString());
        lessonDTO.setCollectionPrefix(collectionPrefix);
        lessonDTO.setCollectionParentPrefix(collectionParentPrefix);
        lessonDTO.setImageUrl(imageUrl);
        if (!pdfUrl.isBlank()){
            lessonDTO.setPdfUrl(pdfUrl);
        }
        if (!videoId.isBlank()) {
            lessonDTO.setVideoId(videoId);
        }

        lessonService.save(lessonDTO);
        // Here you would save lessonDTO to your database
        model.addAttribute("message", "Lesson saved successfully");

        return "redirect:/home/index";
    }
}