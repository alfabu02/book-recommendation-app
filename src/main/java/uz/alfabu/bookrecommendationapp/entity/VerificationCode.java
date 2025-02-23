package uz.alfabu.bookrecommendationapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import uz.alfabu.bookrecommendationapp.entity.abstractentity.AbsDateTimeEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class VerificationCode extends AbsDateTimeEntity {
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public boolean equalsCode(String code) {
        return this.code.equals(code);
    }
}
