package com.securetrustbank.transactions.exceptions;

public class DeserializationException extends RuntimeException {
    public DeserializationException(String message){
        super(message);
    }
}