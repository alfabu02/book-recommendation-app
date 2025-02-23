package uz.alfabu.bookrecommendationapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.alfabu.bookrecommendationapp.service.contract.MailSendService;

@Service
@RequiredArgsConstructor
public class MailSendServiceImpl implements MailSendService {
    private final JavaMailSender mailSender;

    @Async
    @Override
    public void send(String code, String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Alfabu");
        message.setTo(to);
        message.setSubject("Confirmation code");
        message.setText("CODE: " + code);
        mailSender.send(message);
    }
}
