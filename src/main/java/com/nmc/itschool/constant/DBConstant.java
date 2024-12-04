package com.nmc.itschool.constant;

import com.nmc.itschool.dto.ContentDTO;

import java.util.ArrayList;
import java.util.List;

public class DBConstant {
    public static final String SUBJECT_COLLECTION_PARENT_CODE_KHAC = "A00";

    public static final List<ContentDTO> CONTENT_DTOS = new ArrayList<ContentDTO>(){{
        add(new ContentDTO(1L, "", "Toán Và Nghệ Thuật", "Toán học và nghệ thuật có mối quan hệ đa chiều", "/toan-va-nghe-thuat", "/toan_va_nghe_thuat.jpg"));
        add(new ContentDTO(1L, "", "STEM Toán Học", "STEM là phương pháp giáo dục tích hợp, liên môn hiệu quả", "/stem-toan-hoc", "/stem_toan_hoc.jpg"));
        add(new ContentDTO(1L, "", "Lịch Sử Toán", "Lịch sử toán học đưa vào chương trình học tập, làm phong phú trải nghiệm học tập và môn toán trở nên sống động", "/lich-su-toan", "/lich_su_toan.jpg"));
        add(new ContentDTO(1L, "", "Video", "Công nghệ số giúp học sinh hình dung và hiểu sâu về toán học thông qua việc sử dụng phần mềm và video", "/video", "/video.jpg"));
        add(new ContentDTO(1L, "", "Giải Toán Bằng Tiếng Anh", "Học toán bằng tiếng Anh giúp nắm vững kiến thức cơ bản và nâng cao, phát triển kỹ năng giải quyết vấn đề logic", "/giai-toan-bang-tieng-anh", "/giai_toan_bang_tieng_anh.jpg"));
    }};

    public static final String TYPE_TEST_ANSWER_CHOOSE = "A";
    public static final String TYPE_TEST_ANSWER_WRITE = "B";
    public static final String TYPE_COLLECTION_PARENT_LESSON = "LESSON";
    public static final String TYPE_COLLECTION_PARENT_NEWS = "NEWS";
}
