package zw.co.marvel.marvelheros.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {
    private Integer statusCode;
    private String message;
    private T body;
}

