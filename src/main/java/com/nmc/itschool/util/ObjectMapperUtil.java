package com.nmc.itschool.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public final class ObjectMapperUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new Jdk8Module());


    public static final String writeValueAsString(Object value) {
        String jsonString = null;
        if (null != value) {
            try {
                jsonString = OBJECT_MAPPER.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonString;
    }


    public static <T> T readValue(String content, Class<T> valueType) {
        T result = null;
        try {
            if (StringUtils.isNotBlank(content)) {
                result = OBJECT_MAPPER.readValue(content, valueType);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static <T> T readValue(String content, TypeReference<?> valueTypeRef) {
        T result = null;
        try {
            if (StringUtils.isNotBlank(content)) {
                result = (T) OBJECT_MAPPER.readValue(content, valueTypeRef);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static String readValueByFieldName(String content, String fieldName) {
        String result = null;
        try {
            if (StringUtils.isNotBlank(content) && content.contains(fieldName)) {
                JsonNode object = OBJECT_MAPPER.readTree(content);
                result = object.get(fieldName).asText();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static final String writeDateValueAsString(Object value, String formatDate,
                                                      String timeZone) {

        if (value == null) {
            return null;
        }

        try {
            SimpleDateFormat df = new SimpleDateFormat(formatDate);

            if (StringUtils.isNoneBlank(timeZone)) {
                TimeZone tz = TimeZone.getTimeZone(timeZone);
                df.setTimeZone(tz);
            }

            OBJECT_MAPPER.setDateFormat(df);
            return OBJECT_MAPPER.writeValueAsString(value);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}