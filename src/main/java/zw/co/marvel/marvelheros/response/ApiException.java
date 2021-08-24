package zw.co.marvel.marvelheros.response;

import lombok.Builder;


public class ApiException extends    Exception{
    public ApiException(String message){
       super(message);
    }

}
