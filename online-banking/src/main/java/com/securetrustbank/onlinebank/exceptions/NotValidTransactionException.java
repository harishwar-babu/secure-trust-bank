package com.securetrustbank.onlinebank.exceptions;

public class NotValidTransactionException extends Exception {
    public NotValidTransactionException(String message){
        super(message);
    }
}
