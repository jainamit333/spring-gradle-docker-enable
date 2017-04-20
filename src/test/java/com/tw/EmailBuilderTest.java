package com.tw;

import com.tw.domain.Email;
import com.tw.domain.EmailId;
import com.tw.domain.EmailRequest;
import com.tw.exception.EmailCreationException;
import com.tw.service.EmailBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by amitjain on 4/19/17.
 */
public class EmailBuilderTest {

    private EmailBuilder emailBuilder;

    @Before
    public void setup(){
        emailBuilder = new EmailBuilder();

    }


    @Test
    public void should_build_email() throws EmailCreationException {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.sender = new EmailId("ean");
        emailRequest.receivers = Arrays.asList(new EmailId("wer"));
        String template = "asd";
        Email email = emailBuilder.build(template, emailRequest);

        Assert.assertEquals(template, email.getBody());
    }

    @Test(expected = EmailCreationException.class)
    public void error_while_creating_email() throws EmailCreationException {

        EmailRequest emailRequest = new EmailRequest();
        String template = "";
        emailBuilder.build(template, emailRequest);
    }

    @Test
    public void print_email_request(){

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.sender = new EmailId("jain@gmail.com");
        emailRequest.receivers = Arrays.asList(new EmailId("jain@gmail.com"));
        emailRequest.bodyTemplateId = "dummy-template";
        System.out.println(emailRequest.toString());

    }
    @Test
    public void test_email_creation_with_subject() throws EmailCreationException {

       EmailRequest emailRequest = new EmailRequest();
       emailRequest.sender = new EmailId("ean");
       emailRequest.receivers = Arrays.asList(new EmailId("wer"));
       Email email =  emailBuilder.build("body","sub",emailRequest);
       Assert.assertNotNull(email);
       Assert.assertEquals("body",email.getBody());
       Assert.assertEquals("sub",email.getSubject());

    }


}
