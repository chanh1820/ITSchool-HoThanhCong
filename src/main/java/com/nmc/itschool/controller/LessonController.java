package com.nmc.itschool.controller;

import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.*;
import com.nmc.itschool.entity.SubjectCollectionEntity;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.mapper.SubjectCollectionMapper;
import com.nmc.itschool.security.CustomUserDetails;
import com.nmc.itschool.service.SubjectCollectionParentService;
import com.nmc.itschool.service.LessonService;
import com.nmc.itschool.util.FileUtil;
import com.nmc.itschool.util.ObjectMapperUtil;
import com.nmc.itschool.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import java.util.ArrayList;
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
    @Autowired
    SubjectCollectionMapper subjectCollectionMapper;
    @GetMapping("/bai-hoc")
    public String lessonPage(Model model) {
        log.info("start lessonPage");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // get Data
        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS = subjectCollectionParentService.getAll();
        List<LessonDTO> lessonDTOS = lessonService.getAll(99999);

        // add data
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(subjectCollectionParentDTOS));
        log.info("lessonDTOS: {}", ObjectMapperUtil.writeValueAsString(lessonDTOS));
//        model.addAttribute("pathFile", FileUtil.getPathResourceFile());
        model.addAttribute("subjectCollectionParentDTOS", subjectCollectionParentDTOS);
        model.addAttribute("lessonDTOS", lessonDTOS);

        log.info("end lessonPage");

        return "lesson/lesson_page";
    }
    @GetMapping("/my-lesson")
    public String myLesson(Model model) {
        log.info("start myLesson");
        List<LessonDTO> lessonDTOS = lessonService.getAll(99999);
        log.info(ObjectMapperUtil.writeValueAsString(lessonDTOS));
        model.addAttribute("lessonDTOS", lessonDTOS);

        log.info("end myLesson");

        return "lesson/lesson_list";
    }

    @GetMapping("/delete/{id}")
    public String deleteLesson(Model model, @PathVariable Long id) {
        log.info("start deleteLesson");
        lessonService.deleteById(id);
        log.info("end deleteLesson");

        return "redirect:/lesson/my-lesson";
    }
    @GetMapping("/{prefix}")
    public String lessonPageByPrefix(Model model, @PathVariable String prefix) {
        prefix = "/" + prefix;
        log.info("start lessonPageByPrefix with prefix {}", prefix);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SubjectCollectionParentDTO subjectCollectionParentDTO = null;
        SubjectCollectionDTO subjectCollectionDTO = null;
        List<LessonDTO> lessonDTOS = new ArrayList<>();
        // get Data
        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS
                = subjectCollectionParentService.getAll();

        for (SubjectCollectionParentDTO itemParent : subjectCollectionParentDTOS){
            if(itemParent.getPrefix().equals(prefix)){
                subjectCollectionParentDTO = itemParent;
                break;
            }
            for(SubjectCollectionEntity childItem: itemParent.getSubjectCollectionEntities()){
                if(childItem.getPrefix().equals(prefix)){
                    subjectCollectionDTO = subjectCollectionMapper.toDTO(childItem);
                    break;
                }
            }

        }

        // add data
//        model.addAttribute("subjectCollectionParentDTOS", subjectCollectionParentDTOS);
        if(subjectCollectionParentDTO != null){
            log.info("1");
            model.addAttribute("subjectCollectionParentDTO", subjectCollectionParentDTO);
        }else if(subjectCollectionDTO != null){
            log.info("2");
            model.addAttribute("subjectCollectionDTO", subjectCollectionDTO);
        }else {
            log.info("3");
            return null;
        }
        log.info("prefix: {}", prefix);

        lessonDTOS = lessonService.findByPrefix(prefix);
        model.addAttribute("lessonDTOS", lessonDTOS);
        log.info("lessonDTOS: {}", ObjectMapperUtil.writeValueAsString(lessonDTOS));

        log.info("end lessonPageByPrefix");

        return "lesson/lesson_of_subject_page";
    }

    @GetMapping("/other/{prefix}")
    public String lessonOtherPageByPrefix(Model model, @PathVariable String prefix) {
        prefix = "/" + prefix;
        log.info("start lessonPageByPrefix with prefix {}", prefix);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SubjectCollectionParentDTO subjectCollectionParentDTO = null;
        SubjectCollectionDTO subjectCollectionDTO = null;
        List<LessonDTO> lessonDTOS = new ArrayList<>();
        // get Data
        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS
                = subjectCollectionParentService.getAll();

        for (SubjectCollectionParentDTO itemParent : subjectCollectionParentDTOS){
            if(itemParent.getPrefix().equals(prefix)){
                subjectCollectionParentDTO = itemParent;
                break;
            }
            for(SubjectCollectionEntity childItem: itemParent.getSubjectCollectionEntities()){
                if(childItem.getPrefix().equals(prefix)){
                    subjectCollectionDTO = subjectCollectionMapper.toDTO(childItem);
                    break;
                }
            }

        }

        // add data
//        model.addAttribute("subjectCollectionParentDTOS", subjectCollectionParentDTOS);
        if(subjectCollectionParentDTO != null){
            log.info("1");
            model.addAttribute("subjectCollectionParentDTO", subjectCollectionParentDTO);
        }else if(subjectCollectionDTO != null){
            log.info("2");
            model.addAttribute("subjectCollectionDTO", subjectCollectionDTO);
        }else {
            log.info("3");
            return null;
        }
        log.info("prefix: {}", prefix);

        lessonDTOS = lessonService.findByPrefix(prefix);
        model.addAttribute("lessonDTOS", lessonDTOS);
        log.info("lessonDTOS: {}", ObjectMapperUtil.writeValueAsString(lessonDTOS));

        log.info("end lessonPageByPrefix");

        return "lesson/lesson_other";
    }
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