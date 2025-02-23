package uz.alfabu.bookrecommendationapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.alfabu.bookrecommendationapp.payload.*;
import uz.alfabu.bookrecommendationapp.service.contract.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<UserDto> signup(@Valid @RequestBody SignupDto signupDto) {
        return authService.signup(signupDto);
    }

    @PatchMapping("/verify-account")
    public ApiResponse<UserDto> verifyAccount(@Valid @RequestBody VerifyAccountDto verifyAccountDto) {
        return authService.verifyAccount(verifyAccountDto);
    }

    @PostMapping("/resend-verification-code")
    public ApiResponse<String> resendVerificationCode(@RequestParam String email) {
        return authService.resendVerificationCode(email);
    }

    @PostMapping("/login")
    public ApiResponse<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @GetMapping("/refresh-token")
    public ApiResponse<TokenDto> refreshToken(@RequestParam String refreshToken) {
        return authService.refreshToken(refreshToken);
    }
}