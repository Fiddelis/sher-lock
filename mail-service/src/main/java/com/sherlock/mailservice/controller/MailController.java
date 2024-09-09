package com.sherlock.mailservice.controller;

import com.sherlock.mailservice.model.RegisterDTO;
import com.sherlock.mailservice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MailController {
    @Autowired
    MailService mailService;

    @RequestMapping(method = RequestMethod.POST,path = "/register")
    @ResponseBody
    public String register(@RequestBody RegisterDTO registerDTO) throws Exception {
        System.out.println(registerDTO);

        mailService.sendEmailWithQRCode(    registerDTO);
        return "Email Sent..!";
    }
}
