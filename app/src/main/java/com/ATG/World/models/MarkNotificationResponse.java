package com.ATG.World.models;

/**
 * Created by User on 1/7/2018.
 */

public class MarkNotificationResponse {
    private String code;
    private String message;

    public MarkNotificationResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

}
