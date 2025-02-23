package uz.alfabu.bookrecommendationapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uz.alfabu.bookrecommendationapp.config.AppTokenConfig;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final AppTokenConfig appTokenConfig;

    public String accessToken(String subject) {
        return generateToken(subject, appTokenConfig.getAccessSecretKey(), appTokenConfig.getAccessExpiration());
    }

    public String refreshToken(String subject) {
        return generateToken(subject, appTokenConfig.getRefreshSecretKey(), appTokenConfig.getRefreshExpiration());
    }

    public String getSubjectFromAccessToken(String token) {
        return getSubjectFromToken(token, appTokenConfig.getAccessSecretKey());
    }

    public String getSubjectFromRefreshToken(String token) {
        return getSubjectFromToken(token, appTokenConfig.getRefreshSecretKey());
    }

    private String generateToken(String subject, String key, long expiration) {
        return JWT
                .create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration * 1000))
                .sign(Algorithm.HMAC256(key));
    }

    private String getSubjectFromToken(String token, String key) {
        try {
            return JWT
                    .require(Algorithm.HMAC256(key))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            log.error("JWT verification failed");
            return null;
        }
    }
}