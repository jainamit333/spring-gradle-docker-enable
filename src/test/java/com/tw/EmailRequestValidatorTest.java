package com.tw;

import com.tw.domain.EmailId;
import com.tw.domain.EmailRequest;
import com.tw.exception.ValidationException;
import com.tw.validator.EmailRequestValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amitjain on 4/18/17.
 */
public class EmailRequestValidatorTest {

    private EmailRequestValidator emailRequestValidator;



    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void init() {
        emailRequestValidator = new EmailRequestValidator();
    }

    @After
    public void tearDown() {
        emailRequestValidator = null;
    }

    @Test
    public void email_request_should_not_be_null() throws ValidationException {
        expectedEx.expect(ValidationException.class);
        expectedEx.expectMessage("email request is null");
        emailRequestValidator.validate(null);
    }

    @Test
    public void email_request_template_id_must_not_be_null() throws ValidationException{
        EmailRequest emailRequest = new EmailRequest();
        expectedEx.expect(ValidationException.class);
        expectedEx.expectMessage("Template id must be blank");
        emailRequestValidator.validate(emailRequest);
    }

    @Test
    public void email_request_at_least_one_receiver() throws ValidationException{
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.bodyTemplateId = "template-1";
        expectedEx.expect(ValidationException.class);
        expectedEx.expectMessage("Should have at-least one receiver");
        emailRequestValidator.validate(emailRequest);
    }

    @Test
    public void email_request_invalid_receiver_email() throws ValidationException{

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.bodyTemplateId ="template-1";
        List<EmailId> receipents = new ArrayList<EmailId>();
        receipents.add(new EmailId("ABCD"));
        emailRequest.receivers = receipents;

        expectedEx.expect(ValidationException.class);
        expectedEx.expectMessage("Invalid receiver email id(s)");
        emailRequestValidator.validate(emailRequest);
    }

}
