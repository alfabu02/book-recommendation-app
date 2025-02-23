package uz.alfabu.bookrecommendationapp.service.contract;

import uz.alfabu.bookrecommendationapp.entity.VerificationCode;

public interface VerificationCodeService {
    VerificationCode saveVerificationCode(String email);

    void clearExpiredCodes();
}
