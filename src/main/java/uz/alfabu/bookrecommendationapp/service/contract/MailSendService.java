package uz.alfabu.bookrecommendationapp.service.contract;

public interface MailSendService {
    void send(String code, String to);
}
