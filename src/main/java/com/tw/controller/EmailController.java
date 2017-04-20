package com.tw.controller;

import com.tw.domain.EmailRequest;
import com.tw.domain.EmailResponse;
import com.tw.exception.EmailFailureException;
import com.tw.exception.ValidationException;
import com.tw.service.EmailService;
import com.tw.validator.EmailRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by amitjain on 4/18/17.
 */

@RestController
public class EmailController {

    @Autowired
    private EmailRequestValidator emailRequestValidator;
    @Autowired
    private EmailService emailService;


    public EmailController(EmailRequestValidator emailRequestValidator,
                           EmailService emailService) {
        this.emailRequestValidator = emailRequestValidator;
        this.emailService = emailService;
    }

    @RequestMapping(method = RequestMethod.POST,path = "/emails")
    public EmailResponse sendEmail(@RequestBody EmailRequest emailRequest) {

        EmailResponse emailResponse = new EmailResponse();
        try{
            emailRequestValidator.validate(emailRequest);
            emailService.buildAndSendEmail(emailRequest);
        }catch (ValidationException ve){
            emailResponse.setStatusCode(HttpStatus.BAD_REQUEST);
            emailResponse.setMessage("email request is null");
        }catch (EmailFailureException e){
            emailResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            emailResponse.setMessage("email request is null");
        }
        return emailResponse;

    }
}
