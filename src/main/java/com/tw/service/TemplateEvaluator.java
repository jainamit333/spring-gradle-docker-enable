package com.tw.service;

import com.tw.exception.NoTemplateFoundException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;


/**
 * Created by amitjain on 4/18/17.
 */
@Component
public class TemplateEvaluator{

    @Autowired
    @Qualifier("velocityEngine")
    private VelocityEngine velocityEngine;

    public TemplateEvaluator(){}

    public TemplateEvaluator(VelocityEngine velocityEngine){
        this.velocityEngine  = velocityEngine;
    }


    public String evaluate(String templateId) throws NoTemplateFoundException{

        try{

            return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                    "./src/main/resources/templates/"+templateId,
                    null);

        }catch (Exception e){
            e.printStackTrace();
            throw new NoTemplateFoundException(e.getMessage());
        }
    }

}
