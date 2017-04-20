package com.tw;

import com.tw.domain.Email;
import com.tw.domain.EmailId;
import com.tw.exception.EmailCreationException;
import com.tw.exception.EmailSenderException;
import com.tw.service.EmailSender;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by amitjain on 4/19/17.
 */
public class EmailSenderTest {


    private EmailSender emailSender;


    @Before
    public void setup(){
        emailSender = new EmailSender();
    }

    @Test
    public void send_email() throws EmailCreationException, EmailSenderException {

        Email email = Email.Create(new EmailId("noreply.tradeaway@gmail.com"),
                "Subject","Body", Arrays.asList(new EmailId("jainamit333@gmail.com")));
        emailSender.send(email);

    }

    @Test
    public void send_email_multiple_recipient() throws EmailCreationException, EmailSenderException {

        Email email = Email.Create(new EmailId("noreply.tradeaway@gmail.com"),
                "Subject","Body", Arrays.asList(new EmailId("jainamit333@gmail.com"),
                        new EmailId("amitj@thoughtworks.com")));
        emailSender.send(email);

    }


}
