package com.securetrustbank.user.feignresponse;
import com.securetrustbank.user.exceptions.*;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
public class FeignExceptionalHandler implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        int statusCode = response.status();
        String exceptionMessage = getErrorMessage(response);
        if(statusCode==404 && (exceptionMessage.contains("account"))){
                return new AccountNumberNotFoundException(exceptionMessage);

        }
        if(statusCode==409){
            if(exceptionMessage.contains("zero")){
                return new NegativeAmountException(exceptionMessage);
            }
            if(exceptionMessage.contains("withdraw")){
                return new GreaterThanCurrentBalanceException(exceptionMessage);
            }
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
