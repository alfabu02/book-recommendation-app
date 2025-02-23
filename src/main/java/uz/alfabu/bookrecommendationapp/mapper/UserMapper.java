package uz.alfabu.bookrecommendationapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import uz.alfabu.bookrecommendationapp.entity.User;
import uz.alfabu.bookrecommendationapp.payload.SignupDto;
import uz.alfabu.bookrecommendationapp.payload.UserDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(SignupDto signupDto);
}
