package uz.alfabu.bookrecommendationapp.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorDto {
    private String message;
    private Integer code;
    private String reason;
}
