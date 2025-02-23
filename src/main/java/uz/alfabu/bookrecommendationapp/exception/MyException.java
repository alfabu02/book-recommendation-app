package uz.alfabu.bookrecommendationapp.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public abstract class MyException extends RuntimeException {
    protected HttpStatus status;
    protected String reason;

    public MyException(String message, HttpStatus status, String reason) {
        super(message);
        this.status = status;
        this.reason = reason;
    }
}
