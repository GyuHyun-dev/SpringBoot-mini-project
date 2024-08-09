package com.mysite.board.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendTemporaryPasswordEmail(String to, String temporaryPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rbgus2028@naver.com"); // 발신자 주소 설정
        message.setTo(to);
        message.setSubject("임시 비밀번호");
        message.setText("임시 비밀번호는 다음과 같습니다: " + temporaryPassword);
        mailSender.send(message);
    }
}