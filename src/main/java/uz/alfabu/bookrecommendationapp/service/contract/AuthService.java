package uz.alfabu.bookrecommendationapp.service.contract;

import uz.alfabu.bookrecommendationapp.payload.*;

public interface AuthService {
    ApiResponse<UserDto> signup(SignupDto signupDto);

    ApiResponse<UserDto> verifyAccount(VerifyAccountDto verifyAccountDto);

    ApiResponse<String> resendVerificationCode(String email);

    ApiResponse<TokenDto> login(LoginDto loginDto);

    ApiResponse<TokenDto> refreshToken(String refreshToken);
}
