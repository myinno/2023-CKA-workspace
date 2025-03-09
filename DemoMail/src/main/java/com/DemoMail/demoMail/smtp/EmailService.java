package com.DemoMail.demoMail.smtp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to); // 수신자 이메일 주소
        message.setSubject(subject); // 이메일 제목
        message.setText(text); // 이메일 내용
        mailSender.send(message); // 이메일 전송
        return true;
    }
}