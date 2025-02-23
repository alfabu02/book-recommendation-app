package uz.alfabu.bookrecommendationapp.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
    private String name;
    private String username;
    private String password;
    private boolean enabled;
}
