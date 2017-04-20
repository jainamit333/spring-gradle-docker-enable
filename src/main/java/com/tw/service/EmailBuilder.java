package com.tw.service;


import com.tw.domain.Email;
import com.tw.domain.EmailRequest;
import com.tw.exception.EmailCreationException;
import org.springframework.stereotype.Service;

/**
 * Created by amitjain on 4/18/17.
 */
@Service
public class EmailBuilder {

    public Email build(String template, EmailRequest emailRequest) throws EmailCreationException {

        return  Email.Create(emailRequest.sender, template, template, emailRequest.receivers);
    }

    public Email build(String bodyTemplate,String subjectTemplate, EmailRequest emailRequest) throws EmailCreationException {

        return  Email.Create(emailRequest.sender, subjectTemplate, bodyTemplate, emailRequest.receivers);
    }
}
