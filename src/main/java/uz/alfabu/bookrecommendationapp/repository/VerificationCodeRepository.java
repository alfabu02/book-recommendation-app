package uz.alfabu.bookrecommendationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alfabu.bookrecommendationapp.entity.VerificationCode;

import java.time.LocalDateTime;
import java.util.Optional;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode,Long> {
    Optional<VerificationCode> findByEmail(String email);

    void deleteAllByExpiresAtBefore(LocalDateTime now);
}
