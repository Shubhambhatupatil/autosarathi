package Autosarthi.demo.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(
            JavaMailSender mailSender){

        this.mailSender = mailSender;
    }

    public void sendTrackingEmail(

            String customerEmail,

            String customerName,

            String trackingLink){

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(customerEmail);

        message.setSubject(
                "AutoSarthi Booking Tracking"
        );

        message.setText(

                "Hello " +

                        customerName +

                        "\n\n"

                        +

                        "Your booking request has been sent successfully.\n\n"

                        +

                        "Track your booking here:\n\n"

                        +

                        trackingLink +

                        "\n\n"

                        +

                        "Thank you for using AutoSarthi."
        );

        mailSender.send(message);
    }
}