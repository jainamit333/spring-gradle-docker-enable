package com.tw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by amitjain on 4/18/17.
 */
@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "I am working.This is a simple spring boot without JPA and DataBase." +
                "User may need to update gradle as per need";
    }
}
