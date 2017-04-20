package com.tw.validator;

import com.tw.domain.EmailId;
import com.tw.domain.EmailRequest;
import com.tw.exception.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.tw.domain.EmailId.IS_VALID_EMAIL;

/**
 * Created by amitjain on 4/18/17.
 */
@Service
public class EmailRequestValidator {

    public void validate(final EmailRequest emailRequest) throws ValidationException {

        if(emailRequest == null) {
            throw new ValidationException("email request is null");
        }

        if(StringUtils.isEmpty(emailRequest.bodyTemplateId)){
            throw new ValidationException("Template id must be blank");
        }

        if(emailRequest.receivers == null ||emailRequest.receivers.isEmpty()){
            throw new ValidationException("Should have at-least one receiver");
        }

        validateEmailIds(emailRequest.receivers);
    }

    private void validateEmailIds(final List<EmailId> emailIds) throws ValidationException {


        Optional<EmailId> invalidEmail = emailIds.stream().filter(email -> IS_VALID_EMAIL.negate()
                .test(email)).findFirst();
        if (invalidEmail.isPresent()){
            throw new ValidationException("Invalid receiver email id(s)");
        }
    }

}
