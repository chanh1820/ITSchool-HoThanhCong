package com.nmc.itschool.controller;

import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.NoteDTO;
import com.nmc.itschool.dto.SubjectCollectionParentDTO;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.service.LessonService;
import com.nmc.itschool.service.NoteService;
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
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/note")
public class NoteController {
//    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    @Autowired
    NoteService noteService;

    @GetMapping("/{randomId}")
    public String saveNote(Model model, @PathVariable String randomId) {
        log.info("start saveNote");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        NoteDTO noteDTO = noteService.findByRandomId(randomId);
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            log.info(userDetails.getUsername());
            log.info(ObjectMapperUtil.writeValueAsString(noteDTO));
            model.addAttribute("noteDTO", noteDTO);
            return "note/note_save";
        }else {
            return "user/user_login";
        }
    }
    @GetMapping("/my-note")
    public String myNote(Model model) {
        log.info("start myNote");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        List<NoteDTO> noteDTOS = noteService.getByUserName(userDetails.getUsername());
        log.info(ObjectMapperUtil.writeValueAsString(noteDTOS));
        model.addAttribute("noteDTOS", noteDTOS);

        log.info("end myNote");

        return "note/note_list";
    }
}