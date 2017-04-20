package com.tw;


import com.tw.exception.NoTemplateFoundException;
import com.tw.service.TemplateEvaluator;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by amitjain on 4/18/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TradeawayApplication.class,TemplateEvaluator.class, VelocityEngine.class},
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TemplateEvaluatorTest {

    @Autowired
    private TemplateEvaluator templateEvaluator;



    @Before
    public void setup(){
        templateEvaluator = new TemplateEvaluator(new VelocityEngine());
    }

    @Test
    public void find_template_from_resource_success() throws NoTemplateFoundException {

        String template = templateEvaluator.evaluate("test.vm");
        Assert.assertNotNull(template);
        Assert.assertEquals("Hi Test Template",template);



    }
}
