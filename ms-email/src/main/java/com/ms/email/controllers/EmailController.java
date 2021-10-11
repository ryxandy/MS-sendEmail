package com.ms.email.controllers;

import com.ms.email.dto.EmailDto;
import com.ms.email.models.EmailModel;
import com.ms.email.repositories.EmailRepository;
import com.ms.email.services.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api
@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    EmailRepository emailRepository;

    @PostMapping("/sending-email")
    @ApiOperation(value = "Send your email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

    @GetMapping("/sent-emails")
    @ApiOperation(value = "Get all emails stored")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<EmailModel> getEmails(){
        System.out.println("Retrieving all Emails, wait a sec... ");
        return emailRepository.findAll();
    }

    @GetMapping("/sent-to/{emailTo}")
    @ApiOperation(value = "Get all emails that were sent to one user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<EmailModel> getEmailsToOneUser(@PathVariable  String emailTo){
        System.out.println("Retrieving all Emails, wait a sec... ");
        return emailRepository.findByEmailTo(emailTo);
    }


}
