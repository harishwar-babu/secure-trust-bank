package com.securetrustbank.user.exceptions;

public class GreaterThanCurrentBalanceException extends Exception {
    public GreaterThanCurrentBalanceException(String message){
        super(message);
    }
}
