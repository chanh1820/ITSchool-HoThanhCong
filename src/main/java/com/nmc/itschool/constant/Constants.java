package com.nmc.itschool.constant;

import org.springframework.http.HttpStatus;

public class Constants {

    public static final String FORMAT_TIMESTAMP = "YYYY-MM-DD HH:MM:SS.sss";
    public static final String FORMAT_YYYYMMDD = "yyyy-MM-dd";
    public static final String FORMAT_MMDDYYYY_HHMMSS = "MM/dd/yyyy hh:mm:ss aa";
    public static final String FORMAT_DATE_TIME = "yyyy/MM/dd hh:mm:ss";
    public static final String FORMAT_HOUR_24H = "HH:mm:ss";
    public static final String FORMAT_HOUR_12H = "hh:mm:ss aa";
    public static final String FORMAT_DATE_MMDDYYYY = "MM/dd/yyyy";
    public static final String DATE_TIME_FORMAT = "yyyy/MM/dd hh:mm:ss";

    public static final Integer STATUS_SUCCESS = HttpStatus.OK.value();
    public static final Integer STATUS_BAD_REQUEST = HttpStatus.BAD_REQUEST.value();
    public static final Integer STATUS_FAIL = HttpStatus.INTERNAL_SERVER_ERROR.value();

}
