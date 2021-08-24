package zw.co.marvel.marvelheros.controllers.base;

import org.springframework.http.ResponseEntity;
import zw.co.marvel.marvelheros.dtos.ApiException;
import zw.co.marvel.marvelheros.dtos.ApiResponse;

@FunctionalInterface
public interface BaseWrapper<T>{
    ResponseEntity<ApiResponse<T>> execute() throws ApiException;
}
