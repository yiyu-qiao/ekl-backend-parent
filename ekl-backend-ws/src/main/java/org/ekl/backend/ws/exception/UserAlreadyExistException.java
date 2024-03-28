package org.ekl.backend.ws.exception;

public class UserAlreadyExistException extends Exception{

    public UserAlreadyExistException(String msg){
        super(msg);
    }
}
