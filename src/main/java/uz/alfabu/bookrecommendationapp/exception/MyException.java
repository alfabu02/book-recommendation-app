package uz.alfabu.bookrecommendationapp.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class MyException extends RuntimeException {
    protected final HttpStatus status;
    protected final String reason;

    protected MyException(String message, HttpStatus status, String reason) {
        super(message);
        this.status = status;
        this.reason = reason;
    }
}
