package com.tw;

import com.tw.domain.Email;
import com.tw.domain.EmailRequest;
import com.tw.exception.EmailCreationException;
import com.tw.exception.EmailFailureException;
import com.tw.exception.EmailSenderException;
import com.tw.exception.NoTemplateFoundException;
import com.tw.service.EmailBuilder;
import com.tw.service.EmailSender;
import com.tw.service.EmailService;
import com.tw.service.TemplateEvaluator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by amitjain on 4/18/17.
 */
public class EmailServiceTest {


    private EmailService emailService;
    @Mock
    private TemplateEvaluator templateEvaluator;
    @Mock
    private EmailBuilder emailBuilder;
    @Mock
    private EmailSender emailSender;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setup(){

        MockitoAnnotations.initMocks(this);
        emailService = new EmailService(templateEvaluator,emailBuilder,emailSender);
    }


    @Test
    public void build_and_send_email_success() throws
            NoTemplateFoundException,
            EmailFailureException,
            EmailSenderException,
            EmailCreationException {

        emailService.buildAndSendEmail(new EmailRequest());

        Mockito.verify(templateEvaluator,
                Mockito.times(2)).evaluate(Mockito.anyString());
        Mockito.verify(emailBuilder)
                .build(Mockito.any(String.class),Mockito.any(String.class)
                , Mockito.any(EmailRequest.class));
        Mockito.verify(emailSender).send(Mockito.any(Email.class));

    }

    @Test
    public void build_and_send_exception_from_template_evaluator() throws NoTemplateFoundException,
            EmailFailureException {

        Mockito.doThrow(new NoTemplateFoundException("Template not found"))
                .when(templateEvaluator).evaluate(Mockito.anyString());

        expectedEx.expect(EmailFailureException.class);
        expectedEx.expectMessage("Template not found");

        emailService.buildAndSendEmail(new EmailRequest());
    }

    @Test
    public void build_and_send_exception_from_email_builder() throws
            NoTemplateFoundException,
            EmailCreationException,
            EmailFailureException {

        Mockito.when(templateEvaluator.evaluate(Mockito.anyString()))
                .thenReturn(new String());
        Mockito.doThrow(new EmailCreationException("Missing Requirement"))
                .when(emailBuilder).build(Mockito.any(String.class),Mockito.any(String.class)
                , Mockito.any(EmailRequest.class));

        expectedEx.expect(EmailFailureException.class);
        expectedEx.expectMessage("Missing Requirement");

        emailService.buildAndSendEmail(new EmailRequest());
    }

    @Test
    public void build_and_send_exception_from_email_sender() throws
            NoTemplateFoundException,
            EmailCreationException,
            EmailSenderException,
            EmailFailureException {

        Mockito.when(templateEvaluator.evaluate(Mockito.anyString()))
                .thenReturn(new String());
        Mockito.when(emailBuilder.build(Mockito.any(String.class), Mockito.any(EmailRequest.class)))
                .thenReturn(new Email());
        Mockito.doThrow(new EmailSenderException("Not able to send email"))
                .when(emailSender).send(Mockito.any(Email.class));

        expectedEx.expect(EmailFailureException.class);
        expectedEx.expectMessage("Not able to send email");

        emailService.buildAndSendEmail(new EmailRequest());
    }


    @Test
    public void build_and_send_email_with_subject_from_template() throws
            EmailFailureException,
            NoTemplateFoundException,
            EmailCreationException,
            EmailSenderException {

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.bodyTemplateId = "test-body";
        emailRequest.subjectTemplateId = "test-subject";

        emailService.buildAndSendEmail(emailRequest);
        Mockito.verify(templateEvaluator).evaluate("test-body");
        Mockito.verify(templateEvaluator).evaluate("test-subject");
        Mockito.verify(emailBuilder).build(Mockito.any(String.class),Mockito.any(String.class)
                , Mockito.any(EmailRequest.class));
        Mockito.verify(emailSender).send(Mockito.any(Email.class));

    }



}
