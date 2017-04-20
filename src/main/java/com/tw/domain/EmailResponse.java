package com.tw.domain;

import org.springframework.http.HttpStatus;

/**
 * Created by amitjain on 4/18/17.
 */
public class EmailResponse {

    private HttpStatus statusCode;
    private String message;

    public EmailResponse() {
        this.statusCode = HttpStatus.OK;
        this.message = "success";
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
