package com.example.books4trade.services;

import com.example.books4trade.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
/*
@Service("mailService")
public class EmailService {

        @Autowired
        public JavaMailSender emailSender;

        @Value("auto@swapabook.xyz")
        private String from;

        public void prepareAndSend(User user, String subject, String body) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(from);
            msg.setTo(user.getEmail());
            msg.setSubject(subject);
            msg.setText(body);
            try{
                this.emailSender.send(msg);
            }
            catch (MailException ex) {
                // simply log it and go on...
                System.err.println(ex.getMessage());
            }
        }

        public void accountRegistration(User user) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(from);
            msg.setTo(user.getEmail());
            msg.setSubject("Account created");
            msg.setText("Thank you for registering to Swap-a-Book" + " " + user.getFirstName() + ".");

            try{
                this.emailSender.send(msg);
            }
            catch (MailException ex) {
                // simply log it and go on...
                System.err.println(ex.getMessage());
            }
        }

        public void bannedUser(User user) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(from);
            msg.setTo(user.getEmail());
            msg.setSubject("Your Account Is Banned.");
            msg.setText("Please contact swapabook@xyz.com.");
            try{
                this.emailSender.send(msg);
            }
            catch (MailException ex) {
                // simply log it and go on...
                System.err.println(ex.getMessage());
            }
        }

        public void prepareAndSend(String subject, String body) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(from);
            msg.setTo("admin@codeup.com");
            msg.setSubject(subject);
            msg.setText(body);
            try{
                this.emailSender.send(msg);
            }
            catch (MailException ex) {
                // simply log it and go on...
                System.err.println(ex.getMessage());
            }
        }
    }
*/
