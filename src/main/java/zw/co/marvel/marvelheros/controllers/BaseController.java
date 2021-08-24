package zw.co.marvel.marvelheros.controllers;

import lombok.val;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import zw.co.marvel.marvelheros.response.ApiException;
import zw.co.marvel.marvelheros.response.ApiResponse;

import java.util.Optional;

@FunctionalInterface
interface BaseWrapper<T>{
   ResponseEntity<ApiResponse<T>>  execute() throws ApiException;
}


public class BaseController {

    public  <T> ResponseEntity<ApiResponse<T>> tryToExecute(BaseWrapper<T> callback){
        try {
           return  callback.execute();
        }
        catch (Exception ex) {
            String message = ex.getMessage();

            if (ex instanceof QueryTimeoutException)
                message = "timed out - please try again";

            if(ex instanceof  DataIntegrityViolationException) {

                message = "You have violated an integrity constraint";
            }
            if(ex instanceof HttpMessageNotWritableException || ex instanceof HttpMessageNotReadableException)
                message = "There's something wrong with your request! Try again";
           return ResponseEntity.badRequest().body(
                    ApiResponse.<T>builder().message(message).statusCode(HttpStatus.BAD_REQUEST.value()).build()
            );
        }

    }

    public  <T> ResponseEntity<ApiResponse<T>> invokeService(Optional<T> item, String success, String fail) {
        ResponseEntity<ApiResponse<T>> result;
        try {

            val response = item.map(subject -> ResponseEntity.ok(
                    ApiResponse.<T>builder().body(
                            subject
                    ).message(success).statusCode(HttpStatus.OK.value()).build()

            ));
            if (response.isPresent()) {
                result = response.get();
            } else
                throw new ApiException(fail);

        } catch (Exception ex) {
            result = ResponseEntity.badRequest().body(
                    ApiResponse.<T>builder().message(ex.getMessage()).statusCode(HttpStatus.BAD_REQUEST.value()).build()
            );
        }
        return result;
    }
}
