package com.tw.exception;

/**
 * Created by amitjain on 4/18/17.
 */
public class EmailFailureException extends Throwable{

    public EmailFailureException(String message) {
        super(message);
    }
}
