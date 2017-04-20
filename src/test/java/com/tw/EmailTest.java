package com.tw;

import com.tw.domain.EmailId;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by amitjain on 4/18/17.
 */
public class EmailTest {

    @Test
    public void should_check_invalid_email_id() {
        Assert.assertFalse(EmailId.IS_VALID_EMAIL.test(new EmailId("ABCD")));
    }

    @Test
    public void should_check_valid_email_id() {
        Assert.assertTrue(EmailId.IS_VALID_EMAIL.test(new EmailId("ABCD@gmail.com")));
    }

}
