package com.fl.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController

public class UnSuccessController {


    @GetMapping("/unsuccess")
    public String unSuccess()
    {
        return "Token失效";
    }
}
