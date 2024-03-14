package com.securetrustbank.registration.exceptions;

public class UserDetailsAlreadyExistsException extends Exception {
    public UserDetailsAlreadyExistsException(String message){
        super(message);
    }
}
