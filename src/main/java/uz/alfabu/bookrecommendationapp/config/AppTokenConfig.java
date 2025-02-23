package uz.alfabu.bookrecommendationapp.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "jwt.token")
public class AppTokenConfig {
    private Long accessExpiration;
    private Long refreshExpiration;
    private String accessSecretKey;
    private String refreshSecretKey;
}
