package com.nmc.itschool.constant;

import com.nmc.itschool.dto.ContentDTO;

import java.util.ArrayList;
import java.util.List;

public class DBConstant {
    public static final String SUBJECT_COLLECTION_PARENT_CODE_KHAC = "A00";

    public static final List<ContentDTO> CONTENT_DTOS = new ArrayList<ContentDTO>(){{
        add(new ContentDTO(1L, "", "Toán Và Nghệ Thuật", "Những nội dung bổ ích về Toán học", "/toan-va-nghe-thuat", "/toan_va_nghe_thuat.png"));
        add(new ContentDTO(1L, "", "STEM Toán Học", "STEM với Toán", "/stem-toan-hoc", "/stem_toan_hoc.png"));
        add(new ContentDTO(1L, "", "Lịch Sử Toán", "Tìm hiểu về lịch sử toán học", "/lich-su-toan", "/lich_su_toan.png"));
        add(new ContentDTO(1L, "", "Video", "Những video bổ ích", "/video", "/video.png"));
    }};
}