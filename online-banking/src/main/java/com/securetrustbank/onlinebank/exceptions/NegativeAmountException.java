package com.securetrustbank.onlinebank.exceptions;

public class NegativeAmountException extends Exception {
    public NegativeAmountException(String message){
        super(message);
    }
}
