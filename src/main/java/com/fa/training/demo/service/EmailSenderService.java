package com.fa.training.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * This method is used to send an email to User
     */
    public void sendEmail(String sendTo, String subject, String content)
                        throws UnsupportedEncodingException, MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom("demomockproject@gmail.com", "FJW_Team3");
        helper.setTo(sendTo);
        helper.setSubject(subject);
        helper.setText(content, true);
        javaMailSender.send(mimeMessage);
    }
}
