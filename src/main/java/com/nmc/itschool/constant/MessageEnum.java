package com.nmc.itschool.constant;

public enum MessageEnum {

    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Internal server error"),

    ERR_INVALID_INPUT("ERR10000", "Invalid input data"),

    ERR_USER_NOT_FOUND("ERR10001", "User not found!"),

    ERR_USER_IS_EXISTING("ERR10002", "User is existing!"),

    ERR_USERNAME_ALREADY_EXISTS("ERR10003", "Username is ready exists!"),

    ERR_EMAIL_ALREADY_EXISTS("ERR10004", "Email is ready exists!"),

    ERR_EMPLOYEE_NOT_FOUND("ERR10005", "Employee not found!"),

    ERR_ARGUMENTS_INVALID("ERR10006", "Arguments invalid"),

    ERR_INCORRECT_OLD_PASSWORD("ERR10007", "Incorrect old password"),

    ERR_NEW_PASSWORD_CANNOT_BE_THE_SAME_AS_THE_OLD_ONE("ERR10008", "New password cannot be the same as the old one"),

    ERR_PERMISSION_ACCESS_DENIED("ERR10009", "You are not authorized to perform this action"),

    ERR_UN_AUTHENTICATED("ERR10010", "Unauthenticated"),

    ERR_BAD_CREDENTIALS("ERR10011", "Bad credentials"),

    ERR_INVALID_TOKEN("ERR10012", "Invalid JWT token"),

    ERR_ROLE_NOT_FOUND("ERR10013", "Role not found!"),

    ERR_MISSING_INVALID_AUTHORIZATION_HEADER("ERR10014", "Missing or invalid Authorization header"),

    ERR_URI_MALFORMED_NOT_CONTAINS_API("ERR10015", "URI is malformed and not contain 'api'"),

    SUCCESS_CHANGE_PASSWORD_SUCCESSFULLY("SUCCESS10000", "Change password successfully");

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
