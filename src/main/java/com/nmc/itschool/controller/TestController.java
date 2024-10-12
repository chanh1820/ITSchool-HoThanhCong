package com.nmc.itschool.controller;

import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.SubjectCollectionParentDTO;
import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.TestDTO;
import com.nmc.itschool.dto.UserDoTestDTO;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.security.CustomUserDetails;
import com.nmc.itschool.service.*;
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
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/test")
public class TestController {
//    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    @Autowired
    SubjectCollectionParentService subjectCollectionParentService;
    @Autowired
    SubjectCollectionService subjectCollectionService;
    @Autowired
    TestService testService;
    @Autowired
    UserDoTestService userDoTestService;

    @GetMapping("/create/info")
    public String addTestInfo(Model model) {
        log.info("start addTestInfo");

        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS = subjectCollectionParentService.getAll();
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(subjectCollectionParentDTOS));
//        model.addAttribute("pathFile", FileUtil.getPathResourceFile());
        model.addAttribute("subjectCollectionParentDTOS", subjectCollectionParentDTOS);

        log.info("end addTestInfo");

        return "test/test_create_info";  // Trả về tệp home.html trong thư mục templates
    }

    @GetMapping("/create/info/{slug}")
//    @RequestMapping(value = "/detail/{slug}", method = RequestMethod.GET)
    public String createDetailQuestion(Model model, @PathVariable String slug) throws UnsupportedEncodingException {
        log.info("start createDetailQuestion");

        TestDTO testDTO = testService.findBySlug(slug);
        if(testDTO != null){
            model.addAttribute("pathFile", FileUtil.getPathResourceFile());
            model.addAttribute("testDTO", testDTO);
        }else {
            throw new AppException(MessageEnum.ERR_LESSON_NOT_FOUND);
        }
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(testDTO));
        log.info("end createDetailQuestion");

        return "test/test_create_detail_question";  // Trả về tệp home.html trong thư mục templates
    }

    @GetMapping("/prepare/{slug}")
    public String prepareTest(Model model, @PathVariable String slug) throws UnsupportedEncodingException {
        log.info("start prepareTest");

        TestDTO testDTO = testService.findBySlug(slug);
        if(testDTO != null){
            model.addAttribute("pathFile", FileUtil.getPathResourceFile());
            model.addAttribute("testDTO", testDTO);
        }else {
            throw new AppException(MessageEnum.ERR_LESSON_NOT_FOUND);
        }
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(testDTO));
        log.info("end prepareTest");

        return "test/test_prepare";
    }

    @GetMapping("/do/{slug}")
    public String doTest(Model model, @PathVariable String slug) throws UnsupportedEncodingException {
        log.info("start doTest");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = "";
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            log.info("userInfo {}", userDetails.getFullName());
            userName = userDetails.getFullName();
        }else {
            model.addAttribute("userInfo", "null");
        }

        TestDTO testDTO = testService.findBySlug(slug);
        UserDoTestDTO userDoTestDTO = userDoTestService.findByUserNameAndSlug(userName, slug);
        if(testDTO != null){
            model.addAttribute("pathFile", FileUtil.getPathResourceFile());
            model.addAttribute("testDTO", testDTO);
            model.addAttribute("userDoTestDTO", userDoTestDTO);
        }else {
            throw new AppException(MessageEnum.ERR_LESSON_NOT_FOUND);
        }
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(testDTO));
        log.info("end doTest");

        return "test/test_do";
    }

    @PostMapping("/api/save")
    public String saveLesson(
            @RequestParam("testCode") String testCode,
            @RequestParam("testName") String testName,
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

        // Save the files and get their URLs
        String imageUrl = FileUtil.saveFile(thumbnailFile);
        String pdfUrl = FileUtil.saveFile(pdfFile);

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