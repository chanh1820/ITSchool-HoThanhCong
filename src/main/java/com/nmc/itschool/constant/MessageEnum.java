package com.nmc.itschool.constant;

public enum MessageEnum {

    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Internal server error"),

    ERR_PREFIX_PARENT_NOT_MATCH("ERR1001", "Prefix parent not match"),
    ERR_PREFIX_CHILD_NOT_MATCH("ERR1001", "Prefix child not match"),
    ERR_LESSON_NOT_FOUND("ERR1002", "Lesson not found"),
    ERR_USER_NAME_EXISTING("ERR1003", "Username existing"),
    ERR_TEST_NOT_FOUND("ERR1004", "Test not found"),
    ERR_SCORE_NOT_FOUND("ERR1002", "Score not found");


    private String messageCode;

    private String message;

    private MessageEnum(String messageCode, String message) {
        this.messageCode = messageCode;
        this.message = message;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
