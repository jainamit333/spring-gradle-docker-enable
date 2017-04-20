package com.tw.service;

import com.tw.domain.Email;
import com.tw.domain.EmailRequest;
import com.tw.exception.EmailCreationException;
import com.tw.exception.EmailFailureException;
import com.tw.exception.EmailSenderException;
import com.tw.exception.NoTemplateFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amitjain on 4/18/17.
 */
@Service
public class EmailService {

    @Autowired
    private TemplateEvaluator templateEvaluator;

    @Autowired
    private  EmailBuilder emailBuilder;

    @Autowired
    private EmailSender emailSender;

    public EmailService(TemplateEvaluator templateEvaluator, EmailBuilder emailBuilder, EmailSender emailSender) {

        this.templateEvaluator = templateEvaluator;
        this.emailBuilder = emailBuilder;
        this.emailSender = emailSender;
    }

    public void buildAndSendEmail(EmailRequest emailRequest) throws EmailFailureException {

        try{

            String bodyTemplate = templateEvaluator.evaluate(emailRequest.bodyTemplateId);
            String subjectTemplate = templateEvaluator.evaluate(emailRequest.subjectTemplateId);
            Email email = emailBuilder.build(bodyTemplate, subjectTemplate,emailRequest);
            emailSender.send(email);

        }catch (NoTemplateFoundException | EmailCreationException | EmailSenderException e){

            throw new EmailFailureException(e.getMessage());
        }

    }
}
