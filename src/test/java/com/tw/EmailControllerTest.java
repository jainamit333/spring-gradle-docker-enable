package com.tw;

import com.sun.deploy.net.HttpResponse;
import com.tw.controller.EmailController;
import com.tw.domain.EmailRequest;
import com.tw.domain.EmailResponse;
import com.tw.exception.EmailFailureException;
import com.tw.exception.ValidationException;
import com.tw.service.EmailService;
import com.tw.validator.EmailRequestValidator;
import org.hibernate.validator.constraints.Email;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by amitjain on 4/18/17.
 */
public class EmailControllerTest {



    private EmailController emailController ;

    @Mock
    private EmailRequestValidator emailRequestValidator;
    @Mock
    private EmailService emailService;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        emailController = new EmailController(emailRequestValidator,emailService);
    }


    @Test
    public void validate_email_request_error() throws ValidationException {

        doThrow(new ValidationException("email request is null")).when(emailRequestValidator)
                .validate(any(EmailRequest.class));
        EmailResponse emailResponse = emailController.sendEmail(null);
        Assert.assertNotNull(emailResponse);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, emailResponse.getStatusCode());
        Assert.assertEquals("email request is null",    emailResponse.getMessage());

    }

    @Test
    public void email_service_fail() throws ValidationException, EmailFailureException {

        doNothing().when(emailRequestValidator).validate(any(EmailRequest.class));
        doThrow(new EmailFailureException("email not send")).when(emailService)
                .buildAndSendEmail(any(EmailRequest.class));
        EmailResponse emailResponse = emailController.sendEmail(null);
        Assert.assertNotNull(emailResponse);

    }

    @Test
    public void email_request_should_return_success() throws ValidationException {

        doNothing().when(emailRequestValidator).validate(any(EmailRequest.class));
        EmailResponse emailResponse = emailController.sendEmail(null);
        Assert.assertNotNull(emailResponse);
        Assert.assertEquals(HttpStatus.OK, emailResponse.getStatusCode());
        Assert.assertEquals("success",    emailResponse.getMessage());

    }



}
