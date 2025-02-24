package uz.alfabu.bookrecommendationapp.exception;

import org.springframework.http.HttpStatus;

public class MyUnauthorizedException extends MyException {
    public MyUnauthorizedException(String message, String reason) {
        super(message, HttpStatus.UNAUTHORIZED, reason);
    }
}
