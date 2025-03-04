package com.nmc.itschool.controller;

import com.nmc.itschool.constant.DBConstant;
import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.SubjectCollectionDTO;
import com.nmc.itschool.dto.SubjectCollectionParentDTO;
import com.nmc.itschool.entity.SubjectCollectionEntity;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.mapper.SubjectCollectionMapper;
import com.nmc.itschool.service.LessonPDFService;
import com.nmc.itschool.service.LessonService;
import com.nmc.itschool.service.SubjectCollectionParentService;
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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/lesson-pdf")
public class LessonPDFController {
//    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    @Autowired
    SubjectCollectionParentService subjectCollectionParentService;
    @Autowired
    LessonPDFService lessonPDFService;
    @Autowired
    SubjectCollectionMapper subjectCollectionMapper;
    @GetMapping("/bai-hoc")
    public String lessonPage(Model model) {
        log.info("start lessonPage");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // get Data
        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS
                = subjectCollectionParentService.getAllByType(DBConstant.TYPE_COLLECTION_PARENT_LESSON);
        List<LessonDTO> lessonDTOS = lessonPDFService.getAll(99999);

        // add data
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(subjectCollectionParentDTOS));
        log.info("lessonDTOS: {}", ObjectMapperUtil.writeValueAsString(lessonDTOS));
//        model.addAttribute("pathFile", FileUtil.getPathResourceFile());
        model.addAttribute("subjectCollectionParentDTOS", subjectCollectionParentDTOS);
        model.addAttribute("lessonDTOS", lessonDTOS);

        log.info("end lessonPage");

        return "lesson_pdf/lesson_page";
    }
    @GetMapping("/my-lesson")
    public String myLesson(Model model) {
        log.info("start myLesson");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        List<LessonDTO> lessonDTOS = lessonPDFService.getAll(99999);
        log.info(ObjectMapperUtil.writeValueAsString(lessonDTOS));
        model.addAttribute("lessonDTOS", lessonDTOS);

        log.info("end myLesson");

        return "lesson_pdf/lesson_list";
    }

    @GetMapping("/delete/{id}")
    public String deleteLesson(Model model, @PathVariable Long id) {
        log.info("start deleteLesson");
        lessonPDFService.deleteById(id);
        log.info("end deleteLesson");

        return "redirect:/lesson-pdf/my-lesson";
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
                = subjectCollectionParentService.getAllByType(DBConstant.TYPE_COLLECTION_PARENT_LESSON);

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

        lessonDTOS = lessonPDFService.findByPrefix(prefix);
        model.addAttribute("lessonDTOS", lessonDTOS);
        log.info("lessonDTOS: {}", ObjectMapperUtil.writeValueAsString(lessonDTOS));

        log.info("end lessonPageByPrefix");

        return "lesson_pdf/lesson_of_subject_page";
    }
    @GetMapping("/create")
    public String saveLessonPage(Model model) {
        log.info("start saveLessonPage");

        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS
                = subjectCollectionParentService.getAllByType(DBConstant.TYPE_COLLECTION_PARENT_LESSON);
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(subjectCollectionParentDTOS));
//        model.addAttribute("pathFile", FileUtil.getPathResourceFile());
        model.addAttribute("subjectCollectionParentDTOS", subjectCollectionParentDTOS);

        log.info("end saveLessonPage");

        return "lesson_pdf/lesson_save";
    }

    @GetMapping("/detail/{slug}")
//    @RequestMapping(value = "/detail/{slug}", method = RequestMethod.GET)
    public String detailLesson(Model model, @PathVariable String slug) throws UnsupportedEncodingException {
        log.info("start detailLesson");

        LessonDTO lessonDTO = lessonPDFService.findBySlug(slug);
        if(lessonDTO != null){
//            model.addAttribute("pathFile", FileUtil.getPathResourceFile());
            model.addAttribute("lessonDTO", lessonDTO);
        }else {
            throw new AppException(MessageEnum.ERR_LESSON_NOT_FOUND);
        }
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(lessonDTO));
        log.info("end detailLesson");

        return "lesson_pdf/lesson_detail";  // Trả về tệp home.html trong thư mục templates
    }

    @PostMapping("/api/create")
    public String saveLesson(
            @RequestParam("lessonName") String lessonName,
            @RequestParam("description") String description,
            @RequestParam("collectionPrefix") String collectionPrefix,
            @RequestParam("collectionParentPrefix") String collectionParentPrefix,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("pdfFile") MultipartFile pdfFile,
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

        lessonPDFService.save(lessonDTO);
        // Here you would save lessonDTO to your database
        model.addAttribute("message", "Lesson saved successfully");

        return "redirect:/home/index";
    }
}