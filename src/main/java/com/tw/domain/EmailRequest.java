package com.tw.domain;

import java.util.List;

/**
 * Created by amitjain on 4/18/17.
 */

public class EmailRequest {

    public EmailId sender;

    public String bodyTemplateId;

    public List<EmailId> receivers;

    public String subjectTemplateId ;

    @Override
    public String toString() {
        return "EmailRequest{" +
                "sender=" + sender +
                ", bodyTemplateId='" + bodyTemplateId + '\'' +
                ", receivers=" + receivers +
                ", subjectTemplateId='" + subjectTemplateId + '\'' +
                '}';
    }
}
