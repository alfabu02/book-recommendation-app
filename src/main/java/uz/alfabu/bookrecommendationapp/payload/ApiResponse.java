package uz.alfabu.bookrecommendationapp.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ApiResponse<T> {
    private T data;
    private String message;
    private Integer status;
    private boolean success;
    // if success is false
    private List<ErrorDto> errors;

    public static <S> ApiResponse<S> success(S data) {
        ApiResponse<S> apiResponse = new ApiResponse<>();
        apiResponse.setData(data);
        apiResponse.setMessage("success");
        apiResponse.setSuccess(true);
        return apiResponse;
    }
}
