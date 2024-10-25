package com.sherlock.mailservice.controller;

import com.sherlock.mailservice.model.RegisterDTO;
import com.sherlock.mailservice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MailController {
    MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping(method = RequestMethod.POST,path = "/register")
    @ResponseBody
    public void register(@RequestBody RegisterDTO registerDTO) throws Exception {
        mailService.sendEmailWithQRCode(registerDTO);
    }
}
