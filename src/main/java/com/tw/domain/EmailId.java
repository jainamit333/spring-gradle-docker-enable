package com.tw.domain;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Created by amitjain on 4/18/17.
 */
public class EmailId {

    public static Predicate<EmailId> IS_VALID_EMAIL = (email) ->
            Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email.emailId).matches();

    private String emailId;

    public EmailId(){}

    public EmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }

    @Override
    public String toString() {
        return emailId;
    }
}
