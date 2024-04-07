package com.securetrustbank.onlinebank.exceptions;

public class GreaterThanCurrentBalanceException extends Exception {
    public GreaterThanCurrentBalanceException(String message){
        super(message);
    }
}
