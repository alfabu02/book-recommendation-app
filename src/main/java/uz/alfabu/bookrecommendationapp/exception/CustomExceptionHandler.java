package uz.alfabu.bookrecommendationapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.alfabu.bookrecommendationapp.payload.ApiResponse;
import uz.alfabu.bookrecommendationapp.payload.ErrorDto;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({MyException.class})
    public ResponseEntity<ApiResponse<Object>> handleMyException(MyException e) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(false);

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        errorDto.setCode(e.getStatus().value());
        errorDto.setReason(e.getReason());
        apiResponse.setErrors(Collections.singletonList(errorDto));

        return ResponseEntity.status(e.getStatus()).body(apiResponse);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();

        List<ErrorDto> errors = e.getFieldErrors()
                .stream()
                .map(fe -> {
                    ErrorDto errorDto = new ErrorDto();
                    errorDto.setMessage(fe.getDefaultMessage());
                    errorDto.setCode(400);
                    return errorDto;
                }).toList();

        apiResponse.setErrors(errors);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

}
