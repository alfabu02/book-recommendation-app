package uz.alfabu.bookrecommendationapp.exception;

import org.springframework.http.HttpStatus;

public class MyConflictException extends MyException {
    public MyConflictException(String message, String reason) {
        super(message, HttpStatus.CONFLICT, reason);
    }

    public MyConflictException(String message) {
        this(message, null);
    }
}
