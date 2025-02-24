package uz.alfabu.bookrecommendationapp.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.alfabu.bookrecommendationapp.entity.VerificationCode;
import uz.alfabu.bookrecommendationapp.repository.VerificationCodeRepository;
import uz.alfabu.bookrecommendationapp.service.contract.VerificationCodeService;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {
    private static final Random RANDOM = new Random();
    private final VerificationCodeRepository verificationCodeRepository;

    private String generateVerificationCode() {
        char[] code = new char[4];

        for (int i = 0; i < 4; i++)
            code[i] = (char) (RANDOM.nextInt(10) + '0');

        return new String(code);
    }

    @Override
    public VerificationCode saveVerificationCode(String email) {
        // create confirmation code
        final String code = generateVerificationCode();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode(code);
        verificationCode.setEmail(email);
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(1);
        verificationCode.setExpiresAt(expiresAt);
        verificationCodeRepository.save(verificationCode);

        return verificationCode;
    }

    @Transactional
    @Override
    public void clearExpiredCodes() {
        verificationCodeRepository.deleteAllByExpiresAtBefore(LocalDateTime.now());
    }
}
