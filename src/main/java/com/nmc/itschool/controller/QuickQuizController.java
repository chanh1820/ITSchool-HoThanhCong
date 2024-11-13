package com.nmc.itschool.controller;

import com.nmc.itschool.dto.*;
import com.nmc.itschool.service.NoteService;
import com.nmc.itschool.service.QuickQuizService;
import com.nmc.itschool.util.FileUtil;
import com.nmc.itschool.util.ObjectMapperUtil;
import com.nmc.itschool.util.StringUtil;
import com.sun.xml.bind.v2.model.annotation.Quick;
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
@RequestMapping("/quick-quiz")
public class QuickQuizController {
//    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    @Autowired
    QuickQuizService quickQuizService;

    @GetMapping("/edit/{randomId}")
    public String saveQuickView(Model model, @PathVariable String randomId) {
        log.info("start saveQuickView");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        QuickQuizDTO quickQuizDTO = quickQuizService.findByRandomId(randomId);
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            log.info(userDetails.getUsername());
            log.info(ObjectMapperUtil.writeValueAsString(quickQuizDTO));
            model.addAttribute("quickQuizDTO", quickQuizDTO);
            return "quick_quiz/quick_quiz_save";
        }else {
            return "user/user_login";
        }
    }
    @GetMapping("/{randomId}")
    public String doQuickQuiz(Model model, @PathVariable String randomId) {
        log.info("start doQuickQuiz");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        QuickQuizDTO quickQuizDTO = quickQuizService.findByRandomId(randomId);

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            log.info(userDetails.getUsername());
            log.info(ObjectMapperUtil.writeValueAsString(quickQuizDTO));
            model.addAttribute("quickQuizDTO", quickQuizDTO);
            return "quick_quiz/quick_quiz_do";
        }else {
            return "user/user_login";
        }
    }
    @GetMapping("/log/{randomId}")
    public String logQuickView(Model model, @PathVariable String randomId) {
        log.info("start logQuickView");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<QuickQuizLogDTO> quickQuizLogDTOS = quickQuizService.findQuickQuizLogs(randomId);
        QuickQuizRankDTO quickQuizRankDTO = quickQuizService.calculatorRank(quickQuizLogDTOS);
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            log.info(userDetails.getUsername());
            log.info(ObjectMapperUtil.writeValueAsString(quickQuizLogDTOS));
            log.info(ObjectMapperUtil.writeValueAsString(quickQuizRankDTO));
            model.addAttribute("quickQuizLogDTOS", quickQuizLogDTOS);
            model.addAttribute("quickQuizRankDTO", quickQuizRankDTO);
            return "quick_quiz/quick_quiz_log";
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

        return "quick_quiz/quick_quiz_list";
    }

    @GetMapping("/thanks-you")
    public String thanksYou(Model model) {
        log.info("Go thanksYou");
        return "quick_quiz/quick_quiz_thanks_you";
    }
    @PostMapping("/api/create/{randomId}")
    public String saveQuickQuiz(
            @RequestParam("title") String title,
            @RequestParam("question") String question,
            @RequestParam("imageContentFile") MultipartFile imageContentFile,
            @RequestParam("answerA") String answerA,
            @RequestParam("answerB") String answerB,
            @RequestParam("answerC") String answerC,
            @RequestParam("answerD") String answerD,
            @RequestParam("result") String result,
            @PathVariable String randomId,
            Model model) {

        // Validate file types
        if (!FileUtil.isImageValid(imageContentFile)) {
            model.addAttribute("error", "Invalid image or PDF file");
            return "add";
        }
        FileUtil fileUtil = new FileUtil();
        // Save the files and get their URLs
        String imageUrl = fileUtil.saveFile(imageContentFile);

        QuickQuizDTO quickQuizDTO = quickQuizService.findByRandomId(randomId);
        quickQuizDTO.setTitle(title);
        quickQuizDTO.setQuestion(question);
        quickQuizDTO.setImageContentFile(imageUrl);
        quickQuizDTO.setAnswerA(answerA);
        quickQuizDTO.setAnswerB(answerB);
        quickQuizDTO.setAnswerC(answerC);
        quickQuizDTO.setAnswerD(answerD);
        quickQuizDTO.setResult(result);

        QuickQuizDTO resultDTO = quickQuizService.save(quickQuizDTO);
        // Here you would save lessonDTO to your database
        model.addAttribute("message", "Quick quiz saved successfully");

        return "redirect:/quick-quiz/my-quick-quiz";
    }
}