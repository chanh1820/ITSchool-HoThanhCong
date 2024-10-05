package com.nmc.itschool.util;

import java.text.Normalizer;

public class StringUtil {
    public static String convertToSlug(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String withoutAccents = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        String slug = withoutAccents.toLowerCase().replaceAll("\\s+", "-");

        return slug;
    }
}
