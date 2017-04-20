package com.tw.service;

import com.google.common.base.Joiner;
import com.tw.domain.Email;
import com.tw.exception.EmailSenderException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by amitjain on 4/18/17.
 */
@Service
public class EmailSender {


    public void send(Email email) throws EmailSenderException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("noreply.tradeaway@gmail.com",
                                "ThoughtWorks");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getSender().getEmailId()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(Joiner.on(",").join(email.getReceivers())));
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
