package uz.alfabu.bookrecommendationapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.alfabu.bookrecommendationapp.entity.Role;
import uz.alfabu.bookrecommendationapp.entity.User;
import uz.alfabu.bookrecommendationapp.entity.VerificationCode;
import uz.alfabu.bookrecommendationapp.exception.MyBadRequestException;
import uz.alfabu.bookrecommendationapp.exception.MyConflictException;
import uz.alfabu.bookrecommendationapp.exception.MyUnauthorizedException;
import uz.alfabu.bookrecommendationapp.mapper.UserMapper;
import uz.alfabu.bookrecommendationapp.payload.*;
import uz.alfabu.bookrecommendationapp.repository.RoleRepository;
import uz.alfabu.bookrecommendationapp.repository.UserRepository;
import uz.alfabu.bookrecommendationapp.repository.VerificationCodeRepository;
import uz.alfabu.bookrecommendationapp.security.JwtProvider;
import uz.alfabu.bookrecommendationapp.service.contract.AuthService;
import uz.alfabu.bookrecommendationapp.service.contract.MailSendService;
import uz.alfabu.bookrecommendationapp.service.contract.VerificationCodeService;
import uz.alfabu.bookrecommendationapp.utils.ThrowUtil;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final String EMAIL_WORD = "email";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final MailSendService mailSendService;
    private final UserMapper userMapper;
    private final VerificationCodeService verificationCodeService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public ApiResponse<UserDto> signup(SignupDto signupDto) {
        ThrowUtil.throwIfNot(Objects.equals(signupDto.getPassword(), signupDto.getPrePassword()), new MyBadRequestException("Passwords are not equal"));

        User user;
        String encodedPassword = passwordEncoder.encode(signupDto.getPassword());
        Optional<User> optionalUser = userRepository.findByEmail(signupDto.getEmail());

        if (optionalUser.isPresent()) {
            // not confirmed user
            user = optionalUser.get();
            ThrowUtil.throwIf(user.isEnabled(), new MyConflictException("User is already registered with this email!", EMAIL_WORD));

            user.setName(signupDto.getName());
            user.setPassword(encodedPassword);
        } else {
            // first new user
            user = userMapper.toEntity(signupDto);
            user.setPassword(encodedPassword);
            user.setEnabled(false);
            Role roleUser = roleRepository.findByName("USER_ROLE").orElseThrow();
            user.setRoles(Set.of(roleUser));
        }

        userRepository.save(user);

        // check sent code before
        Optional<VerificationCode> optionalVerificationCode = verificationCodeRepository.findByEmail(user.getEmail());
        if (optionalVerificationCode.isPresent()) {
            VerificationCode verificationCode = optionalVerificationCode.get();
            if (verificationCode.getExpiresAt().isAfter(LocalDateTime.now())) {
                throw new MyConflictException("Code is sent", "code");
            }

            verificationCodeRepository.delete(verificationCode);
        }

        VerificationCode verificationCode = verificationCodeService.saveVerificationCode(signupDto.getEmail());

        // send code
        mailSendService.send(verificationCode.getCode(), verificationCode.getEmail());

        UserDto dto = userMapper.toDto(user);
        return ApiResponse.success(dto);
    }

    @Override
    public ApiResponse<UserDto> verifyAccount(VerifyAccountDto verifyAccountDto) {
        User user = (User) userDetailsService.loadUserByUsername(verifyAccountDto.getEmail());
        ThrowUtil.throwIf(user.isEnabled(), new MyConflictException("User is already registered with this email!", EMAIL_WORD));

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(user.getEmail()).orElseThrow(() -> new MyBadRequestException("Register first", EMAIL_WORD));

        if (verificationCode.isExpired()) {
            verificationCodeRepository.delete(verificationCode);
            throw new MyBadRequestException("Code is expired! resend code", "expires");
        }

        ThrowUtil.throwIfNot(
                verificationCode.equalsCode(verifyAccountDto.getCode()),
                new MyBadRequestException("Code is wrong!", "code")
        );

        user.setEnabled(true);
        userRepository.save(user);

        return ApiResponse.success(userMapper.toDto(user));
    }

    @Override
    public ApiResponse<String> resendVerificationCode(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        ThrowUtil.throwIf(userDetails.isEnabled(), new MyBadRequestException("Not found!", EMAIL_WORD));

        Optional<VerificationCode> optionalVerificationCode = verificationCodeRepository.findByEmail(email);

        if (optionalVerificationCode.isPresent()) {
            VerificationCode verificationCode = optionalVerificationCode.get();

            ThrowUtil.throwIfNot(
                    verificationCode.isExpired(),
                    new MyBadRequestException("Code is sent! Try again after a minute!", "code")
            );

            verificationCodeRepository.delete(verificationCode);
        }

        VerificationCode newVerificationCode = verificationCodeService.saveVerificationCode(email);

        // send code
        mailSendService.send(newVerificationCode.getCode(), newVerificationCode.getEmail());

        return ApiResponse.success("Code is sent!");
    }

    @Override
    public ApiResponse<TokenDto> login(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        String email = loginDto.getEmail();

        TokenDto tokenDto = getTokenDto(email);
        return ApiResponse.success(tokenDto);
    }

    private TokenDto getTokenDto(String email) {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccessToken(jwtProvider.accessToken(email));
        tokenDto.setRefreshToken(jwtProvider.refreshToken(email));
        return tokenDto;
    }

    @Override
    public ApiResponse<TokenDto> refreshToken(String refreshToken) {
        String email = jwtProvider.getSubjectFromRefreshToken(refreshToken);
        ThrowUtil.throwIf(Objects.isNull(email), new MyUnauthorizedException("Please login first", "refreshToken"));

        TokenDto tokenDto = getTokenDto(email);
        return ApiResponse.success(tokenDto);
    }
}