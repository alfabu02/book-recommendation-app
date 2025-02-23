package uz.alfabu.bookrecommendationapp.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}
