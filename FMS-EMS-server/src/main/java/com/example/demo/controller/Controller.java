package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by next on 2018/11/3.
 */
@RestController
public class Controller {
    @RequestMapping("/test")
    public String test(){
        return "test FMS EMS";
    }
}
