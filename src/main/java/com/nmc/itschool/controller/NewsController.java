package com.nmc.itschool.controller;

import com.nmc.itschool.constant.DBConstant;
import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.NewsDTO;
import com.nmc.itschool.dto.NewsDTO;
import com.nmc.itschool.dto.SubjectCollectionDTO;
import com.nmc.itschool.dto.SubjectCollectionParentDTO;
import com.nmc.itschool.entity.SubjectCollectionEntity;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.mapper.SubjectCollectionMapper;
import com.nmc.itschool.service.NewsService;
import com.nmc.itschool.service.NewsService;
import com.nmc.itschool.service.SubjectCollectionParentService;
import com.nmc.itschool.util.FileUtil;
import com.nmc.itschool.util.ObjectMapperUtil;
import com.nmc.itschool.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/news")
public class NewsController {
//    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    @Autowired
    SubjectCollectionParentService subjectCollectionParentService;
    @Autowired
    NewsService newsService;
    @Autowired
    SubjectCollectionMapper subjectCollectionMapper;
    @GetMapping("/bai-hoc")
    public String newsPage(Model model) {
        log.info("start newsPage");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // get Data
        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS
                = subjectCollectionParentService.getAllByType(DBConstant.TYPE_COLLECTION_PARENT_NEWS);
        List<NewsDTO> newsDTOS = newsService.getAll(99999);

        // add data
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(subjectCollectionParentDTOS));
        log.info("newsDTOS: {}", ObjectMapperUtil.writeValueAsString(newsDTOS));
//        model.addAttribute("pathFile", FileUtil.getPathResourceFile());
        model.addAttribute("subjectCollectionParentDTOS", subjectCollectionParentDTOS);
        model.addAttribute("newsDTOS", newsDTOS);

        log.info("end newsPage");

        return "news/news_page";
    }
    @GetMapping("/my-news")
    public String myNews(Model model) {
        log.info("start myNews");
        List<NewsDTO> newsDTOS = newsService.getAll(99999);
        log.info(ObjectMapperUtil.writeValueAsString(newsDTOS));
        model.addAttribute("newsDTOS", newsDTOS);

        log.info("end myNews");

        return "news/news_list";
    }

    @GetMapping("/delete/{id}")
    public String deleteNews(Model model, @PathVariable Long id) {
        log.info("start deleteNews");
        newsService.deleteById(id);
        log.info("end deleteNews");

        return "redirect:/news/my-news";
    }
    @GetMapping("/{prefix}")
    public String newsPageByPrefix(Model model, @PathVariable String prefix) {
        prefix = "/" + prefix;
        log.info("start newsPageByPrefix with prefix {}", prefix);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SubjectCollectionParentDTO subjectCollectionParentDTO = null;
        SubjectCollectionDTO subjectCollectionDTO = null;
        List<NewsDTO> newsDTOS = new ArrayList<>();
        // get Data
        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS
                = subjectCollectionParentService.getAllByType(DBConstant.TYPE_COLLECTION_PARENT_NEWS);

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

        newsDTOS = newsService.findByPrefix(prefix);
        model.addAttribute("newsDTOS", newsDTOS);
        log.info("newsDTOS: {}", ObjectMapperUtil.writeValueAsString(newsDTOS));

        log.info("end newsPageByPrefix");

        return "news/news_of_subject_page";
    }

    @GetMapping("/other/{prefix}")
    public String newsOtherPageByPrefix(Model model, @PathVariable String prefix) {
        prefix = "/" + prefix;
        log.info("start newsPageByPrefix with prefix {}", prefix);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SubjectCollectionParentDTO subjectCollectionParentDTO = null;
        SubjectCollectionDTO subjectCollectionDTO = null;
        List<NewsDTO> newsDTOS = new ArrayList<>();
        // get Data
        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS
                = subjectCollectionParentService.getAllByType(DBConstant.TYPE_COLLECTION_PARENT_NEWS);

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

        newsDTOS = newsService.findByPrefix(prefix);
        model.addAttribute("newsDTOS", newsDTOS);
        log.info("newsDTOS: {}", ObjectMapperUtil.writeValueAsString(newsDTOS));

        log.info("end newsPageByPrefix");

        return "news/news_other";
    }
    @GetMapping("/create")
    public String saveNewsPage(Model model) {
        log.info("start saveNewsPage");

        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS
                = subjectCollectionParentService.getAllByType(DBConstant.TYPE_COLLECTION_PARENT_NEWS);
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(subjectCollectionParentDTOS));
//        model.addAttribute("pathFile", FileUtil.getPathResourceFile());
        model.addAttribute("subjectCollectionParentDTOS", subjectCollectionParentDTOS);

        log.info("end saveNewsPage");

        return "news/news_save";
    }

    @GetMapping("/detail/{slug}")
//    @RequestMapping(value = "/detail/{slug}", method = RequestMethod.GET)
    public String detailNews(Model model, @PathVariable String slug) throws UnsupportedEncodingException {
        log.info("start detailNews");

        NewsDTO newsDTO = newsService.findBySlug(slug);
        if(newsDTO != null){
//            model.addAttribute("pathFile", FileUtil.getPathResourceFile());
            model.addAttribute("newsDTO", newsDTO);
        }else {
            throw new AppException(MessageEnum.ERR_LESSON_NOT_FOUND);
        }
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(newsDTO));
        log.info("end detailNews");

        return "news/news_detail";  // Trả về tệp home.html trong thư mục templates
    }

    @PostMapping("/api/create")
    public String saveNews(
            @RequestParam("newsName") String newsName,
            @RequestParam("description") String description,
            @RequestParam("collectionPrefix") String collectionPrefix,
            @RequestParam("collectionParentPrefix") String collectionParentPrefix,
//            @RequestParam("imageFile") MultipartFile imageFile,
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
//        String imageUrl = fileUtil.saveFile(imageFile);
        String pdfUrl = fileUtil.saveFile(pdfFile);

        // Create and save the NewsDTO
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setNewsCode(null);
        newsDTO.setNewsName(newsName);
        newsDTO.setDescription(description);
        newsDTO.setSlug(StringUtil.convertToSlug(newsName)+"-"+ UUID.randomUUID().toString());
        newsDTO.setCollectionPrefix(collectionPrefix);
        newsDTO.setCollectionParentPrefix(collectionParentPrefix);
        newsDTO.setImageUrl(null);
        if (!pdfUrl.isBlank()){
            newsDTO.setPdfUrl(pdfUrl);
        }
        if (!videoId.isBlank()) {
            newsDTO.setVideoId(videoId);
        }

        newsService.save(newsDTO);
        // Here you would save newsDTO to your database
        model.addAttribute("message", "News saved successfully");

        return "redirect:/home/index";
    }
}