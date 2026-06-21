package Autosarthi.demo.controller;

import Autosarthi.demo.service.EmailService;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/email")

@CrossOrigin("*")

public class EmailController {

    private final EmailService emailService;

    public EmailController(
            EmailService emailService){

        this.emailService =
                emailService;
    }

    @PostMapping("/send")

    public String sendMail(

            @RequestParam String email,

            @RequestParam String name,

            @RequestParam String link){

        emailService.sendTrackingEmail(

                email,

                name,

                link
        );

        return "Email Sent";
    }
}
