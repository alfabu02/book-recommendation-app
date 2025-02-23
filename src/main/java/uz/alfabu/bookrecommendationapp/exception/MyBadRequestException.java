package uz.alfabu.bookrecommendationapp.exception;

import org.springframework.http.HttpStatus;

public class MyBadRequestException extends MyException {
    public MyBadRequestException(String message, String reason) {
        super(message, HttpStatus.BAD_REQUEST, reason);
    }

    public MyBadRequestException(String message) {
        this(message, null);
    }
}
