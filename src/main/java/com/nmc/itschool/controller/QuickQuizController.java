package com.nmc.itschool.controller;

import com.nmc.itschool.dto.NoteDTO;
import com.nmc.itschool.dto.QuickQuizDTO;
import com.nmc.itschool.dto.TestDTO;
import com.nmc.itschool.service.NoteService;
import com.nmc.itschool.service.QuickQuizService;
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

import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/quick-view")
public class QuickQuizController {
//    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    @Autowired
    QuickQuizService quickQuizService;

    @GetMapping("/{randomId}")
    public String saveQuickView(Model model, @PathVariable String randomId) {
        log.info("start saveQuickView");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        QuickQuizDTO quickQuizDTO = quickQuizService.findByRandomId(randomId);
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            log.info(userDetails.getUsername());
            log.info(ObjectMapperUtil.writeValueAsString(quickQuizDTO));
            model.addAttribute("quickQuizDTO", quickQuizDTO);
            return "note/quick_quiz_save";
        }else {
            return "user/user_login";
        }
    }
    @GetMapping("/my-quick-quiz")
    public String myQuickQuiz(Model model) {
        log.info("start myQuickQuiz");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        List<QuickQuizDTO> quickQuizDTOS = quickQuizService.getByUserName(userDetails.getUsername());
        log.info(ObjectMapperUtil.writeValueAsString(quickQuizDTOS));
        model.addAttribute("quickQuizDTOS", quickQuizDTOS);

        log.info("end myQuickQuiz");

        return "note/quick_quiz_list";
    }

    @PostMapping("/api/save")
    public String saveLesson(
            @RequestParam("title") String title,
            @RequestParam("question") String question,
            @RequestParam("description") String description,
            @RequestParam("collectionParentPrefix") String collectionParentPrefix,
            @RequestParam("collectionPrefix") String collectionPrefix,
            @RequestParam("thumbnailFile") MultipartFile thumbnailFile,
            @RequestParam("pdfFile") MultipartFile pdfFile,
            @RequestParam("numberChooseTest") String numberChooseTest,
            @RequestParam("numberWriteTest") String numberWriteTest,
            Model model) {

        // Validate file types
        if (!FileUtil.isImageValid(thumbnailFile) || !FileUtil.isPdfValid(pdfFile)) {
            model.addAttribute("error", "Invalid image or PDF file");
            return "add";
        }
        FileUtil fileUtil = new FileUtil();
        // Save the files and get their URLs
        String imageUrl = fileUtil.saveFile(thumbnailFile);
        String pdfUrl = fileUtil.saveFile(pdfFile);

        // Create and save the LessonDTO
        TestDTO testDTO = new TestDTO();
        testDTO.setTestCode(testCode);
        testDTO.setTestName(testName);
        testDTO.setDescription(description);
        testDTO.setSlug(StringUtil.convertToSlug(testName)+"-"+ UUID.randomUUID().toString());
        testDTO.setCollectionPrefix(collectionPrefix);
        testDTO.setCollectionParentPrefix(collectionParentPrefix);
        testDTO.setThumbnailFile(imageUrl);
        testDTO.setPdfFile(pdfUrl);
        testDTO.setAuthor("");
        testDTO.setNumberChooseTest(Integer.valueOf(numberChooseTest));
        testDTO.setNumberWriteTest(Integer.valueOf(numberWriteTest));

        TestDTO result = testService.save(testDTO);
        // Here you would save lessonDTO to your database
        model.addAttribute("message", "Lesson saved successfully");

        return "redirect:/test/create/info/"+result.getSlug();
    }
}