package uz.alfabu.bookrecommendationapp.exception;

import org.springframework.http.HttpStatus;

public class MyNotFoundException extends MyException {
    public MyNotFoundException(String message, String reason) {
        super(message, HttpStatus.NOT_FOUND, reason);
    }

    public MyNotFoundException(String message) {
        this(message, null);
    }
}
