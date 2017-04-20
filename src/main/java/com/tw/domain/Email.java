package com.tw.domain;


import com.tw.exception.EmailCreationException;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by amitjain on 4/18/17.
 */

public class Email {
    
    private EmailId sender;

    private List<EmailId> receivers;

    private String subject;

    private String body;

    public static Email Create(EmailId sender, String subject, String body, List<EmailId> receivers) throws EmailCreationException {

        if(sender == null || receivers==null || receivers.isEmpty() || body == null || body.isEmpty()){

            throw new EmailCreationException("Error while creating Email Domain ");
        }

        Email email = new Email();
        email.sender = sender;
        email.receivers = receivers;
        email.body = body;
        email.subject = subject;

        return email;

    }

    public EmailId getSender() {
        return sender;
    }

    public List<EmailId> getReceivers() {
        return receivers;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
