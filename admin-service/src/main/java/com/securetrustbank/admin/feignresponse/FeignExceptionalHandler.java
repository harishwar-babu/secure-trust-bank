package com.securetrustbank.admin.feignresponse;

import com.securetrustbank.admin.exceptions.DecodingException;
import com.securetrustbank.admin.exceptions.ServiceUnavailableException;
import com.securetrustbank.admin.exceptions.UserNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
public class FeignExceptionalHandler implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        int statusCode = response.status();
        String exceptionMessage = getErrorMessage(response);
        if(statusCode==404){
            return new UserNotFoundException(exceptionMessage);
        }
        return new ServiceUnavailableException("service is down or unavailable");
    }
    private String getErrorMessage(Response response){
        String message="";
        try {
            message=new String(response.body().asInputStream().readAllBytes());
        }
        catch (IOException exception){
            throw new DecodingException(exception.getMessage());
        }
        return message;
    }
}
