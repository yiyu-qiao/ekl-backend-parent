package org.ekl.backend.ws.exception;

public class UsernameOrPasswordInvalidException extends Exception{
    public UsernameOrPasswordInvalidException(String msg){
        super(msg);
    }
}
