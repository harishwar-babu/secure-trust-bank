package com.securetrustbank.user.exceptions;

public class NegativeAmountException extends Exception {
    public NegativeAmountException(String message){
        super(message);
    }
}
