package uz.alfabu.bookrecommendationapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
public class LoginDto {
    @Schema(defaultValue = "someone@exampel.com")
    @Pattern(regexp = "[a-z0-9A-Z.]+@[a-z]+\\.[a-z]+", message = "email example: alfabu02@gmail.com")
    @NotBlank(message = "email is required")
    private String email;

    @Length(min = 4, max = 30, message = "password length must be between {min} and {max}")
    @NotBlank(message = "password is required")
    private String password;
}
