package com.nmc.itschool.controller;

import com.nmc.itschool.constant.DBConstant;
import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.*;
import com.nmc.itschool.entity.SubjectCollectionEntity;
import com.nmc.itschool.entity.TestCollectionEntity;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.mapper.SubjectCollectionMapper;
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
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
    @Autowired
    ScoreService scoreService;
    @Autowired
    SubjectCollectionMapper subjectCollectionMapper;

    @GetMapping("/create/{uuid}")
    public String addTestInfo(@PathVariable String uuid, Model model) {
        log.info("start addTestInfo");

        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS
                = subjectCollectionParentService.getAllByType(DBConstant.TYPE_COLLECTION_PARENT_LESSON);
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(subjectCollectionParentDTOS));
//        model.addAttribute("pathFile", FileUtil.getPathResourceFile());
        model.addAttribute("subjectCollectionParentDTOS", subjectCollectionParentDTOS);
        model.addAttribute("testCollectionDTO", testService.getCollectionByUUID(uuid));

        log.info("end addTestInfo");

        return "test/test_create_info";  // Trả về tệp home.html trong thư mục templates
    }

    @GetMapping("/create/info/{slug}")
//    @RequestMapping(value = "/detail/{slug}", method = RequestMethod.GET)
    public String createDetailQuestion(Model model, @PathVariable String slug) throws UnsupportedEncodingException {
        log.info("start createDetailQuestion");

        TestDTO testDTO = testService.findBySlug(slug);
        if(testDTO != null){
//            model.addAttribute("pathFile", FileUtil.getPathResourceFile());
            model.addAttribute("testDTO", testDTO);
        }else {
            throw new AppException(MessageEnum.ERR_LESSON_NOT_FOUND);
        }
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(testDTO));
        log.info("end createDetailQuestion");

        return "test/test_create_detail_question";  // Trả về tệp home.html trong thư mục templates
    }

    @GetMapping("/trac-nghiem")
    public String testPage(Model model) {
        log.info("start testPage");

        // get Data
        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS
                = subjectCollectionParentService.getAllByType(DBConstant.TYPE_COLLECTION_PARENT_LESSON);
        List<TestCollectionDTO> testCollectionDTOS = testService.getCollectionAll(999);

        // add data
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(testCollectionDTOS));
//        model.addAttribute("pathFile", FileUtil.getPathResourceFile());
        model.addAttribute("subjectCollectionParentDTOS", subjectCollectionParentDTOS);
        model.addAttribute("testCollectionDTOS", testCollectionDTOS);

        log.info("end testPage");

        return "test/test_page";  // Trả về tệp home.html trong thư mục templates
    }

    @GetMapping("/my-test")
    public String myTest(Model model) {
        log.info("start myTest");
        List<TestDTO> testDTOS = testService.getAll(99999);
        log.info(ObjectMapperUtil.writeValueAsString(testDTOS));
        model.addAttribute("testDTOS", testDTOS);

        log.info("end myTest");

        return "test/test_list";
    }
    @GetMapping("/delete/{id}")
    public String deleteTest(Model model, @PathVariable Long id) {
        log.info("start deleteTest");
        TestDTO testDTO = testService.findById(id);
        if(testDTO != null){
            testService.deleteById(id);
        }
        log.info("end deleteTest");
        return "redirect:/test/test-list-by-collection/" + testDTO.getTestCollectionUUID();
    }
    @GetMapping("/prepare/{slug}")
    public String prepareTest(Model model, @PathVariable String slug) throws UnsupportedEncodingException {
        log.info("start prepareTest");

        TestCollectionDTO testCollectionDTO = testService.getCollectionBySlug(slug);
        if(testCollectionDTO != null){
//            model.addAttribute("pathFile", FileUtil.getPathResourceFile());
            model.addAttribute("testCollectionDTO", testCollectionDTO);
        }else {
            throw new AppException(MessageEnum.ERR_LESSON_NOT_FOUND);
        }
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(testCollectionDTO));
        log.info("end prepareTest");

        return "test/test_prepare";
    }
    @GetMapping("/{prefix}")
    public String testPageByPrefix(Model model, @PathVariable String prefix) {
        prefix = "/" + prefix;
        log.info("start testPageByPrefix with prefix {}", prefix);
        SubjectCollectionParentDTO subjectCollectionParentDTO = null;
        SubjectCollectionDTO subjectCollectionDTO = null;
        List<TestDTO> testDTOS = new ArrayList<>();
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

        testDTOS = testService.findByPrefix(prefix);
        model.addAttribute("testDTOS", testDTOS);
        log.info("testDTOS: {}", ObjectMapperUtil.writeValueAsString(testDTOS));

        log.info("end testPageByPrefix");

        return "test/test_of_subject_page";
    }
    @GetMapping("/do/{slug}")
    public String doTest(Model model, @PathVariable String slug) throws UnsupportedEncodingException {
        log.info("start doTest");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = "";
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            log.info("userInfo {}", userDetails.getUsername());
            userName = userDetails.getUsername();
        }else {
            model.addAttribute("userInfo", "null");
        }

        TestDTO testDTO = testService.findBySlug(slug);
        UserDoTestDTO userDoTestDTO = userDoTestService.getByTestSlugAndUserName(slug, userName);
        if(testDTO != null){
//            model.addAttribute("pathFile", FileUtil.getPathResourceFile());
            log.info("testDTO {}", testDTO);
            log.info("userDoTestDTO {}", userDoTestDTO);
            model.addAttribute("testDTO", testDTO);
            model.addAttribute("userDoTestDTO", userDoTestDTO);
        }else {
            throw new AppException(MessageEnum.ERR_LESSON_NOT_FOUND);
        }
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(userDoTestDTO));
        log.info("end doTest");

        return "test/test_do";
    }
    @GetMapping("/score/{randomId}")
    public String resultScore(Model model, @PathVariable String randomId) throws UnsupportedEncodingException {
        log.info("start resultScore");

        ScoreDTO scoreDTO = scoreService.findByRandomId(randomId);
        if(scoreDTO != null){
//            model.addAttribute("pathFile", FileUtil.getPathResourceFile());
            model.addAttribute("scoreDTO", scoreDTO);
        }else {
            throw new AppException(MessageEnum.ERR_SCORE_NOT_FOUND);
        }
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(scoreDTO));
        log.info("end resultScore");

        return "test/score_result";
    }

    @PostMapping("/api/save/{uuid}")
    public String saveLesson(
            @RequestParam("pdfFile") MultipartFile pdfFile,
            @RequestParam("testTopicName") String testTopicName,
            @PathVariable String uuid,
            Model model) {

        // Validate file types
        if (!FileUtil.isPdfValid(pdfFile)) {
            model.addAttribute("error", "Invalid image or PDF file");
            return "add";
        }
        FileUtil fileUtil = new FileUtil();
        // Save the files and get their URLs
        String pdfUrl = fileUtil.saveFile(pdfFile);
        TestCollectionDTO testCollectionDTO = testService.getCollectionByUUID(uuid);


        // Create and save the LessonDTO
        TestDTO testDTO = new TestDTO();
        testDTO.setTestCode("");
        testDTO.setTestName(testCollectionDTO.getTestCollectionName());
        testDTO.setTestTopicName(testTopicName);
        testDTO.setTestCollectionUUID(uuid);
        testDTO.setDescription(testCollectionDTO.getDescription());
        testDTO.setSlug(StringUtil.convertToSlug(testCollectionDTO.getSlug())+"-"+ UUID.randomUUID().toString());
        testDTO.setCollectionPrefix(testCollectionDTO.getCollectionPrefix());
        testDTO.setCollectionParentPrefix(testCollectionDTO.getCollectionParentPrefix());
        testDTO.setThumbnailFile(testCollectionDTO.getThumbnailFile());
        testDTO.setPdfFile(pdfUrl);
        testDTO.setAuthor("");
        testDTO.setNumberChooseTest(testCollectionDTO.getNumberChooseTest());
        testDTO.setNumberWriteTest(testCollectionDTO.getNumberWriteTest());
        testDTO.setMinuteTime(testCollectionDTO.getMinuteTime());
        testDTO.setMaxPoint(testCollectionDTO.getMaxPoint());

        TestDTO result = testService.save(testDTO);
        // Here you would save lessonDTO to your database
        model.addAttribute("message", "Lesson saved successfully");

        return "redirect:/test/create/info/" + result.getSlug();
    }
    ///--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    ///--------------------------------------------TEST COLLECTION------------------------------------------------------------------------------------------------------------------------------------
    ///--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/create-test-collection/info")
    public String addTestCollectionInfo(Model model) {
        log.info("start addTestCollectionInfo");

        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS
                = subjectCollectionParentService.getAllByType(DBConstant.TYPE_COLLECTION_PARENT_LESSON);
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(subjectCollectionParentDTOS));
//        model.addAttribute("pathFile", FileUtil.getPathResourceFile());
        model.addAttribute("subjectCollectionParentDTOS", subjectCollectionParentDTOS);

        log.info("end addTestCollectionInfo");

        return "test/test_collection_create_info";  // Trả về tệp home.html trong thư mục templates
    }
    @GetMapping("/my-test-collection")
    public String myTestCollection(Model model) {
        log.info("start myTestCollection");
        List<TestCollectionDTO> testCollectionDTOS = testService.getCollectionAll(99999);
        log.info(ObjectMapperUtil.writeValueAsString(testCollectionDTOS));
        model.addAttribute("testCollectionDTOS", testCollectionDTOS);
        log.info("end myTestCollection");
        return "test/test_collection_list";
    }
    @GetMapping("/test-list-by-collection/{uuid}")
    public String testCollectionList(Model model, @PathVariable String uuid) {
        log.info("start myTest");
        TestCollectionDTO testCollectionDTO = testService.getCollectionByUUID(uuid);
        log.info(ObjectMapperUtil.writeValueAsString(testCollectionDTO));
        model.addAttribute("testCollectionDTO", testCollectionDTO);
        model.addAttribute("testDTOS", testCollectionDTO.getTestEntityList());

        log.info("end myTest");

        return "test/test_list";
    }

    @PostMapping("/api/save-test-collection")
    public String saveTestCollection(
            @RequestParam("testCollectionName") String testCollectionName,
            @RequestParam("description") String description,
            @RequestParam("collectionParentPrefix") String collectionParentPrefix,
            @RequestParam("collectionPrefix") String collectionPrefix,
            @RequestParam("thumbnailFile") MultipartFile thumbnailFile,
            @RequestParam("numberChooseTest") String numberChooseTest,
            @RequestParam("numberWriteTest") String numberWriteTest,
            @RequestParam("minuteTime") String minuteTime,
            @RequestParam("maxPoint") String maxPoint,
            Model model) {

        // Validate file types
        if (!FileUtil.isImageValid(thumbnailFile)) {
            model.addAttribute("error", "Invalid image file");
            return "add";
        }
        FileUtil fileUtil = new FileUtil();
        // Save the files and get their URLs
        String imageUrl = fileUtil.saveFile(thumbnailFile);

        // Create and save the LessonDTO
        TestCollectionDTO testCollectionDTO = new TestCollectionDTO();
        testCollectionDTO.setTestCollectionUUID(UUID.randomUUID().toString());
        testCollectionDTO.setTestCollectionName(testCollectionName);
        testCollectionDTO.setDescription(description);
        testCollectionDTO.setSlug(StringUtil.convertToSlug(testCollectionName)+"-"+ UUID.randomUUID().toString());
        testCollectionDTO.setCollectionPrefix(collectionPrefix);
        testCollectionDTO.setCollectionParentPrefix(collectionParentPrefix);
        testCollectionDTO.setThumbnailFile(imageUrl);
        testCollectionDTO.setNumberChooseTest(Integer.valueOf(numberChooseTest));
        testCollectionDTO.setNumberWriteTest(Integer.valueOf(numberWriteTest));
        testCollectionDTO.setMinuteTime(Integer.valueOf(minuteTime));
        testCollectionDTO.setMaxPoint(Integer.valueOf(maxPoint));

        TestCollectionDTO result = testService.saveCollection(testCollectionDTO);
        // Here you would save lessonDTO to your database
        model.addAttribute("message", "Lesson saved successfully");

        return "redirect:/test/test-list-by-collection/"+result.getTestCollectionUUID();
    }

    @GetMapping("/delete-collection/{id}")
    public String deleteTestCollection(Model model, @PathVariable Long id) {
        log.info("start deleteTestCollection");
        testService.deleteCollectionById(id);
        log.info("end deleteTestCollection");
        return "redirect:/test/my-test-collection";
    }
}