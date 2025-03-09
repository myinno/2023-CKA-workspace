package com.example.mailsynology.sample;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class SynologyMailSender {
    public static void main(String[] args) {
        // Synology 메일 서버 설정
        final String host = "synology-nas-ip 또는 도메인";
        final int port = 587;  // 일반적으로 587(TLS) 또는 465(SSL)
        final String username = "your-email@your-domain.com";
        final String password = "your-email-password";

        // 수신자 설정
        String to = "recipient@example.com";
        String from = "your-email@your-domain.com";
        String subject = "JavaMail 테스트";
        String body = "Synology Mail 서버를 통한 JavaMail 테스트 메시지입니다.";

        // SMTP 프로퍼티 설정
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");  // TLS 사용 시
        // props.put("mail.smtp.ssl.enable", "true");    // SSL 사용 시
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // 세션 생성
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // 메시지 구성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // 메일 전송
            Transport.send(message);
            System.out.println("이메일 전송 성공!");

        } catch (Exception e) {
            throw new RuntimeException("메일 전송 실패: " + e.getMessage());
        }
    }
}