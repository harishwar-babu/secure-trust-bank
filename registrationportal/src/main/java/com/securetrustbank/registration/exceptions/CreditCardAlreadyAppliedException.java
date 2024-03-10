package com.securetrustbank.registration.exceptions;

public class CreditCardAlreadyAppliedException extends Exception {
    public CreditCardAlreadyAppliedException(String message){
        super(message);
    }
}
