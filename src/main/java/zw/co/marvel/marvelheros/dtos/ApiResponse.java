package zw.co.marvel.marvelheros.dtos;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ApiResponse<T> {
    private HttpStatus statusCode;
    private String message;
    private T body;
}

